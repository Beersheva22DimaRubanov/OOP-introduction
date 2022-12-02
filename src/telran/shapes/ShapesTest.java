package telran.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShapesTest {

	@Test
	public void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));	
	}
	
	private void displayStrings(String[] strings) {
		for(String str: strings) {
			System.out.println(str);
		}
		
	}

	
}
