package dnt.maths.problems600.problem655;

import static java.lang.Math.floorMod;
import static java.lang.Math.pow;

public class MultiplierFor2Chars implements Multiplier
{
    @Override
    public int multiplier(int source, int destination)
    {
        if(source == destination)
        {
            return 100;
        }
        return floorMod((source * 21) - (destination * 21), 100);
    }
}
