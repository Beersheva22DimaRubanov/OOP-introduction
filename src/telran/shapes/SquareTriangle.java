package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {
		String[] res = new String[super.getWidth()];
		res[0] = getFirstLine(offset, isLeftDiagonal);
		for (int i = 1; i < res.length - 1; i++) {
			res[i] = getMiddleLine(offset, i, isLeftDiagonal);
		}
		res[res.length - 1] = getLine(offset);
		return res;
	}

	private String getFirstLine(int offset, boolean isLeftDiagonal2) {
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + super.getSymbol();
		} else {
			res = getOffset(offset + super.getWidth() - 1) + super.getSymbol();
		}
		return res;
	}

	private String getMiddleLine(int offset, int index, boolean isLeftDiagonal) {
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + super.getSymbol() + getOffset(index - 1) + super.getSymbol();
		} else {
			res = getOffset(offset + super.getWidth() - index - 1) + super.getSymbol() + getOffset(index - 1)
					+ super.getSymbol();
		}
		return res;
	}
}
