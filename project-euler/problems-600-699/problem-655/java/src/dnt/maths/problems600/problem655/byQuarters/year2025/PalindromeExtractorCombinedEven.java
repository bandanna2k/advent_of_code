package dnt.maths.problems600.problem655.byQuarters.year2025;

import dnt.maths.problems600.problem655.ReverseDigits;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorCombinedEven implements PalindromeExtractor
{
    private static final int DIVISOR1 = BD10000019.intValue();
    private static final int DIVISOR2 = BD10000019.intValue() * 2;

    private final ModuliCalculator moduliOuter;
    private final ModuliCalculator moduliInner;
    private final Consumer<BigDecimal> palindromeConsumer;
    private final ReverseDigits reverseDigits;
    private long palindromeCount = 0;

    public PalindromeExtractorCombinedEven(ModuliCalculator moduliOuter,
                                           ModuliCalculator moduliInner,
                                           Consumer<BigDecimal> palindromeConsumer)
    {
        this.palindromeConsumer = palindromeConsumer;
        this.moduliOuter = moduliOuter;
        this.moduliInner = moduliInner;
        reverseDigits = null;//ReverseDigits.getReverseDigits(8);
    }

    public void go()
    {
        int firstOuter = moduliOuter.getFirst();
        int lastOuter = moduliOuter.getLast();
        int lastInner = moduliInner.getLast();
        for (int outer = firstOuter; outer <= lastOuter; outer++)
        {
            int modOuter = moduliOuter.get(outer);

            for (int inner = 0; inner <= lastInner; inner++)
            {
                int modInner = moduliInner.get(inner);

                int modulusSum = modOuter + modInner;
                if (modulusSum == 0 ||
                        modulusSum == DIVISOR1 ||
                        modulusSum == DIVISOR2)
                {
//                    BigDecimal bigPalindrome = BigDecimal.ZERO.(
//                            String.format("%0" + moduliA.getDigitCount() + "d", (a)) +
//                                    String.format("%0" + moduliB.getDigitCount() + "d", (b)) +
//                                    String.format("%0" + moduliC.getDigitCount() + "d", (c)) +
//                                    String.format("%0" + moduliD.getDigitCount() + "d", (d))
//                    );
//                    palindromeConsumer.accept(bigPalindrome);
                    palindromeCount++;
                }
            }
        }
    }

    public long getPalindromeCount()
    {
        return palindromeCount;
    }
}
