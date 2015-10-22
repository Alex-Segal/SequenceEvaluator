package com.expirian.util;

import java.util.Iterator;

import com.expirian.exceptions.InvalidExpressionException;


/*
	Personal note:
	Please except my apologies for the very limited time I have in the evening and not completing 100% of requirements. 
	I will be more than happy to talk about multi-threading during face to face / phone interview.
	
	In this exercise parentheses are not evaluated in parallel. 
	Since I support nested parentheses it will be possible to do so only if we solve the expression backwards (the most inner parentheses go first). 
	This will require place the equation or parts of it multiple times in memory.
	The way I solve it is getting the iterator or string expression and lineary advance forward to solve the problem.
	For this type of problem, in my opinion, non multi-threaded way will be sufficient and clean solution.

*/

public class Parser{
	
	int position = 0;
	int singleChar;
	String str;
	Iterator<Character> expressionItr;

	public double parse(String str) throws InvalidExpressionException {
		this.str = str;
		return startParsing();
	}
	
	public double parse(Iterator<Character> expressionItr) throws InvalidExpressionException {
		this.expressionItr = expressionItr;
		return startParsing();
	}
	
	private double startParsing() throws InvalidExpressionException{
		nextChar();

		double value = parseExpression(); // not multi threaded		
		if (singleChar != -1)
			throw new InvalidExpressionException("Invalid character : " + (char) singleChar);
		return value;
	}

	// The synchronized method will ensure that no 2 threads will access it concurrently.
	public synchronized double parseExpression() throws InvalidExpressionException { // parse expression within parentheses
		double value = parseSubExpression();
		while (true) {
			ignorWhiteSpace();
			if (singleChar == '+') { // in case of addition
				nextChar();
				value = value + parseSubExpression();
			} else if (singleChar == '-') { // in case of subtraction
				nextChar();
				value = value - parseSubExpression();
			} else {
				return value;
			}
		}
	}

	public double parseSubExpression() throws InvalidExpressionException { // parse high level operation (multiplication and division)
		double value = parseNumber();
		while (true) {
			ignorWhiteSpace();
			if (singleChar == '/') { // in case of division
				nextChar();
				value = value / parseNumber();
			} else if (singleChar == '*' || singleChar == '(') { // in case of multiplication
				if (singleChar == '*')
					nextChar();
				value = value * parseNumber();
			} else {
				return value;
			}
		}
	}

	private double parseNumber() throws InvalidExpressionException { // parse value and determine if negative
		double value;
		boolean negative = false;
		ignorWhiteSpace();
		if (singleChar == '+' || singleChar == '-') {
			// check if the value is negative
			negative = singleChar == '-';
			nextChar();
			ignorWhiteSpace();
		}
		if (singleChar == '(') { // in case of open parentheses do recursion (include nested parentheses)!
			nextChar();
			value = parseExpression();
			if (singleChar == ')')
				nextChar();
		} else { // construct full length number
			StringBuilder sb = new StringBuilder();
			while ((singleChar >= '0' && singleChar <= '9') || singleChar == '.') { // added support for double numbers
				sb.append((char) singleChar);
				nextChar();
			}
			if (sb.length() == 0) // If invalid char exist in the expression, throw an exception.
				throw new InvalidExpressionException("Invalid character : " + (char) singleChar);
			value = Double.parseDouble(sb.toString());
		}
		ignorWhiteSpace();
		if (singleChar == '^') { // in power 
			nextChar();
			value = Math.pow(value, parseNumber());
		}
		if (negative)
			value = -value; // append minus to number if negative
		return value;
	}
	
	void nextChar() {
		if (str != null){
			// get the ascii of a character. If end of string the result is -1.
			singleChar = (position < str.length()) ? str.charAt(position) : -1;
			position++;
		}else{
			singleChar = (expressionItr.hasNext()) ? expressionItr.next() : -1;
		}

	}

	void ignorWhiteSpace() { // ignore white spaces
		while (Character.isWhitespace(singleChar))
			nextChar();
	}

}
