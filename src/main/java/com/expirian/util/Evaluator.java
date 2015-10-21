package com.expirian.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Evaluator {
	
	ArrayList<String> workingSequenceList = new ArrayList<String>();
	ArrayList<String> finalSequenceList = new ArrayList<String>();
	
	
	public static double eval(final String str) {
	    class Parser {
	        int pos = -1;
	        int singleChar;

	        void eatChar() {
	            singleChar = (++pos < str.length()) ? str.charAt(pos) : -1;
	            int i = 0;
	        }

	        void eatSpace() {
	            while (Character.isWhitespace(singleChar)) eatChar();
	        }

	        double parse() {
	            eatChar();
	            double v = parseExpression();
	            if (singleChar != -1) throw new RuntimeException("Unexpected: " + (char)singleChar);
	            return v;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor | term brackets
	        // factor = brackets | number | factor `^` factor
	        // brackets = `(` expression `)`

	        double parseExpression() {
	            double v = parseTerm();
	            for (;;) {
	                eatSpace();
	                if (singleChar == '+') { // addition
	                    eatChar();
	                    v += parseTerm();
	                } else if (singleChar == '-') { // subtraction
	                    eatChar();
	                    v -= parseTerm();
	                } else {
	                    return v;
	                }
	            }
	        }

	        double parseTerm() {
	            double v = parseFactor();
	            for (;;) {
	                eatSpace();
	                if (singleChar == '/') { // division
	                    eatChar();
	                    v /= parseFactor();
	                } else if (singleChar == '*' || singleChar == '(') { // multiplication
	                    if (singleChar == '*') eatChar();
	                    v *= parseFactor();
	                } else {
	                    return v;
	                }
	            }
	        }

	        double parseFactor() {
	            double v;
	            boolean negate = false;
	            eatSpace();
	            if (singleChar == '+' || singleChar == '-') { // unary plus & minus
	                negate = singleChar == '-';
	                eatChar();
	                eatSpace();
	            }
	            if (singleChar == '(') { // brackets
	                eatChar();
	                v = parseExpression();
	                if (singleChar == ')') eatChar();
	            } else { // numbers
	                StringBuilder sb = new StringBuilder();
	                while ((singleChar >= '0' && singleChar <= '9') || singleChar == '.') {
	                    sb.append((char)singleChar);
	                    eatChar();
	                }
	                if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)singleChar);
	                v = Double.parseDouble(sb.toString());
	            }
	            eatSpace();
	            if (singleChar == '^') { // exponentiation
	                eatChar();
	                v = Math.pow(v, parseFactor());
	            }
	            if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
	            return v;
	        }
	    }
	    return new Parser().parse();
	}
	
	
	
	
	
	public double evaluate(Iterator<Character> expressionItr){
		
		ArrayList<String> sequenceList = new ArrayList<String>();
		
		sequenceList = sequenceToArray(expressionItr);
		
		double result = evaluateArray(sequenceList);

		
		// make this array global and run a process to create another one with sequence of operator number.
		// each time you hit "(" recursively run the same method and return a double from there to store it as a regular number.
				
		
		return 0;
	}
	
	private double evaluateArray(ArrayList<String> sequenceList){
		
		for (int i = 0; i < sequenceList.size(); i++){
			if (!sequenceList.get(0).equals("(")){
				finalSequenceList.add(sequenceList.get(0));
				sequenceList.remove(0);
			}else{
				
			}
		}
		
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
	}
}
