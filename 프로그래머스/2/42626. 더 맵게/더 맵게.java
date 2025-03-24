import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        
        for (int sc: scoville) pq.add(sc);
        
        while (pq.size() >= 2) {
            int lv1 = pq.poll();
            if (lv1 >= K) {
                pq.add(lv1);
                break;
            }
            int lv2 = pq.poll();
            int shakeLv = lv1 + (lv2*2);
            pq.add(shakeLv);
            cnt++;
        }

        return pq.peek() >= K ? cnt : -1;
    }
}