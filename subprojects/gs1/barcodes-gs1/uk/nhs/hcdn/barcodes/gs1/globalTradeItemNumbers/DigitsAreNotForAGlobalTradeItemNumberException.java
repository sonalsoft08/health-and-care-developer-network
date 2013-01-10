package uk.nhs.hcdn.barcodes.gs1.globalTradeItemNumbers;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.barcodes.Digits;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class DigitsAreNotForAGlobalTradeItemNumberException extends Exception
{
	public DigitsAreNotForAGlobalTradeItemNumberException(@NotNull final Digits digits)
	{
		super(message(digits));
	}

	public DigitsAreNotForAGlobalTradeItemNumberException(@NotNull final Digits digits, @NotNull final IncorrectCheckDigitForGlobalTradeItemNumberIllegalStateException cause)
	{
		super(message(digits), cause);
	}

	private static String message(final Digits digits)
	{
		return format(ENGLISH, "Digits (%1$s) are not fot a Global Trade Item Number", digits);
	}
}