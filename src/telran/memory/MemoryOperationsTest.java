package telran.memory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MemoryOperationsTest {
	private static final long MGB = 1024*1024;
	byte ar[];

	@Test
	void maxMemoryTesttest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean filException = false;

		try {
			ar = new byte[maxMemory + 1];
		} catch (Throwable e) {
			filException = true;
		}
		assertTrue(filException);
	}
	
	@Test
	void standartMemoryMethods() {
		Runtime runtime = Runtime.getRuntime();
		System.out.printf("Maximal memory JVM may require from OS: %d, current total JVM memory: %d, "
				+ "current free JVM: %d", runtime.maxMemory()/MGB, runtime.totalMemory()/MGB, 
				runtime.freeMemory()/MGB);
	}
}
