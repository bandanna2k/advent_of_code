package dnt.maths.problems600.problem655.byQuarters;

@Deprecated
public record ModulusRecord(int number, int modulus)
{

    public static int number(int[] value)
    {
        return value[0];
    }
    public static int modulus(int[] value)
    {
        return value[1];
    }
}
