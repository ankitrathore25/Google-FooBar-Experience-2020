package practice;

import java.util.HashMap;
import java.util.Map;

/*Level 3-3*/

public class Fuel_Injection_Perfection {

	public static int solution(String x) {
		/* call to the helper function */

//		return memoization(x, new HashMap<String, Integer>());
		return recursiveHelperMethod(x);
	}

	public static String increaseXBy1(String x) {

		/* helper function to increase the number x by 1 */

		StringBuilder ans = new StringBuilder();
		for (int i = x.length() - 1; i >= 0; i--) {
			if (x.charAt(i) == '9') {
				ans.append(0);
			} else {
				ans.append(x.charAt(i) - '0' + 1);
				StringBuilder sb = new StringBuilder();
				sb.append(x.substring(0, i)).append(ans.reverse());
				return sb.toString();
			}
		}
		ans.append(1);
		return ans.reverse().toString();
	}

	public static String decreaseXBy1(String x) {

		/* helper function to decrease the number x by 1 */

		if (x.length() == 1)
			return String.valueOf(x.charAt(0) - '0' - 1);
		StringBuilder ans = new StringBuilder();
		for (int i = x.length() - 1; i >= 0; i--) {
			if (x.charAt(i) == '0') {
				ans.append(9);
			} else {
				int newInt = x.charAt(i) - '0' - 1;
				if (i == 0 && x.charAt(0) == '1' && newInt == 0) {
					return ans.toString();
				} else {
					ans.append(newInt);
					StringBuilder sb = new StringBuilder();
					sb.append(x.substring(0, i)).append(ans.reverse());
					return sb.toString();
				}
			}
		}
		return "0";
	}

	public static String divideXBy2(String x) {

		/* helper function to divide the number x by 2 */

		if (x.length() == 1)
			return String.valueOf(Integer.valueOf(x) / 2);
		StringBuilder ans = new StringBuilder();
		int num = x.charAt(0) - '0';
		for (int i = 0; i < x.length(); i++) {
			if (num % 2 == 0) {
				ans.append(String.valueOf(num / 2));
				if (i < x.length() - 1)
					num = x.charAt(i + 1) - '0';
			} else {
				ans.append(String.valueOf(num / 2));
				num = 10 + x.charAt(i + 1) - '0';

			}
		}
		int j = 0;
		while (j < ans.length() && ans.charAt(j) == '0')
			j++;
		ans.replace(0, j, "");
		return ans.toString();
	}

	public static int modXBy2(String x) {

		/* helper function to find modulo of number x by 2 */
		return ((x.charAt(x.length() - 1) - '0') % 2 == 0) ? 0 : 1;
	}

	public static int modXBy4(String x) {

		/* helper function to find modulo of the number x by 4 */
		if (x.isEmpty())
			return 0;

		if (x.length() < 2) {
			int n = Integer.valueOf(x);
			return n - (n / 4) * 4;
		}
		/* return the modulo of x by 4 */
		int n2 = Integer.valueOf(x.substring(x.length() - 2));
		return n2 - (n2 / 4) * 4;
	}

	public static int recursiveHelperMethod(String x) {
		/*
		 * Time: O(n) Faster than memoization.
		 */

		/* If number is less than and equal to 1 then return 0 */
		if (x.length() == 1 && x.charAt(0) - '0' <= 1) {
			return 0;
		}
		/* If number x is divisible by 2 then recursively call on x/2 */
		if (modXBy2(x) == 0) {
			return recursiveHelperMethod(divideXBy2(x)) + 1;
		} else if (x.equals("3")) {
			/*
			 * special case: if number is 3 then its remainder will be 3 if divided by 4,
			 * but we will not increase the number rather decrease to 2 which eventually
			 * give minimum.
			 */
			return recursiveHelperMethod(decreaseXBy1(x)) + 1;
		} else {
			/*
			 * we find the remainder after dividing number x by 4. If remainder is 1 the we
			 * want to move closer to the multiple of 4 and we will do so by reducing the
			 * number by 1. If remainder is 3 then we will increase number x by 1 to reach
			 * the multiple of 4. Value of remainder will be only 1 or 3.
			 */
			int rem = modXBy4(x);
			if (rem == 1) {
				return recursiveHelperMethod(decreaseXBy1(x)) + 1;
			} else {
				/* (rem == 3) */
				return recursiveHelperMethod(increaseXBy1(x)) + 1;
			}
		}
	}

