import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> que1 = new ArrayDeque<>();
        Queue<Long> que2 = new ArrayDeque<>();
        long sum1=0, sum2=0;
        int answer=0;
        for (long q1 : queue1) {
            sum1 += q1;
            que1.add(q1);
        }
        for (long q2 : queue2) {
            sum2 += q2;
            que2.add(q2);
        }
        while (sum1 != sum2 && answer <= 300_000) {
            if (sum1 > sum2) {
                long c = que1.poll();
                que2.add(c);
                sum1 -= c;
                sum2 += c;
            } else if (sum1 < sum2) {
                long c = que2.poll();
                que1.add(c);
                sum1 += c;
                sum2 -= c;
            }
            answer++;
        }
        return sum1 != sum2 ? -1 : answer;
    }
}