package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorOddAD implements PalindromeExtractor
{
    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliMid;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private final ReverseDigits reverseDigits;
    private long palindromeCount = 0;

    public PalindromeExtractorOddAD(ModuliCalculator moduliA,
                                    ModuliCalculator moduliMid,
                                    ModuliCalculator moduliD,
                                    Consumer<BigDecimal> palindromeConsumer)
    {
        this.palindromeConsumer = palindromeConsumer;
        this.moduliA = moduliA;
        this.moduliMid = moduliMid;
        this.moduliD = moduliD;
        assert moduliA.getDigitCount() == moduliD.getDigitCount();
        reverseDigits = ReverseDigits.getReverseDigits(8);
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

            for (int i = 0; i < 10; i++)
            {
                int modMid = moduliMid.get(i);

                int modulusSum = (modA) + (modMid) + (modD);
                if (modulusSum % BD10000019.intValue() == 0)
                {
                    BigDecimal bigPalindrome = new BigDecimal(
                            String.format("%0" + moduliA.getDigitCount() + "d", (a)) +
                                    String.format("%0" + moduliMid.getDigitCount() + "d", (i)) +
                                    String.format("%0" + moduliD.getDigitCount() + "d", (d))
                    );
                    palindromeConsumer.accept(bigPalindrome);
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
