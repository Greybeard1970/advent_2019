package mwarren.advent2019;

import java.util.*;
import java.io.*;

public class Advent1 {

  public static void main(String[] args) throws Exception {
	int total = 0;
	File file = new File("Advent1_input.txt");
	Scanner scanner = new Scanner(file);

	while (scanner.hasNextLong()) {
	  long mass = scanner.nextLong();
	  while ((mass = findMass(mass)) > 0) {
		total += mass;
	  }
	}

	System.out.println("total: " + total);
	// 3353880 - part 1
	// 5027950 - part 2
  }

  public static long findMass(long input) {
	long ret = (input / 3) - 2;
	return (ret > 0) ? ret : 0;
  }
}
