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

public class StorageBean
{
    private final StorageClassInfo info;

    public StorageBean(final StorageClassInfo info) {
        this.info = info;
    }

    public boolean isDefined() {
        return this.info.getClassReference() != null;
    }

    public StorageClassInfo getInfo()
    {
        return this.info;
    }
}
