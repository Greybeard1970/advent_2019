package mwarren.advent2019;

public class Advent4 {

    public static void main(String[] args) {
        int start = 193651;
        int end = 649729;
        int validPasswords = 0;

        for (int i = start; i <= end; i++) {
            if (isValid(i))
                validPasswords++;
        }
        
        System.out.println("Number of valid passwords: " + validPasswords);
    }

    private static boolean isValid(int num) {
        int[] bucket = new int[10];
        boolean hasDouble = false;

        int last = 10;
        int current;

        while (num > 0) {
            current = num % 10;
            bucket[current]++;

            if (last < current)
                return false;

            last = current;
            num /= 10;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 2) {
                hasDouble = true;
                break;
            }
        }

        return true && hasDouble;
    }
}
