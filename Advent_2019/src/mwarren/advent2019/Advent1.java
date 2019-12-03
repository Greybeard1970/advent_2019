package mwarren.advent2019;

import java.io.File;
import java.util.Scanner;

public class Advent1
{
    public static void main(String[] args) throws Exception
    {
        long total = 0;
        File file = new File("Advent1_input.txt");
        Scanner scanner = new Scanner(file);

//        total = part1(scanner);
        total = part2(scanner);

        System.out.println("total: " + total);
    }
    
    private static long part1(Scanner scanner) {
        long total = 0;
        
        while (scanner.hasNextLong()) {
            total += findMass(scanner.nextLong());
        }

        return total;
    }

    private static long part2(Scanner scanner) {
        long total = 0;
        
        while (scanner.hasNextLong()) {
            long mass = scanner.nextLong();
            while ((mass = findMass(mass)) > 0)
            {
                total += mass;
            }
        }

        return total;
    }

    public static long findMass(long input)
    {
        long ret = (input / 3) - 2;
        return (ret > 0) ? ret : 0;
    }
}
