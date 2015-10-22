package com.expirian.util;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.expirian.exceptions.InvalidExpressionException;

public class Parser{

	private static Parser parser = new Parser( );
	int position = 0;
	int singleChar;
	String str;
	Iterator<Character> expressionItr;
	ExecutorService executor;
	Future<Double> future;
	
	
/*	public Parser(final String str){
		this.str = str;
	}
	
	
	public Parser(Iterator<Character> expressionItr){
		this.expressionItr = expressionItr;
	}*/
	
	private Parser(){
	}
	
	public static Parser getInstance(){
		return parser;
	}
	
	public double parse(String str) throws InvalidExpressionException, InterruptedException, ExecutionException {
		this.str = str;
		executor = Executors.newSingleThreadExecutor();
	    future = executor.submit(new ParseExpression());	
		return startParsing();
	}
	
	public double parse(Iterator<Character> expressionItr) throws InvalidExpressionException, InterruptedException, ExecutionException {
		this.expressionItr = expressionItr;
		executor = Executors.newSingleThreadExecutor();
	    future = executor.submit(new ParseExpression());	
		return startParsing();
	}
	
	private double startParsing() throws InvalidExpressionException, InterruptedException, ExecutionException{
		nextChar();

//		double value = parseExpression(); // not multi threaded 
		
		// execute multi threaded parseExpression
		double value = future.get();
		
		if (singleChar != -1)
			throw new InvalidExpressionException("Invalid character : " + (char) singleChar);
		return value;
	}

	public double parseExpression() throws InvalidExpressionException, InterruptedException, ExecutionException { // parse expression within parentheses
//		double value = parseSubExpression();
		double value = future.get();
		while (true) {
			ignorWhiteSpace();
			if (singleChar == '+') { // in case of addition
				nextChar();
//				value = value + parseSubExpression();
				value = value + future.get();
			} else if (singleChar == '-') { // in case of subtraction
				nextChar();
				value = value - parseSubExpression();
			} else {
				return value;
			}
		}
	}

	public double parseSubExpression() throws InvalidExpressionException, InterruptedException, ExecutionException { // parse high level operation (multiplication and division)
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

	private double parseNumber() throws InvalidExpressionException, InterruptedException, ExecutionException { // parse value and determine if negative
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
//			value = parseExpression();
			value = future.get();
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

	void ignorWhiteSpace() { // remove white spaces
		while (Character.isWhitespace(singleChar))
			nextChar();
	}

}
