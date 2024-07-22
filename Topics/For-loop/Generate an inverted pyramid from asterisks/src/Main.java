import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Get the number of rows for the pyramid
        int n = scanner.nextInt();

        // For each row..
        // write a loop that starts at 'n' and ends at 1 (hint: you can use a decrement)
        // For each column..
        // write another loop inside the first one that starts at the current row number and ends at 1
        // print an asterisk ('*') without going to the next line (use 'System.out.print' instead of 'println')
        // After each row, print a newline character ('\n')

        for (int row = n; row >= 1 ; row--) {
            for (int col = row; col >= 1 ; col--) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}