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

package uk.nhs.hcdn.dts.domain.schema.xmlConstructors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.TextXmlConstructor;
import uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.XmlConstructor;
import uk.nhs.hcdn.common.xml.XmlSchemaViolationException;
import uk.nhs.hcdn.dts.domain.AddressType;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static uk.nhs.hcdn.dts.domain.AddressType.addressType;

public final class AddressTypeTextXmlConstructor extends TextXmlConstructor<AddressType>
{
	@NotNull
	public static final XmlConstructor<?, ?> AddressTypeTextXmlConstructorInstance = new AddressTypeTextXmlConstructor();

	private AddressTypeTextXmlConstructor()
	{
		super(null);
	}

	@NotNull
	@Override
	protected AddressType convert(@NotNull final String text) throws XmlSchemaViolationException
	{
		@Nullable final AddressType result = addressType(text);
		if (result == null)
		{
			throw new XmlSchemaViolationException(format(ENGLISH, "text %1$s is not a valid AddressType", text));
		}
		return result;
	}

	@NotNull
	@Override
	public Class<AddressType> type()
	{
		return AddressType.class;
	}
}
