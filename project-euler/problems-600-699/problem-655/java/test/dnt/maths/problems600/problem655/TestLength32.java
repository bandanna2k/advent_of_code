package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.BaseModuli;
import dnt.maths.problems600.problem655.byQuarters.ModuliFor8Digits;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.common.StringUtils.*;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength32
{
    /*
    Needs -Xmx12g
     */
    @Test
    public void testLength32()
    {
        ModuliFor8Digits outer1to8 = new ModuliFor8Digits(1, BD10000019.intValue());
        ModuliFor8Digits outer9to16 = new ModuliFor8Digits(9, BD10000019.intValue());
        ModuliFor8Digits outer17to24 = new ModuliFor8Digits(17, BD10000019.intValue());
        ModuliFor8Digits outer25to32 = new ModuliFor8Digits(25, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractor(outer25to32, outer17to24, outer9to16, outer1to8);
        extractor.go();
    }

    private class PalindromeExtractor
    {
        private final ModuliFor8Digits moduliA;
        private final ModuliFor8Digits moduliB;
        private final ModuliFor8Digits moduliC;
        private final ModuliFor8Digits moduliD;

        public PalindromeExtractor(ModuliFor8Digits moduliA,
                                   ModuliFor8Digits moduliB,
                                   ModuliFor8Digits moduliC,
                                   ModuliFor8Digits moduliD)
        {
            this.moduliA = moduliA;
            this.moduliB = moduliB;
            this.moduliC = moduliC;
            this.moduliD = moduliD;
        }

        void go()
        {
            for (int i = 1; i < 10_000_000; i++)
            {
                BaseModuli.Record recordA = moduliA.get(i);

                for (int j = 0; j < 10_000_000; j++)
                {
                    BaseModuli.Record recordB = moduliB.get(j);

                    int c = Integer.parseInt(reverse(String.valueOf(recordB.number())));
                    int d = Integer.parseInt(reverse(String.valueOf(recordA.number())));
                    BaseModuli.Record recordC = moduliC.get(c);
                    BaseModuli.Record recordD = moduliD.get(d);

//                    System.out.println(recordA);
//                    System.out.println(recordB);
//                    System.out.println(recordC);
//                    System.out.println(recordD);

                    int modulusSum = recordA.modulus() + recordB.modulus() + recordC.modulus() + recordD.modulus();
                    if(modulusSum % BD10000019.intValue() == 0)
                    {
                        System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
                        System.out.printf("%08d%08d%08d%08d %% 10000019", recordA.number(), recordB.number(), recordC.number(), recordD.number());
                        return;
                    }
                }
            }
        }
    }

    @Test // 48 secs
    public void test32CharPalindromesOptimised()
    {
        int length = 32;
        BigDecimal firstDivisible = new BigDecimal("10000000000000000000000009679266");
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, firstDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
