package practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*Level 3-2*/

public class Test_Time_Comparision {

	public static int myMethod1(int[] l) {
		final long startTime = System.currentTimeMillis();
		int len = l.length;
		int[][] isYDivisibleByX = new int[len][len];
		int[] count = new int[len];
		count[0] = 0;
		for (int j = 1; j < len; j++) {
			int c = 0;
			for (int i = 0; i < j; i++) {
				if (isYDivisibleByX[i][j] == 0) {
					isYDivisibleByX[i][j] = (l[j] % l[i] == 0 ? 1 : -1);
				}
				if (isYDivisibleByX[i][j] == 1)
					c++;
			}
			count[j] = c;
		}

		int totalCount = 0;
		for (int i = 2; i < len; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (isYDivisibleByX[i][j] == 0) {
					isYDivisibleByX[i][j] = (l[j] % l[i] == 0 ? 1 : -1);
				}
				if (isYDivisibleByX[j][i] == 1)
					totalCount += count[j];
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return totalCount;
	}

	public static int myMethod2(int[] l) {
		final long startTime = System.currentTimeMillis();
		int len = l.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (l[j] % l[i] == 0) {
					for (int k = j + 1; k < len; k++) {
						if (l[k] % l[j] == 0)
							count++;
					}
				}
			}
		}

		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return count;
	}

	public static int myMethod3(int[] l) {
		final long startTime = System.currentTimeMillis();
		int len = l.length;
		int numOfTriplets = 0;
		int[] count = new int[len];
		count[0] = 0;
		for (int j = 1; j < len; j++) {
			for (int i = 0; i < j; i++) {
				if (l[j] % l[i] == 0) {
					count[j]++;
					numOfTriplets += count[i];
				}
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return numOfTriplets;
	}

	public static int solution(int[] l, int method) {
		final long startTime = System.currentTimeMillis();
		int len = l.length;
		boolean[][] isYDivisibleByX = (method == 1 ? findDivisibilityMatrix1(l, len) : findDivisibilityMatrix2(l, len));
		int[] count = new int[len];
		count[0] = 0;
		for (int j = 1; j < len; j++) {
			int c = 0;
			for (int i = 0; i < j; i++) {
				if (isYDivisibleByX[i][j])
					c++;
			}
			count[j] = c;
		}

		int totalCount = 0;
		for (int i = 2; i < len; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (isYDivisibleByX[j][i])
					totalCount += count[j];
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return totalCount;
	}

	public static boolean[][] findDivisibilityMatrix1(int[] l, int len) {
		boolean[][] isYDivisibleByX = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (i == j || l[j] % l[i] == 0)
					isYDivisibleByX[i][j] = true;
				else
					isYDivisibleByX[i][j] = false;
			}
		}
		return isYDivisibleByX;
	}

	public static boolean[][] findDivisibilityMatrix2(int[] l, int len) {
		boolean[][] isYDivisibleByX = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				int t = l[j] / l[i];
				if (i == j || t * l[i] == l[j])
					isYDivisibleByX[i][j] = true;
				else
					isYDivisibleByX[i][j] = false;
			}
		}
		return isYDivisibleByX;
	}

//	public static Set<Integer> getAllSmallerNumbersWhichDivideGivenN(int n) {
//
//	}

	public static int answer(int[] l) {
		final long startTime = System.currentTimeMillis();
		Set<List<Integer>> log = new HashSet<>();
		for (int i1 = 0; i1 < l.length; i1++) {
			for (int i2 = i1 + 1; i2 < l.length; i2++) {
				if (l[i2] % l[i1] != 0) {
					continue;
				}

				for (int i3 = i2 + 1; i3 < l.length; i3++) {
					if ((l[i3] % l[i2] == 0)) {
						log.add(Arrays.asList(l[i1], l[i2], l[i3]));
					}
				}
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return log.size();
	}

	public static void main(String[] args) {
		int[] l = new int[] { 1, 1, 1 };
		System.out.println(myMethod1(l));
		System.out.println(myMethod2(l));
		System.out.println(myMethod3(l));
		System.out.println(solution(l, 1));
		System.out.println(solution(l, 2));
		System.out.println(answer(l));
		int[] l1 = new int[] { 1, 2, 3, 4, 5, 6 };
		System.out.println(myMethod1(l1));
		System.out.println(myMethod2(l1));
		System.out.println(myMethod3(l1));
		System.out.println(solution(l1, 1));
		System.out.println(solution(l1, 2));
		System.out.println(answer(l1));
		int[] l2 = randomNumberGenerator.generateRandomNumber();
		System.out.println();
		System.out.println("Method1:" + myMethod1(l2));
		System.out.println("Method2:" + myMethod2(l2));
		System.out.println("Method3:" + myMethod3(l2));
		System.out.println("Method4:" + solution(l2, 2));
		System.out.println("Method5:" + solution(l2, 2));
		System.out.println(answer(l2));

	}

}
