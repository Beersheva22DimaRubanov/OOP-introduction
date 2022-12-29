package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.List;

public class ArrayListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception{
		collection = new ArrayList<>(2);
		super.setUp();
	}

	
}
