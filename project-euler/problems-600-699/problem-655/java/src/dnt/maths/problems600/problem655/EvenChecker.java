package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;
import dnt.common.Pair;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

class EvenChecker
{
    private final List<Pair<BigDecimal, Integer>> list;
    private final int length;
    private final BigDecimal divisor;

    private int palindromeCount = 0;

    public EvenChecker(int length, BigDecimal divisor)
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
//        list.forEach(item ->
//        {
//                System.out.println("INFO:" + item);
//        });
    }

    public void loadOrWritePalindromes() throws IOException
    {
        loadOrWritePalindromes(0, ZERO);
    }
    public void loadOrWritePalindromes(int listIndex, BigDecimal value) throws IOException
    {
        File file = new File(String.format("palindromes.%dchars.txt", length));

        if(file.exists())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while(null != (line = reader.readLine()))
                {
                    palindromeCount++;
                }
            }
        }
        else
        {
            try (PrintWriter writer = new PrintWriter(file))
            {
                checkNextIncrement(writer, listIndex, value);
            }
            System.out.printf("INFO: File size: %d\n", file.length());
        }
    }

    private void checkNextIncrement(PrintWriter writer, int listIndex, BigDecimal value)
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
            writer.println(testValue);
//            if(divisor != null)
//            {
//                test(testValue);
//            }
            palindromeCount++;

            checkNextIncrement(writer, listIndex + 1, testValue);
        }
    }

    public void go()
    {
        checkNextIncrement(new NoopPrintWriter(), 0, ZERO);
    }

    public int getPalindromeCount()
    {
        return palindromeCount;
    }

    private void test(BigDecimal value)
    {
        BigDecimal divided = value.divide(divisor, 6, RoundingMode.DOWN);
        // System.out.printf("%s %d\n", value, String.valueOf(value).length());
        if (BigDecimalUtils.isWholeNumber(divided))
        {
            palindromeCount++;
//                System.out.println(value);
        }
    }

    private static class NoopPrintWriter extends PrintWriter
    {
        public NoopPrintWriter()
        {
            super(new OutputStream()
            {
                @Override
                public void write(int i)
                {
                }
            });
        }

        @Override
        public void println(String x)
        {
        }
    }
}
