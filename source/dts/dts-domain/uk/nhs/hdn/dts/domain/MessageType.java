/*
 * © Crown Copyright 2013
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.nhs.hdn.dts.domain;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hdn.common.naming.Description;
import uk.nhs.hdn.common.serialisers.CouldNotSerialiseValueException;
import uk.nhs.hdn.common.serialisers.CouldNotWriteValueException;
import uk.nhs.hdn.common.serialisers.ValueSerialisable;
import uk.nhs.hdn.common.serialisers.ValueSerialiser;

import java.util.HashMap;
import java.util.Map;

public enum MessageType implements Description, ValueSerialisable
{
	Data("Data will have a data file and a control file"),
	Report("Report will have a control file"),
	;

	@SuppressWarnings("UtilityClassWithoutPrivateConstructor")
	private static final class CompilerWorkaround
	{
		private static final Map<String, MessageType> Index = new HashMap<>(3);
	}

	@NonNls
	@NotNull
	private final String description;

	@SuppressWarnings("ThisEscapedInObjectConstruction")
	MessageType(@NonNls @NotNull final String description)
	{
		this.description = description;
		CompilerWorkaround.Index.put(name(), this);
	}

	@Override
	public void serialiseValue(@NotNull final ValueSerialiser valueSerialiser) throws CouldNotSerialiseValueException
	{
		try
		{
			valueSerialiser.writeValue(name());
		}
		catch (CouldNotWriteValueException e)
		{
			throw new CouldNotSerialiseValueException(this, e);
		}
	}

	@NotNull
	@Override
	public String description()
	{
		return description;
	}

	@SuppressWarnings("MethodNamesDifferingOnlyByCase")
	@Nullable
	public static MessageType messageType(@NotNull final String value)
	{
		return CompilerWorkaround.Index.get(value);
	}
}
