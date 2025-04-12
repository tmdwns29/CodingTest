import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int a=0, b=0;
        for (int i=0; i<N; i++) {
            int ai = input.nextInt();
            int bi = input.nextInt();
            if (ai>bi) a++;
            else if (ai<bi) b++;
        }
        System.out.println(a+" "+b);
    }
}