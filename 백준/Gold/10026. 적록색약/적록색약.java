import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int N;
    static int[][] area;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;

    static void bfs(int i, int j, char color) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];

            for (int d=0; d<4; d++) {
                int nx = x + dr[d];
                int ny = y + dc[d];
                if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && area[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        area = new int[N][N];
        visited = new boolean[N][N];
        int a_cnt = 0, b_cnt = 0;

        for (int i=0; i<N; i++) {
            String temp = input.next();
            for (int j=0; j<N; j++) {
                area[i][j] = temp.charAt(j);
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, (char)area[i][j]);
                    a_cnt++;
                }
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (area[i][j] == 'G') {
                    area[i][j] = 'R';
                }
            }
        }
        visited = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, (char)area[i][j]);
                    b_cnt++;
                }
            }
        }
        System.out.println(a_cnt+" "+b_cnt);
    }
}