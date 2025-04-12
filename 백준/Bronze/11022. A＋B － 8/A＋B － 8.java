import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int tc=1; tc<=T; tc++) {
            int a = input.nextInt();
            int b = input.nextInt();

            System.out.printf("Case #%d: %d + %d = %d\n", tc, a, b, a+b);
        }
    }
}