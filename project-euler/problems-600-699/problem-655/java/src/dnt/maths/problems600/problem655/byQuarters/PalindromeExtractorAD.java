package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.common.IntegerUtils.reverseDigits;
import static dnt.common.StringUtils.reverse;
import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorAD
{
    private final String formatAD;
    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private long palindromeCount = 0;

    public PalindromeExtractorAD(ModuliCalculator moduliA,
                                 ModuliCalculator moduliD,
                                 Consumer<BigDecimal> palindromeConsumer)
    {
        this.palindromeConsumer = palindromeConsumer;
        this.moduliA = moduliA;
        this.moduliD = moduliD;
        this.formatAD = "%0" + moduliA.getDigitCount() + "d";
        assert moduliA.getDigitCount() == moduliD.getDigitCount();
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

            int modulusSum = recordA.modulus() + recordD.modulus();
            if (modulusSum % BD10000019.intValue() == 0)
            {
                BigDecimal bigPalindrome = new BigDecimal(
                        String.format("%0" + moduliA.getDigitCount() + "d", recordA.number()) +
                                String.format("%0" + moduliD.getDigitCount() + "d", recordD.number())
                );
                palindromeConsumer.accept(bigPalindrome);
                palindromeCount++;
            }
        }
    }

    public long getPalindromeCount()
    {
        return palindromeCount;
    }
}
