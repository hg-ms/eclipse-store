package org.eclipse.store.storage.restadapter.types;

/*-
 * #%L
 * Eclipse Storage REST Adapter
 * %%
 * Copyright (C) 2023 Eclipse Foundation
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import org.eclipse.serializer.persistence.binary.types.Binary;
import org.eclipse.serializer.persistence.binary.types.BinaryPersistence;
import org.eclipse.serializer.persistence.types.PersistenceTypeDefinitionMember;

public class ValueReaderPrimitive implements ValueReader
{
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	private final PersistenceTypeDefinitionMember typeDefinition;

	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public ValueReaderPrimitive(final PersistenceTypeDefinitionMember member)
	{
		super();
		this.typeDefinition = member;
	}

	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////

	@Override
	public Object readValue(final Binary binary, final long offset)
	{
		return ViewerBinaryPrimitivesReader.readPrimitive(this.typeDefinition.type(), binary, offset);
	}

	@Override
	public long getBinarySize(final Binary binary, final long offset)
	{
		return BinaryPersistence.resolvePrimitiveFieldBinaryLength(this.typeDefinition.type());
	}
}
