import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        int[] days = new int[len];
        
        for (int i=0; i<len; i++) {
            int day = (100-progresses[i]) / speeds[i];
            int flag = (100-progresses[i]) % speeds[i];
            days[i] = flag == 0 ? day : day+1;
        }
        
        int maxDay = Integer.MIN_VALUE;
        List<Integer> answer = new ArrayList<>();
        for (int day : days) {
            if (day > maxDay) {
                maxDay = day;
                answer.add(1);
            }
            else {
                int num = answer.get(answer.size()-1)+1;
                answer.set(answer.size()-1, num);
            }
        }
        return answer;
    }

}