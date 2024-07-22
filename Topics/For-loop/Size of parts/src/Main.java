import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here

        int shipped = 0, fixed = 0, reject = 0;
        int numOfParts = scanner.nextInt();

        for (int i = 0; i < numOfParts; i++) {
            int n = scanner.nextInt();
            if (n == 1) {
                fixed++;
            } else if (n == 0) {
                shipped++;
            } else {
                reject++;
            }
        }

        System.out.println(shipped + " " + fixed + " " + reject);
    }
}