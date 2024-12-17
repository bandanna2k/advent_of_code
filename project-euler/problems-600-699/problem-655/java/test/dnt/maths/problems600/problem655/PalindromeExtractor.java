package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModulusRecord;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.common.StringUtils.reverse;
import static dnt.maths.problems600.problem655.Constants.BD10000019;

class PalindromeExtractor
{
    private final String formatAD;
    private final String formatBC;
    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private long palindromeCount = 0;

    public PalindromeExtractor(ModuliCalculator moduliA,
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
        this.formatAD = "%0" + moduliA.getDigitCount() + "d";
        this.formatBC = "%0" + moduliB.getDigitCount() + "d";
        assert moduliA.getDigitCount() == moduliD.getDigitCount();
        assert moduliB.getDigitCount() == moduliC.getDigitCount();
    }

    void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();
        for (int i = firstA; i <= lastA; i++)
        {
            ModulusRecord recordA = moduliA.get(i);

            int d = Integer.parseInt(reverse(String.format(formatAD, i)));
            ModulusRecord recordD = moduliD.get(d);

            int lastB = moduliB.getLast();
            int lastC = moduliC.getLast();
            assert lastB == lastC;
            for (int j = 0; j <= lastB; j++)
            {
                ModulusRecord recordB = moduliB.get(j);

                int c = Integer.parseInt(reverse(String.format(formatBC, j)));
                ModulusRecord recordC = moduliC.get(c);

try
{
    Thread.sleep(1);
}
catch (InterruptedException e)
{
    throw new RuntimeException(e);
}

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
}
