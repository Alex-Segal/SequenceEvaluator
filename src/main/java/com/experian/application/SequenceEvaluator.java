package com.experian.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import com.expirian.exceptions.InvalidExpressionException;
import com.expirian.util.Evaluator;

public class SequenceEvaluator {

	public static void main(String[] args) throws InvalidExpressionException, InterruptedException, ExecutionException {
		
		
		ArrayList list = new ArrayList();
//		String sequence = "2+ (50 + 6 0) * 2 * 1 0 / (6 * (2+1))";
		String sequence = "5*(3+1*(3+2))";
		
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
