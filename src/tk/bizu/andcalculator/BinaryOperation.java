package tk.bizu.andcalculator;

public class BinaryOperation extends Node {
	private char sign;
	private static final char[] SIGNS = new char[] { '+', '-', '/', '*', '^', 'C' };

	public BinaryOperation(Node leftChild, Node rightChild, char sign) {
		super(leftChild, rightChild);
		this.sign = sign;
	}

	public BinaryOperation(char sign) {
		this(null, null, sign);
	}

	public static boolean isBinary(char sign) {
		for (int i = 0; i < SIGNS.length; i++) {
			if (SIGNS[i] == sign) {
				return true;
			}
		}
		return false;
	}

	public static String[] getSignsInOrder() {
		String[] array_signs = new String[SIGNS.length];
		for (int k = 0; k < array_signs.length; k++) {
			array_signs[k] = SIGNS[k] + "";
		}
		return array_signs;
	}

	@Override
	public double calculate() {
		double a = leftChild.calculate();
		double b = rightChild.calculate();
		switch (sign) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '/':
			return a / b;
		case '*':
			return a * b;
		case '^':
			return exp(a, b);
		default:
			return 0;
		}
	}

	public double exp(double a, double b) {
		double c = 1;
		for (int i = 0; i < b; i++) {
			c *= a;
		}
		return c;
	}

	@Override
	public String getStringValue() {
		return sign + "";
	}
}
