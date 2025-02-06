import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] house = new int [N][3];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<3; j++) {
				house[i][j] = sc.nextInt();
			}
		}
		
		for (int i=1; i<N; i++) {
			house[i][0] += Math.min(house[i-1][1], house[i-1][2]);
			house[i][1] += Math.min(house[i-1][0], house[i-1][2]);
			house[i][2] += Math.min(house[i-1][0], house[i-1][1]);
		}
		int min_value = Integer.MAX_VALUE;
		for (int h : house[N-1]) {
			if (h < min_value) {
				min_value = h;
			}
		}
		System.out.println(min_value);
	}
}