package dnt.adventofcode.day8;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Day8Part1Test extends TestBase
{
    private static Stream<Arguments> inputs()
    {
        return Stream.of(
                Arguments.of("/day8test.txt", 10, 40),
                Arguments.of("/day8real.txt", 1000, 69192)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, int maxConnections, final long expected) throws IOException
    {

        List<Record> points = new ArrayList<>();
        try (InputStream inputStream = Day8Part1Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                String[] split = line.split(",");
                int[] xyz = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                points.add(new Record(xyz));
            });
        }

        // Pre-populate
        int size = points.size();
        for (Record record : points)
        {
            record.setDistances(new double[size]);
        }

        // Calc distances
        Set<Distance> distances = new TreeSet<>((d1, d2) -> -Double.compare(d2.distance, d1.distance));
        for (int pointA = 0; pointA < size; pointA++)
        {
            for (int pointB = 0; pointB < size; pointB++)
            {
                Record A = points.get(pointA);
                Record B = points.get(pointB);

                if (pointA == pointB)
                {
                    A.distances[pointB] = Double.MAX_VALUE;
                    B.distances[pointA] = Double.MAX_VALUE;
                    continue;
                }

                double dist = distance(A.point, B.point);
                A.distances[pointB] = dist;
                B.distances[pointA] = dist;
                distances.add(new Distance(A.point, B.point, dist));
            }
        }

        for (Distance distance : distances)
        {
//            System.out.println(distance);
        }

        int connections = 0;
        List<Set<String>> circuits = new ArrayList<>();
        for (Distance distance : distances)
        {
            System.out.println("-----");
//            System.out.println(distance);
            String point1 = toString(distance.point1);
            String point2 = toString(distance.point2);

            Set<String> mergedCircuit = new TreeSet<>();
            Iterator<Set<String>> iterator = circuits.iterator();
            while (iterator.hasNext())
            {
                Set<String> circuit = iterator.next();
                if (circuit.contains(point1) && circuit.contains(point2))
                {
                    //System.out.printf("Do nothing. %s %s%n", point1, point2);
                    System.out.println("Removing: " + circuit);
                    iterator.remove();
                    mergedCircuit.addAll(circuit);
                }
                else if (circuit.contains(point1) || circuit.contains(point2))
                {
                    System.out.println("Removing: " + circuit);
                    iterator.remove();
//                    System.out.printf("Merging circuit. Before: %s%n", mergedCircuit);
                    mergedCircuit.addAll(circuit);
                    mergedCircuit.add(point1);
                    mergedCircuit.add(point2);
//                    System.out.printf("Merging circuit. After: %s%n", mergedCircuit);
                }
            }

            if(mergedCircuit.isEmpty())
            {
                TreeSet<String> newCircuit = new TreeSet<>();
                newCircuit.add(point1);
                newCircuit.add(point2);
                System.out.printf("New circuit. %s%n", newCircuit);
                circuits.add(newCircuit);
            }
            else
            {
                System.out.println("Putting: " + mergedCircuit);
                circuits.add(mergedCircuit);
            }

            System.out.println("Circuit sizes: " + circuits.stream().map(Set::size).map(String::valueOf).collect(Collectors.joining(",")));

            connections++;
            if(connections >= maxConnections) break;
        }

        circuits.sort(Comparator.comparingInt(strings -> -strings.size()));
        System.out.println(circuits.stream().map(Set::size).map(String::valueOf).collect(Collectors.joining(",")));

        int total = circuits.get(0).size() * circuits.get(1).size() * circuits.get(2).size();
        assertThat(total).isEqualTo(expected);
    }

    private static String toString(int[] point)
    {
        return String.format("%d,%d,%d", point[0], point[1], point[2]);
    }

    private static double distance(int[] a, int[] b)
    {
        int x2_x1 = b[0] - a[0];
        int y2_y1 = b[1] - a[1];
        int z2_z1 = b[2] - a[2];
        double result = (x2_x1 * x2_x1) + (y2_y1 * y2_y1) + (z2_z1 * z2_z1);
        return Math.sqrt(result);
    }

    private static Stream<Arguments> points()
    {
        return Stream.of(
                Arguments.of(new int[]{0, 0, 0}, new int[]{3, 4, 0}, 5.0d),
                Arguments.of(new int[]{0, 0, 0}, new int[]{1, 1, 1}, 1.73d),
                Arguments.of(new int[]{2, 2, 2}, new int[]{1, 1, 1}, 1.73d)
        );
    }

    @ParameterizedTest()
    @MethodSource("points")
    void distBetween2Points(int[] p1, int[] p2, double expected)
    {
        assertThat(distance(p1, p2)).isEqualTo(expected, offset(0.01d));
    }

    private static final class Distance
    {
        public final int[] point1;
        public final int[] point2;
        public final double distance;

        Distance(int[] point1, int[] point2, double distance)
        {
            this.point1 = point1;
            this.point2 = point2;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object obj)
        {
            return point1[0] == point2[0] && point1[1] == point2[1] && point1[2] == point2[2];
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(Arrays.hashCode(point1), Arrays.hashCode(point2));
        }

        @Override
        public String toString()
        {
            return "Distance[" +
                    "point1=" + Arrays.toString(point1) + ", " +
                    "point2=" + Arrays.toString(point2) + ", " +
                    "distance=" + distance + ']';
        }

    }

    private static final class Record
    {
        public final int[] point;
        private double[] distances;

        private Record(int[] point)
        {
            this.point = point;
        }

        public void setDistances(double[] distances)
        {
            this.distances = distances;
        }

        public double[] distances()
        {
            return distances;
        }

        @Override
        public String toString()
        {
            return "Record{" +
                    "point=" + Arrays.toString(point) +
                    ", distances=" + Arrays.toString(distances) +
                    '}';
        }
    }

    private static class MergingException extends Throwable
    {
    }
}
