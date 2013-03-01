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

package uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.constructors.objectConstructors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.schemaViolationInvalidJsonExceptions.SchemaViolationInvalidJsonException;

import java.util.HashMap;
import java.util.Map;

public final class StringGenericObjectConstructor extends AbstractGenericObjectConstructor<String>
{
	@NotNull
	public static final ObjectConstructor<?> StringGenericObjectConstructorInstance = new StringGenericObjectConstructor();

	private StringGenericObjectConstructor()
	{
	}

	@NotNull
	@Override
	protected String convertKey(@NotNull final String key) throws SchemaViolationInvalidJsonException
	{
		return key;
	}

	@NotNull
	@Override
	public Map<String, Object> newCollector()
	{
		return new HashMap<>(100);
	}

	@Nullable
	@Override
	public Object collect(@NotNull final Map<String, Object> collector)
	{
		return collector;
	}
}
