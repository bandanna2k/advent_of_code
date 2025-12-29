package dnt.maths.problems600.problem655.byQuarters;

import dnt.maths.problems600.problem655.ReverseDigits;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorOdd implements PalindromeExtractor
{
    private static final int DIVISOR1 = BD10000019.intValue();
    private static final int DIVISOR2 = BD10000019.intValue() * 2;
    private static final int DIVISOR3 = BD10000019.intValue() * 3;
    private static final int DIVISOR4 = BD10000019.intValue() * 4;

    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliMid;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private final ReverseDigits reverseDigitsAD;
    private final ReverseDigits reverseDigitsBC;
    private long palindromeCount = 0;

    public PalindromeExtractorOdd(ModuliCalculator moduliA,
                                  ModuliCalculator moduliB,
                                  ModuliCalculator moduleMid,
                                  ModuliCalculator moduliC,
                                  ModuliCalculator moduliD,
                                  Consumer<BigDecimal> palindromeConsumer)
    {
        this.palindromeConsumer = palindromeConsumer;
        this.moduliA = moduliA;
        this.moduliB = moduliB;
        this.moduliMid = moduleMid;
        this.moduliC = moduliC;
        this.moduliD = moduliD;
        assert moduliA.getDigitCount() == moduliD.getDigitCount();
        assert moduliB.getDigitCount() == moduliC.getDigitCount();
        reverseDigitsAD = ReverseDigits.getReverseDigits(moduliA.getDigitCount());
        reverseDigitsBC = ReverseDigits.getReverseDigits(moduliB.getDigitCount());

        int lastB = moduliB.getLast();
        int lastC = moduliC.getLast();
        assert lastB == lastC;
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();
        for (int a = firstA; a <= lastA; a++)
        {
            int modA = moduliA.get(a);

            int d = reverseDigitsAD.reverseDigits[a];
            int modD = moduliD.get(d);

            int lastB = moduliB.getLast();
            for (int b = 0; b <= lastB; b++)
            {
                int modB = moduliB.get(b);

                int c = reverseDigitsBC.reverseDigits[b];
                int modC = moduliC.get(c);

                for(int k = 0; k < 10; k++)
                {
                    int modMid = moduliMid.get(k);

                    int modulusSum = (modA) + (modB) + (modMid) + (modC) + (modD);
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
                                String.format("%0" + moduliMid.getDigitCount() + "d", (k)) +
                                String.format("%0" + moduliC.getDigitCount() + "d", (c)) +
                                String.format("%0" + moduliD.getDigitCount() + "d", (d))
                        );
                        palindromeConsumer.accept(bigPalindrome);
                        palindromeCount++;
                    }
                }
            }
        }
        System.out.printf("\nPalindrome count:" + palindromeCount);
    }

    @Override
    public long getPalindromeCount()
    {
        return palindromeCount;
    }
}
