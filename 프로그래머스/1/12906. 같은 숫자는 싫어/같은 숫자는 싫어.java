import java.util.*;

public class Solution {
    public Stack<Integer> solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int curNum : arr) {
            if (!stack.isEmpty()) {
                int prevNum = stack.peek();
                if (curNum != prevNum) {
                    
                    stack.push(curNum);
                }
            }
            else {
                stack.push(curNum);
            }
        }
        return stack;
    }
}