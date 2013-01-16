/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.barcodes.gs1.server;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.barcodes.gs1.server.parsing.Gs1CompanyPrefixResourceStateSnapshotUser;
import uk.nhs.hcdn.barcodes.gs1.server.parsing.TuplesParserFactory;
import uk.nhs.hcdn.common.fileWatching.FailedToReloadException;
import uk.nhs.hcdn.common.fileWatching.FileWatcher;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.AbstractRegisterableMethodRoutingRestEndpoint;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.methodEndpoints.AbstractGetMethodEndpoint;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.methodEndpoints.AbstractHeadMethodEndpoint;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.methodEndpoints.MethodEndpoint;
import uk.nhs.hcdn.common.parsers.ParserFactory;
import uk.nhs.hcdn.common.parsers.sources.FileSource;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;

import static java.lang.Thread.MIN_PRIORITY;
import static java.nio.charset.Charset.forName;
import static uk.nhs.hcdn.common.http.Method.GET;
import static uk.nhs.hcdn.common.http.Method.HEAD;

public final class Gs1CompanyPrefixRestEndpoint extends AbstractRegisterableMethodRoutingRestEndpoint<Gs1CompanyPrefixResourceStateSnapshot> implements Gs1CompanyPrefixResourceStateSnapshotUser
{
	private static final Charset Utf8 = forName("UTF-8");

	// volatile - it may be changed during a request
	@SuppressWarnings("InstanceVariableMayNotBeInitialized") // Actually, it is - by callback
	@NotNull
	private volatile Gs1CompanyPrefixResourceStateSnapshot gs1CompanyPrefixResourceStateSnapshot;

	public Gs1CompanyPrefixRestEndpoint(@NotNull final File containingFolder, @NotNull final String fileName)
	{
		super("/gs1/organisation/", NoAuthentication);

		final FileSource fileSource = new FileSource(Utf8, new File(containingFolder, fileName).toPath().toAbsolutePath());
		@SuppressWarnings("ThisEscapedInObjectConstruction") final ParserFactory parserFactory = new TuplesParserFactory(this);
		final ParsingFileReloader fileReloader = new ParsingFileReloader(parserFactory, fileSource);
		try
		{
			fileReloader.reload();
		}
		catch (FailedToReloadException e)
		{
			throw new IllegalStateException("Could not load tuples", e);
		}
		final FileWatcher fileWatcher = new FileWatcher(containingFolder, fileName, fileReloader);
		final Thread thread = new Thread(fileWatcher, getClass().getSimpleName() + "FileWatcher");
		thread.setDaemon(true);
		thread.setPriority(MIN_PRIORITY);
		thread.start();
	}

	@Override
	public void use(@NotNull final Gs1CompanyPrefixResourceStateSnapshot gs1CompanyPrefixResourceStateSnapshot)
	{
		this.gs1CompanyPrefixResourceStateSnapshot = gs1CompanyPrefixResourceStateSnapshot;
	}

	@Override
	protected void registerMethodEndpointsExcludingOptions(@NotNull final Map<String, MethodEndpoint<Gs1CompanyPrefixResourceStateSnapshot>> methodEndpointsRegister)
	{
		methodEndpointsRegister.put(HEAD, new AbstractHeadMethodEndpoint<Gs1CompanyPrefixResourceStateSnapshot>()
		{
		});

		methodEndpointsRegister.put(GET, new AbstractGetMethodEndpoint<Gs1CompanyPrefixResourceStateSnapshot>()
		{
		});
	}

	@NotNull
	@Override
	protected Gs1CompanyPrefixResourceStateSnapshot snapshotOfStateThatIsInvariantForRequest()
	{
		return gs1CompanyPrefixResourceStateSnapshot;
	}
}
