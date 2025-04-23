import java.util.*;

class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 0;
        int right = distance;
        
        Arrays.sort(rocks);
        
        while (left<=right) {
            int mid = (left+right)/2;
            if (removeRockCnt(mid, rocks, distance)<=n) {
                // 2 11 (12) 14 17 21
                left = mid+1;
                answer = mid;
            } else {
                right = mid-1;
            }
        }
        return answer;
    }
    static int removeRockCnt(int mid, int[] rocks, int distance) {
        int remove = 0;
        int before = 0;
        int end = distance;
        
        for (int i=0; i<rocks.length; i++) {
            if (rocks[i] - before < mid) {
                remove++;
            } else {
                before = rocks[i];
            }
        }
        if (end - before < mid) {
            remove++;
        }
        return remove;
    }

}