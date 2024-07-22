import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // User Input
        String userInput = scanner.nextLine();

        // Assign a variable to each requirement
        String[] parts = userInput.split(" ");
        int numberOfUppercaseLetters = Integer.valueOf(parts[0]);
        int numberOfLowercaseLetters = Integer.valueOf(parts[1]);
        int numberOfDigits = Integer.valueOf(parts[2]);
        int numberOfSymbols = Integer.valueOf(parts[3]);

        // Empty stringbuilder to add the elemets to
        ArrayList<Character> password = new ArrayList();

        // Add uppercase letters
        for (int i = 0; i < numberOfUppercaseLetters; i++) {
            password.add((char)('A' + random.nextInt(26)));
        }

        // Add lowercase letters
        for (int i = 0; i < numberOfLowercaseLetters; i++) {
            password.add((char) ('a' + random.nextInt(26)));
        }

        // Digits
        for (int i = 0; i < numberOfDigits; i++) {
            password.add((char) ('0' + random.nextInt(10)));
        }

        // Fill the remaining amount to the required length
        while (password.size() < numberOfSymbols) {
            char ch;
            int type = random.nextInt(3);
            if (type == 0) {
                ch = (char) ('A' + random.nextInt(26));
            } else if (type == 1) {
                ch = (char) ('a' + random.nextInt(26));
            } else {
                ch = (char) ('0' + random.nextInt(10));
            }
            password.add(ch);

        }

        // Randomise the password
        Collections.shuffle(password);

        // Make sure that no two chars are the same
        for (int i = 1; i < password.size(); i++) {
            if (password.get(i) == password.get(i - 1)) {
                // Find a non repeating character to swap with
                for (int j = i + 1; j < password.size(); j++) {
                    if (password.get(j) != password.get(i - 1) && (j == password.size() - 1 || password.get(j) != password.get(j + 1))) {
                        Collections.swap(password, i, j);
                        break;
                    }
                }
            }
        }

        // Convert to a string
        StringBuilder result = new StringBuilder(password.size());
        for (Character ch : password) {
            result.append(ch);
        }

        System.out.println(result.toString());
    }
}