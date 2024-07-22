import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();
        String[] parts = userInput.split(" ");

        String word = parts[0];
        int size = Integer.valueOf(parts[1]);

        System.out.println(moveSubstring(word, size));

    }

    private static String moveSubstring(String word, int size) {
        if (size > word.length()) {
            return word;
        }
        StringBuilder result = new StringBuilder();
        String substring = word.substring(0, size);
        String remaining = word.substring(size);

        result.append(remaining + substring);
        return result.toString();
    }
}