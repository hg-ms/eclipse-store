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

import org.eclipse.store.storage.types.StorageManager;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
public class StorageManagerController
{

    @Inject
    StorageManager storageManager;

    @GET
    public String testStorageManager()
    {
        return "storageManagerRoot=" + (this.storageManager.root() == null);
    }
}
