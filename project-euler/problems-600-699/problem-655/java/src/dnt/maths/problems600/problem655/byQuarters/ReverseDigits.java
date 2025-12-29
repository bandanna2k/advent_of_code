package dnt.maths.problems600.problem655.byQuarters;

import java.io.*;
import java.time.Instant;

public abstract class ReverseDigits
{
    private static final int MAX_NUMBERS = 10_000_000 * 10;
    static final int[] REVERSE_DIGITS = getReverseDigits();

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
        String padding = "";
        for (int i = 0; i < Math.max(0, 8 - input.length()); i++)
        {
            padding += "0";
        }
        return padding + input;
    }

    private static final String REVERSE_DIGITS_BIN = "/tmp/reverseDigits.bin";
    private static void save(int[] values)
    {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(REVERSE_DIGITS_BIN))) {
            for (int n : values)
                dos.writeInt(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Deprecated // Not fast at all
    static int[] load()
    {
        int[] reverseDigits = new int[MAX_NUMBERS];
        try (DataInputStream dis = new DataInputStream(new FileInputStream(REVERSE_DIGITS_BIN))) {
            for (int i = 0; i < MAX_NUMBERS; i++) {
                reverseDigits[i] = dis.readInt();
            }
        } catch (IOException e) {
            return null;
        }
        return reverseDigits;
    }
}
