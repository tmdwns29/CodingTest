import java.util.*;

class Main {
    static int N, M;
    static int[][] adj;

    static int BFS(int start, int target) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i=1; i<=N; i++) {
                if (adj[cur][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    distance[i] = distance[cur] + 1;
                    if (i == target) {
                        return distance[i];
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        adj = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int s = input.nextInt();
            int e = input.nextInt();
            adj[s][e] = 1;
            adj[e][s] = 1;
        }

        int answer = Integer.MAX_VALUE;
        int person = -1;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    int distance = BFS(i, j);
                    if (distance == Integer.MAX_VALUE) {
                        sum = Integer.MAX_VALUE;
                        break;
                    }
                    sum += distance;
                }
            }

            if (sum < answer) {
                answer = sum;
                person = i;
            }
        }
        System.out.println(person);
    }
}