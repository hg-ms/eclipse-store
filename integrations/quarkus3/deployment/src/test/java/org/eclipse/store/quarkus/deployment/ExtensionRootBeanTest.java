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

import org.eclipse.store.quarkus.deployment.test.SomeInitializerForStorage;
import org.eclipse.store.quarkus.deployment.test.SomeRootWithStorage;
import org.eclipse.store.storage.types.StorageManager;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;

public class ExtensionRootBeanTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() ->
                    ShrinkWrap.create(JavaArchive.class)
                            .addClasses(SomeInitializerForStorage.class, SomeRootWithStorage.class, CleanupUtil.class)
                            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
            );

    @AfterEach
    public void cleanup() {
        CleanupUtil.deleteDirectory(new File("storage"));
    }

    @Inject
    StorageManager storageManager;

    // Required to trigger creation of Root and execution of RootCreator.
    @Inject
    SomeRootWithStorage root;

    @Test
    public void testRootBeanCreation() {
        Assertions.assertNotNull(this.storageManager);
        Assertions.assertTrue(this.storageManager.isRunning());
        Assertions.assertNotNull(this.storageManager.root());
        Assertions.assertInstanceOf(SomeRootWithStorage.class, this.storageManager.root());
        SomeRootWithStorage root = (SomeRootWithStorage) this.storageManager.root();
        Assertions.assertEquals("Initial value of Root", root.getData());
    }
}