	public static int bruteForce(int x) {
		/*
		 * Time (2^n) This method is not compatible for input constraints as length of
		 * input string could be 309, which can not be store in int variable.
		 */
		if (x <= 1) {
			return 0;
		}
		if (x % 2 == 0) {
			int a1 = bruteForce(x / 2);
			return a1 + 1;
		} else {
			int inc = bruteForce(x + 1);
			int decr = bruteForce(x - 1);
			return (inc < decr ? inc : decr) + 1;
		}
	}

	public static int memoization(String x, Map<String, Integer> memo) {

		/* If number is less than and equal to 1 then return 0 */
		if (x.length() == 1 && x.charAt(0) - '0' <= 1) {
			return 0;
		}
		/* If we've already calculated the result for a number then return it. */
		if (memo.containsKey(x))
			return memo.get(x);

		int numOfSteps = 0;
		/* If number x is divisible by 2 then recursively call on x/2 */
		if (modXBy2(x) == 0) {
			numOfSteps = memoization(divideXBy2(x), memo) + 1;
		} else {
			/*
			 * If number x is odd then we can either increase the number x by 1 or decrease
			 * the number x by 1. We recursively call for x+1 and x-1 and select the minimum
			 * number of steps out of two.
			 */
			int decresedXStepsCount = memoization(divideXBy2(decreaseXBy1(x)), memo);
			int increasedXStepCounts = memoization(divideXBy2(increaseXBy1(x)), memo);
			numOfSteps = (increasedXStepCounts < decresedXStepsCount ? increasedXStepCounts : decresedXStepsCount) + 2;
		}

		/* Store the numOfSteps for the number x, which will be used for sub-problems */
		memo.put(x, numOfSteps);

		/* return the number of steps */
		return numOfSteps;
	}

	public static int dp(int x) {

		/*
		 * This didn't work because input is in string which could have length at most
		 * 309, which is not compatible for int variable otherwise this method works.
		 */
		if (x == 1)
			return 0;
		int[] dp = new int[x + 1];
		dp[0] = 0;
		dp[1] = 0;
		boolean isEven = true;
		for (int i = 2; i <= x; i++) {
			if (isEven) {
				dp[i] = Math.min(dp[i / 2], Math.min(dp[i - 1], dp[(i + 1) / 2] + 1)) + 1;
				isEven = false;
			} else {
				dp[i] = Math.min(dp[i - 1], dp[(i + 1) / 2] + 1) + 1;
				isEven = true;
			}
		}
		return dp[x];
	}

	public static void main(String[] args) {

//		System.out.println("15:" + solution("15"));
//		System.out.println("58:" + solution("58"));
//		System.out.println("70:" + solution("70"));
//		System.out.println("4:" + solution("4"));
//		System.out.println("30:" + solution("30"));
//		System.out.println("27:" + solution("27"));
//		System.out.println("62:" + solution("62"));
//		System.out.println("130:" + solution("130"));
//		System.out.println("126:" + solution("126"));
//		System.out.println("140:" + solution("140"));

		String[] x = new String[] { "15", "4", "58", "70", "30", "27", "130", "12648923472934793247934739478392847" };
		for (int i = 0; i < x.length; i++) {
			long startTime = System.nanoTime();
			int ans = memoization(x[i], new HashMap<String, Integer>());
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("memo time for " + ans + ": " + totalTime);

			startTime = System.nanoTime();
			ans = recursiveHelperMethod(x[i]);
			endTime = System.nanoTime();
			totalTime = endTime - startTime;
			System.out.println("helper time for " + ans + ": " + totalTime);
		}

	}

}
