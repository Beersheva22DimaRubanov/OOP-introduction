package telran.shapes;

public class Square extends Rectangle{
	private int size;
	
	public Square(int size) {
		super(size, size);
		this.size= size;
	}

	public void setHeight(int size) {
		setSquareSize(size);
	}

	public void setWidth(int size) {
		setSquareSize(size);
	}
	
	private void setSquareSize(int size) {
		super.setHeight(size);
		super.setWidth(size);
	}
}
