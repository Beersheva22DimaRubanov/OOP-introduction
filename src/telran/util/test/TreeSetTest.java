package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import telran.util.TreeSet;

public class TreeSetTest extends SortedTest {
	Random random = new Random();
	private static final int N_RUNS = 10;
	private static final int N_NUMBERS = 100;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}

}
