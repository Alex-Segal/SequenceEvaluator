package com.experian.application;

import java.util.ArrayList;
import java.util.Iterator;

import com.expirian.exceptions.InvalidExpressionException;
import com.expirian.util.Evaluator;

public class SequenceEvaluator {

	public static void main(String[] args) throws InvalidExpressionException {
		
		// This class is not part of the application. File was created for internal debugging only.
		// The application run and tested with JUnit test cases.
		
		
		ArrayList list = new ArrayList();
//		String sequence = "2+ (50 + 6 0) * 2 * 1 0 / (6 * (2+1))";
		String sequence = "5*(2+98)";
		
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}

		Iterator<Character> itr = list.iterator();

		Evaluator ev = new Evaluator();
//		double result = ev.eval(sequence);
		double result = ev.evaluate(itr);
		System.out.println(result);
		System.exit(0);

	}

}
