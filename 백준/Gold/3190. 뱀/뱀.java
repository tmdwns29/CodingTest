import java.awt.*;
import java.util.*;

class Command {
    private int time;
    private char dir;

    Command(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }

    public int getTime() {
        return time;
    }

    public char getDir() {
        return dir;
    }
}

public class Main {
    static int N, K, L;
    static int[][] board;
    static ArrayDeque<Command> command;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        K = input.nextInt();
        board = new int[N][N];
        board[0][0] = 1;  // 뱀 시작 위치
        command = new ArrayDeque<>();

        // 사과 위치 입력
        for (int i = 0; i < K; i++) {
            int row = input.nextInt() - 1;
            int col = input.nextInt() - 1;
            board[row][col] = 2;
        }

        // 방향 변환 명령 입력
        L = input.nextInt();
        for (int i = 0; i < L; i++) {
            int X = input.nextInt();
            char C = input.next().charAt(0);
            command.add(new Command(X, C));
        }

        // 게임 실행
        System.out.println(startGame());
    }

    static int startGame() {
        Deque<Point> snake = new ArrayDeque<>();
        snake.add(new Point(0, 0));

        // 시계 방향: 상, 우, 하, 좌
        int[] moveDR = {-1, 0, 1, 0};
        int[] moveDC = {0, 1, 0, -1};

        int dir = 1; // 초기 방향 오른쪽
        int time = 0;

        while (true) {
            time++;
            Point head = snake.peekFirst();
            int nx = head.x + moveDR[dir];
            int ny = head.y + moveDC[dir];

            // 보드 범위 안이고, 자기 몸이 아닐 경우에만 진행
            if (nx>=0 && nx<N && ny>=0 && ny<N && board[nx][ny] != 1) {

                // 이동하는 칸에 사과가 있으면, 그 칸에 머리 두기
                if (board[nx][ny] == 2) {
                    board[nx][ny] = 1;
                }
                // 사과가 없으면, 그 칸에 머리 두고, 꼬리 줄이기
                else {
                    board[nx][ny] = 1;
                    Point tail = snake.pollLast();
                    board[tail.x][tail.y] = 0;
                }

                snake.addFirst(new Point(nx, ny)); // 머리 위치 갱신

                // 명령어의 방향 전환 시간이 현재 시간에 도달하면,
                if (!command.isEmpty() && time == command.peek().getTime()) {
                    char turn = command.poll().getDir();
                    if (turn == 'L') {
                        dir = (dir + 3) % 4; // 반시계 회전 (왼쪽)
                    }
                    else if (turn == 'D') {
                        dir = (dir + 1) % 4; // 시계 회전 (오른쪽)
                    }
                }
            }
            else {
                return time;
            }
        }
    }
}