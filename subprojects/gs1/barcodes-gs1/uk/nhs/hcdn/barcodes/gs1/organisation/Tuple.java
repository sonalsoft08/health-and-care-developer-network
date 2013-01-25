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

package uk.nhs.hcdn.barcodes.gs1.organisation;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hcdn.barcodes.gs1.companyPrefixes.Gs1CompanyPrefix;
import uk.nhs.hcdn.common.reflection.toString.AbstractToString;
import uk.nhs.hcdn.common.serialisers.*;

import java.util.Set;

public final class Tuple extends AbstractToString implements MapSerialisable, Serialisable
{
	@SuppressWarnings("ConstantNamingConvention") @FieldTokenName @NonNls @NotNull public static final String gs1CompanyPrefixField = "gs1CompanyPrefix";
	@SuppressWarnings("ConstantNamingConvention") @FieldTokenName @NonNls @NotNull public static final String trustField = "trust";
	@SuppressWarnings("ConstantNamingConvention") @FieldTokenName @NonNls @NotNull public static final String organsationNameField = "organisationName";
	@SuppressWarnings("ConstantNamingConvention") @FieldTokenName @NonNls @NotNull public static final String additionalInformationField = "additionalInformation";

	@NotNull
	private final Gs1CompanyPrefix gs1CompanyPrefix;

	@NonNls
	@NotNull
	private final String trust;

	@NonNls
	@NotNull
	private final String organisationName;

	@NotNull
	private final AdditionalInformation additionalInformation;

	public Tuple(@NotNull final Gs1CompanyPrefix gs1CompanyPrefix, @NonNls @NotNull final String trust, @NonNls @NotNull final String organisationName, @NotNull final AdditionalInformation additionalInformation)
	{
		this.gs1CompanyPrefix = gs1CompanyPrefix;
		this.trust = trust;
		this.organisationName = organisationName;
		this.additionalInformation = additionalInformation;
	}

	@Override
	public void serialise(@NotNull final Serialiser serialiser) throws CouldNotSerialiseException
	{
		try
		{
			serialiseMap(serialiser);
		}
		catch (CouldNotSerialiseMapException e)
		{
			throw new CouldNotSerialiseException(this, e);
		}
	}

	@SuppressWarnings("FeatureEnvy")
	@Override
	public void serialiseMap(@NotNull final MapSerialiser mapSerialiser) throws CouldNotSerialiseMapException
	{
		try
		{
			mapSerialiser.writeProperty(gs1CompanyPrefixField, gs1CompanyPrefix);
			mapSerialiser.writeProperty(trustField, trust);
			mapSerialiser.writeProperty(organsationNameField, organisationName);
			mapSerialiser.writeProperty(additionalInformationField, additionalInformation);
		}
		catch (CouldNotWritePropertyException e)
		{
			throw new CouldNotSerialiseMapException(this, e);
		}
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}

		final Tuple tuple = (Tuple) obj;

		if (!gs1CompanyPrefix.equals(tuple.gs1CompanyPrefix))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return gs1CompanyPrefix.hashCode();
	}

	@NotNull
	public Gs1CompanyPrefix gs1CompanyPrefix()
	{
		return gs1CompanyPrefix;
	}

	@NonNls
	@NotNull
	public String trust()
	{
		return trust;
	}

	@NonNls
	@NotNull
	public String organisationName()
	{
		return organisationName;
	}

	@NotNull
	public Set<AdditionalInformationKey> addtionalInformationAvailableForKeys()
	{
		return additionalInformation.addtionalInformationAvailableForKeys();
	}

	@Nullable
	public Object additionalInformationFor(@NotNull @NonNls final AdditionalInformationKey additionalInformationKey)
	{
		return additionalInformation.additionalInformationFor(additionalInformationKey);
	}
}