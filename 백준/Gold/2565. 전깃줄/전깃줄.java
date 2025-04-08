import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * 1 - 8
		 * 3
		 */
		int N = sc.nextInt();
		int[][] arr = new int[N][2];
		int[] dp = new int[N];
		for (int i=0; i<N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
		
		int max = 0;
		for (int i=0; i<N; i++) {
			dp[i] = 1;
			for (int j=0; j<i; j++) {
				if (arr[i][1] > arr[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(N-max);
	}
}