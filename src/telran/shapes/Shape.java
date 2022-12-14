package telran.shapes;

public abstract class Shape {
public static final String SYMBOL = "*";
	
	private static String symbol = SYMBOL;

	public Shape(int width, int height) {
		this.width = width;
		this.height = height;
	}

	private int width;
	private int height;
	
	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Shape.symbol = symbol;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
public abstract  String[] presentation(int offset);
}
