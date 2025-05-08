import java.util.*;

class Solution {
    static int cnt;
    static int[][] answer;
    static class Node {
        int num, x, y;
        Node left, right;
        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        
        for (int i=0; i<nodeinfo.length; i++) {
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodes, (a, b) -> {            
            return a.y == b.y ? a.x-b.x : b.y-a.y;
        });
        // Arrays.sort(nodes, (a, b) -> {
        //     return b.y - a.y;
        // });
        
        Node root = nodes[0];
        for (int i=1; i<nodes.length; i++) {
            update(root, nodes[i]);
        }
        preorder(root);
        cnt = 0;
        postorder(root);
        
        return answer;
        /*
           1      2      3     4     5     6     7     8     9
        [[5,3],[11,5],[13,3],[3,5],[6,1],[1,3],[8,6],[7,2],[2,2]]
        7 | 4 2 | 6 1 3 | 9 8 | 5
        */
    }
    
    static void preorder(Node node) {
        answer[0][cnt++] = node.num;
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }
    
    static void postorder(Node node) {
        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        answer[1][cnt++] = node.num;
    }
    
    static void update(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else update(parent.left, child);
        }
        else {
            if (parent.right == null) parent.right = child;
            else update(parent.right, child);
        }
    }
}