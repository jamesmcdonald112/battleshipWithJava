import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int first = scanner.nextInt();
        int last = scanner.nextInt();

        int sum = 0;
        for (int i = first; i <= last; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}