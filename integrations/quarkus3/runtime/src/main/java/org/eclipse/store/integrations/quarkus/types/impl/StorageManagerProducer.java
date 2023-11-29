package org.eclipse.store.integrations.quarkus.types.impl;

/*-
 * #%L
 * Eclipse Store Quarkus 3 Extension - Runtime
 * %%
 * Copyright (C) 2023 MicroStream Software
 * %%
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * #L%
 */

import java.util.Map;

import org.eclipse.microprofile.config.Config;
import org.eclipse.serializer.reflect.ClassLoaderProvider;
import org.eclipse.store.integrations.quarkus.types.config.EmbeddedStorageFoundationCustomizer;
import org.eclipse.store.integrations.quarkus.types.config.StorageManagerInitializer;
import org.eclipse.store.storage.embedded.configuration.types.EmbeddedStorageConfigurationBuilder;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageFoundation;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.eclipse.store.storage.types.StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class StorageManagerProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageManagerProducer.class);

    @Inject
    Config config;

    @Inject
    StorageBean storageBean;


    @Inject
    Instance<EmbeddedStorageFoundationCustomizer> customizers;

    @Inject
    Instance<StorageManagerInitializer> initializers;

    @Produces
    @ApplicationScoped
    public StorageManager getStorageManager() {

        final Map<String, String> properties = ConfigurationCoreProperties.getProperties(this.config);
        LOGGER.info(
                "Loading default StorageManager from MicroProfile Config properties. The keys: "
                        + properties.keySet()
        );

        final EmbeddedStorageConfigurationBuilder builder = EmbeddedStorageConfigurationBuilder.New();
        for (final Map.Entry<String, String> entry : properties.entrySet()) {
            builder.set(entry.getKey(), entry.getValue());
        }
        final EmbeddedStorageFoundation<?> foundation = builder.createEmbeddedStorageFoundation();
        foundation.setDataBaseName("Generic");

        LOGGER.debug("Executing EmbeddedStorageFoundationCustomizer beans");

        this.customizers.stream()
                .forEach(customizer -> customizer.customize(foundation));

        // Required when using Quarkus
        foundation.onConnectionFoundation(cf -> cf.setClassLoaderProvider(ClassLoaderProvider.New(
                Thread.currentThread()
                        .getContextClassLoader())));


        final EmbeddedStorageManager storageManager = foundation
                .createEmbeddedStorageManager();

        if (this.isAutoStart(properties)) {
            LOGGER.debug("Start StorageManager");
            storageManager.start();
        }

        if (!this.storageBean.isDefined()) {
            LOGGER.debug("Executing StorageManagerInitializer beans");
            // Only execute at this point when no storage root bean has defined with @Storage
            // Initializers are called from StorageBeanCreator.create if user has defined @Storage.
            this.initializers.stream()
                    .forEach(initializer -> initializer.initialize(storageManager));
        }

        return storageManager;
    }


    private boolean isAutoStart(final Map<String, String> properties) {
        return Boolean.parseBoolean(properties.getOrDefault("autoStart", "true"));

    }

    public void dispose(@Disposes final StorageManager manager) {
        LOGGER.info("Closing the default StorageManager");
        manager.close();
    }
}
