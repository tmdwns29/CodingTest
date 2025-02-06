import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

class Main {
	static int N, cnt;
	static int[][] arr;
	static int[] p1dr = {0,0, 1,1, 1,1,1 };
	static int[] p1dc = {1,1, 0,0, 1,1,1 };
	static int[] p2dr = {0,1, 1,1, 0,1,1 };
	static int[] p2dc = {1,1, 0,1, 1,0,1 };

	static boolean check_wall(int flag, int p2r, int p2c) {
		// 방향 판단 후 : 검사
		if (flag == 1) {
			if (arr[p2r][p2c] == 1) { // 0 0 , 0 1
				return false;
			}
			return true;
		}
		else if (flag == 2) {
			if (arr[p2r][p2c] == 1) {
				return false;
			}
			return true;
		}
		else {
			if (arr[p2r][p2c] == 1) {
				return false;
			}
			if (p2r>=1 && p2r<N+1 && arr[p2r-1][p2c] == 1) {
				return false;
			}
			if (p2c>=1 && p2c<N+1 && arr[p2r][p2c-1] == 1) {
				return false;
			}
			return true;
		}
	}
	
	static void move_pipe(int pp1r, int pp1c, int pp2r, int pp2c) {

		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {pp1r, pp1c, pp2r, pp2c});
		
		while(!queue.isEmpty()) {
			int[] pp = queue.poll();
			int p1r = pp[0], p1c = pp[1];
			int p2r = pp[2], p2c = pp[3];
			
			// N,N에 도달한 경우
			if (p2r == N-1 && p2c == N-1) {
				cnt++;
				continue;
			}
			
			// 파이프 방향 확인(이동위치 할당)
			int ds, de;
			if (p1r == p2r) { ds = 0; de = 2; }
			else if (p1c == p2c) { ds = 2; de = 4; }
			else { ds = 4; de = 7; }
			
			// 놓여진 방향에 따른 이동위치 탐색
			for (int d=ds; d<de; d++) {
				int p1nr = p1r + p1dr[d], p1nc = p1c + p1dc[d];
				int p2nr = p2r + p2dr[d], p2nc = p2c + p2dc[d];
				int flag;
				if (p1nr == p2nr) flag = 1;
				else if (p1nc == p2nc) flag = 2;
				else flag = 3;
				
				// 01 02 | 01 12
				if (p1nr>=0 && p1nr<N && p1nc>=0 && p1nc<N) {
					if (p2nr>=0 && p2nr<N && p2nc>=0 && p2nc<N) {
						
						// 이동 위치에 벽 존재 여부 확인
						if (check_wall(flag, p2nr, p2nc)) {
							queue.offer(new int[] {p1nr, p1nc, p2nr, p2nc});
						}
					}
				}
			}
			
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		move_pipe(0, 0, 0, 1);
		
		System.out.println(cnt);
		br.close();
	}
}