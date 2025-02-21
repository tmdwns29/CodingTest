import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, L, R, number, day;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		while(check_diff()) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) {
						BFS(i, j);
					}
				}
			}
			visited = new boolean[N][N];
			day++;
		}
		System.out.println(day);
		sc.close();
	}
	
	static void BFS(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		ArrayList<int[]> curCuntry = new ArrayList<>();
		visited[i][j] = true;
		queue.add(new int[] {i, j});
		curCuntry.add(new int[] {i, j});
		int sum = map[i][j]; // 2,2

		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int r = p[0], c = p[1];
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr>=0 && nr<N && nc>=0 && nc<N) {
					int pop_diff = Math.abs(map[r][c] - map[nr][nc]);
					if (pop_diff>=L && pop_diff<=R && !visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr, nc});
						curCuntry.add(new int[] {nr, nc});
						sum += map[nr][nc];
					}
				}
			}
		}
		for (int[] p : curCuntry) {
			map[p[0]][p[1]] = sum / curCuntry.size();
		}
//		for (int[] s: map) {
//			System.out.println(Arrays.toString(s));
//		}
//		System.out.println();
	}
	
	static boolean check_diff() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				
				for (int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (nr>=0 && nr<N && nc>=0 && nc<N) {
						int pop_diff = Math.abs(map[i][j] - map[nr][nc]);
						if (pop_diff>=L && pop_diff<=R) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}