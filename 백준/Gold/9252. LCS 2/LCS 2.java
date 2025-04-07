import java.util.Scanner;

class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();

		int[][] lcs = new int[b.length+1][a.length+1];
		
		for (int i=1; i<b.length+1; i++) {
			for (int j=1; j<a.length+1; j++) {
				if (a[j-1] != b[i-1]) {
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				} else {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				}
			}
		}
		int i=b.length, j=a.length;
		String str = "";
		
		while (lcs[i][j] != 0) {
			if (lcs[i][j] == lcs[i][j-1]) {
				j--;
			} else if (lcs[i][j] == lcs[i-1][j]) {
				i--;
			} else {
				str += b[i-1];
				i--; j--;
			}
		}
		System.out.println(lcs[b.length][a.length]);
		for (int s=str.length()-1; s>=0; s--) System.out.print(str.charAt(s));
		System.out.println();
		
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