package telran.cipher.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

class CipherTest {

	@Test
	void baiseCipherTest() {
		BaseCipher base = new BaseCipher(20);
		String sys = base.cipher(245);
		System.out.println(sys);
		assertEquals(245, base.decipher(sys));
		
		BaseCipher key = new BaseCipher(-1);
		String cipher = key.cipher(1234567);
		assertEquals(1234567, key.decipher(cipher));
		
		BaseCipher bigKey = new BaseCipher(345678);
		String bigCipher = bigKey.cipher(789094213);
		assertEquals(789094213, bigKey.decipher(bigCipher));

	}

}
