import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String userInput = scanner.next();
        int result = 0;
        switch (userInput) {
            case "one":
                result = 1;
                break;
            case "two":
                result = 2;
                break;
            case "three":
                result = 3;
                break;
            case "four":
                result = 4;
                break;
            case "five":
                result = 5;
                break;
            case "six":
                result = 6;
                break;
            case "seven":
                result = 7;
                break;
            case "eight":
                result = 8;
                break;
            case "nine":
                result = 9;
                break;
            default:
                System.out.println("Must be between one to nine");
        }
        System.out.println(result);
    }
}