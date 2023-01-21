package telran.util;

public class LinearRecurtion {
	public static long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}

	public static int power(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		int res = 0;
		int count = a;
		if (a < 0) {
			res = (getPower(-count, a, b));
		} else if (a > 0) {
			res = getPower(count, a, b);
		}

		return res;
	}

	private static int getPower(int count, int a, int b) {
		int res = 0;
		int sum = a;
		if (b == 0) {
			res = 1;
		} else if (b > 1) {
			sum = getSum(count, sum);
			res = sum + getPower(count - 1, sum, b - 1);
		}
		return res;
	}

	private static int getSum(int count, int a) {
		int res = a;
		if (count > 1) {
			res = a + getSum(count - 1, a);
		}
		return res;
	}

	static public long sum(int ar[]) {
		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex != ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		int count = x;
		long res = 0;
		if (x == 1) {
			res = 1;
		} else if (x > 0) {
			res = square(x - 1) + ((x - 1) + (x - 1)) + 1;
		}
		return res;
	}

	public static void reverse(int ar[]) {
		reverse(0, ar.length - 1, ar);
	}

	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}
	}

	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;
	}

	public static boolean isSubstring(String string, String substring) {
		if (substring.length() > string.length()) {
			throw new IllegalArgumentException();
		}
		return checkSubstring(string, substring, 0, 0) > 0 ? true : false;
	}

	private static int checkSubstring(String string, String substring, int i, int flag) {
		int res = 0;
		int count = flag;
		if (i < string.length() - substring.length() && count == 0) {
			String tmp = string.substring(i, (i + substring.length()));
			count = checkChars(tmp, substring, 0);
			res = count + checkSubstring(string, substring, i + 1, res);
		}
		return res;
	}

	private static int checkChars(String string, String substring, int i) {
		boolean res = false;
		if (i < substring.length()) {
			res = substring.charAt(i) == string.charAt(i);
			checkChars(string, substring, i + 1);
		}
		return res ? 1 : 0;
	}

}
