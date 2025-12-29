package dnt.maths.problems600.problem655;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ReverseDigits
{
    private final static Map<Integer, ReverseDigits> CACHE = new HashMap<>();

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
        this.reverseDigits = getNewReverseDigits(number);
        this.number = number;
    }

    private static int[] getNewReverseDigits(int number)
    {
        System.out.printf(Instant.now() + " Start:\tReversing digits for %d.%n", number);
        int power = (int) Math.pow(10, number);
        int[] reverseDigits = new int[power];
        for (int i = 0; i < power; i++)
        {
            reverseDigits[i] = reverseDigits(i, number);
        }
        System.out.println(Instant.now() + " End:\tReversing digits.");
        return reverseDigits;
    }

    static int reverseDigits(int n) {
        return reverseDigits(n, 8);
    }
    private static int reverseDigits(int n, int padTo) {
        String string = padLeft(String.valueOf(n), padTo);
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
    private static String padLeft(String input, int padTo) {
        String padding = "";
        for (int i = 0; i < Math.max(0, padTo - input.length()); i++)
        {
            padding += "0";
        }
        return padding + input;
    }
}
