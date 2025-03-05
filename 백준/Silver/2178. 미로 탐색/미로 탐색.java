import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, M;
	static boolean[][] visited;
	static int[][] miro;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N][M];
		miro = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String temp = sc.next();
			for (int j=0; j<M; j++) {
				miro[i][j] = temp.charAt(j) - '0';
			}
		}
		miroSearch(0, 0);
		
		System.out.println(miro[N-1][M-1]);
		sc.close();
	}
	
	static void miroSearch(int i, int j) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(i, j));
		visited[i][j] = true;
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.x, c = p.y;
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && miro[nr][nc] == 1) {
					visited[nr][nc] = true;
					miro[nr][nc] = miro[r][c] + 1;
					queue.add(new Point(nr, nc));
				}
			}
		}
	}
}