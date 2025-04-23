import java.util.*;

class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, n, computers);
                answer++;
            }
        }
        return answer;
    }
    static void bfs(int x, int n, int[][] computers) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        visited[x] = true;
        
        while (!queue.isEmpty()) {
            int c = queue.poll();
            
            for (int i=0; i<n; i++) {
                if (i!=c && computers[c][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}