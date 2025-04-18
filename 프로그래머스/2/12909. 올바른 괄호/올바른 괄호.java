import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int cnt = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else {
                if (cnt == 0) return false;
                cnt--;
            }
        }
        return cnt == 0;
    }
}