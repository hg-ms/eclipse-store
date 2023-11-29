package org.eclipse.store.integrations.quarkus.deployment;

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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.store.integrations.quarkus.types.impl.StorageClassInfo;

import io.quarkus.builder.item.SimpleBuildItem;

/**
 *  {@link io.quarkus.builder.item.BuildItem} that is derived from {@link io.quarkus.arc.deployment.BeanArchiveIndexBuildItem} that keep tracks
 *  of the class annotated with {@link org.eclipse.store.integrations.quarkus.types.Storage}.
 */
public final class StorageBeanBuildItem extends SimpleBuildItem
{

    private final StorageClassInfo rootClassInfo;

    public StorageBeanBuildItem(final StorageClassInfo rootClassInfo)
    {
        this.rootClassInfo = rootClassInfo;
    }

    /**
     * Return the class where {@link org.eclipse.store.storage.types.Storage} is defined or empty optional if
     * not defined by user.
     * @return Class with @Storage or empty.
     */
    public Optional<Class<?>> getRootClass()
    {
        return Optional.ofNullable(this.rootClassInfo).map(StorageClassInfo::getClassReference);
    }


    /**
     * Returns the names of the fields that have {@link jakarta.inject.Inject} within class annotated with
     * {@link org.eclipse.store.integrations.quarkus.types.Storage}. Empty list when no annotated placed anywhere.
     * @return names of the fields to Inject or empty list if nothing to inject manually.
     */
    public List<String> getFieldsToInject()
    {
        if (this.rootClassInfo == null) {
            return Collections.emptyList();
        } else {
            return this.rootClassInfo.getFieldsToInject();
        }
    }
}
