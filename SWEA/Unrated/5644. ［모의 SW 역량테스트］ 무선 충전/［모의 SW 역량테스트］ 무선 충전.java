import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    static int M, A;
    static int[] userA, userB;
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static class BC {
        int r, c, C, P;

        BC(int r, int c, int C, int P) {
            this.r = r;
            this.c = c;
            this.C = C;
            this.P = P;
        }

        boolean check(int userY, int userX) {
            return Math.abs(this.r - userX) + Math.abs(this.c - userY) <= C;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            M = input.nextInt();
            A = input.nextInt();
            userA = new int[M];
            userB = new int[M];
            List<BC> BCs = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                userA[i] = input.nextInt();
            }
            for (int i = 0; i < M; i++) {
                userB[i] = input.nextInt();
            }

            for (int i = 0; i < A; i++) {
                int x = input.nextInt() - 1;
                int y = input.nextInt() - 1;
                int C = input.nextInt();
                int P = input.nextInt();

                BC bc = new BC(y, x, C, P);
                BCs.add(bc);
            }
            int xA = 0, yA = 0;
            int xB = 9, yB = 9;
            int totalCharge = 0;

            for (int time = 0; time <= M; time++) {
                List<BC> A_BC = findBC(xA, yA, BCs);
                List<BC> B_BC = findBC(xB, yB, BCs);

                totalCharge += maxCharge(A_BC, B_BC);

                if (time == M) break;

                xA += dr[userA[time]];
                yA += dc[userA[time]];
                xB += dr[userB[time]];
                yB += dc[userB[time]];
            }
            System.out.printf("#%d %d\n", tc, totalCharge);
        }
    }

    static List<BC> findBC(int x, int y, List<BC> bcList) {
        List<BC> possBCs = new ArrayList<>();
        for (BC bc : bcList) {
            if (bc.check(y, x)) {
                possBCs.add(bc);
            }
        }
        return possBCs;
    }

    static int maxCharge(List<BC> A_BC, List<BC> B_BC) {
        int maxCharge = 0;

        if (A_BC.isEmpty() && B_BC.isEmpty()) return 0;

        if (B_BC.isEmpty()) {
            for (BC a : A_BC) {
                maxCharge = Math.max(maxCharge, a.P);
            }
            return maxCharge;
        }

        if (A_BC.isEmpty()) {
            for (BC b : B_BC) {
                maxCharge = Math.max(maxCharge, b.P);
            }
            return maxCharge;
        }

        for (BC a : A_BC) {
            for (BC b : B_BC) {
                if (a.r == b.r && a.c == b.c) {
                    maxCharge = Math.max(maxCharge, a.P / 2 + b.P / 2);
                } else {
                    maxCharge = Math.max(maxCharge, a.P + b.P);
                }
            }
        }
        return maxCharge;
    }
}
