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

package uk.nhs.hdn.crds.registry.server.hazelcast.hazelcastDataReaders.identifierDataReaders;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.crds.registry.domain.identifiers.StuffIdentifier;

import java.util.UUID;

public final class StuffIdentifierHazelcastDataReader extends AbstractIdentifierHazelcastDataReader<StuffIdentifier>
{
	@NotNull
	public static final AbstractIdentifierHazelcastDataReader<StuffIdentifier> StuffIdentifierDataReaderInstance = new StuffIdentifierHazelcastDataReader();

	private StuffIdentifierHazelcastDataReader()
	{
	}

	@NotNull
	@Override
	protected StuffIdentifier newIdentifier(@NotNull final UUID uuid)
	{
		return new StuffIdentifier(uuid);
	}
}
