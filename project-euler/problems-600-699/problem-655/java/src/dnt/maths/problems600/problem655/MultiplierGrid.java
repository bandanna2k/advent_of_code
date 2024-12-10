package dnt.maths.problems600.problem655;

public class MultiplierGrid {

    public final int divisor;
    public final int sourceWidth;
    public final int destinationHeight;
    public final int[][] grid;

    private final int tens; // Bad name

    public MultiplierGrid(int charsToMultiply, int divisor)
    {
        assert charsToMultiply > 1;
        this.divisor = divisor;

        tens = (int) Math.pow(10, charsToMultiply);
        this.sourceWidth = tens;
        this.destinationHeight = tens;

        grid = new int[sourceWidth][destinationHeight];
        for (int source = 0; source < grid.length; source++)
        {
            for (int destination = 0; destination < grid[0].length; destination++)
            {
                grid[source][destination] = multiplier(divisor, source, destination);
            }
        }
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
        for (int i = 0; i < grid.length; i++) {
            sb.append(String.format("%02d ", destinationCounter++));
        }
        sb.append("\n");

        sb.append("     ");
        for (int i = 0; i < grid.length; i++) {
            sb.append("== ");
        }
        sb.append("\n");

        int sourceCounter = 0;
        for (int i = 0; i < grid.length; i++) {
            sb.append(String.format("%02d | ", sourceCounter++));
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(String.format("%02d ", grid[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
