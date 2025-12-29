package dnt.maths.problems600.problem655.byQuarters;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static dnt.maths.problems600.problem655.byQuarters.ReverseDigits.REVERSE_DIGITS;

public class ReverseDigitsTest
{
    @Test
    public void shouldReverseDigits()
    {
        Assertions.assertThat(REVERSE_DIGITS[10_000]).isEqualTo(1);
        Assertions.assertThat(REVERSE_DIGITS[12345]).isEqualTo(54321);
    }
}