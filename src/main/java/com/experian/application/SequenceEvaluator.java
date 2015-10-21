package com.experian.application;

import java.util.ArrayList;
import java.util.Iterator;

import com.expirian.util.Evaluator;

public class SequenceEvaluator {

	public static void main(String[] args) {
		
		
		ArrayList list = new ArrayList();
		String sequence = "( 50 + 6 0) * 1 0 / (6 * (2+1))";
		sequence = sequence.replaceAll("\\s","");
		
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}

		Iterator<Character> itr = list.iterator();

		Evaluator ev = new Evaluator();
		ev.evaluate(itr);
		

	}

}
