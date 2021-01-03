package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Please_Pass_The_Coded_Message {

	private static int maxSubarraySum = 0;
	private static List<Integer> maxSubarrayList = new ArrayList<>();

	public static int solution(int[] l) {
		maxSubarraySum = 0;
		maxSubarrayList.clear();

		recursiveHelperMethod(l, 0, new ArrayList<>(), 0);

		/*
		 * after finding the maximum length subArray with sum divisible by 3 we need to
		 * generate maximum number out of it.
		 */
		Collections.sort(maxSubarrayList);
		int ans = 0, i = 0;
		for (int num : maxSubarrayList) {
			ans += num * Math.pow(10, i++);
		}
		return ans;
	}

	public static void recursiveHelperMethod(int[] arr, int index, List<Integer> listSoFar, int subarraySumSoFar) {
		/*
		 * If sumSoFar is divisible by 3 then we need to check whether this subArray is
		 * greater in length than the previously stored array then store it in variable
		 * for further use.
		 */
		if (subarraySumSoFar % 3 == 0) {
			if ((listSoFar.size() > maxSubarrayList.size())
					|| (listSoFar.size() == maxSubarrayList.size() && subarraySumSoFar > maxSubarraySum)) {
				maxSubarraySum = subarraySumSoFar;
				maxSubarrayList.clear();
				maxSubarrayList.addAll(new ArrayList<>(listSoFar));
			}
		}
		// terminal condition
		if (index == arr.length)
			return;

		/*
		 * call recursively once excluding the integer at position index and then
		 * including the integer at position index
		 */
		recursiveHelperMethod(arr, index + 1, new ArrayList<>(listSoFar), subarraySumSoFar);
		List<Integer> copyListSoFar = new ArrayList<>(listSoFar);
		copyListSoFar.add(arr[index]);
		recursiveHelperMethod(arr, index + 1, copyListSoFar, subarraySumSoFar + arr[index]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] l = new int[] { 3, 1, 4, 1, 5, 9 };
		int[] l2 = new int[] { 3, 1, 4, 1 };
		System.out.println(solution(l));
		System.out.println(solution(l2));
	}

}
