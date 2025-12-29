package dnt.maths.problems600.problem655;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.within;

public class ProgressTest
{
    @Test
    public void testProgress()
    {
        AtomicLong time = new AtomicLong(1000);
        Progress progress = new Progress(time::get);
        progress.start();

        time.set(2000);
        progress.progress(new BigDecimal("100"));
        Assertions.assertThat(progress.progress()).isEqualTo(0.1, within(0.0001));

        time.set(3000);
        progress.progress(new BigDecimal("200"));
        Assertions.assertThat(progress.progress()).isEqualTo(0.2, within(0.0001));
    }
}