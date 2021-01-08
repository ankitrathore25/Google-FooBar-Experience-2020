
/*Level 2-2*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hey_i_already_did_this {

	public static int solution(String n, int b) {

		int k = n.length();
		/*
		 * We will keep all the numbers in visited HashMap whose key is the number and
		 * value is the index (or position) at which it arrives.
		 */
		Map<String, Integer> visited = new HashMap<>();
		int index = 0;

		/* Iterate on number till it is already visited earlier. */
		while (!visited.containsKey(n)) {

			/* mark this number as visited */
			visited.put(n, index++);

			/* generate maximum and minimum number out of the current number n */
			String[] maxMin = getMaxMin(n);

			/* parse the max and min value with base b */
			int x = Integer.parseInt(maxMin[0], b);
			int y = Integer.parseInt(maxMin[1], b);

			/* subtract the max and min and get the output in base b */
			String z = Integer.toString(x - y, b);

			/* We need to prepend 0's if the length of z is not equal to k */
			StringBuilder sb = new StringBuilder();
			while (sb.length() < k - z.length()) {
				sb.append('0');
			}
			sb.append(z);

			/* assign the new number to n for looping again */
			n = sb.toString();
		}
		/*
		 * length of the loop will be the length from index of the occurrence of
		 * repeated(last n) number till end.
		 */
		return visited.size() - visited.get(n);
	}

	public static String[] getMaxMin(String n) {
		/*
		 * we first sort the character array and make new string with descending and
		 * ascending order of the character.
		 */
		char[] charArray = n.toCharArray();
		Arrays.parallelSort(charArray);
		StringBuilder sbMax = new StringBuilder();
		StringBuilder sbMin = new StringBuilder();
		for (int i = 0; i < charArray.length; i++) {
			sbMax.append(charArray[charArray.length - 1 - i]);
			sbMin.append(charArray[i]);
		}

		/* return max and min value in a string array */
		return new String[] { sbMax.toString(), sbMin.toString() };
	}

	public static void main(String[] args) {
		String n = "210022";
//		String n = "1211";
		int b = 3;
		System.out.println(solution(n, b));
	}

}
