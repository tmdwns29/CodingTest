import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        for (int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while (pq.size() >= 2) {
            int lv1 = pq.poll();
            if (lv1 >= K) {
                pq.add(lv1);
                break;
            }
            int lv2 = pq.poll();
            int shakeLv = lv1 + (lv2*2);
            cnt++;
            pq.add(shakeLv);
        }

        return pq.peek() >= K ? cnt : -1;
    }
}