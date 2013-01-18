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

package uk.nhs.hcdn.common.http.server.sun.restEndpoints.methodEndpoints;

import com.sun.net.httpserver.Headers;
import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.ResourceStateSnapshot;

import static uk.nhs.hcdn.common.http.ResponseCode.BadRequestResponseCode;

public final class BadRequestMethodEndpoint<R extends ResourceStateSnapshot> extends AbstractBodylessMethodEndpoint<R>
{
	@NotNull
	private static final MethodEndpoint<?> BadRequestMethodEndpointInstance = new BadRequestMethodEndpoint();

	@SuppressWarnings({"unchecked", "MethodNamesDifferingOnlyByCase"})
	@NotNull
	public static <R extends ResourceStateSnapshot> MethodEndpoint<R> badRequestMethodEndpoint()
	{
		return (MethodEndpoint<R>) BadRequestMethodEndpointInstance;
	}

	private BadRequestMethodEndpoint()
	{
	}

	@Override
	protected void validateRequestHeaders(@NotNull final Headers requestHeaders)
	{
	}

	@Override
	protected int addResponseHeaders(@NotNull final Headers responseHeaders)
	{
		return BadRequestResponseCode;
	}
}