package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;
import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRiqhtTriangle;

public class ShapesTest {

	@Test
	public void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));	
		
		Square square = new Square(10);
		square.setHeight(5);
		displayStrings(square.presentation(20));
		
		SquareLeftTriangle leftTriangle = new SquareLeftTriangle(7, true);
		displayStrings(leftTriangle.presentation(20));
		
		SquareRiqhtTriangle rightTriangle = new SquareRiqhtTriangle(10, false);
		displayStrings(rightTriangle.presentation(10));
		
		Shape[] shapes = {leftTriangle, leftTriangle, square, rectangle};
		Canvas canvas = new Canvas(5, 4, shapes);
//		canvas.setDirection("column");
		displayStrings(canvas.presentation(5));
		
	}
	
	private void displayStrings(String[] strings) {
		for(String str: strings) {
			System.out.println(str);
		}
		
	}

	
}
