package telran.shapes;

public class Square extends Rectangle{
	
	public Square(int size) {
		super(size, size);
	}
	
	@Override
	public void setHeight(int size) {
		setSquareSize(size);
	}

	@Override
	public void setWidth(int size) {
		setSquareSize(size);
	}
	
	private void setSquareSize(int size) {
		super.setHeight(size);
		super.setWidth(size);
	}
}
