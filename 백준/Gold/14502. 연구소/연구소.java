import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    static int N, M, max_area;
    static int[][] map, tmpMap;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    static int count_area() {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (tmpMap[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    static void build_wall(int idx) {
        if (idx == 3) {
            attack_virus();
            return;
        }

        for (int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    build_wall(idx+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void attack_virus() {
        Deque<Point> virus = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                tmpMap[i][j] = map[i][j];
                if (tmpMap[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        while(!virus.isEmpty()) {
            Point p = virus.poll();
            int r = p.x;
            int c = p.y;

            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr>=0 && nr<N && nc>=0 && nc<M && tmpMap[nr][nc] == 0) {
                    tmpMap[nr][nc] = 2;
                    virus.add(new Point(nr, nc));
                }
            }
        }
        max_area = Math.max(max_area, count_area());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        map = new int[N][M];
        tmpMap = new int[N][M];

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j] = input.nextInt();
            }
        }

        build_wall(0);

        System.out.println(max_area);

    }
}