package telran.shapes;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int offset) {
		this.margin = offset;
	}

	public int getHeight() {
		int res = 0;
		for (int i = 0; i < shapes.length; i++) {
			res += shapes[i].getHeight();
		}
		return res + (getMargin() * (shapes.length - 1));
	}

	@Override
	public String[] presentation(int offset) {
		switch (getDirection()) {
		case "row":
			return getRowArray(offset);
		case "column":
			return getColumnArray(offset);
		}
		return null;
	}

	private String[] getColumnArray(int offset) {
		setWidth(5);
		int width = getWidth();
		int height = getHeights(shapes, width) + (getMargin() * (shapes.length - 1));
		String[] res = new String[height];
		int currentHeigth = 0;
		for (int i = 0; i < shapes.length; i++) {
			if (shapes[i] instanceof Canvas) {
				((Canvas) shapes[i]).setDirection("column");
			}
			String[] helper = shapes[i].presentation(2);
			System.arraycopy(helper, 0, res, currentHeigth, helper.length);
			arrayCopy(res, helper, currentHeigth);
			currentHeigth += helper.length;
			if (i < shapes.length - 1) {
				arrayCopy(res, getVerticalMargin(width, margin), currentHeigth);
				currentHeigth += margin;
			}
		}
		return res;
	}

	private String[] getRowArray(int offset) {
		
		int height = shapes[0].getHeight();
		String[] res = new String[height];
		shapes[0].setHeight(height);
		res = shapes[0].presentation(offset);
		for (int i = 1; i < shapes.length; i++) {
			if (shapes[i] instanceof Canvas) {
				((Canvas) shapes[i]).setDirection(direction);
			}
			shapes[i].setHeight(height);
			res = joinArrays(res, shapes[i].presentation(margin));
		}
		return res;
	}

	private void arrayCopy(String[] arrayTo, String[] arrayFrom, int currentHeigth) {
		System.arraycopy(arrayFrom, 0, arrayTo, currentHeigth, arrayFrom.length);
	}

	private String[] getVerticalMargin(int width, int margin) {
		String[] res = new String[margin];
		for (int i = 0; i < res.length; i++) {
			res[i] = " ".repeat(width);
		}
		return res;
	}

	private int getHeights(Shape[] shapes, int width) {
		int res = 0;
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setWidth(width);
			res += shapes[i].getHeight();
		}
		return res;
	}

	private String[] joinArrays(String[] string1, String[] string2) {
		String[] res = new String[string1.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = string1[i] + string2[i];
		}
		return res;
	}

}
