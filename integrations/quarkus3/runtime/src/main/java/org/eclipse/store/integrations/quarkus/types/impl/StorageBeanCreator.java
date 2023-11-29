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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.arc.BeanCreator;
import io.quarkus.arc.SyntheticCreationalContext;

public class StorageBeanCreator implements BeanCreator<Object>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageBeanCreator.class);

    @Override
    public Object create(final SyntheticCreationalContext<Object> creationalContext)
    {
        LOGGER.debug("Create Bean: Creating bean with info about @Storage annotated class");

        Map<String, Object> map = creationalContext.getParams();
        final StorageClassInfo storageClassInfo = new StorageClassInfo((Class<?>) map.get(BeanCreatorParameterNames.CLASS_NAME)
                , (String) map.get(BeanCreatorParameterNames.FIELDS));

        return new StorageBean(storageClassInfo);

    }

}
