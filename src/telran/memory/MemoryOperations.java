package telran.memory;

import java.util.Arrays;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		int res = Integer.MAX_VALUE;
		boolean running = true;
		boolean flag = true;
		byte ar[] = null;
		int right = Integer.MAX_VALUE;
		int left = 0;
		int middle = (right + left) / 2;
		while (right>=left && running) {
			ar = null;
			try {
				ar = new byte[middle];
				flag = false;
			} catch (Throwable e) {
				right = middle - 1;
			}
			ar = null;
			if (!flag) {
				try {
					ar = new byte[middle + 1];
					left = middle + 1;
					flag = true;
				} catch (Throwable f) {
					res = middle;
					running = false;
				}
			}
			middle = (left + right) / 2;
		}
		return res;
	}
}
