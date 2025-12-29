package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.byQuarters.ModulusRecord.modulus;
import static dnt.maths.problems600.problem655.byQuarters.ModulusRecord.number;
import static dnt.maths.problems600.problem655.byQuarters.ReverseDigits.REVERSE_DIGITS;

public class PalindromeExtractorEven implements PalindromeExtractor
{
    private static final int DIVISOR1 = BD10000019.intValue();
    private static final int DIVISOR2 = BD10000019.intValue() * 2;
    private static final int DIVISOR3 = BD10000019.intValue() * 3;
    private static final int DIVISOR4 = BD10000019.intValue() * 4;

    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private long palindromeCount = 0;

    public PalindromeExtractorEven(ModuliCalculator moduliA,
                               ModuliCalculator moduliB,
                               ModuliCalculator moduliC,
                               ModuliCalculator moduliD,
                               Consumer<BigDecimal> palindromeConsumer)
    {
        this.palindromeConsumer = palindromeConsumer;
        this.moduliA = moduliA;
        this.moduliB = moduliB;
        this.moduliC = moduliC;
        this.moduliD = moduliD;
        assert moduliA.getDigitCount() == moduliD.getDigitCount();
        assert moduliB.getDigitCount() == moduliC.getDigitCount();

        int lastB = this.moduliB.getLast();
        int lastC = this.moduliC.getLast();
        assert lastB == lastC;
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();

        for (int a = firstA; a <= lastA; a++)
        {
            int[] recordA = moduliA.get(a);

            int d = REVERSE_DIGITS[a];
            int[] recordD = moduliD.get(d);

            int lastB = moduliB.getLast();
            for (int b = 0; b <= lastB; b++)
            {
                int[] recordB = moduliB.get(b);

                int c = REVERSE_DIGITS[b];
                int[] recordC = moduliC.get(c);

                checkPalindromeFound(recordA, recordB, recordC, recordD);
            }
        }
        System.out.printf("\nPalindrome count:" + palindromeCount);
    }

    private void checkPalindromeFound(int[] recordA, int[] recordB, int[] recordC, int[] recordD)
    {
        int modulusSum = modulus(recordA) + modulus(recordB) + modulus(recordC) + modulus(recordD);
        if (modulusSum == 0 ||
                modulusSum == DIVISOR1 ||
                modulusSum == DIVISOR2 ||
                modulusSum == DIVISOR3 ||
                modulusSum == DIVISOR4)
        {
//                    System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
//                    System.out.printf("%08d%08d%08d%08d %% 10000019\n", recordA.number(), recordB.number(), recordC.number(), recordD.number());
//
            BigDecimal bigPalindrome = new BigDecimal(
                    String.format("%0" + moduliA.getDigitCount() + "d", number(recordA)) +
                            String.format("%0" + moduliB.getDigitCount() + "d", number(recordB)) +
                            String.format("%0" + moduliC.getDigitCount() + "d", number(recordC)) +
                            String.format("%0" + moduliD.getDigitCount() + "d", number(recordD))
            );
            palindromeConsumer.accept(bigPalindrome);
            palindromeCount++;
        }
    }

    public long getPalindromeCount()
    {
        return palindromeCount;
    }
}
