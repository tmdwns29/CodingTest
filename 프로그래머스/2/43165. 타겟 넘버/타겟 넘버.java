class Solution {
    static boolean[] op;
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        op = new boolean[numbers.length];
        dfs(0, numbers, target);
        
        return answer;
    }
    
    static void dfs(int cnt, int[] numbers, int target) {
        if (cnt == numbers.length) {
            int sum = 0;
            for (int i=0; i<numbers.length; i++) {
                if (op[i]) sum += numbers[i];
                else sum += -numbers[i];
            }
            if (sum == target) answer++;
            return;
        }
        op[cnt] = true;
        dfs(cnt+1, numbers, target);
        op[cnt] = false;
        dfs(cnt+1, numbers, target);
    }
}