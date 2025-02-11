import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
	static int N, M, R;
	static String[][] arr, res;
	
	static void rotate_arr(Deque<String> temp, int n) {
		for (int r=0; r<R; r++) {
			temp.addFirst(temp.pollLast());
		}
		
		// ↓
		for (int r1=n-1; r1<=N-n; r1++) {
			res[r1][n-1] = temp.poll();
		}
		
		// →
		for (int c1=n; c1<=M-n; c1++) {
			res[N-n][c1] = temp.poll();
		}
		
		// ↑
		for (int r2=N-n-1; r2>n-1; r2--) {
			res[r2][M-n] = temp.poll();
		}
		
		// ←
		for (int c2=M-n; c2>n-1; c2--) {
			res[n-1][c2] = temp.poll();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		arr = new String[N][M];
		res = new String[N][M];
		Deque<String> deque = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				arr[i][j] = sc.next();
			}
		}
		
		// 각 사이클 추출
		for (int n=1; n<=Math.min(N, M)/2; n++) {
			deque = new ArrayDeque<>();
			// ↓
			for (int r1=n-1; r1<=N-n; r1++) {
//				System.out.print(arr[r1][n-1] + " ");
				deque.add(arr[r1][n-1]);
			}
			
			// →
			for (int c1=n; c1<=M-n; c1++) {
//				System.out.print(arr[N-n][c1] + " ");
				deque.add(arr[N-n][c1]);
			}
			
			// ↑
			for (int r2=N-n-1; r2>n-1; r2--) {
//				System.out.print(arr[r2][M-n] + " ");
				deque.add(arr[r2][M-n]);
			}
			
			// ←
			for (int c2=M-n; c2>n-1; c2--) {
//				System.out.print(arr[n-1][c2] + " ");
				deque.add(arr[n-1][c2]);
			}
			rotate_arr(deque, n);
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
		
		
		/*
		 *  시작 (0,0)	(1,1)	(2,2)
				r = 0	r = 1	r = 2
				c = 0	c = 1	c = 2
				r = N-1	r = N-2	r = N-3
				c = M-1	c = M-2 c = M-3
			
			A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
			   ↓                                       ↑
			A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
			   ↓         ↓                   ↑         ↑
			A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
			   ↓                                       ↑
			A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]
		 */
	}
}