
public class Notation extends Object {

	/**
	 * @author ahsan
	 * @param infix
	 * @return
	 * @throws InvalidNotationFormatException
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		NotationQueue<Character> postfix = new NotationQueue<Character>();
		NotationStack<Character> stackInfix = new NotationStack<Character>();
		try {
			for (int i = 0; i < infix.length(); i++) {// go through the index of the string
				if (Character.isDigit(infix.charAt(i))) {// checking if character is digit at the infix index
					postfix.enqueue(infix.charAt(i));// adding the digit to the queue
				} else if (infix.charAt(i) == '(') {// check if the index in infix is a '('
					stackInfix.push(infix.charAt(i));
				} else if (infix.charAt(i) == '*' || infix.charAt(i) == '/') {
					while ((!stackInfix.isEmpty() && stackInfix.top() != '(')) {
						Character a = stackInfix.pop();
						postfix.enqueue(a);

					}
					stackInfix.push(infix.charAt(i));
				} else if (infix.charAt(i) == '+' || infix.charAt(i) == '-') {
					while (!stackInfix.isEmpty() && stackInfix.top() != '(' && stackInfix.top() != '*'
							&& stackInfix.top() != '/') {
						Character a = stackInfix.pop();
						postfix.enqueue(a);

					}
					stackInfix.push(infix.charAt(i));
				} else if (infix.charAt(i) == ')') {
					while (!stackInfix.isEmpty() && stackInfix.top() != '(') {
						Character a = stackInfix.pop();
						postfix.enqueue(a);
					}
					if (!stackInfix.isEmpty() && stackInfix.top() == '(') {
						stackInfix.pop();
					} else {
						throw new InvalidNotationFormatException();
					}
				}

			}

			while (!stackInfix.isEmpty()) {
				Character a = stackInfix.pop();
				postfix.enqueue(a);
			}
			return postfix.toString();

		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
	}

	/**
	 * 
	 * @param postfix
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		NotationStack<String> stackInfix = new NotationStack<String>();
		try {
			for (int i = 0; i < postfix.length(); i++) {
				if (Character.isDigit(postfix.charAt(i))) {
					stackInfix.push(Character.toString(postfix.charAt(i)));
				} else if (postfix.charAt(i) == '*' || postfix.charAt(i) == '+' || postfix.charAt(i) == '/'
						|| postfix.charAt(i) == '-') {
					String v1 = stackInfix.pop();
					String v2 = stackInfix.pop();
					String function = "(" + v2 + postfix.charAt(i) + v1 + ")";
					stackInfix.push(function);
				}
			}
			if (!(stackInfix.size() == 1)) {
				throw new InvalidNotationFormatException();
			}
			return stackInfix.pop();

		} catch (StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
	}

	/**
	 * 
	 * @param postfixExpr
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		NotationStack<String> stackInfix = new NotationStack<String>();
		String num = "";
		try {
			double result = 0;
			for (int i = 0; i < postfixExpr.length(); i++) {

				if (Character.isDigit(postfixExpr.charAt(i)) || postfixExpr.charAt(i) == '(') {
					stackInfix.push(Character.toString(postfixExpr.charAt(i)));
				} else if (postfixExpr.charAt(i) == '*' || postfixExpr.charAt(i) == '+' || postfixExpr.charAt(i) == '/'
						|| postfixExpr.charAt(i) == '-') {
					String v1 = stackInfix.pop();
					String v2 = stackInfix.pop();
					double value1 = Double.parseDouble(v1);
					double value2 = Double.parseDouble(v2);
					if (postfixExpr.charAt(i) == '+') {
						result = value2 + value1;
					} else if (postfixExpr.charAt(i) == '-') {
						result = value2 - value1;
					} else if (postfixExpr.charAt(i) == '/') {
						result = value2 / value1;
					} else if (postfixExpr.charAt(i) == '*') {
						result = value2 * value1;
					}
					stackInfix.push(Double.toString(result));
				}
			}
			if (!(stackInfix.size() == 1)) {
				throw new InvalidNotationFormatException();

			}
			num = stackInfix.pop();

		} catch (StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}

		return Double.parseDouble(num);
	}
}
