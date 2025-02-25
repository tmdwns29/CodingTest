import java.util.Arrays;
import java.util.Scanner;

// 1~n까지의 수 중 r개를 선택하여 순열
public class Main {
    static int N, K;
    static int[][] belt;

    static void rotate() {
        int lastItem[] = belt[2*N-1];
        for (int i=2*N-1; i>=1; i--) belt[i] = belt[i-1];
        belt[0] = lastItem;
    }

    static void move() {
        for (int i=2*N-1; i>=0; i--) {
            if (i == N-1 && belt[i][1] == 1) {
                belt[i][1] = 0;
            }

            if (belt[i][1] == 1) { // 로봇이 존재
                // 그 다음 칸 내구성이 1이상이고, 그 다음 칸 로봇이 없으면
                if (belt[(i + 1) % (2 * N)][0] >= 1 && belt[(i + 1) % (2 * N)][1] == 0) {
                    belt[i][1] = 0; // 이동 전의 칸 로봇 상태 없애기
                    belt[(i + 1) % (2 * N)][1] = 1; // 이동한 칸에 로봇 상태 추가
                    belt[(i + 1) % (2 * N)][0]--; // 내구성 1감소
                }
            }
        }
    }

    static int calc_durability() {
        int cnt = 0;
        for (int[] b : belt) {
            if (b[0] == 0) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        belt = new int[2*N][2];

        for (int i=0; i<2*N; i++) {
            belt[i][0] = sc.nextInt();
        }

        int step = 0;
        do {
            // 1. 회전
            rotate();

            // 2. 로봇 이동
            move();

            // 3. 로봇 상차
            // 올리는 위치에 로봇이 없고, 내구도가 1이상이면
            if (belt[0][0] != 0) {
                belt[0][0]--;
                belt[0][1] = 1;
            }

            // 4. 로봇 하차
            if (belt[N-1][1] == 1) {
                belt[N-1][1] = 0;
            }
            step++;
        } while(calc_durability() < K);

        System.out.println(step);

        sc.close();
    }
}