import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static int danji(int i, int j, int num) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {i, j});
		visited[i][j] = num;
		int danji_cnt = 1;
		
		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int r = p[0];
			int c = p[1];
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] == 1 && visited[nr][nc] == 0) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = num;
					danji_cnt++;
				}
			}
		}
		return danji_cnt;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		visited = new int[N][N];
		ArrayList<Integer> danjis = new ArrayList<>();
		for (int i=0; i<N; i++) {
			String[] temp = sc.next().split("");
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		int cnt = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 1 && visited[i][j] == 0) {
					danjis.add(danji(i, j, cnt++));
				}
			}
		}
		Collections.sort(danjis);
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<N; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(cnt-1);
		for (Integer d: danjis) {
			System.out.println(d);
		}
		

	}
}