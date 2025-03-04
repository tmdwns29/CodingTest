import java.awt.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        n = input.nextInt();
        m = input.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];
        int a = 0, b = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                map[i][j] = input.nextInt();
                if (map[i][j] == 2) {
                    a = i; b = j;
                    map[i][j] = 0;
                }
            }
        }
        BFS(a, b);

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (map[i][j] == 1) {
                    boolean flag = false;
                    for (int d=0; d<4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr == a && nc == b) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) map[i][j] = -1;
                }
            }
        }

        for (int[] ma : map) {
            for (int m : ma) {
                System.out.print(m+" ");
            }
            System.out.println();
        }
    }

    static void BFS(int i, int j) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(i, j));
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            int r = p.x, c = p.y;

            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc]) {
                    if (map[nr][nc] == 1) {
                        map[nr][nc] = map[r][c] + 1;
                        queue.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}