import java.util.*;

class Main {
    static int N, M, D, max_enemy = Integer.MIN_VALUE;
    static int[] archer;
    static int[][] board, temp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        board = new int[N+1][M]; // 성을 포함한 배열
        temp = new int[N+1][M];
        archer = new int[3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 궁수 3명 배치
        set_archer(0, 0);

        System.out.println(max_enemy);
    }

    // 임시 배열 <- 원본배열 복사
    static void reset_board() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = board[i][j];
            }
        }
    }

    // 궁수 3명 배치(조합)
    static void set_archer(int idx, int start) {
        if (idx == 3) {
            reset_board();
            max_enemy = Math.max(start_game(), max_enemy);
            return;
        }

        for (int i = start; i < M; i++) {
            archer[idx] = i;
            set_archer(idx + 1, i + 1);
        }
    }

    // 게임 시작
    static int start_game() {
        int ans = 0;

        // 배열에 1(적)이 없을 때까지
        while (check_temp()) {
            Set<int[]> attackSet = new HashSet<>(); // 여러 궁수로부터 같은 적이 공격받을 경우 중복방지

            for (int a : archer) {
                ArrayList<int[]> targets = new ArrayList<>(); // 적의 위치 저장

                // 적 탐색(성 바로 윗부분부터)
                for (int i = N - 1; i >= 0; i--) {
                    for (int j = 0; j < M; j++) {
                        if (temp[i][j] == 1) {
                            int dist = Math.abs(N - i) + Math.abs(a - j);
                            if (dist <= D) { // 거리 D이하일 시,
                                targets.add(new int[]{dist, j, i}); // 적의 정보 (거리, 열, 행) 순으로 저장
                            }
                        }
                    }
                }
                // 공격할 적이 있으면,
                if (!targets.isEmpty()) {
                    // 거리순 → 왼쪽 순으로 정렬
                    targets.sort((o1, o2) -> { // 거리와 열을 정렬 기준으로 재선정
                        if (o1[0] != o2[0]) { // 거리 비교 시 짧은 것부터
                            return Integer.compare(o1[0], o2[0]); // 거리 짧은 순
                        }
                        return Integer.compare(o1[1], o2[1]); // 거리가 같으면 가장 왼쪽 거부터
                    });

                    // 가장 적절한 적 공격 목록에 추가 (좌표를 문자열로 변환하여 Set 사용)
                    int[] finalTarget = targets.get(0);
                    attackSet.add(new int[] {finalTarget[2], finalTarget[1]}); // (행, 열)
                }
            }

            // 공격 후 적 제거
            ans += attack_enemy(attackSet);

            // 적 이동
            move_enemy();
        }
        return ans;
    }
    
    // 적 공격
    static int attack_enemy(Set<int[]> attackSet) {
        int cnt = 0;
        for (int[] enemy : attackSet) {
            int x = enemy[0];
            int y = enemy[1];
            if (temp[x][y] == 1) {
                temp[x][y] = 0;
                cnt++;
            }
        }
        return cnt;
    }

    // 적 이동
    static void move_enemy() {
        for (int i = N - 1; i > 0; i--) {
            temp[i] = Arrays.copyOf(temp[i - 1], M); // 위쪽 행을 아래로 복사
        }
        Arrays.fill(temp[0], 0); // 첫 번째 행(적들이 생성되는 곳)은 항상 0
    }
    
    // 게임 종료 여부 판단
    static boolean check_temp() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 1) return true;
            }
        }
        return false;
    }
}
