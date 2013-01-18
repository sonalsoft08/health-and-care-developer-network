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

package uk.nhs.hcdn.common.parsers.json.jsonParseEventHandlers.constructors.objectConstructors.fieldExpectations;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.parsers.json.jsonParseEventHandlers.schemaViolationInvalidJsonExceptions.SchemaViolationInvalidJsonException;

public final class NonEmptyStringFieldExpectation extends NonNullFieldExpectation<String>
{
	@NotNull
	public static FieldExpectation<String> nonEmptyStringField(@NonNls @NotNull final String key)
	{
		return new NonEmptyStringFieldExpectation(key);
	}

	public NonEmptyStringFieldExpectation(@NotNull final String key)
	{
		super(key, String.class, null, null);
	}

	@Override
	public void putConstantStringValue(@NotNull final Object[] constructorArguments, @NotNull final String value) throws SchemaViolationInvalidJsonException
	{
		if (value.isEmpty())
		{
			throw new SchemaViolationInvalidJsonException("Empty string");
		}
		super.putConstantStringValue(constructorArguments, value);
	}
}
