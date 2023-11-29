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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ReflectionUtils
{

    private ReflectionUtils()
    {
    }

    public static List<Field> findFields(final Class<?> clazz, final Predicate<Field> predicate)
    {

        return findAllFields(clazz).stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static List<Field> findAllFields(final Class<?> clazz)
    {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .collect(Collectors.toList());
    }

}
