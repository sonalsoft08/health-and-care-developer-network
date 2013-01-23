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

package uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.XmlSchemaViolationException;

public interface XmlParseEventHandler
{
	void startDocument();

	void endDocument() throws XmlSchemaViolationException;

	void startElement(@NotNull final String name) throws XmlSchemaViolationException;

	void endElement(@NotNull final String name) throws XmlSchemaViolationException;

	void attribute(@NotNull final String key, @NotNull final String value) throws XmlSchemaViolationException;

	// Text can occur more than once for an element
	void text(@NotNull final String text) throws XmlSchemaViolationException;
}
