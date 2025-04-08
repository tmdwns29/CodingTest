import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = 0;
        long right = (long)((long)times[times.length-1] * (long)n);
        
        System.out.print(right);
        
        while (left <= right) {
            long mid = (left+right)/2;
            long complete = 0;
            
            // System.out.println("mid: "+mid);
            
            for (int i=0; i<times.length; i++) {
                complete += mid/times[i];
            }
            
            // System.out.println("complete: "+complete);
            
            if (complete < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
            // System.out.println(left+","+right);
            // System.out.println();
            
            /*
                left=0, right=60

                while() {
                    1)
                    mid=30

                    4=30/7 // 30분동안 4명
                    3=30/10 // 30분동안 3명
                    0  7  10  14  20  21  28  30
                    2  1   1   1   1   1   1   1

                    complete=7
                    if (7 > 6) {
                        right = 30-1;
                    }
                    
                    2)
                    mid=14
                    
                    
                } 
            */
        }
        
        return answer;
    }
}