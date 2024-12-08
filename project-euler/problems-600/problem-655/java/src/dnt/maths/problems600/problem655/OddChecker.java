package dnt.maths.problems600.problem655;

import dnt.common.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

class OddChecker
{
    private final List<Pair<BigDecimal, Integer>> list;
    private final int length;
    private final BigDecimal divisor;

    private int testCount = 0;
    private int palindromeCount = 0;

    public OddChecker(int length, BigDecimal divisor)
    {
        this.divisor = divisor;
        assert length % 2 == 0;
        this.length = length;
        list = new ArrayList<>();
        final int size = length - 1;
        for (int i = 0; i <= (size / 2); i++)
        {
            BigDecimal a = new BigDecimal(10).pow(i);
            BigDecimal z = new BigDecimal(10).pow(size - i);
            list.add(new Pair(a.add(z), 0));
        }
        list.get(0).setRight(1);
        list.forEach(item ->
        {
//                System.out.println("INFO:" + item);
        });
    }

    private void checkNextIncrement(int listIndex, BigDecimal value)
    {
        if (listIndex == list.size())
        {
            return;
        }

        BigDecimal adder = list.get(listIndex).getLeft();
        BigDecimal testValue = value;
        for (int i = 0; i < 9; i++)
        {
            testValue = testValue.add(adder);
            test(testValue);

            checkNextIncrement(listIndex + 1, testValue);
        }
    }

    public void go()
    {
//            checkNextIncrement(0, list.get(0).left);
        checkNextIncrement(0, ZERO);
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
            palindromeCount++;
//                System.out.println(value);
        }
        testCount++;
    }

    public static boolean isWholeNumber(BigDecimal number)
    {
        return number.remainder(BigDecimal.ONE).compareTo(ZERO) == 0;
    }
}
