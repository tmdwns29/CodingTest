import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            List<Point> homes = new ArrayList<>();
            int answer = Integer.MIN_VALUE;

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        homes.add(new Point(i, j));
                    }
                }
            }

            for (int k=1; k<N+2; k++) {
                int cost = k*k+(k-1)*(k-1);
                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        int home_cnt = 0;
                        for (int h=0; h<homes.size(); h++) {
                            Point p = homes.get(h);
                            if (Math.abs(p.x-i) + Math.abs(p.y-j) < k) {
                                home_cnt++;
                            }
                        }
                        if (home_cnt * M >= cost) {
                            answer = Math.max(answer, home_cnt);
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}