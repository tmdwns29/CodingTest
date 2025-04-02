import java.util.*;

class Solution {
    static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] maps) {
        int answer;
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int r = p.x, c = p.y;
            
            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr>=0 && nr<N && nc>=0 && nc<M && maps[nr][nc] == 1 && !visited[nr][nc]) {
                    queue.add(new Point(nr, nc));
                    maps[nr][nc] = maps[r][c] + 1;
                    visited[nr][nc] = true;
                }
            }
        }
        // for (int[] s : maps) {
        //     for (int n : s) {
        //         System.out.print(n+" ");
        //     }
        //     System.out.println();
        // }
        
        answer = maps[N-1][M-1] == 1 ? -1 : maps[N-1][M-1];
        return answer;
    }
    
}