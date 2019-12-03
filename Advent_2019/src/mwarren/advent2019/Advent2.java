package mwarren.advent2019;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

// 1.1 = 4462686
// 1.2 = 5936

public class Advent2 {

  public static void main(String[] args) throws Exception {
	String input = "1,9,10,3,2,3,11,0,99,30,40,50";
	File file = new File("Advent2_input.txt");
	Scanner scanner = new Scanner(file);
	input = scanner.nextLine();
	
	int[] originalIntArr = splitStrToIntArr(input);
	int currVal = 0;
	int target = 19690720;
	int noun = 0;
	int verb = 0;
	
	while (true) {
//        System.out.println("-------------------------------------------------------------------------------------------------------");
//        System.out.println("-------------------------------------------------------------------------------------------------------");
        int[] intArr = originalIntArr.clone();
        intArr[1] = noun;
        intArr[2] = verb;
//        System.out.println(Arrays.toString(intArr));
        for (int opPos = 0; opPos < intArr.length; opPos += 4) {
//          System.out.println("OpPos: " + opPos);

          int operation = intArr[opPos];
          if (operation == 99) break;

          int opOnePos = intArr[opPos+1];
          int opOneVal = intArr[opOnePos];
          int opTwoPos = intArr[opPos+2];
          int opTwoVal = intArr[opTwoPos];
          int destPos = opPos + 3;
          int destVal = intArr[destPos];
          
          if (operation == 1) {
                intArr[destVal] = opOneVal + opTwoVal;
          }
          else if (operation == 2) {
                intArr[destVal] = opOneVal * opTwoVal;
          }

//          System.out.println("Iteration: " + opPos);
//          System.out.println(Arrays.toString(intArr));
//          System.out.println("-------------------------------------------------");
        }

        if (intArr[0] == target) {
          System.out.println("Noun: " + noun + " - Verb: " + verb);
          System.out.println(Arrays.toString(intArr));
          break;
        }
        if (noun >= intArr.length && verb >= intArr.length) break;

        if (noun >= intArr.length-1) {
          noun = 0;
          verb++;
        }
        else
          noun++;
	}
  }
  
  public static int[] splitStrToIntArr(String theStr) {
    String[] strArr = theStr.split(",");
    int[] intArr = new int[strArr.length];
    for (int i = 0; i < strArr.length; i++) {
       String num = strArr[i];
       intArr[i] = Integer.parseInt(num);
    }
    return intArr;
 }
}
