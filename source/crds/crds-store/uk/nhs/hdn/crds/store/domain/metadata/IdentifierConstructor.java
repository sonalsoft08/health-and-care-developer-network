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

package uk.nhs.hdn.crds.store.domain.metadata;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.serialisers.separatedValues.SeparatedValueSerialiser;
import uk.nhs.hdn.crds.store.domain.identifiers.Identifier;
import uk.nhs.hdn.crds.store.domain.identifiers.ProviderIdentifier;
import uk.nhs.hdn.crds.store.domain.identifiers.RepositoryEventIdentifier;
import uk.nhs.hdn.crds.store.domain.identifiers.RepositoryIdentifier;

import java.util.UUID;

import static java.util.Locale.UK;
import static uk.nhs.hdn.crds.store.domain.metadata.ProviderMetadataRecord.csvSerialiserForProviderMetadataRecords;
import static uk.nhs.hdn.crds.store.domain.metadata.ProviderMetadataRecord.tsvSerialiserForProviderMetadataRecords;
import static uk.nhs.hdn.crds.store.domain.metadata.RepositoryMetadataRecord.csvSerialiserForRepositoryMetadataRecords;
import static uk.nhs.hdn.crds.store.domain.metadata.RepositoryMetadataRecord.tsvSerialiserForRepositoryMetadataRecords;

public enum IdentifierConstructor
{
	Provider
	{
		@NotNull
		@Override
		public Identifier construct(@NotNull final UUID uuid)
		{
			return new ProviderIdentifier(uuid);
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser tsvSerialiser()
		{
			return tsvSerialiserForProviderMetadataRecords();
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser csvSerialiser(final boolean writeHeaderLine)
		{
			return csvSerialiserForProviderMetadataRecords(writeHeaderLine);
		}
	},
	Repository
	{
		@NotNull
		@Override
		public Identifier construct(@NotNull final UUID uuid)
		{
			return new RepositoryIdentifier(uuid);
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser tsvSerialiser()
		{
			return tsvSerialiserForRepositoryMetadataRecords();
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser csvSerialiser(final boolean writeHeaderLine)
		{
			return csvSerialiserForRepositoryMetadataRecords(writeHeaderLine);
		}
	},
	RepositoryEvent
	{
		@NotNull
		@Override
		public Identifier construct(@NotNull final UUID uuid)
		{
			return new RepositoryEventIdentifier(uuid);
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser tsvSerialiser()
		{
			throw new UnsupportedOperationException();
		}

		@NotNull
		@Override
		public SeparatedValueSerialiser csvSerialiser(final boolean writeHeaderLine)
		{
			throw new UnsupportedOperationException();
		}
	}
	;

	@NotNull
	public Identifier construct(@NotNull final String uuid)
	{
		return construct(UUID.fromString(uuid));
	}

	@NotNull
	public String restName()
	{
		return name().toLowerCase(UK);
	}

	@NotNull
	public abstract Identifier construct(@NotNull final UUID uuid);

	@NotNull
	public abstract SeparatedValueSerialiser tsvSerialiser();

	@NotNull
	public abstract SeparatedValueSerialiser csvSerialiser(final boolean writeHeaderLine);
}
