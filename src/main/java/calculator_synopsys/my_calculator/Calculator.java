package calculator_synopsys.my_calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

public class Calculator {

	static final Logger logger = Logger.getLogger(Calculator.class);

	// Return result for arithmetic operations which form the expression
	public static int operationResolver(List<String> expression, Map<String, Integer> expressionMap) throws Exception {

		long result = Integer.MIN_VALUE;
		try {
			// Only two arguments are permitted for arithmetic operations
			if (expression.size() != 3)
				throw new Exception(
						"Incorrect number of arguments passed to an Arithmatic Operator - Please check usage");

			logger.debug("Resolving an arithmetic operation");

			if ("mult".equals(expression.get(0)))
				result = ((long) performCalculation(expression.get(1), expressionMap))
				* performCalculation(expression.get(2), expressionMap);
			else if ("add".equals(expression.get(0)))
				result = ((long) performCalculation(expression.get(1), expressionMap))
				+ performCalculation(expression.get(2), expressionMap);
			else if ("sub".equals(expression.get(0)))
				result = ((long) performCalculation(expression.get(1), expressionMap))
				- performCalculation(expression.get(2), expressionMap);
			else if ("div".equals(expression.get(0)))
				result = ((long) performCalculation(expression.get(1), expressionMap))
				/ performCalculation(expression.get(2), expressionMap);
			else
				throw new Exception("Invalid Operation performed");

			// Overflow cases
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
				throw new Exception("Result exceeds Integer capacity");
			// Invalid Operation case
			if (result == Integer.MIN_VALUE)
				throw new Exception("An error occurred : Please try again");
		} catch (Exception e) {
			throw e;
		}
		return (int) result;
	}

	// Returns the result for entered expression
	public static int performCalculation(String inputExpression, Map<String, Integer> expressionMap) throws Exception {
		int result = Integer.MIN_VALUE;
		try {
			// A list which contains sub-expressions
			List<String> expression = new ArrayList<String>();
			int parenthesis = 0;
			int start = inputExpression.indexOf('(');

			logger.debug("Calculating result of entered expression/sub-expression..");
			if (start < 0) {
				// If variable already evaluated
				if (expressionMap.get(inputExpression) != null)
					return expressionMap.get(inputExpression);
				// Return Value of operand
				return Integer.decode(inputExpression);
			}

			// Extract first operator of said expression
			String firstOperator = inputExpression.substring(0, start).trim();
			if (!isValidOperator(firstOperator))
				throw new Exception("Invalid operator specified '" + firstOperator + "' Please check usage");
			// Adding operation name
			expression.add(firstOperator);

			// Divide expression into sub-expressions
			for (int i = start; i < inputExpression.length(); i++) {
				if (inputExpression.charAt(i) == '(')
					parenthesis++;
				if (inputExpression.charAt(i) == ')')
					parenthesis--;
				if ((inputExpression.charAt(i) == ',' && parenthesis == 1) || parenthesis == 0) {
					expression.add(inputExpression.substring(start + 1, i).trim());
					start = i;
				}
			}
			logger.debug("Checking if given operation is arithmetic or let");
			// Let operation handler
			if ("let".equals(expression.get(0))) {
				logger.debug("Entering let operation..");
				// A let operation needs to contain four arguments
				if (expression.size() != 4)
					throw new Exception("Incorrect number of arguments passed to a Let Operator - Please check usage");

				String variableName = expression.get(1);
				Integer prevValue = expressionMap.get(variableName);
				// Assign value for a variable specified in Let
				expressionMap.put(variableName, performCalculation(expression.get(2), expressionMap));
				// Return result
				result = performCalculation(expression.get(3), expressionMap);
				// Insert old value back into list
				expressionMap.put(variableName, prevValue);
			}
			// Arithmetic operation handler
			else {
				logger.debug("Entering arithmetic operation..");
				return operationResolver(expression, expressionMap);
			}

		} catch (Exception e) {
			throw e;
		}
		logger.info("Result computed");
		return result;
	}

	// Checks if given expression has valid parenthesis
	public static boolean hasValidParentheses(String inputExpression) {
		Stack<Character> parentheses = new Stack<Character>();

		for (char c : inputExpression.toCharArray()) {
			if (c == '(')
				parentheses.push('(');
			else if (c == ')') {
				if (!parentheses.isEmpty())
					parentheses.pop();
				else
					return false;
			}
		}

		return parentheses.isEmpty();
	}

	// Checks if given expression starts with a valid operator
	public static boolean isValidOperator(String inputExpression) {
		if (inputExpression.startsWith("let") || inputExpression.startsWith("add") || inputExpression.startsWith("div")
				|| inputExpression.startsWith("mult") || inputExpression.startsWith("sub"))
			return true;
		return false;
	}

	// Checks if given expression is a valid expression
	public static boolean isValidExpression(String inputExpression) {
		if (isValidOperator(inputExpression) && hasValidParentheses(inputExpression))
			return true;
		return false;
	}

	public static void main(String[] args) {

		try {
			//Invalid number of arguments
			if (args.length != 1)
				throw new Exception("Invalid number of arguments supplied");

			// Input from command line
			String inputExpression = args[0];

			logger.info("Execution of the program begins");
			int start = inputExpression.indexOf('(');

			// Gracefully exit if invalid input is entered
			if (start < 0 || !isValidExpression(inputExpression)) {
				throw new Exception("Input structure invalid - Please check usage");
			}
			logger.info("Contains valid parentheses and initial operator");
			// Find output of entered expression
			int result = performCalculation(inputExpression.trim(), new HashMap<String, Integer>());
			if (result == Integer.MIN_VALUE)
				throw new Exception("Error occurred");
			else
				System.out.println("================================ \n The result is : "
						+ result);
		} catch (NumberFormatException nfe) {
			System.out.println(
					"Number format exception: Please ensure the numbers are between (-2147483647 ,2147483647)");
			logger.error("Exception : " + nfe.getMessage());
			System.exit(0);
		} catch (Exception e) {
			System.out.println("An exception has occured ");
			System.out.println("Exception : " + e.getMessage());
			logger.error("Exception : " + e.getMessage());
			System.exit(0);
		}
	}
}