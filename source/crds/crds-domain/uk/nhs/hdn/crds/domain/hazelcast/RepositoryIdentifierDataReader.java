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

package uk.nhs.hdn.crds.domain.hazelcast;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.crds.domain.RepositoryIdentifier;

import java.util.UUID;

public final class RepositoryIdentifierDataReader extends AbstractIdentifierDataReader<RepositoryIdentifier>
{
	@NotNull
	public static final AbstractIdentifierDataReader<RepositoryIdentifier> RepositoryIdentifierDataReaderInstance = new RepositoryIdentifierDataReader();

	private RepositoryIdentifierDataReader()
	{
	}

	@NotNull
	@Override
	protected RepositoryIdentifier newIdentifier(@NotNull final UUID uuid)
	{
		return new RepositoryIdentifier(uuid);
	}
}
