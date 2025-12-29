package dnt.maths.problems600.problem655;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Supplier;

import static dnt.maths.problems600.problem655.Progress.DurationFormatter.formatDuration;

public class Progress
{
    private final Supplier<Long> timeSource;

    private long start;
    private Optional<BigDecimal> maxBigDecimal = Optional.empty();
    private double progress;

    public Progress(Supplier<Long> timeSource)
    {
        this.timeSource = timeSource;
    }

    public static Progress getAndStart()
    {
        Progress progress = new Progress(System::currentTimeMillis);
        progress.start();
        return progress;
    }

    void start()
    {
        start = timeSource.get();
    }

    void progress(BigDecimal value)
    {
        if (maxBigDecimal.isEmpty())
        {
            maxBigDecimal = Optional.of(getMaxWithSameDigits(value).add(BigDecimal.ONE));
        }

        progress = value
                .setScale(100, RoundingMode.HALF_EVEN)
                .divide(maxBigDecimal.get(), RoundingMode.HALF_EVEN)
                .doubleValue();
        double percentage = progress * 100;
        long now = timeSource.get();
        long durationMs = now - start;
        long estimatedDurationMs = (long)(((double)durationMs) / progress);
        long estimatedTimeLeft = estimatedDurationMs - durationMs;

        System.out.printf("Percentage: %1.2f, Current duration: %s, Estimate time left: %s%n",
                percentage,
                formatDuration(durationMs),
                formatDuration(estimatedTimeLeft));
    }

    double progress()
    {
        return progress;
    }

    public static BigDecimal getMaxWithSameDigits(BigDecimal value)
    {
        int digitCount = value.toBigInteger().toString().length();

        // Create max value: 999...9 with same number of digits
        StringBuilder maxStr = new StringBuilder();
        for (int i = 0; i < digitCount; i++)
        {
            maxStr.append("9");
        }

        return new BigDecimal(maxStr.toString());
    }

    public static class DurationFormatter
    {

        /**
         * Convert milliseconds to human-readable duration format.
         * Examples: "1 second", "5 seconds", "1 hour", "1 hour, 30 mins"
         */
        public static String formatDuration(long millis)
        {
            if (millis < 0)
            {
                throw new IllegalArgumentException("Duration must be non-negative");
            }

            long seconds = millis / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            // Calculate remainders
            long remainingHours = hours % 24;
            long remainingMinutes = minutes % 60;
            long remainingSeconds = seconds % 60;

            StringBuilder sb = new StringBuilder();

            if (days > 0)
            {
                sb.append(days).append(" ").append(pluralize("day", days));
            }

            if (remainingHours > 0)
            {
                if (sb.length() > 0)
                {
                    sb.append(", ");
                }
                sb.append(remainingHours).append(" ").append(pluralize("hour", remainingHours));
            }

            if (remainingMinutes > 0)
            {
                if (sb.length() > 0)
                {
                    sb.append(", ");
                }
                sb.append(remainingMinutes).append(" ").append(pluralize("min", remainingMinutes));
            }

            if (remainingSeconds > 0)
            {
                if (sb.length() > 0)
                {
                    sb.append(", ");
                }
                sb.append(remainingSeconds).append(" ").append(pluralize("second", remainingSeconds));
            }

            return sb.length() == 0 ? "0 seconds" : sb.toString();
        }

        private static String pluralize(String unit, long count)
        {
            return count == 1 ? unit : unit + "s";
        }

        // Test examples
        public static void main(String[] args)
        {
            System.out.println(formatDuration(1000));                          // 1 second
            System.out.println(formatDuration(5000));                          // 5 seconds
            System.out.println(formatDuration(60000));                         // 1 min
            System.out.println(formatDuration(90000));                         // 1 min, 30 seconds
            System.out.println(formatDuration(3600000));                       // 1 hour
            System.out.println(formatDuration(5400000));                       // 1 hour, 30 mins
            System.out.println(formatDuration(86400000));                      // 1 day
            System.out.println(formatDuration(90061000));                      // 1 day, 1 hour, 1 min, 1 second
            System.out.println(formatDuration(0));                             // 0 seconds
        }
    }
}
