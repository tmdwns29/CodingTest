import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, M, answer = Integer.MIN_VALUE;
	static int[][] map, tmp;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		tmp = new int[N][M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		wall(0);
		System.out.println(answer);
		
		sc.close();
	}
	
	static void wall(int cnt) {
		if (cnt == 3) {
			attack();
			return;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void attack() {
		Queue<Point> tmpVirus = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				tmp[i][j] = map[i][j];
				if (tmp[i][j] == 2) {
					tmpVirus.add(new Point(i, j));
				}
			}
		}
		
		while (!tmpVirus.isEmpty()) {
			Point p = tmpVirus.poll();
			int r = p.x, c = p.y;
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr>=0 && nr<N && nc>=0 && nc<M && tmp[nr][nc] == 0) {
					tmp[nr][nc] = 2;
					tmpVirus.add(new Point(nr, nc));
				}
			}
		}
		int area = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (tmp[i][j] == 0) area++;
			}
		}
		answer = Math.max(area, answer);
	}
}