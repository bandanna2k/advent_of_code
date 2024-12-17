package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModulusRecord;

import static dnt.common.StringUtils.reverse;
import static dnt.maths.problems600.problem655.Constants.BD10000019;

class PalindromeExtractor
{
    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private long palindromeCount = 0;

    public PalindromeExtractor(ModuliCalculator moduliA,
                               ModuliCalculator moduliB,
                               ModuliCalculator moduliC,
                               ModuliCalculator moduliD)
    {
        this.moduliA = moduliA;
        this.moduliB = moduliB;
        this.moduliC = moduliC;
        this.moduliD = moduliD;
    }

    void go()
    {
        int first = moduliA.getFirst();
        int last = moduliA.getLast();
        for (int i = first; i <= last; i++)
        {
            ModulusRecord recordA = moduliA.get(i);

            int d = Integer.parseInt(reverse(String.format("%08d", i)));
            ModulusRecord recordD = moduliD.get(d);

            for (int j = 0; j < 100_000_000; j++)
            {
                ModulusRecord recordB = moduliB.get(j);

                int c = Integer.parseInt(reverse(String.format("%08d", j)));
                ModulusRecord recordC = moduliC.get(c);

                int modulusSum = recordA.modulus() + recordB.modulus() + recordC.modulus() + recordD.modulus();
                if (modulusSum % BD10000019.intValue() == 0)
                {
//                        System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
                    System.out.printf("%08d%08d%08d%08d %% 10000019\n", recordA.number(), recordB.number(), recordC.number(), recordD.number());
                    palindromeCount++;
                    return;
                }
            }
        }
        System.out.printf("\nPalindrome count:" + palindromeCount);
    }
}
