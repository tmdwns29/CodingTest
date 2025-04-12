import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long n = input.nextInt();
        long m = input.nextInt();

        System.out.println(Math.abs(n-m));
    }
}