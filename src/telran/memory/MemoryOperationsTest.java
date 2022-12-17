package telran.memory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MemoryOperationsTest {
	byte ar[];

	@Test
	void maxMemoryTesttest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean filException = false;
		
		try {
			ar = new byte[maxMemory + 1];
		} catch(Throwable e) {
			filException = true;
		}
assertTrue(filException);
	}
}
