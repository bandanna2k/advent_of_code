package dnt.maths.problems600.problem655.byQuarters;

import java.time.Instant;

import static dnt.common.IntegerUtils.reverseDigits;

public abstract class ReverseDigits
{
    static final int[] REVERSE_DIGITS = getReverseDigits();

    private static int[] getReverseDigits()
    {
        System.out.println(Instant.now() + " Start:\tReversing digits.");
        final int MAX_NUMBERS = 10_000_000 * 10;
        int[] reverseDigits = new int[MAX_NUMBERS];
        for (int i = 0; i < MAX_NUMBERS; i++)
        {
            reverseDigits[i] = reverseDigits(i);
        }
        System.out.println(Instant.now() + " End:\tReversing digits.");
        return reverseDigits;
    }
}
