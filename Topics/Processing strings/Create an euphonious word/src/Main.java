import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner s = new Scanner(System.in);
        String word = s.next();

        int consecutiveVowels = 0;
        int consecutiveConsonants = 0;
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                consecutiveVowels++;
                consecutiveConsonants = 0;
                if (consecutiveVowels == 3) {
                    count++;
                    consecutiveVowels = 1;
                }
            } else {
                consecutiveConsonants++;
                consecutiveVowels = 0; // Reset as the pattern is restarting
                if (consecutiveConsonants == 3) {
                    count++;
                    consecutiveConsonants = 1;
                }
            }
        }

        System.out.println(count);
    }

    /**
     * Confirms if character is a vowel.
     *
     * @param c char to check
     * @return true if it is a vowel, false otherwise.
     */
    private static boolean isVowel(char c) {
        return "aeiouy".indexOf(c) != -1;
    }

}