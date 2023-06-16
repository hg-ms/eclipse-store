package org.eclipse.store.storage.exceptions;

/*-
 * #%L
 * Eclipse Store Storage
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

@SuppressWarnings("serial")
public class StorageExceptionStructureValidation extends StorageException
{
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public StorageExceptionStructureValidation()
	{
		super();
	}

	public StorageExceptionStructureValidation(final String message)
	{
		super(message);
	}

	public StorageExceptionStructureValidation(final Throwable cause)
	{
		super(cause);
	}

	public StorageExceptionStructureValidation(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public StorageExceptionStructureValidation(
		final String    message           ,
		final Throwable cause             ,
		final boolean   enableSuppression ,
		final boolean   writableStackTrace
	)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
