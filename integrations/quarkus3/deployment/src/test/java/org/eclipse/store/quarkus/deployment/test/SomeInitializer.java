package org.eclipse.store.quarkus.deployment.test;

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

import org.eclipse.store.integrations.quarkus.types.config.StorageManagerInitializer;
import org.eclipse.store.storage.types.StorageManager;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SomeInitializer implements StorageManagerInitializer
{
    @Override
    public void initialize(StorageManager storageManager)
    {
        Object rootObject = storageManager.root();
        if (rootObject == null) {
            SomeRoot root = new SomeRoot();
            root.setData("Initial value");
            storageManager.setRoot(root);
        } else {
            throw new IllegalStateException("StorageManager should not have already a Root object assigned");
        }
    }
}
