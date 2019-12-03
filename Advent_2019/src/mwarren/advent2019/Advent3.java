package mwarren.advent2019;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Advent3
{

    // TODO - possibly change to HashMap so I don't have to iterate through the List
    private static List<Point> intersections = new ArrayList<>();
    private static List<Integer> intersectionSteps = new ArrayList<>();

    public static void main(String[] args) throws Exception
    {
        File file = new File("Advent3_input.txt");
        Scanner scanner = new Scanner(file);

        String wire1 = scanner.nextLine();
        String wire2 = scanner.nextLine();
        
//        String wire1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
//        String wire2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";

        Point origin = new Point(0, 0, 0);
        List<Point> previousPoints = new ArrayList<>();
        
        List<Point> wirePoints = plotWire(wire1, previousPoints);
        previousPoints.addAll(wirePoints);
        wirePoints = plotWire(wire2, previousPoints);
        
        int minDistance = findMinimumDistance(origin, intersections);
        System.out.println("Min distance: " + minDistance);
        
        int minSteps = findMinimumSteps();
        System.out.println("Min steps: " + minSteps);
    }
    
    private static int findMinimumDistance(Point origin, List<Point> intersections) {
        int minDistance = Integer.MAX_VALUE;
        
        for (Point intersection : intersections) {
            minDistance = Math.min(intersection.getDistanceFrom(origin), minDistance);
        }
        
        return minDistance;
    }

    private static int findMinimumSteps() {
        int minSteps = Integer.MAX_VALUE;
        
        for (Integer steps : intersectionSteps) {
            minSteps = Math.min(steps, minSteps);
        }
        
        return minSteps;
    }

    private static class Point
    {
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

        public Point moveLeft()
        {
            x--;
            stepCounter++;
            return new Point(this);
        }

        public Point moveRight()
        {
            x++;
            stepCounter++;
            return new Point(this);
        }

        public Point moveUp()
        {
            y++;
            stepCounter++;
            return new Point(this);
        }

        public Point moveDown()
        {
            y--;
            stepCounter++;
            return new Point(this);
        }

        public int getDistanceFrom(Point origin)
        {
            return Math.abs(this.x - origin.x) + Math.abs(this.y - origin.y);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;

            if (obj == null)
                return false;

            Point other = (Point) obj;

            if (this.x != other.x || this.y != other.y)
                return false;

            return true;
        }
        
        public void setStep(int step) {
            this.step = step;
        }
        
        public int getStep() {
            return this.step;
        }

        public String toString()
        {
            return "X: " + x + " / Y: " + y + " - step: " + stepCounter;
        }
    }

    private static List<Point> plotWire(String wire, List<Point> existingPoints)
    {

        List<Point> points = new ArrayList<>();
        String[] steps = wire.split(",");
        Point currentPoint = new Point(0, 0, 0);

        System.out.println("Step size: " + steps.length);
        int stepCounter = 0;
        for (String step : steps)
        {
//            System.out.println("step: " + step);
//            System.out.println(stepCounter);
            String direction = step.substring(0, 1);
            int len = Integer.parseInt(step.substring(1));

            switch (direction)
            {
                case "U":
                    for (int i = 0; i < len; i++)
                    {
                        Point newPoint = currentPoint.moveUp();
                        
                        if (existingPoints.contains(newPoint))  {
                            Point p = getPoint(existingPoints, newPoint);
                            intersections.add(newPoint);
                            intersectionSteps.add(p.stepCounter + newPoint.stepCounter);
                        }

                        points.add(newPoint);
                    }
                    break;
                case "D":
                    for (int i = 0; i < len; i++)
                    {
                        Point newPoint = currentPoint.moveDown();

                        if (existingPoints.contains(newPoint))  {
                            Point p = getPoint(existingPoints, newPoint);
                            intersections.add(newPoint);
                            intersectionSteps.add(p.stepCounter + newPoint.stepCounter);
                        }

                        points.add(newPoint);
                    }
                    break;
                case "L":
                    for (int i = 0; i < len; i++)
                    {
                        Point newPoint = currentPoint.moveLeft();

                        if (existingPoints.contains(newPoint))  {
                            Point p = getPoint(existingPoints, newPoint);
                            intersections.add(newPoint);
                            intersectionSteps.add(p.stepCounter + newPoint.stepCounter);
                        }

                        points.add(newPoint);
                    }
                    break;
                case "R":
                    for (int i = 0; i < len; i++)
                    {
                        Point newPoint = currentPoint.moveRight();

                        if (existingPoints.contains(newPoint))  {
                            Point p = getPoint(existingPoints, newPoint);
                            intersections.add(newPoint);
                            intersectionSteps.add(p.stepCounter + newPoint.stepCounter);
                        }

                        points.add(newPoint);
                    }
                    break;
            }
            stepCounter++;
        }

        return points;
    }
    
    private static Point getPoint(List<Point> existingPoints, Point newPoint) {
        Point retPoint = null;

        for (Point p : existingPoints) {
            if (p.equals(newPoint)) return p;
        }

        return retPoint;
    }

}
