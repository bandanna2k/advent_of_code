package dnt.adventofcode;

public class TestBase
{
    protected void debug(final String message)
    {
//        System.out.println(message);
    }
    protected void debug_(final String message)
    {
//        System.out.printf(message);
    }
    protected void info(final String message)
    {
        System.out.println(message);
    }
    protected static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }
}
