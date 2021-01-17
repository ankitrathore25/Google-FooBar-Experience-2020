package practice;

/*Level 3-2*/

public class Find_the_Access_Codes {

	public static int solution(int[] l) {
		int len = l.length;
		int numOfTriplets = 0;
		/*
		 * value at ith index in count array gives the number of times an integer at ith
		 * position in list is divisible by previous numbers in the list.
		 */
		int[] count = new int[len];
		count[0] = 0;
		for (int j = 1; j < len; j++) {
			for (int i = 0; i < j; i++) {
				/*
				 * check for every number before the jth index. If any ith number divide the
				 * integer at jth position then increase the count and add the count of ith
				 * integer as this will be forming a triplet.
				 */
				if (l[j] % l[i] == 0) {
					count[j]++;
					numOfTriplets += count[i];
				}
			}
		}

		return numOfTriplets;
	}

	public static void main(String[] args) {
		int[] l = new int[] { 1, 1, 1 };
		System.out.println(solution(l));
		int[] l1 = new int[] { 1, 2, 3, 4, 5, 6 };
		System.out.println(solution(l1));
		int[] l2 = randomNumberGenerator.generateRandomNumber();
		System.out.println(solution(l2));

	}

}
