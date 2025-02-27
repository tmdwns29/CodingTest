import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();
        String S = input.next();

        int idx = 0, cnt = 0, res = 0;
        while (idx <= M-3) {
            String tmp = "";
            for (int i=idx; i<idx+3; i++) {
                tmp += S.charAt(i);
            }
            if (tmp.equals("IOI")) {
                idx += 2;
                cnt++;
                if (cnt == N) {
                    cnt--;
                    res++;
                }
            }
            else {
                cnt = 0;
                idx++;
            }
        }
        System.out.println(res);

    }
}