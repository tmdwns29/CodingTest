import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        int len = completion.length;
        
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for (int i=0; i<len; i++) {
            if (!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        // "ana", "mislav", "mislav", "stanko"
        // "ana", "mislav", "stanko"
        return participant[len];
    }
}