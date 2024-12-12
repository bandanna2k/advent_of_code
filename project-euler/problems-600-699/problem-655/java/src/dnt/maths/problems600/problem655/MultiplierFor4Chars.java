package dnt.maths.problems600.problem655;

import static java.lang.Math.floorMod;

public class MultiplierFor4Chars implements Multiplier
{
    private static final int CHARS = 4;
    private static final int MODULUS = (int) Math.pow(10, CHARS);


    @Override
    public int multiplier(int source, int destination)
    {
        if(source == destination)
        {
            return MODULUS;
        }
        return floorMod((source * 8421) - (destination * 8421), MODULUS);
    }
}
