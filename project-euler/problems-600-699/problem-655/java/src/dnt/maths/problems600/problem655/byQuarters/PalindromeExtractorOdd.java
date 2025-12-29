package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.common.IntegerUtils.reverseDigits;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.byQuarters.ModulusRecord.modulus;
import static dnt.maths.problems600.problem655.byQuarters.ModulusRecord.number;

public class PalindromeExtractorOdd implements PalindromeExtractor
{
    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliMid;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
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
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();
        for (int a = firstA; a <= lastA; a++)
        {
            int[] recordA = moduliA.get(a);

            int d = reverseDigits(a);
            int[] recordD = moduliD.get(d);

            int lastB = moduliB.getLast();
            int lastC = moduliC.getLast();
            assert lastB == lastC;
            for (int j = 0; j <= lastB; j++)
            {
                int[] recordB = moduliB.get(j);

                int c = reverseDigits(j);
                int[] recordC = moduliC.get(c);

                for(int k = 0; k < 10; k++)
                {
                    int[] recordMid = moduliMid.get(k);

                    int modulusSum = modulus(recordA) + modulus(recordB) + modulus(recordMid) + modulus(recordC) + modulus(recordD);
                    if (modulusSum % BD10000019.intValue() == 0)
                    {
//                    System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
//                    System.out.printf("%08d%08d%08d%08d %% 10000019\n", recordA.number(), recordB.number(), recordC.number(), recordD.number());
//
                        BigDecimal bigPalindrome = new BigDecimal(
                                String.format("%0" + moduliA.getDigitCount() + "d", number(recordA)) +
                                String.format("%0" + moduliB.getDigitCount() + "d", number(recordB)) +
                                String.format("%0" + moduliMid.getDigitCount() + "d", number(recordMid)) +
                                String.format("%0" + moduliC.getDigitCount() + "d", number(recordC)) +
                                String.format("%0" + moduliD.getDigitCount() + "d", number(recordD))
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
