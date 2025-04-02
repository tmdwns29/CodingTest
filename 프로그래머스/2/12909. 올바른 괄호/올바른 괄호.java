import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == '(') {
                stack.push('(');
            }
            else {
                if (i == 0) return false;
                char c = ' ';
                if (!stack.isEmpty()) {
                    c = stack.pop();
                }
                if (c == '(') continue;
                if (c == ' ') return false;
                
            }
        }

        return stack.isEmpty();
    }
}