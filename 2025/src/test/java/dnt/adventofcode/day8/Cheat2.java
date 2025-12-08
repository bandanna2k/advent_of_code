package dnt.adventofcode.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Cheat2
{
    private List<JunctionBox> parseJunctionBoxes(final List<String> input)
    {
        final List<JunctionBox> junctionBoxes = new ArrayList<>();
        for (final String line : input)
        {
            final String[] splitLine = line.split(",");
            junctionBoxes.add(new JunctionBox(Long.valueOf(splitLine[0]), Long.valueOf(splitLine[1]),
                    Long.valueOf(splitLine[2])));
        }
        return junctionBoxes;
    }

    private TreeMap<Double, Connection> calculateDistances(final List<JunctionBox> coordinates)
    {
        // Build a sorted map of distance to connection (so basically a sorted map of
        // distances between two junction boxes).
        final TreeMap<Double, Connection> connectionDistances = new TreeMap<>();
        for (final JunctionBox c : coordinates)
        {
            for (final JunctionBox o : coordinates)
            {
                if (!c.equals(o))
                {
                    connectionDistances.put(c.getDistance(o), new Connection(c, o));
                }
            }
        }
        return connectionDistances;
    }

    private List<Set<JunctionBox>> calculateCircuits(final TreeMap<Double, Connection> connectionDistances,
                                                     final List<JunctionBox> junctionBoxes, final int nrOfIterations)
    {
        int iterations = 0;
        // The list of all circuits (with a circuit being defined as a Set of junction
        // boxes), initialized a list of sets of single junction boxes.
        final List<Set<JunctionBox>> circuits = junctionBoxes.stream().map(jb -> new HashSet<>(Set.of(jb)))
                .collect(Collectors.toList());

        // We are going to loop over all connections between two junction boxes
        // nrOfIterations times.
        for (final Connection connection : connectionDistances.values())
        {
            final JunctionBox j1 = connection.j1();
            final JunctionBox j2 = connection.j2();

            // A list of circuits which were combined as a result of adding the two junction
            // boxes j1 and j2.
            final List<Set<JunctionBox>> combinedCircuits = new ArrayList<>();

            for (final Set<JunctionBox> circuit : circuits)
            {
                if (circuit.contains(j1) || circuit.contains(j2))
                {
                    // This circuit contains j1 or j2, so add the other one to it (its a Set, so
                    // doubling is not a problem).
                    circuit.add(j1);
                    circuit.add(j2);
                    // Add this circuit to the list of combined circuits.
                    combinedCircuits.add(circuit);
                }
            }

            if (combinedCircuits.size() > 1)
            {
                // More than one circuits was altered as a result of adding the two junction
                // boxes, so combine those.
                final Set<JunctionBox> total = new HashSet<>();
                for (final Set<JunctionBox> circuit : combinedCircuits)
                {
                    circuits.remove(circuit);
                    total.addAll(circuit);
                }
                circuits.add(total);
            }

            iterations++;
            if (iterations == nrOfIterations)
            {
                break;
            }
        }
        return circuits;
    }

    private long calculateProduct(final List<Set<JunctionBox>> circuits)
    {
        long total = 1;
        int i = 0;
        for (final Set<JunctionBox> circuit : circuits)
        {
            System.out.println(circuit.size());
            total *= circuit.size();
            i++;
            if (i == 3)
            {
                break;
            }
        }
        return total;
    }

    private void sortCircuits(final List<Set<JunctionBox>> circuits)
    {
        // Sort the circuits by their size.
        Collections.sort(circuits, new Comparator<Set<JunctionBox>>()
        {
            @Override
            public int compare(final Set<JunctionBox> o1, final Set<JunctionBox> o2)
            {
                return o2.size() - o1.size();
            }

        });
    }

    private long calculateFinalConnection(final TreeMap<Double, Connection> connectionDistances,
                                          final List<JunctionBox> junctionBoxes)
    {
        final List<Set<JunctionBox>> circuits = junctionBoxes.stream().map(jb -> new HashSet<>(Set.of(jb)))
                .collect(Collectors.toList());

        // We are going to loop over all connections between two junction boxes.
        for (final Connection connection : connectionDistances.values())
        {
            final JunctionBox j1 = connection.j1();
            final JunctionBox j2 = connection.j2();

            // A list of circuits which were combined as a result of adding the two junction
            // boxes j1 and j2.
            final List<Set<JunctionBox>> combinedCircuits = new ArrayList<>();

            for (final Set<JunctionBox> circuit : circuits)
            {
                if (circuit.contains(j1) || circuit.contains(j2))
                {
                    // This circuit contains j1 or j2, so add the other one to it (its a Set, so
                    // doubling is not a problem).
                    circuit.add(j1);
                    circuit.add(j2);
                    // Add this circuit to the list of combined circuits.
                    combinedCircuits.add(circuit);
                }
            }

            if (combinedCircuits.size() > 1)
            {
                // More than one circuits was altered as a result of adding the two junction
                // boxes, so combine those.
                final Set<JunctionBox> total = new HashSet<>();
                for (final Set<JunctionBox> circuit : combinedCircuits)
                {
                    circuits.remove(circuit);
                    total.addAll(circuit);
                }
                circuits.add(total);
            }

            if (circuits.size() == 1 && circuits.get(0).size() == junctionBoxes.size())
            {
                // There is only 1 circuits and it contains all junction boxes.
                return j1.x() * j2.x();
            }
        }

        throw new IllegalArgumentException("No pair of junction boxes leads to 1 big circuit");
    }

    protected String runPart2(final List<String> input)
    {
        final List<JunctionBox> junctionBoxes = parseJunctionBoxes(input);
        return String.valueOf(calculateFinalConnection(calculateDistances(junctionBoxes), junctionBoxes));
    }

    protected String runPart1(final List<String> input)
    {
        final List<JunctionBox> junctionBoxes = parseJunctionBoxes(input);
        final List<Set<JunctionBox>> circuits = calculateCircuits(calculateDistances(junctionBoxes), junctionBoxes,
                1000);
        sortCircuits(circuits);
        return String.valueOf(calculateProduct(circuits));
    }

    public static void main(final String... args) throws IOException
    {
        List<String> points = new ArrayList<>();
        try (InputStream inputStream = Day8Part1Test.class.getResourceAsStream("/day8real.txt"))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                points.add(line);
            });
        }

        System.out.println(new Cheat2().runPart1(points));
    }


    public record Connection(JunctionBox j1, JunctionBox j2)
    {
    }

    public record JunctionBox(long x, long y, long z)
    {

        public double getDistance(final JunctionBox other)
        {
            return Math.sqrt(Math.pow(this.x() - other.x(), 2) + Math.pow(this.y() - other.y(), 2)
                    + Math.pow(this.z() - other.z(), 2));
        }
    }
}
