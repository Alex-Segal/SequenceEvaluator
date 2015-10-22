package com.expirian.util;

import java.util.concurrent.Callable;

public class ParseExpression implements Callable<Double>{

	
	
	public Double call() throws Exception {
		
		Parser parser = Parser.getInstance();
		
		double value = parser.parseSubExpression();
		while (true) {
			parser.ignorWhiteSpace();
			if (parser.singleChar == '+') { // in case of addition
				parser.nextChar();
				value = value + parser.parseSubExpression();
			} else if (parser.singleChar == '-') { // in case of subtraction
				parser.nextChar();
				value = value - parser.parseSubExpression();
			} else {
				System.out.println("within thread and the value is: " + value);
				return value;
			}
		}
	}

}
