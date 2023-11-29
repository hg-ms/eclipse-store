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

/**
 * Names in the {@link java.util.Map} passed to {@link io.quarkus.arc.BeanCreator}.
 */
public interface BeanCreatorParameterNames
{
    String CLASS_NAME = "className";
    String FIELDS = "fields";
}
