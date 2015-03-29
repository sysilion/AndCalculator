package tk.bizu.andcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
	private final static String[] SIGNS_IN_ORDER = getSignsInOrder();

	private String expression;

	public Parser(String expression) {
		this.expression = expression;
	}

	private static String[] getSignsInOrder() {
		List<String> result = new ArrayList<String>();
		List<String> binary = Arrays.asList(BinaryOperation.getSignsInOrder());
		result.addAll(binary);
		return result.toArray(new String[0]);
	}

	private ArrayList<Token> parse() {
		expression = expression.replaceAll(Pattern.quote("("), " ( ");
		expression = expression.replaceAll("\\)", " ) ");

		for (int i = 0; i < SIGNS_IN_ORDER.length; i++) {
			String sign = SIGNS_IN_ORDER[i];
			expression = expression.replaceAll(Pattern.quote(sign), " " + sign + " ");
		}

		for (String sign : SIGNS_IN_ORDER) {
			expression = expression.replaceAll(Pattern.quote(sign), " " + sign + " ");
		}

		String[] m = expression.split(" ");

		ArrayList<Token> a = new ArrayList<Token>();

		for (int r = 0; r < m.length; r++) {
			if (!m[r].trim().equals("")) {
				a.add(new StringToken(m[r]));
			}
		}

		return a;
	}

	private Node treeer(Token[] tokens) {
		if (tokens.length == 0) {
			return null;
		} else if (tokens.length == 1) {
			if (tokens[0] instanceof Node) {
				return (Node) tokens[0];
			}
			try {
				double num = Double.parseDouble(tokens[0].getStringValue());
				return new Number(num);
			} catch (NumberFormatException ex) {
				// System.out.println("false");
			}
		}

		String[] signs = SIGNS_IN_ORDER;
		;

		boolean found = false;
		int signIndex = 0;
		for (int i = 0; i < signs.length && (!found); i++) {
			for (int j = 0; j < tokens.length; j++) {
				if (signs[i].equals(tokens[j].getStringValue()) && !(tokens[j] instanceof Node)) {
					found = true;
					signIndex = j;
					break;
				}
			}
		}

		if (found) {
			Token[] leftPart = Arrays.copyOfRange(tokens, 0, signIndex);
			Token[] rightPart = Arrays.copyOfRange(tokens, signIndex + 1, tokens.length);
			Node left = treeer(leftPart);
			Node right = treeer(rightPart);

			String sign = tokens[signIndex].getStringValue();
			Node root = new BinaryOperation(left, right, sign.charAt(0));
			return root;
		} else {
			throw new RuntimeException("Sign expected.");
		}

	}

	private Node handleBrackets(ArrayList<Token> tokens) {
		tokens.add(0, new StringToken("("));
		tokens.add(new StringToken(")"));
		while (tokens.size() > 1) {
			prvy_for: for (int i = 0; i < tokens.size(); i++) {
				if (tokens.get(i).getStringValue().equals("(")) {
					OUTER: for (int j = i + 1; j < tokens.size(); j++) {
						switch (tokens.get(j).getStringValue().charAt(0)) {
						case '(':
							break OUTER;
						case ')':
							Token[] exp_braces = new Token[j - i - 1]; // expression
																		// between
																		// braces
							for (int p = 0; p < exp_braces.length; p++) {
								exp_braces[p] = tokens.get(p + i + 1);
							}
							Node hruska = treeer(exp_braces);
							tokens.subList(i + 1, j + 1).clear();
							tokens.set(i, hruska);
							break prvy_for;
						}
					}
				}
			}

		}
		return (Node) tokens.get(0);
	}

	/**
	 * Metóda process rozparsuje zoznam tokenov na tokeny, pošle ich metóde
	 * hanadleBrackets
	 */
	public Node process() {
		// rozparsuje na tokeny
		ArrayList<Token> tokens = parse();
		// premeni na pole
		// Token[] tokensArray = tokens.toArray(new Token[0]);
		// posle treeeru, aby z toho spravil strom, ktory sa da potom vyhodnotit
		return handleBrackets(tokens);
	}
}