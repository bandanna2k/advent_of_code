package dnt.maths.problems600.problem655;

import static java.lang.Math.floorMod;

public class MultiplierFor5Chars implements Multiplier
{
    private static final int CHARS = 5;
    private static final int MODULUS = (int) Math.pow(10, CHARS);


    @Override
    public int multiplier(int source, int destination)
    {
        if(source == destination)
        {
            return MODULUS;
        }
        return floorMod((source * 68421) - (destination * 68421), MODULUS);
    }
}
