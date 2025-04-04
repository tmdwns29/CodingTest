import java.util.*;

class Solution {
    static int[] visited;
    static boolean[][] adjList;
    
    public int solution(int n, int[][] edge) {

        int m = edge.length;
        adjList = new boolean[n+1][n+1];
        visited = new int[n+1];
        
        for (int i=0; i<m; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            adjList[a][b] = true;
            adjList[b][a] = true;
        }
        return bfs(1);
    }
    
    static int bfs(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        visited[n] = 1;
        int maxNum = Integer.MIN_VALUE;
        int cnt = 0;
        
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (int i=1; i<adjList.length; i++) {
                if (adjList[c][i] && visited[i] == 0) {
                    queue.add(i);
                    visited[i] = visited[c] + 1;
                    maxNum = Math.max(visited[i], maxNum);
                    
                }
            }
        }
        for (int v : visited) if (maxNum == v) cnt++;
        return cnt;
    }
}