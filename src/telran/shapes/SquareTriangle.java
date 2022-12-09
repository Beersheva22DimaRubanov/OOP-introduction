package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {
		String[] res = new String[getWidth()];
		res[0] = getApex(offset, isLeftDiagonal);
		for (int i = 1; i < res.length - 1; i++) {
			res[i] = getHypotenuse(offset, i, isLeftDiagonal);
		}
		res[res.length - 1] = getLine(offset);
		return res;
	}

	private String getApex(int offset, boolean isLeftDiagonal2) {
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + getSymbol();
		} else {
			res = getOffset(offset + getWidth() - 1) + getSymbol();
		}
		return res;
	}

	private String getHypotenuse(int offset, int index, boolean isLeftDiagonal) {
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + getSymbol() + getOffset(index - 1) + getSymbol();
		} else {
			res = getOffset(offset + getWidth() - index - 1) + getSymbol() + getOffset(index - 1)
					+ getSymbol();
		}
		return res;
	}
}
