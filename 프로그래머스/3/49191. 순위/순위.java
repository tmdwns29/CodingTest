import java.util.*;

class Solution {
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] players = new int[n+1][n+1];
        
        for (int i=0; i<results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            players[a][b] = 1;
        }
        
        for (int i=1; i<n+1; i++) {
            if (bfs(i, n, players) == n-1) {
                answer++;
            }
        }
        return answer;
    }
    
    static int bfs(int x, int n, int[][] players) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] v = new boolean[n+1];
        int cnt = 0;
        queue.add(x);
        v[x] = true;
        
        while (!queue.isEmpty()) {
            int c = queue.poll();
            
            for (int i=1; i<n+1; i++) {
                if (players[c][i] == 1 && !v[i]) {
                    queue.add(i);
                    v[i] = true;
                    cnt++;
                }
            }
        }
        
        v = new boolean[n+1];
        queue.add(x);
        v[x] = true;
        
        while (!queue.isEmpty()) {
            int c = queue.poll();
            
            for (int i=1; i<n+1; i++) {
                if (players[i][c] == 1 && !v[i]) {
                    queue.add(i);
                    v[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}