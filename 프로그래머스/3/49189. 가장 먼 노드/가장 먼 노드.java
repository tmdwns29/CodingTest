import java.util.*;

class Solution {
    static int[] visited;
    static ArrayList<Integer>[] adjList;
    
    public int solution(int n, int[][] edge) {

        int m = edge.length;
        adjList = new ArrayList[n+1];
        visited = new int[n+1];
        
        for (int i=1; i<n+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int i=0; i<m; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            adjList[a].add(b);
            adjList[b].add(a);
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
            for (int i=0; i<adjList[c].size(); i++) {
                int num = adjList[c].get(i);
                if (visited[num] == 0) {
                    queue.add(num);
                    visited[num] = visited[c] + 1;
                    maxNum = Math.max(visited[num], maxNum);
                    
                }
            }
        }
        for (int v : visited) if (maxNum == v) cnt++;
        return cnt;
    }
}