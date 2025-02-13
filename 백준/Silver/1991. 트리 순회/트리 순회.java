import java.util.Scanner;

class Main {
    static char[][] tree;
    static void preorder(char node) {
        System.out.printf("%c", node);
        if (tree[node-'A'+1][0] != '.') {
            preorder(tree[node-'A'+1][0]);
        }
        if (tree[node-'A'+1][1] != '.') {
            preorder(tree[node-'A'+1][1]);
        }
    }

    static void inorder(char node) {
        if (tree[node-'A'+1][0] != '.') {
            inorder(tree[node-'A'+1][0]);
        }
        System.out.printf("%c", node);
        if (tree[node-'A'+1][1] != '.') {
            inorder(tree[node-'A'+1][1]);
        }
    }

    static void postorder(char node) {
        if (tree[node-'A'+1][0] != '.') {
            postorder(tree[node-'A'+1][0]);
        }
        if (tree[node-'A'+1][1] != '.') {
            postorder(tree[node-'A'+1][1]);
        }
        System.out.printf("%c", node);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        tree = new char[N+1][2];

        for (int i=1; i<N+1; i++) {
            char node = input.next().charAt(0);
            char left = input.next().charAt(0);
            char right = input.next().charAt(0);

            tree[node-'A'+1][0] = left;
            tree[node-'A'+1][1] = right;
        }
        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');
    }
}