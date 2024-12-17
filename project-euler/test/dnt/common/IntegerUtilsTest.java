package dnt.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerUtilsTest
{
    @Test
    public void testReverse()
    {
        assertThat(IntegerUtils.reverseDigits(100000)).isEqualTo(1);
        assertThat(IntegerUtils.reverseDigits(12345)).isEqualTo(54321);
    }
}