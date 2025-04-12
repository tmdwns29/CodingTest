import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] num = new int[5];

        for (int i=0; i<5; i++) {
            num[i] = input.nextInt();
        }
        System.out.println(num[0]*num[1] - num[2]*num[3]*num[4]);
    }
}