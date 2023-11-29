package org.eclipse.store.quarkus.deployment;

/*-
 * #%L
 * Eclipse Store Quarkus 3 Extension - Deployment
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

import java.io.File;

import org.eclipse.store.quarkus.deployment.test.StorageManagerController;
import org.eclipse.store.storage.types.StorageManager;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;

public class ExtensionStorageManagerNotStartedTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() ->
                    ShrinkWrap.create(JavaArchive.class)
                            .addClasses(StorageManagerController.class, CleanupUtil.class)
                            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                            .add(new StringAsset("org.eclipse.store.autoStart=false"), "application.properties")
            );

    @Inject
    StorageManager storageManager;

    @AfterEach
    public void cleanup() {
        CleanupUtil.deleteDirectory(new File("storage"));
    }

    @Test
    public void testStorageManagerProducer() {
        Assertions.assertNotNull(this.storageManager);
        Assertions.assertNull(this.storageManager.root());
        Assertions.assertFalse(this.storageManager.isRunning());
    }
}
