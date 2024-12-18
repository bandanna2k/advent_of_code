package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.common.IntegerUtils.reverseDigits;
import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorEven implements PalindromeExtractor
{
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
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();
        for (int a = firstA; a <= lastA; a++)
        {
            ModulusRecord recordA = moduliA.get(a);

            int d = reverseDigits(a);
            ModulusRecord recordD = moduliD.get(d);

            int lastB = moduliB.getLast();
            int lastC = moduliC.getLast();
            assert lastB == lastC;
            for (int j = 0; j <= lastB; j++)
            {
                ModulusRecord recordB = moduliB.get(j);

                int c = reverseDigits(j);
                ModulusRecord recordC = moduliC.get(c);

                int modulusSum = recordA.modulus() + recordB.modulus() + recordC.modulus() + recordD.modulus();
                if (modulusSum % BD10000019.intValue() == 0)
                {
//                    System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
//                    System.out.printf("%08d%08d%08d%08d %% 10000019\n", recordA.number(), recordB.number(), recordC.number(), recordD.number());
//
                    BigDecimal bigPalindrome = new BigDecimal(
                            String.format("%0" + moduliA.getDigitCount() + "d", recordA.number()) +
                                    String.format("%0" + moduliB.getDigitCount() + "d", recordB.number()) +
                                    String.format("%0" + moduliC.getDigitCount() + "d", recordC.number()) +
                                    String.format("%0" + moduliD.getDigitCount() + "d", recordD.number())
                    );
                    palindromeConsumer.accept(bigPalindrome);
                    palindromeCount++;
                }
            }
        }
        System.out.printf("\nPalindrome count:" + palindromeCount);
    }

    public long getPalindromeCount()
    {
        return palindromeCount;
    }
}
