/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.common.http.server.sun.restEndpoints.methodEndpoints;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.resourceStates.resourceContents.ResourceContent;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.resourceStates.ResourceStateSnapshot;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static uk.nhs.hcdn.common.http.RequestHeader.*;
import static uk.nhs.hcdn.common.http.server.sun.helpers.RequestHeadersHelper.*;
import static uk.nhs.hcdn.common.http.ResponseCode.*;
import static uk.nhs.hcdn.common.http.server.sun.helpers.ResponseHeadersHelper.withoutEntityHeaders;

public abstract class AbstractHeadOrGetMethodEndpoint<R extends ResourceStateSnapshot> implements MethodEndpoint<R>
{
	// last modified is starting to look quite egregious
	@Override
	public final void handle(@NotNull final String rawRelativeUriPath, @Nullable final String rawQueryString, @NotNull final HttpExchange httpExchange, @NotNull final R resourceStateSnapshot) throws IOException, BadRequestException
	{
		final Headers requestHeaders = httpExchange.getRequestHeaders();
		validateNoContentLength(requestHeaders);
		validateNoRequestBody(requestHeaders, httpExchange);
		validateHeadersOmitted(requestHeaders, DateHeaderName);

		@Nullable final String expect = validateZeroOrOneInstanceOfRequestHeader(requestHeaders, ExpectHeaderName);
		if (expect != null)
		{
			httpExchange.sendResponseHeaders(ExpectationFailedResponseCode, NoContentBodyMagicValue);
			httpExchange.close();
			return;
		}

		// now if-match when etags supported

		@Nullable final Date ifUnmodifiedSince = validateZeroOrOneInstanceOfRfc2822Header(requestHeaders, IfUnmodifiedSinceHeaderName);
		if (ifUnmodifiedSince != null && resourceStateSnapshot.ifUnmodifiedSincePreconditionFailed(ifUnmodifiedSince))
		{
			withoutEntityHeaders(httpExchange, CacheControlHeaderValueMaximum);
			httpExchange.sendResponseHeaders(PreconditionFailedResponseCode, NoContentBodyMagicValue);
			httpExchange.close();
			return;
		}

		// now if-none-match when etags supported

		@Nullable final Date ifModifiedSince = validateZeroOrOneInstanceOfRfc2822Header(requestHeaders, IfModifiedSinceHeaderName);
		if (ifModifiedSince != null && !resourceStateSnapshot.ifModifiedSinceNotModified(ifModifiedSince))
		{
			withoutEntityHeaders(httpExchange, CacheControlHeaderValueMaximum);
			httpExchange.sendResponseHeaders(NotModifiedResponseCode, NoContentBodyMagicValue);
			httpExchange.close();
			return;
		}

		final ResourceContent content;
		try
		{
			content = resourceStateSnapshot.content(rawRelativeUriPath, rawQueryString);
		}
		catch (NotFoundException ignored)
		{
			httpExchange.sendResponseHeaders(NotFoundResponseCode, NoContentBodyMagicValue);
			httpExchange.close();
			return;
		}

		content.setHeaders(httpExchange, resourceStateSnapshot.lastModifiedInRfc2822Form());
		send(httpExchange, content);
		httpExchange.close();
	}

	protected abstract void send(@NotNull final HttpExchange httpExchange, @NotNull final ResourceContent content) throws IOException;

	private static void validateNoContentLength(final Headers requestHeaders) throws BadRequestException
	{
		@NonNls @Nullable final String contentLength = requestHeaders.getFirst(ContentLengthHeaderName);
		final boolean hasContent = contentLength != null && !"0".equals(contentLength);
		if (hasContent)
		{
			throw new BadRequestException("HEAD and GET should not have a non-zero Content-Length header");
		}
	}

	private static void validateNoRequestBody(final Headers requestHeaders, final HttpExchange httpExchange) throws BadRequestException
	{
		validateTransferEncodingRequestHeader(requestHeaders);

		final InputStream requestBody = httpExchange.getRequestBody();
		try
		{
			//noinspection ResultOfMethodCallIgnored
			requestBody.read();
			throw new BadRequestException("HEAD and GET should not be sent a request body");
		}
		catch (IOException ignored)
		{
		}
		finally
		{
			try
			{
				requestBody.close();
			}
			catch (IOException ignored)
			{
			}
		}
	}

}
