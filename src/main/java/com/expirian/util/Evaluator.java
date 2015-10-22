package com.expirian.util;

import java.util.Iterator;

import com.expirian.exceptions.InvalidExpressionException;

public class Evaluator {

	
	public double evaluate(Iterator<Character> itr) throws InvalidExpressionException {
		Parser parser = new Parser(itr);
	    return parser.parse();
	}
	
	public double evaluate(String str) throws InvalidExpressionException {
		Parser parser = new Parser(str);
	    return parser.parse();
	}
	

	
	
	// not needed. Leave commented out just in case I will have time for multi-threaded solution.
/*	private ArrayList<String> sequenceToArray(Iterator<Character> expressionItr){
		// Convert expression to array
		ArrayList<String> sequenceList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		int counter = -1;
		Character singleChar;
		boolean parenthesesFlag = false;
		boolean numberFlag = false;
		while (expressionItr.hasNext()){
			expressionItr.hasNext();
			singleChar = expressionItr.next();
			numberFlag = Character.isDigit(singleChar);
			
			if (numberFlag){
				buffer.append(singleChar);
				if (expressionItr.hasNext())
					continue;
				else
					sequenceList.add(buffer.toString());
			}else{
				if (buffer.length() > 0){
					sequenceList.add(buffer.toString());
					buffer.delete(0, buffer.length());
				}
				buffer.append(singleChar);
				sequenceList.add(buffer.toString());
				buffer.delete(0, buffer.length());
			}
		}		
		return sequenceList;
	}*/
}
