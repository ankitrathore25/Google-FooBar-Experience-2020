package practice;

/*Level 1-1*/

public class Re_id {

	public static String solution(int i) {
		int num = 21000;
		String longString = "";
		int[] isPrime = new int[num];
		for (int u = 2; u < num; u++) {
			if (isPrime[u] == 0) {
				longString += String.valueOf(u);
				if (longString.length() >= 10005)
					break;
				for (int v = u * u; v < num; v += u) {
					isPrime[v] = 1;
				}
			}
		}
		return longString.substring(i, i + 5);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(3));
	}

}
