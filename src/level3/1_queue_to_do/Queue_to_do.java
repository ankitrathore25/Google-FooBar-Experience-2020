
/* Level 3-1 */

public class Queue_to_do {

	public static int solution(int start, int length) {
		int ans = 0;
		final long startTime = System.currentTimeMillis();
		/*
		 * Idea is to get the XOR or each row. 
		 * We also know that 4^5^6 = (1^2^3)^(1^2^3^4^5^6)
		 * 
		 * Time Complexity: O(length)
		 */

		for (int i = 0; i < length; i++) {
			ans ^= getXorOfFirstNNaturalNumbers(start + length * i - 1)
					^ getXorOfFirstNNaturalNumbers(start + length * i + length - 1 - i);
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution is: " + (endTime - startTime));
		return ans;
	}

	public static int getXorOfFirstNNaturalNumbers(int n) {
		/*
		 * there is a pattern for XORing first n natural numbers which repeats after
		 * every 4 cycles.
		 */
		if (n <= 0)
			return 0;
		int rem = n % 4;
		if (rem == 0)
			return n;
		else if (rem == 1)
			return 1;
		else if (rem == 2)
			return n + 1;
		else
			return 0;
	}

	public static int solution1(int start, int length) {
		final long startTime = System.currentTimeMillis();
		int ans = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i; j++) {
				ans ^= (start + length * i + j);
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total time for solution1 is: " + (endTime - startTime));
		return ans;
	}

	public static void main(String[] args) {
		int start = 0;
		int length = 3;
		System.out.println(solution(start, length));
		System.out.println(solution1(start, length));

		start = 17;
		length = 4;
		System.out.println(solution(start, length));
		System.out.println(solution1(start, length));

		start = 17;
		length = 3;
		System.out.println(solution(start, length));
		System.out.println(solution1(start, length));
//		System.out.println("Correct output: " + (17 ^ 18 ^ 19 ^ 20 ^ 21 ^ 23));

		start = 15;
		length = 1;
		System.out.println(solution(start, length));
		System.out.println(solution1(start, length));

		start = 0;
		length = 45000;
		System.out.println(solution(start, length));
		System.out.println(solution1(start, length));

//		int ans = 0;
//		for (int i = 0; i <= 2000000000; i++) {
//			ans ^= i;
//			System.out.println(i + ":" + ans);
//			try {
//				TimeUnit.MILLISECONDS.sleep(250);
////				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		System.out.println("Correct output: " + (0 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8 ^ 9 ^ 10 ^ 11));
	}

}
