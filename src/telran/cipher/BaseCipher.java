package telran.cipher;

public class BaseCipher {
	private static final int MIN_CHAR_VALUE = 33;
	private static final int MAX_CHAR_VALUE = 126;
	private static final int MIN_KEY_LENGTH = 2;
	private static final int MAX_KEY_LENGTH = 94;
	private String key;

	public BaseCipher(int length) {
		if (length < MIN_KEY_LENGTH)
			length = MIN_KEY_LENGTH;
		else if (length > MAX_KEY_LENGTH)
			length = MAX_KEY_LENGTH;
		this.key = generateKey(length);
	}

	public String getKey() {
		return key;
	}

	private String generateKey(int length) {
		String res = "";
		char[] symbolArr = new char[length];
		int[] checkArr = new int[MAX_CHAR_VALUE + 1];
		for (int i = 0; i < symbolArr.length; i++) {
			int number = getRandomInt(MIN_CHAR_VALUE, MAX_CHAR_VALUE);
			while (checkArr[number] == 1) {
				number = getRandomInt(MIN_CHAR_VALUE, MAX_CHAR_VALUE);
			}
			symbolArr[i] = (char) number;
			checkArr[number] = 1;
			res = res + symbolArr[i];
		}
		return res;
	}

	private int getRandomInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	public String cipher(int number) {
		String res = "";
		char[] symbols = key.toCharArray();
		while (number != 0) {
			res = symbols[getIndex(number)] + res;
			number /= key.length();
		}
		return res;
	}

	private int getIndex(int number) {
		int num = number % key.length();
		return num;
	}

	public int decipher(String cipher) {
		int res = 0;
		char [] cipherArr = cipher.toCharArray();
		int [] indexes = new int [cipherArr.length];
		int multiplier = key.length();
		int index = cipherArr.length-1;
		for(int i = 0; i<cipherArr.length; i++) {
			int j = 0;
			while(cipherArr[i] != key.charAt(j)) {
				j++;
			}
			res += getResult(j, multiplier, index);
			index--;
		}
		return res;
	}

	private int getResult(int j, int multiplier, int index) {
		int res = multiplier;
			res = (int)Math.pow(multiplier, index);
		return res*j;
	}

}
