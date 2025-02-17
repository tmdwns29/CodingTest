import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static int N;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] mexynos, temp;
    static ArrayList<int[]> cores;
    static int minWire, maxCore;

    static void dfs(int idx, int wireLength, int coreCount) {
        if (idx == cores.size()) {
            // 최대 코어개수가 존재할 때, 최소 전선 개수도 함께 갱신
            if (coreCount > maxCore) {
                maxCore = coreCount;
                minWire = wireLength;
            }
            // 최대 코어 개수는 동일하고, 더 적은 전선개수 존재 할 경우 갱신.
            else if (coreCount == maxCore && minWire > wireLength) {
                minWire = wireLength;
            }
            return;
        }

        int[] p = cores.get(idx);

        for (int d=0; d<4; d++) {
            int count = 0;
            int nx = p[0];
            int ny = p[1];

            while (true) {
                nx += dr[d];
                ny += dc[d];

                if (nx>=0 && nx<N && ny>=0 && ny<N) {
                    if (mexynos[nx][ny] == 1) { // 다른 코어 존재
                        count = 0;
                        break;
                    }
                    count++;
                }
                else {
                    break;
                }
            }
            // 현재 코어에서 count만큼 전선 채우기
            int cur_coreX = p[0];
            int cur_coreY = p[1];

            for (int i=0; i<count; i++) {
                cur_coreX += dr[d];
                cur_coreY += dc[d];
                mexynos[cur_coreX][cur_coreY] = 1;
            }

            if (count == 0) dfs(idx+1, wireLength, coreCount);
            else {
                dfs(idx+1, wireLength + count, coreCount + 1);

                cur_coreX = p[0];
                cur_coreY = p[1];

                for (int i=0; i<count; i++) {
                    cur_coreX += dr[d];
                    cur_coreY += dc[d];
                    mexynos[cur_coreX][cur_coreY] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int tc=1; tc<=T; tc++) {
            N = input.nextInt();
            mexynos = new int[N][N];
            temp = new int[N][N];
            cores = new ArrayList<>();

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    mexynos[i][j] = input.nextInt();
                    if(i!=0 && i!=N-1 && j!=0 && j!=N-1 && mexynos[i][j] == 1) {
                        cores.add(new int[] {i, j});
                    }
                }
            }
            minWire = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            dfs(0, 0, 0);

            System.out.printf("#%d %d\n", tc, minWire);
        }

    }
}