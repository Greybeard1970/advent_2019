package mwarren.advent2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Advent6 {
    public static void main(String args[]) throws FileNotFoundException {
        Set<String> planetSet = new HashSet<String>();
        HashMap<String, String> planetMap = new HashMap<String, String>();
        int orbits = 0;
        File file = new File("Advent6_input.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNext()) {
          String line = scanner.nextLine();
          String[] ps = line.split("[)]");
          planetSet.add(ps[0]);
          planetSet.add(ps[1]);
          planetMap.put(ps[1], ps[0]);
        }

        for (String planet : planetSet) {
            orbits += countOrbits(planetMap, planet);
        }

        System.out.println("# of orbits: " + orbits);
    }

    public static int countOrbits(HashMap<String, String> map, String planet) {
        int orbits = 0;

        while (map.get(planet) != null) {
            planet = map.get(planet);
            orbits += 1;
        }

        return orbits;
    }
}