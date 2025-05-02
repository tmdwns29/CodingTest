import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        Queue<Long> que1 = new ArrayDeque<>();
        Queue<Long> que2 = new ArrayDeque<>();
        long sum1=0, sum2=0;
        int cnt=0;
        
        for (long q1 : queue1) {
            sum1 += q1;
            que1.add(q1);
        }
        for (long q2 : queue2) {
            sum2 += q2;
            que2.add(q2);
        }
        while (sum1 != sum2 && cnt <= 600_000) {
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
            cnt++;
        }
        
        
        
        /*
        3, 2, 7, 2 = 14
        4, 6, 5, 1 = 16
        
        3, 2, 7, 2, 4 = 18
        6, 5, 1 = 12
        
        2, 7, 2, 4 = 15
        6, 5, 1, 3 = 15
        */
        
        answer = sum1 != sum2 ? -1 : cnt;
        
        return answer;
    }
}