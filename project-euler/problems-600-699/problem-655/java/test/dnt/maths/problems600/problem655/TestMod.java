package dnt.maths.problems600.problem655;

import org.junit.Test;

import static dnt.maths.problems600.problem655.Constants.INT_BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMod
{

    public static final long LONG = 10000019L;

    @Test
    public void testModula()
    {
        /*
        100 5281 4 1825 001
        100 0000 0 0000 000 = 100000000000000 % 10000019
            5281 0 0000 000 = 528100000000
                 4 0000 000 = 40000000
                   1825 000 = 1825000
         */
        int a = 361;
        long b = 8996629L;
        long mid = 9999943L;
        int c = 1825000;
        int d = 1;
        assertThat(100000000000000L % INT_BD10000019).isEqualTo(a);
        assertThat(528100000000L % INT_BD10000019).isEqualTo(b);
        assertThat(40000000 % INT_BD10000019).isEqualTo(mid);
        assertThat(1825000 % INT_BD10000019).isEqualTo(c);
        assertThat(1 % INT_BD10000019).isEqualTo(d);
        assertThat((a + b + mid + c + d)).isEqualTo(20821934);

        long l1 = 100528141825001L - 20821934;
        long divided = l1 / INT_BD10000019;
        assertThat(divided * INT_BD10000019).isEqualTo(l1);
    }
}
