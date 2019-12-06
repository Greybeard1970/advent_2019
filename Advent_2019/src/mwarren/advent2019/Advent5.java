package mwarren.advent2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Advent5
{
    private int input;

    public Advent5(int input) {
        this.input = input;
    }

    public static void main(String[] args) {
        int input = 1;
        Advent5 a = new Advent5(input);
        
        DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        long millis = 1575553819;
        System.out.println("Date: " + df.format(new Date(millis * 1000)));
        
        try
        {
            a.execute();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    private void execute() throws FileNotFoundException {
        File file = new File("Advent5_input.txt");
        Scanner scanner = new Scanner(file);
        
        int[] instructions = splitStrToIntArr(scanner.nextLine());
        
        int commandPointer = 0;
        int command = instructions[commandPointer];
        Integer input = null;

        do {
            System.out.println(Arrays.toString(instructions));
            System.out.println("Command: " + command + " - pointer: " + commandPointer);
            // Assuming there can be, at most, only 4 digit3. 
            // Maybe change to a List instead?
            int opCode = command % 100; command = command / 100;
            int mode1 = command % 10;  command = command / 10;
            int mode2 = command % 10;  command = command / 10;
            int mode3 = command % 10;   // Is this even used?
            int val1 = (mode1 == 0) ? instructions[instructions[commandPointer + 1]] : instructions[commandPointer + 1];
            int val2 = (mode2 == 0) ? instructions[instructions[commandPointer + 2]] : instructions[commandPointer + 2];
//            int val3 = (mode3 == 0) ? instructions[instructions[commandPointer + 3]] : instructions[commandPointer + 3];
            
            switch (opCode) {
                case 1: // Add
                    instructions[instructions[commandPointer + 3]] = val1 + val2;
                    commandPointer += 4;
                    break;
                case 2: // Multiply
                    instructions[instructions[commandPointer + 3]] = val1 * val2;
                    commandPointer += 4;
                    break;
                case 3: // Get input
                    instructions[instructions[commandPointer + 1]] = getUserInput();
                    commandPointer += 2;
                    break;
                case 4: // Store output
                    System.out.println("Output: " + instructions[instructions[commandPointer + 1]]);
                    commandPointer += 2;
                    break;
                case 5: // If true
                    commandPointer = (val1 != 0) ? val2 : commandPointer + 3;
                    break;
                case 6: // If false
                    commandPointer = (val1 == 0) ? val2 : commandPointer + 3;
                    break;
                case 7: // Less than
                    instructions[instructions[commandPointer+3]] = val1 < val2 ? 1 : 0;
                    commandPointer += 4;
                    break;
                case 8: // Equals
                    instructions[instructions[commandPointer+3]] = val1 == val2 ? 1 : 0;
                    commandPointer += 4;
                    break;
            }
            
            command = instructions[commandPointer];
        } while (command != 99);
        
        System.out.println("Program has finished");
    }
    
    private Integer getUserInput() {
        System.out.print("Input value: ");
        return Integer.parseInt(new Scanner(System.in).nextLine().trim());
    }
    
    // Could possibly use a stream and box to an array of Integer instances
    public static int[] splitStrToIntArr(String theStr)
    {
        String[] strArr = theStr.split(",");
        int[] intArr = new int[strArr.length];

        for (int i = 0; i < strArr.length; i++)
            intArr[i] = Integer.parseInt(strArr[i]);

        return intArr;
    }
}
