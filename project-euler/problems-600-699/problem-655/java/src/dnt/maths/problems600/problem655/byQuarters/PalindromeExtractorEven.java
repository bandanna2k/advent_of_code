package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

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
    private final ReverseDigits reverseDigits;
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

        this.reverseDigits = ReverseDigits.getReverseDigits(8);
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();

        for (int a = firstA; a <= lastA; a++)
        {
            int modA = moduliA.get(a);

            int d = reverseDigits.reverseDigits[a];
            int modD = moduliD.get(d);

            int lastB = moduliB.getLast();
            for (int b = 0; b <= lastB; b++)
            {
                int modB = moduliB.get(b);

                int c = reverseDigits.reverseDigits[b];
                int modC = moduliC.get(c);

                checkPalindromeFound(a, modA, b, modB, c, modC, d, modD);
            }
        }
        System.out.printf("\nPalindrome count:" + palindromeCount);
    }

    private void checkPalindromeFound(
            int a, int modA,
            int b, int modB,
            int c, int modC,
            int d, int modD)
    {
        int modulusSum = (modA) + (modB) + (modC) + (modD);
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
                    String.format("%0" + moduliA.getDigitCount() + "d", (a)) +
                            String.format("%0" + moduliB.getDigitCount() + "d", (b)) +
                            String.format("%0" + moduliC.getDigitCount() + "d", (c)) +
                            String.format("%0" + moduliD.getDigitCount() + "d", (d))
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
