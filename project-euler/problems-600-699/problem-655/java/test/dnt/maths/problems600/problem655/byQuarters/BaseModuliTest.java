package dnt.maths.problems600.problem655.byQuarters;

import org.junit.Test;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.byQuarters.BaseModuli.*;
import static java.lang.Math.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseModuliTest
{
    @Test
    public void testModIncrement()
    {
        assertThat(getModIncrement(BD10000019, 1)).isEqualTo(1);
        assertThat(getModIncrement(BD10000019, 2)).isEqualTo(10);
        assertThat(getModIncrement(BD10000019, 3)).isEqualTo(100);
        assertThat(getModIncrement(BD10000019, 4)).isEqualTo(1000);
        assertThat(getModIncrement(BD10000019, 5)).isEqualTo(10000);
        assertThat(getModIncrement(BD10000019, 6)).isEqualTo(100000);
        assertThat(getModIncrement(BD10000019, 7)).isEqualTo(1000000);
        assertThat(getModIncrement(BD10000019, 8)).isEqualTo(10000000);
        assertThat(getModIncrement(BD10000019, 9)).isEqualTo(9999829);
        assertThat(getModIncrement(BD10000019, 10)).isEqualTo(9998119);

        int modIncrement9 = getModIncrement(BD10000019, 10);
        int oneBillion = 1_000_000_000;
        int mod1Billion = oneBillion % BD10000019.intValue();
        int mod2Billion = (oneBillion * 2) % BD10000019.intValue();
        assertThat(floorMod(mod2Billion - mod1Billion, BD10000019.intValue())).isEqualTo(modIncrement9);
    }
}