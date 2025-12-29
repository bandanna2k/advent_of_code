package dnt.maths.problems600.problem655.byQuarters;

import org.junit.Test;

import static dnt.maths.problems600.problem655.byQuarters.ReverseDigits.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ReverseDigitsTest
{
    @Test
    public void shouldReverseDigits()
    {
        assertThat(REVERSE_DIGITS[12345]).isEqualTo(54321000);
        assertThat(REVERSE_DIGITS[123450]).isEqualTo(5432100);
        assertThat(REVERSE_DIGITS[1234500]).isEqualTo(543210);
        assertThat(REVERSE_DIGITS[12345000]).isEqualTo(54321);

        assertThat(REVERSE_DIGITS[1]).isEqualTo(10000000);
        assertThat(REVERSE_DIGITS[10]).isEqualTo(1000000);

        assertThat(REVERSE_DIGITS[10_000]).isEqualTo(1000);
        assertThat(REVERSE_DIGITS[2426095]).isEqualTo(59062420);
    }

    @Test
    public void testReverseDigits()
    {
        assertThat(reverseDigits(2426095)).isEqualTo(59062420);
    }

    @Test
    public void testPadding()
    {
        assertThat(padLeft("2426095")).isEqualTo("02426095");
        assertThat(padLeft("1")).isEqualTo("00000001");
        assertThat(padLeft("987654321")).isEqualTo("987654321");
        assertThat(padLeft("7654321")).isEqualTo("07654321");
    }
}