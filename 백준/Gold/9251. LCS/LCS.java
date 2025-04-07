import java.util.Scanner;

class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int[][] lcs = new int[a.length+1][b.length+1];
		
		for (int i=1; i<a.length+1; i++) {
			for (int j=1; j<b.length+1; j++) {
				if (a[i-1] != b[j-1]) {
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				} else {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				}
			}
		}
		System.out.println(lcs[a.length][b.length]);
		/*
		 *   0 A C A Y K P
		 * 0 0 0 0 0 0 0 0
		 * C 0 0 1 1 1 1 1
		 * A 0 1 1 2 2 2 2
		 * P 0 1 1 2 2 2 3
		 * C 0 1 2 2 2 2 3
		 * A 0 1 2 3 3 3 3
		 * K 0 1 2 3 3 4 4
		 */
		sc.close();
	}
}