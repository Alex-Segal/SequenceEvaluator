package com.expirian.test;

import org.junit.Test;

import com.expirian.exceptions.InvalidExpressionException;
import com.expirian.util.Evaluator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExpressionTest {

	private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void EvaluateItrTest() throws InvalidExpressionException {

    	ArrayList list = new ArrayList();
		String sequence = "20 + 6 * (2+2 * (1+1))";
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}
		Iterator<Character> itr = list.iterator();
		Evaluator ev = new Evaluator();
		double result = ev.evaluate(itr);

        assertEquals(result, 56, DOUBLE_DELTA);
    }
    
    @Test
    public void EvaluateStrTest() throws InvalidExpressionException {

    	ArrayList list = new ArrayList();
		String sequence = "20.0 + 6 * (2+2 * (1+1))";
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}
		Evaluator ev = new Evaluator();
		double result = ev.evaluate(sequence);

        assertEquals(result, 56, DOUBLE_DELTA);
    }



}
