import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int[] steps = new int[300];
        int[] dp = new int[300];

        for (int i=0; i<N; i++) {
            steps[i] = input.nextInt();
        }
        dp[0] = steps[0];
        dp[1] = steps[0] + steps[1];
        dp[2] = Math.max(steps[0], steps[1]) + steps[2];

        for (int i=3; i<N; i++) {
            dp[i] = Math.max(dp[i-3]+steps[i-1], dp[i-2]) + steps[i];
        }
        System.out.println(dp[N-1]);
    }
}