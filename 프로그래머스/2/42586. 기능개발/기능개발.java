import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        int[] days = new int[progresses.length];
        
        int cnt = 1;
        while (!check(progresses)) {
            cnt++;
            for (int i=0; i<progresses.length; i++) {
                progresses[i] += speeds[i];
                if (progresses[i] >= 100) {
                    continue;
                }
                days[i] = cnt;
            }
        }
        int maxDay = Integer.MIN_VALUE;
        List<Integer> arr = new ArrayList<>();
        int idx = 0;
        for (int day : days) {
            if (day > maxDay) {
                maxDay = day;
                arr.add(1);
            }
            else {
                int num = arr.get(idx);
                arr.set(idx, ++num);
            }
            idx = arr.size() - 1;
            
        }
        System.out.println(arr.toString());
        return arr;
    }
    
    static boolean check(int[] progresses) {
        for (int p : progresses) {
            if (p < 100) {
                return false;
            }
        }
        return true;
    }
}