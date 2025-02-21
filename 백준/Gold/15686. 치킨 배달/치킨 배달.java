import java.util.ArrayList;
import java.util.Scanner;

class Main {
	static int N, M, sum_chicken_dist, answer=Integer.MAX_VALUE;
	static int[][] map;
	static int[] sel;
	static ArrayList<int[]> chickenJip;
	static ArrayList<int[]> jip;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		chickenJip = new ArrayList<>();
		jip = new ArrayList<>();
		sel = new int[M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					chickenJip.add(new int[] {i, j});
				}
				else if (map[i][j] == 1) {
					jip.add(new int[] {i, j});
				}
			}
		}
		chickenJip_sel(0, 0);
		System.out.println(answer);
		sc.close();
	}
	
	static void chickenJip_sel(int idx, int start) {
		if (idx == M) {
			answer = Math.min(calcChickenDist(sel), answer);
			return;
		}
		
		for (int i=start; i<chickenJip.size(); i++) {
			sel[idx] = i;
			chickenJip_sel(idx+1, i+1);
		}
	}
	
	static int calcChickenDist(int[] selChickenJip) {
		sum_chicken_dist = 0;
		for (int[] j : jip) {
			int jip_r = j[0];
			int jip_c = j[1];
			int min_chicken_dist = Integer.MAX_VALUE;
			for (int c : selChickenJip) {
				int[] chicken = chickenJip.get(c);
				int chicken_r = chicken[0];
				int chicken_c = chicken[1];
				
				int dist = Math.abs(jip_r-chicken_r) + Math.abs(jip_c - chicken_c);
				min_chicken_dist = Math.min(dist, min_chicken_dist);
			}
			sum_chicken_dist += min_chicken_dist;
		}
		return sum_chicken_dist;
	}
}