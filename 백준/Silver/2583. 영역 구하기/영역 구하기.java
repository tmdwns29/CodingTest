import java.util.*;

class Main {
    static int N, M, K;
    static int[][] paper;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int BFS(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {i, j});
        int area = 1;

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int r = p[0], c = p[1];

            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr>=0 && nr<N && nc>=0 && nc<M && paper[nr][nc] == 0) {
                    queue.add(new int[] {nr, nc});
                    area++;
                    paper[nr][nc] = 1;
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        paper = new int[N][M];
        ArrayList<Integer> areas = new ArrayList<>();

        for (int k=0; k<K; k++) {
            int y1 = sc.nextInt();
            int x1 = sc.nextInt();
            int y2 = sc.nextInt();
            int x2 = sc.nextInt();

            for (int y=y1; y<y2; y++) {
                for (int x=x1; x<x2; x++) {
                    paper[x][y] = 1;
                }
            }
        }
        int cnt = 0;
        for (int y=0; y<N; y++) {
            for (int x=0; x<M; x++) {
                if (paper[y][x] == 0) {
                    paper[y][x] = 1;
                    areas.add(BFS(y, x));
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        Collections.sort(areas);
        for (int n: areas) System.out.print(n + " ");
    }
}