package mwarren.advent2019;

import java.util.stream.IntStream;

public class Advent4 {

  public static void main(String[] args) {
    int start = 193651;
    int end   = 649729;
	int validPasswords = 0;
	
	System.out.println("Valid: " + isValid(111122));

//	for (int i = start; i <= end; i++) {
//	  if (isValid(i)) validPasswords++;
//	}
	
	System.out.println("Number of valid passwods: " + validPasswords);
//	isValid(start);
//	IntStream.range(start, end).forEach(num -> {
//	  if (isValid(num)) {
////		numValidPasswords++;
//	  }
//	});
  }
  
  private static boolean isValid(int num) {
	boolean isValid = true;
	boolean hasDouble = false;
	boolean doubleAlreadySeen = false;
	
    int lastSeen = 10; 
    int current;

    while (num > 0) {
        current = num % 10;

        if (lastSeen < current)
            return false;

        if (lastSeen == current && !doubleAlreadySeen)  {
          hasDouble = true;
          doubleAlreadySeen = true;
        } else if (lastSeen == current && doubleAlreadySeen)  {
          hasDouble = false;
          doubleAlreadySeen = false;
        }

        lastSeen = current;
        num /= 10;
    }

    return true && hasDouble;	
  }
}
