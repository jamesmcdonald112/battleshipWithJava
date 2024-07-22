import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        // Your code here
        String[] words = sentence.split(" ");
        String largest = words[0];

        for(String word : words) {
            if (word.length() > largest.length()) {
                largest = word;
            }
        }

        System.out.println(largest);
    }
}