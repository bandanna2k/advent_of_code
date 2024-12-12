package dnt.maths.problems600.problem655;

import static java.lang.Math.*;

public class MultiplierGridImpl implements Multiplier
{
    public final int divisor;
    public final int sourceWidth;
    public final int destinationHeight;
    public final int[][] grid;

    private final int tens; // Bad name
    private final String format;

    public MultiplierGridImpl(int charsToMultiply, int divisor)
    {
        assert charsToMultiply > 1;
        this.divisor = divisor;

        tens = (int) pow(10, charsToMultiply);
        this.sourceWidth = tens;
        this.destinationHeight = tens;
        this.format = "%0" + charsToMultiply + "d ";


        grid = new int[sourceWidth][destinationHeight];
        for (int i = 0; i < sourceWidth; i++)
        {
            int value = tens;
            int source = i;
            int destination = i;
            grid[source][destination] = value;
            for (int j = 1; j < destinationHeight; j++)
            {
                value = (value + 1) % tens;
                destination = (destination + divisor) % tens;
                grid[source][destination] = value;
            }
        }
    }

    @Override
    public int multiplier(int source, int destination)
    {
        return grid[source][destination];
    }

    int multiplier(int divisor, int source, int destination) {
        int testSource = source + divisor;
        int counter = 1;
        while ((testSource % tens) != destination) {
            counter++;
            testSource += divisor;
        }
        return counter;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        int destinationCounter = 0;
        sb.append("     ");
        for (int i = 0; i < grid.length; i++)
        {
            sb.append(String.format(format, destinationCounter++));
        }
        sb.append("\n");

        sb.append("     ");
        for (int i = 0; i < grid.length; i++) {
            sb.append("== ");
        }
        sb.append("\n");

        int sourceCounter = 0;
        for (int i = 0; i < grid.length; i++) {
            sb.append(String.format(format, sourceCounter++) + "| ");
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(String.format(format, grid[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
