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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import static uk.nhs.hdn.crds.domain.RootMap.OptimumHashLoadFactor;

public final class ConvenientLinkedHashSet<E extends DataWriter> extends HashSet<E> implements DataWriter
{
	private ConvenientLinkedHashSet(final int size)
	{
		super(size, OptimumHashLoadFactor);
	}

	public ConvenientLinkedHashSet(@NotNull final E element0)
	{
		super(1, OptimumHashLoadFactor);
		add(element0);
	}

	public ConvenientLinkedHashSet(@NotNull final Collection<E> set, @NotNull final E appendElement)
	{
		super(set.size() + 1, OptimumHashLoadFactor);
		addAll(set);
		add(appendElement);
	}

	@Override
	public void writeData(@NotNull final DataOutput out) throws IOException
	{
		out.writeInt(size());
		for (final E e : this)
		{
			e.writeData(out);
		}
	}

	@NotNull
	public static <E extends DataWriter> ConvenientLinkedHashSet<E> readData(@NotNull final DataInput in, @NotNull final DataReader<E> elementReader) throws IOException
	{
		final int size = in.readInt();
		final ConvenientLinkedHashSet<E> set = new ConvenientLinkedHashSet<>(size);
		for (int index = 0; index < size; index++)
		{
			set.add(elementReader.readData(in));
		}
		return set;
	}
}