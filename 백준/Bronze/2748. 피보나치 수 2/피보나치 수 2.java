import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[91];
        dp[1] = 1;

        for (int i=2; i<=N; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        System.out.println(dp[N]);
    }
}