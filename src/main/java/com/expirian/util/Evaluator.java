package com.expirian.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Evaluator {
	
	public double evaluate(Iterator<Character> expressionItr){
		
		ArrayList<String> sequenceList = new ArrayList<String>();
		
		sequenceList = sequenceToArray(expressionItr);
				
		
		return 0;
	}
	
	
	private ArrayList<String> sequenceToArray(Iterator<Character> expressionItr){
		
		ArrayList<String> sequenceList = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		int counter = -1;
		Character singleChar;
		boolean parenthesesFlag = false;
		boolean numberFlag = false;
		while (expressionItr.hasNext()){
			singleChar = expressionItr.next();
			numberFlag = Character.isDigit(singleChar);
			
			if (numberFlag){
				buffer.append(singleChar);
				continue;
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
	}

}
