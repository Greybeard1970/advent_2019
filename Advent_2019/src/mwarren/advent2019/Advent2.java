package mwarren.advent2019;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

// 1.1 = 4462686
// 1.2 = 5936

public class Advent2
{

    public static void main(String[] args) throws Exception
    {
        File file = new File("Advent2_input.txt");
        Scanner scanner = new Scanner(file);
        String input = scanner.nextLine();

        int[] originalIntArr = splitStrToIntArr(input);

        // part1(originalIntArr);
        part2(originalIntArr);
    }

    private static void part1(int[] originalArr)
    {
        int[] arr = parseArray(12, 2, originalArr);
        System.out.println(Arrays.toString(arr));
    }

    private static void part2(int[] originalArr)
    {
        int target = 19690720;

        int noun = 0;
        int verb = 0;
        while (true)
        {
            int[] intArr = parseArray(noun, verb, originalArr);

            if (intArr[0] == target)
            {
                System.out.println("Noun: " + noun + " - Verb: " + verb);
                System.out.println(Arrays.toString(intArr));
                break;
            }

            if (noun >= intArr.length && verb >= intArr.length)
                break;

            if (noun >= intArr.length - 1)
            {
                noun = 0;
                verb++;
            }
            else
                noun++;
        }
    }

    public static int[] splitStrToIntArr(String theStr)
    {
        String[] strArr = theStr.split(",");
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++)
        {
            String num = strArr[i];
            intArr[i] = Integer.parseInt(num);
        }
        return intArr;
    }

    private static int[] parseArray(int noun, int verb, int[] sourceArr)
    {
        int[] targetArr = sourceArr.clone();
        targetArr[1] = noun;
        targetArr[2] = verb;

        for (int opPos = 0; opPos < targetArr.length; opPos += 4)
        {

            int operation = targetArr[opPos];
            if (operation == 99)
                break;

            int opOnePos = targetArr[opPos + 1];
            int opOneVal = targetArr[opOnePos];
            int opTwoPos = targetArr[opPos + 2];
            int opTwoVal = targetArr[opTwoPos];
            int destPos = opPos + 3;
            int destVal = targetArr[destPos];

            if (operation == 1)
            {
                targetArr[destVal] = opOneVal + opTwoVal;
            }
            else if (operation == 2)
            {
                targetArr[destVal] = opOneVal * opTwoVal;
            }
        }

        return targetArr;
    }
}
