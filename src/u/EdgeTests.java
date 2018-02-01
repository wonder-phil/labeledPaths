package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.junit.Test;

import g.Coefficients;
import g.Graphs;

public class EdgeTests {

	@Test
	public void zeroEdgeTest() {
		
		Coefficients cs = new Coefficients();
		
		//
		// (3n)^{0} = 1 !!!
		//
		
		BigFraction bFraction = new BigFraction(1,1);
		BigFraction actualValue = cs.zeroEdge();
		
		// Numerator test
		assertTrue(actualValue.getNumerator().equals(bFraction.getNumerator()));
		
		// Denominator test
		assertTrue(actualValue.getDenominator().equals(bFraction.getDenominator()));
	}
	
	@Test
	public void posEdgeTest() {
		
		//
		// (3n)^{1} = 3n !!!
		//
		int n = 1000;
		
		Coefficients cs = new Coefficients();
		BigFraction bFraction = new BigFraction(((n+1)*3),1);
		BigFraction actualValue = cs.posEdge(n);
		
		// Numerator test
		assertTrue(actualValue.getNumerator().equals(bFraction.getNumerator()));
		
		// Denominator test
		assertTrue(actualValue.getDenominator().equals(bFraction.getDenominator()));
	}
	
	
	@Test
	public void negEdgeTest() {
		
		//
		// (3n)^{-1} = 1/3n !!!
		//
		int n = 1000;
		Coefficients cs = new Coefficients();
		
		BigFraction bFraction = new BigFraction(1,3*(n+1));
		BigFraction actualValue = cs.negEdge(n);
		
		// Numerator test
		assertTrue(actualValue.getNumerator().equals(bFraction.getNumerator()));
		
		// Denominator test
		assertTrue(actualValue.getDenominator().equals(bFraction.getDenominator()));
	}

}
