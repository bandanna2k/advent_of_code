package dnt.maths.problems600.problem655.byQuarters;

import java.time.Instant;

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

    static int reverseDigits(int n) {
        String string = padLeft(String.valueOf(n));
        String reversed = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            // Append each character to the new string in reverse order
            reversed += string.charAt(i);
        }
        return Integer.parseInt(reversed);
    }

    static String padLeft(String input) {
        String padding = "";
        for (int i = 0; i < Math.max(0, 8 - input.length()); i++)
        {
            padding += "0";
        }
        return padding + input;
    }
}
