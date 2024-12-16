package dnt.common;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static dnt.common.StringUtils.reverse;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class StringUtilsTest
{
    public static class ReverseTest
    {
        @Test
        public void testReverse()
        {
            assertThat(reverse("Petal")).isEqualTo("lateP");
            assertThat(reverse("100000")).isEqualTo("000001");
        }
    }
}