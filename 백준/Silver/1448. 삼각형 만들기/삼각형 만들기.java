import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(nums);
		
		int max_sum = -1;
		for (int i=0; i<N-2; i++) {
			boolean f1 = nums[i  ] < nums[i+1]+nums[i+2];
			boolean f2 = nums[i+1] < nums[i  ]+nums[i+2];
			boolean f3 = nums[i+2] < nums[i  ]+nums[i+1];
			if (f1 && f2 && f3) {
				max_sum = Math.max(max_sum, nums[i]+nums[i+1]+nums[i+2]);
			}
		}
		System.out.println(max_sum);
		br.close();
	}
}