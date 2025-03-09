import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    static int N, M, answer;
    static int[][] office;
    static ArrayList<CCTV> cctvs;
    static int[][][] direction = {
            {},
            {{0},{1},{2},{3}},
            {{0,2}, {1,3}},
            {{0,1}, {1,2}, {2,3}, {3,0}},
            {{0,1,2}, {1,2,3}, {2,3,0}, {3,0,1}},
            {{0,1,2,3}}
    };
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class CCTV {
        int r, c, id;

        CCTV(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        office = new int[N][M];
        cctvs = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                office[i][j] = input.nextInt();
                if (office[i][j]>=1 && office[i][j]<=5) {
                    cctvs.add(new CCTV(i, j, office[i][j]));
                }
            }
        }

        dfs(0, office);
        System.out.println(answer);
    }

    static void dfs(int idx, int[][] office) {
        if (idx == cctvs.size()) {
            answer = Math.min(answer, countArea(office));
            return;
        }

        CCTV cctv = cctvs.get(idx);

        for (int[] dir : direction[cctv.id]) {
            int[][] tmpOffice = copyOffice(office);
            for (int d : dir) {
                monitor(tmpOffice, cctv.r, cctv.c, d);
            }
            dfs(idx+1, tmpOffice);
        }

    }

    static int countArea(int[][] office) {
        int cnt = 0;
        for (int[] off : office) {
            for (int zero : off) {
                if (zero == 0) cnt++;
            }
        }
        return cnt;
    }

    static void monitor(int[][] tmpOffice, int r, int c, int d) {
        int nr = r, nc = c;

        while (true) {
            nr += dr[d];
            nc += dc[d];

            if (nr>=0 && nr<N && nc>=0 && nc<M) {
                if (tmpOffice[nr][nc] == 6) break;
                else if (tmpOffice[nr][nc] == 0) tmpOffice[nr][nc] = -1;
            }
            else break;
        }
    }

    static int[][] copyOffice(int[][] office) {
        int[][] tmp = new int[N][M];

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                tmp[i][j] = office[i][j];
            }
        }
        return tmp;
    }
}