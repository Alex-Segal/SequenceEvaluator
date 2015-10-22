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
	ArrayList list = new ArrayList();
	Evaluator ev = new Evaluator();

    @Test
    public void EvaluateItrTest1() throws InvalidExpressionException {

		String sequence = "20 + 6 * (2+2 * (1+1))";
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}
		Iterator<Character> itr = list.iterator();
		double result = ev.evaluate(itr);
        assertEquals(result, 56, DOUBLE_DELTA);
    }
    
    @Test
    public void EvaluateItrTest2() throws InvalidExpressionException {

		String sequence = "(95 - 5) / (4+4)";
		for (char ch : sequence.toCharArray()){
			list.add(ch);
		}
		Iterator<Character> itr = list.iterator();
		double result = ev.evaluate(itr);
        assertEquals(result, 11.25, DOUBLE_DELTA);
    }
    
    @Test
    public void EvaluateStrTest3() throws InvalidExpressionException {

		String sequence = "20.0 + 6 * (2+2 * (1+1))";
		double result = ev.evaluate(sequence);
        assertEquals(result, 56, DOUBLE_DELTA);
    }

    @Test
    public void EvaluateStrTest4() throws InvalidExpressionException {

		String sequence = "(-2)*6 * ( 6 * (2-1))";
		double result = ev.evaluate(sequence);
        assertEquals(result, -72, DOUBLE_DELTA);
    }


}
