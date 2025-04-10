import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N,W,H, answer;
    static int[][] map, temp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Block {
        int r, c, num;
        public Block(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            temp = new int[H][W];
            answer = Integer.MAX_VALUE;

            for (int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<W; j++) {
                    map[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, new int[N]);
            System.out.printf("#%d %d\n", tc, answer);

        }
    }

    private static void dfs(int cnt, int[] sel) {
        if (cnt == N) {
            dropBall(sel);
            answer = Math.min(answer, cntBlock());
            reset();
            return;
        }
        for (int i=0; i<W; i++) {
            sel[cnt] = i;
            dfs(cnt+1, sel);
        }
    }

    private static void reset() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static int cntBlock() {
        int cnt = 0;
        for (int[] ma : map) {
            for (int m : ma) {
                if (m != 0) cnt++;
            }
        }
        return cnt;
    }

    private static void dropBall(int[] sel) {
        int r = 0, c = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<H; j++) {
                int col = sel[i];
                if (map[j][col] != 0) {
                    r = j;
                    c = sel[i];
                    break;
                }
            }
            bfs(r, c);
            dropBlock();
        }
    }

    private static void dropBlock() {
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<W; i++) {
            for (int j=0; j<H; j++) {
                if (map[j][i] != 0) {
                    stack.push(map[j][i]);
                }
            }
            for (int j=H-1; j>=0; j--) {
                if (stack.isEmpty()) {
                    map[j][i] = 0;
                } else {
                    map[j][i] = stack.pop();
                }
            }
        }
    }

    private static void bfs(int r, int c) {
        Queue<Block> queue = new ArrayDeque<>();
        queue.add(new Block(r, c, map[r][c]));
        map[r][c] = 0;

        while (!queue.isEmpty()) {
            Block b = queue.poll();

            for (int s=1; s<b.num; s++) {
                for (int d=0; d<4; d++) {
                    int nr = b.r + dr[d] * s;
                    int nc = b.c + dc[d] * s;

                    if (nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc] != 0) {
                        queue.add(new Block(nr, nc, map[nr][nc]));
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

}