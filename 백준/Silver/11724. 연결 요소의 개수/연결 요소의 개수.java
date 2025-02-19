import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, M;
	static boolean[] visited;
	static int[][] adj;
	static void BFS(int n) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(n);
		
		while (!queue.isEmpty()) {
			int c = queue.poll();
			
			for (int i=1; i<adj[c].length; i++) {
				if (!visited[i] && adj[c][i] == 1) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		adj = new int[N+1][N+1];
		visited = new boolean[N+1];
		int cnt = 0;
		for (int i=0; i<M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adj[u][v] = 1;
			adj[v][u] = 1;
		}
		
		for (int i=1; i<N+1; i++) {
			if (!visited[i]) {
				BFS(i);
				cnt++;
			}
		}
		System.out.println(cnt);
		sc.close();
	}
}