package dnt.maths.problems600.problem655.byQuarters.year2025;

import dnt.maths.problems600.problem655.ReverseDigits;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class PalindromeExtractorMultiThreadedEven implements PalindromeExtractor
{
    private static final int DIVISOR1 = BD10000019.intValue();
    private static final int DIVISOR2 = BD10000019.intValue() * 2;
    private static final int DIVISOR3 = BD10000019.intValue() * 3;
    private static final int DIVISOR4 = BD10000019.intValue() * 4;

    private final ModuliCalculator moduliA;
    private final ModuliCalculator moduliB;
    private final ModuliCalculator moduliC;
    private final ModuliCalculator moduliD;
    private final Consumer<BigDecimal> palindromeConsumer;
    private final ReverseDigits reverseDigitsAD;
    private final ReverseDigits reverseDigitsBC;

    public PalindromeExtractorMultiThreadedEven(ModuliCalculator moduliA,
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

        int lastB = this.moduliB.getLast();
        int lastC = this.moduliC.getLast();
        assert lastB == lastC;

        this.reverseDigitsAD = ReverseDigits.getReverseDigits(moduliA.getDigitCount());
        this.reverseDigitsBC = ReverseDigits.getReverseDigits(moduliB.getDigitCount());
    }

    public void go()
    {
        int firstA = moduliA.getFirst();
        int lastA = moduliA.getLast();

        int cpuCores = Runtime.getRuntime().availableProcessors();
        try (ExecutorService executor = Executors.newFixedThreadPool(cpuCores, customThreadFactory)) {
            System.out.println("Executor created with " + cpuCores + " threads for CPU-bound tasks.");

            for (int a = firstA; a <= lastA; a++)
            {
                int finalA = a;
                executor.submit(() -> {
                    int modA = moduliA.get(finalA);

                    int d = reverseDigitsAD.reverseDigits[finalA];
                    int modD = moduliD.get(d);

                    int lastB = moduliB.getLast();
                    for (int b = 0; b <= lastB; b++)
                    {
                        int modB = moduliB.get(b);

                        int c = reverseDigitsBC.reverseDigits[b];
                        int modC = moduliC.get(c);

                        checkPalindromeFound(finalA, modA, b, modB, c, modC, d, modD);
                    }
                });
            }
        }
    }

    private void checkPalindromeFound(
            int a, int modA,
            int b, int modB,
            int c, int modC,
            int d, int modD)
    {
        int modulusSum = (modA) + (modB) + (modC) + (modD);
        if (modulusSum == 0 ||
                modulusSum == DIVISOR1 ||
                modulusSum == DIVISOR2 ||
                modulusSum == DIVISOR3 ||
                modulusSum == DIVISOR4)
        {
//                    System.out.printf("Palindrome found. %s %s %s %s\n", recordA, recordB, recordC, recordD);
//                    System.out.printf("%08d%08d%08d%08d %% 10000019\n", recordA.number(), recordB.number(), recordC.number(), recordD.number());
//
            BigDecimal bigPalindrome = new BigDecimal(
                    String.format("%0" + moduliA.getDigitCount() + "d", (a)) +
                            String.format("%0" + moduliB.getDigitCount() + "d", (b)) +
                            String.format("%0" + moduliC.getDigitCount() + "d", (c)) +
                            String.format("%0" + moduliD.getDigitCount() + "d", (d))
            );
            palindromeConsumer.accept(bigPalindrome);
        }
    }

    public long getPalindromeCount()
    {
        throw new RuntimeException("Not implemented.");
    }

    private final ThreadFactory customThreadFactory = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("cpu-task-thread-" + count.incrementAndGet());
            t.setDaemon(false); // Example: make them non-daemon
            return t;
        }
    };
}
