import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here

        int userInput = scanner.nextInt();
        String result = "You have chosen a ";
        switch (userInput) {
            case 1:
                result += "square";
                break;
            case 2:
                result += "circle";
                break;
            case 3:
                result += "triangle";
                break;
            case 4:
                result += "rhombus";
                break;
            default:
                result = "There is no such shape!";
                break;
        }
        System.out.println(result);
    }
}