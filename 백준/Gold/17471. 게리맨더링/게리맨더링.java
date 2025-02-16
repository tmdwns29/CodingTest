import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int N, min_diff = Integer.MAX_VALUE;
    static int[] pop;
    static int[][] adj;

    static void dfs(ArrayList<Integer> area, int idx, boolean[] visited) {
        visited[idx] = true;

        for (int i=0; i<area.size(); i++) {
            if (adj[idx][area.get(i)] == 1 && !visited[area.get(i)]) {
                dfs(area, area.get(i), visited);
            }
        }
    }

    static boolean check(boolean[] sel) {
        ArrayList<Integer> areaA = new ArrayList<>();
        ArrayList<Integer> areaB = new ArrayList<>();

        for (int i=1; i<=N; i++) {
            if (sel[i]) areaA.add(i);
            else areaB.add(i);
        }

        // 선거구에 구역이 하나라도 없으면 불가능
        if (areaA.isEmpty() || areaB.isEmpty()) return false;

        // 각 선거구에 있는 구역이 서로 연결되어 있는지
        boolean[] v = new boolean[N+1];
        dfs(areaA, areaA.get(0), v);
        dfs(areaB, areaB.get(0), v);

        for (int i=1; i<=N; i++) {
            if (!v[i]) return false;
        }

        return true;
    }

    static void power_set(int idx, boolean[] sel) {
        if (idx == N+1) {
            if (check(sel)) {
                int sumA = 0, sumB = 0;
                for (int i=1; i<=N; i++) {
                    if (sel[i]) sumA += pop[i];
                    else sumB += pop[i];
                }
                min_diff = Math.min(Math.abs(sumA-sumB), min_diff);
            }
            return;
        }
        sel[idx] = true;
        power_set(idx+1, sel);
        sel[idx] = false;
        power_set(idx+1, sel);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        pop = new int[N+1];
        adj = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            pop[i] = input.nextInt();
        }

        for (int i=1; i<=N; i++) {
            int adj_num = input.nextInt();
            for (int j = 0; j < adj_num; j++) {
                int m = input.nextInt();
                adj[i][m] = 1;
                adj[m][i] = 1;
            }
        }
        power_set(1, new boolean[N+1]);

        System.out.println(min_diff == Integer.MAX_VALUE ? -1 : min_diff);
    }
}