package dnt.maths.problems600.problem655;

import static java.lang.Math.floorMod;

public class MultiplierFor3Chars implements Multiplier
{
    private static final int CHARS = 3;
    private static final int MODULUS = (int) Math.pow(10, CHARS);


    @Override
    public int multiplier(int source, int destination)
    {
        if(source == destination)
        {
            return MODULUS;
        }
        return floorMod((source * 421) - (destination * 421), MODULUS);
    }
}
