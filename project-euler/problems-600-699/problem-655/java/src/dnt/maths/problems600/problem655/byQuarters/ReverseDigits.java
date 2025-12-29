package dnt.maths.problems600.problem655.byQuarters;

import java.io.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ReverseDigits
{
    private final static Map<Integer, ReverseDigits> CACHE = new HashMap<>();

    private static final int MAX_NUMBERS = 10_000_000 * 10;

    public final int[] reverseDigits;
    private final int number;

    public static ReverseDigits getReverseDigits(int number) {
        ReverseDigits reverseDigits = CACHE.get(number);
        if(reverseDigits == null)
        {
            reverseDigits = new ReverseDigits(number);
            CACHE.put(reverseDigits.number, reverseDigits);
        }
        return reverseDigits;
    }
    private ReverseDigits(int number)
    {
        this.reverseDigits = getReverseDigits();
        this.number = number;
    }

    private static int[] getReverseDigits()
    {
        System.out.println(Instant.now() + " Start:\tReversing digits.");
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
        return padLeft(input, 8);
    }
    static String padLeft(String input, int padTo) {
        String padding = "";
        for (int i = 0; i < Math.max(0, padTo - input.length()); i++)
        {
            padding += "0";
        }
        return padding + input;
    }
}
