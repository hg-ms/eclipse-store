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

import org.eclipse.store.integrations.quarkus.types.Storage;

@Storage
public class SomeRootWithStorage
{

    private String data;

    public String getData()
    {
        return this.data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
