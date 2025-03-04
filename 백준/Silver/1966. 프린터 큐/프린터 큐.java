import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc=0; tc<T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int max_num = Integer.MIN_VALUE;
			int cnt = 0;
			int target = -1;
			Queue<Point> queue = new ArrayDeque<>();
			
			for (int i=0; i<N; i++) {
				int n = sc.nextInt();
				queue.add(new Point(i, n));
				max_num = Math.max(max_num, n);
				if (i == M) {
					target = n;
				}
			}
			
			while (true) {
				Point p = queue.poll(); // 1
				
				if (p.y < max_num) { // 현재 가장 큰 우선순위가 존재하면
					queue.add(p); // 뒤에 배치
					continue;
				}
				else if (p.y == max_num) {
					max_num = Integer.MIN_VALUE;
					for (Point pr : queue) {
						max_num = Math.max(max_num, pr.y);
					}
					cnt++;
				}
				
				if (target == p.y && p.x == M) break;
				
			}

			System.out.println(cnt);
		}
		
		sc.close();
	}
}