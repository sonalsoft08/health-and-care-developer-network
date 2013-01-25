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
import uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.TextXmlConstructor;
import uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.XmlConstructor;
import uk.nhs.hcdn.common.xml.XmlSchemaViolationException;
import uk.nhs.hcdn.dts.domain.statusRecords.StatusCode;

import static uk.nhs.hcdn.dts.domain.statusRecords.StatusCode.statusCode;

public final class StatusCodeTextXmlConstructor extends TextXmlConstructor<StatusCode>
{
	@NotNull
	public static final XmlConstructor<?, ?> StatusCodeTextXmlConstructorInstance = new StatusCodeTextXmlConstructor();

	private StatusCodeTextXmlConstructor()
	{
		super(null);
	}

	@NotNull
	@Override
	protected StatusCode convert(@NotNull final String text) throws XmlSchemaViolationException
	{
		try
		{
			return statusCode(text);
		}
		catch (RuntimeException e)
		{
			throw new XmlSchemaViolationException(e);
		}
	}

	@NotNull
	@Override
	public Class<StatusCode> type()
	{
		return StatusCode.class;
	}
}