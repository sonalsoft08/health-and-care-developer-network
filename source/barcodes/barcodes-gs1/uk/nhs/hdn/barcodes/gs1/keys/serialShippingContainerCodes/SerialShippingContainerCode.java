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

package uk.nhs.hdn.barcodes.gs1.keys.serialShippingContainerCodes;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.digits.Digits;
import uk.nhs.hdn.barcodes.gs1.keys.AbstractKeyFormatCheckDigitNumber;

public final class SerialShippingContainerCode extends AbstractKeyFormatCheckDigitNumber<SerialShippingContainerCodeFormat>
{
	public SerialShippingContainerCode(@NotNull final SerialShippingContainerCodeFormat serialShippingContainerCodeFormat, @NotNull final Digits digits)
	{
		super(serialShippingContainerCodeFormat, digits);
	}
}
