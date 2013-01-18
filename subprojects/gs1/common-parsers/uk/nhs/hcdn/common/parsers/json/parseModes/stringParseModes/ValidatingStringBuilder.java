/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.common.parsers.json.parseModes.stringParseModes;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.parsers.json.jsonReaders.InvalidCodePointException;
import uk.nhs.hcdn.common.reflection.toString.AbstractToString;

import static java.lang.Character.isHighSurrogate;
import static java.lang.Character.isLowSurrogate;

public final class ValidatingStringBuilder extends AbstractToString
{
	@SuppressWarnings("StringBufferField")
	private final StringBuilder stringBuilder;
	private boolean previousCharacterIsHighSurrogate;

	public ValidatingStringBuilder(final int sizeGuess)
	{
		stringBuilder = new StringBuilder(sizeGuess);
		previousCharacterIsHighSurrogate = false;
	}

	@SuppressWarnings("NumericCastThatLosesPrecision")
	public void appendUtf16CharacterCheckingForInvalidCodePoints(final int character) throws InvalidCodePointException
	{
		appendUtf16CharacterCheckingForInvalidCodePoints((char) character);
	}

	public void appendUtf16CharacterCheckingForInvalidCodePoints(final char character) throws InvalidCodePointException
	{
		if (previousCharacterIsHighSurrogate)
		{
			if (!isLowSurrogate(character))
			{
				throw new InvalidCodePointException();
			}
			previousCharacterIsHighSurrogate = false;
		}
		else
		{
			if (isLowSurrogate(character))
			{
				throw new InvalidCodePointException();
			}
			previousCharacterIsHighSurrogate = isHighSurrogate(character);
		}
		stringBuilder.append(character);
	}

	@NotNull
	public String asString() throws InvalidCodePointException
	{
		if (previousCharacterIsHighSurrogate)
		{
			throw new InvalidCodePointException();
		}
		return stringBuilder.toString();
	}
}
