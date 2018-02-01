package u;

import static org.junit.Assert.*;
import g.Graphs;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.util.BigRealField;
import org.junit.Test;
import java.math.*;

import g.Graphs;
import g.PGB_Algorithm;
//import g.MMFractionalExample;
import g.Coefficients;

public class AGMAcceleratingTests {


	
	@Test
	public void createZeroEdgeTest() {
		int n = 100;
		Coefficients cs = new Coefficients();
		
		
		BigFraction edge = cs.createZeroEdge_nSq_DENOM(n);
		BigInteger nTimes3 = BigInteger.valueOf((n+1)*3);
		BigInteger nSquared9 = nTimes3.multiply(nTimes3);
		
		edge = edge.add(nSquared9);
		
		
		
		assertTrue(cs.detectZeroEdge(edge ,n));
	}
	
	
	@Test
	public void createPlusOneEdgeTest() {
		int n = 100;
		Coefficients cs = new Coefficients();
		
		BigFraction edge = cs.createPlusOneEdge_nSq_DENOM(n);
		BigInteger nTimes3 = BigInteger.valueOf((n+1)*3);
		
		edge = edge.multiply(nTimes3);
		
		/*
		 * (3n)(3n) is a +2 edge !!!
		 */
		
		
		
		assertFalse(cs.detectPlusOneEdge(edge ,n));
	}
	
	
	@Test
	public void createMinusOneEdgeTest() {
		int n = 100;
		Coefficients cs = new Coefficients();
		
		BigFraction edge = cs.createMinusOneEdgeONE_NUM(n);
		//BigFraction edge = PGB_Algorithm.createMinusOneEdge(n);
		
		edge = edge.multiply(2*(n+1));
		
		assertTrue(cs.detectMinusOneEdge(edge ,n));
	}
	

	
	@Test
	public void detectMinusOneEdgeTest() {
		int n = 100;
		
		BigFraction minusOneEdge = new BigFraction(3,n*3);
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(minusOneEdge,n));
	}
	
	@Test
	public void detectNotMinusOneEdgeTest() {
		int n = 100;
		
		BigFraction zeroEdge = new BigFraction(3*n,3*n);
		zeroEdge  = zeroEdge.multiply(zeroEdge);
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectMinusOneEdge(zeroEdge,n));
	}
	
	@Test
	public void detectPlusOneEdgeTest() {
		int n = 100;
		
		BigFraction plusOneEdge = new BigFraction(3*n,1);
		
		plusOneEdge = plusOneEdge.multiply(2*(n-1));
		
		Coefficients cs = new Coefficients();
		assertTrue(cs.detectPlusOneEdge(plusOneEdge,n));
	}
	
	@Test
	public void detectNotPlusOneEdgeTest() {
		int n = 100;
		
		BigFraction zeroEdge = new BigFraction(3*n,3*n);
		zeroEdge  = zeroEdge.multiply(zeroEdge);
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectMinusOneEdge(zeroEdge,n));
		
	}
	
	@Test
	public void detectZeroEdgeTest1() {
	
		/*
		 * Testing for zero,+1,-1 edges only works with the numerators
		 * 
		 * Only works with the numerator
		 */
		int n = 100;
		
		BigFraction zeroEdge = new BigFraction(3*n,3*n);
		zeroEdge = zeroEdge.multiply(zeroEdge);
		// (3n)^2/(3n)^2
		
		/*
		 * +1 edge:
		 */

		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(zeroEdge,n));
	}
	
	@Test
	public void detectNotZeroEdgeTest1() {
	
		/*
		 * Testing for zero,+1,-1 edges only works with the numerators
		 * 
		 * Only works with the numerator
		 */
		int n = 100;
				
		BigFraction zeroEdge = new BigFraction(3*(n+1),1); 
		zeroEdge = zeroEdge.multiply(zeroEdge);
		// (3n)^2
		
		/*
		 * +1 edge:
		 */
		BigFraction nonZeroEdge1 = new BigFraction(3*(n+1),1);
		nonZeroEdge1 = nonZeroEdge1.multiply(nonZeroEdge1);
		nonZeroEdge1 = nonZeroEdge1.multiply(nonZeroEdge1);
		/*
		 * -1 edge
		 */
		BigFraction nonZeroEdgeM1 = new BigFraction(1,3*(n+1));
		/*
		 * 
		 */
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectZeroEdge(nonZeroEdgeM1,n));
		assertFalse(cs.detectZeroEdge(nonZeroEdge1,n));
	}
	

}
