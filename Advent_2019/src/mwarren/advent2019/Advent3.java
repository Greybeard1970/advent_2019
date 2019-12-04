package mwarren.advent2019;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Advent3
{
    private static List<Point>   intersections     = new ArrayList<>();
    private static List<Integer> intersectionSteps = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();
        File file = new File("Advent3_input.txt");
        Scanner scanner = new Scanner(file);

        String wire1 = scanner.nextLine();
        String wire2 = scanner.nextLine();

        Map<Point, Point> wirePoints = plotWire(wire1, new HashMap<>());
        wirePoints = plotWire(wire2, wirePoints);

        Point origin = new Point(0, 0, 0);
        System.out.println("Min distance: " + intersections.stream().parallel() .map(p -> p.getDistanceFrom(origin)).min(Integer::compare) .get());
        System.out.println("Min steps: " + intersectionSteps.stream().parallel() .min(Integer::compare).get());

        Instant finish = Instant.now();
        System.out.println( "Elapsed: " + Duration.between(start, finish).toMillis());
    }

    private static class Point {
        private int x;
        private int y;
        private int stepCounter;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.stepCounter = step;
        }

        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
            this.stepCounter = p.stepCounter;
        }

        public Point move(String direction) {
            if ("U".equals(direction)) y++;
            if ("D".equals(direction)) y--;
            if ("R".equals(direction)) x++;
            if ("L".equals(direction)) x--;
            stepCounter++;
            return new Point(this);
        }

        public int getDistanceFrom(Point origin) {
            return Math.abs(this.x - origin.x) + Math.abs(this.y - origin.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            return (this.x != ((Point) obj).x || this.y != ((Point) obj).y) ? false : true;
        }
    }

    private static Map<Point, Point> plotWire(String wire, Map<Point, Point> existingPoints) {
        Map<Point, Point> points = new HashMap<>();
        String[] steps = wire.split(",");
        Point currentPoint = new Point(0, 0, 0);

        for (String step : steps)
        {
            String direction = step.substring(0, 1);
            int len = Integer.parseInt(step.substring(1));

            IntStream.range(0, len).forEach($ -> {
                Point newPoint = currentPoint.move(direction);
                checkForIntersection(existingPoints, newPoint);
                points.put(newPoint, newPoint);
            });
        }

        return points;
    }

    private static void checkForIntersection(Map<Point, Point> existingPoints, Point point)
    {
        Point p = existingPoints.get(point);
        if (p != null) {
            intersections.add(point);
            intersectionSteps.add(p.stepCounter + point.stepCounter);
        }
    }
}
