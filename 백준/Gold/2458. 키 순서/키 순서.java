import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, answer;
    static int[][] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new int[N+1][N+1];
        answer = 0;

        for (int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a][b] = 1;
        }

        for (int i=1; i<N+1; i++) {
            if (bfs(i) == N-1) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static int bfs(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        boolean[] visited = new boolean[N+1];
        visited[n] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int c = queue.poll();

            for (int i=1; i<N+1; i++) {
                if (adjList[c][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }

        queue = new ArrayDeque<>();
        queue.add(n);
        visited = new boolean[N+1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            int c = queue.poll();

            for (int i=1; i<N+1; i++) {
                if (adjList[i][c] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}