import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int maxNum = Integer.MIN_VALUE;
		for (int i=0; i<N; i++) {
			for (int j=0; j<i+1; j++) {
				if (arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		for (int d : dp) maxNum = Math.max(maxNum, d);
		System.out.println(maxNum);
	}
}