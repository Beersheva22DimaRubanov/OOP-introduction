package telran.memory;

import java.util.Arrays;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		int res = Integer.MAX_VALUE;
		byte ar[] = null;
		int maxMemory = Integer.MAX_VALUE;
		int minMemory = 0;
		int middle = (maxMemory + minMemory) / 2;
		int maxAvaliable = 0;
		while (maxMemory >= minMemory) {
			ar = null;
			try {
				ar = new byte[middle];
				maxAvaliable = middle;
				minMemory = middle + 1;
			} catch (Throwable e) {
				maxMemory = middle - 1;
			}
			middle = minMemory + (maxMemory - minMemory) / 2;
		}
		return maxAvaliable;
	}
}
