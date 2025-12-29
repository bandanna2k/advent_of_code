package dnt.maths.problems600.problem655;

import dnt.common.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

@Deprecated(since = "Doesn't work.")
class CheckByPalindromeOddLength implements Checker
{
    public static final int COUNT_OF_DIGITS = 9;
    private final List<BigDecimal> adders;
    private final BigDecimal divisor;

    private int testCount = 0;
    private int palindromeCount = 0;
    private final Consumer<BigDecimal> palindromeConsumer;

    public CheckByPalindromeOddLength(int length, BigDecimal divisor)
    {
        this(length, divisor, x -> {});
    }
    public CheckByPalindromeOddLength(int length, BigDecimal divisor, Consumer<BigDecimal> palindromeConsumer)
    {
        this.divisor = divisor;
        this.palindromeConsumer = palindromeConsumer;
        assert length % 2 == 1;
        adders = new ArrayList<>();
        final int size = length - 1;
        int i;
        for (i = 0; i <= ((size - 1) / 2); i++)
        {
            BigDecimal a = new BigDecimal(10).pow(i);
            BigDecimal z = new BigDecimal(10).pow(size - i);
            adders.add(a.add(z));
        }
        adders.add(new BigDecimal(10).pow(size - i));
    }

    private void checkNextIncrement(int listIndex, BigDecimal value)
    {
        if (listIndex == adders.size())
        {
            return;
        }

        BigDecimal adder = adders.get(listIndex);
        BigDecimal testValue = value;
        for (int i = 0; i < COUNT_OF_DIGITS; i++)
        {
            testValue = testValue.add(adder);
            test(testValue);

            checkNextIncrement(listIndex + 1, testValue);
        }
    }

    public void go()
    {
        checkNextIncrement(0, adders.get(0));
    }

    public int getTestCount()
    {
        return testCount;
    }

    public int getPalindromeCount()
    {
        return palindromeCount;
    }

    private void test(BigDecimal value)
    {
        BigDecimal divided = value.divide(divisor, 6, RoundingMode.DOWN);
        // System.out.printf("%s %d\n", value, String.valueOf(value).length());
        if (isWholeNumber(divided))
        {
            palindromeConsumer.accept(value);
            palindromeCount++;
        }
        testCount++;
    }

    public static boolean isWholeNumber(BigDecimal number)
    {
        return number.remainder(BigDecimal.ONE).compareTo(ZERO) == 0;
    }
}
