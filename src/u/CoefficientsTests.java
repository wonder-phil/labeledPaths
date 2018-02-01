package u;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import g.Coefficients;
import org.apache.commons.math3.fraction.*;

public class CoefficientsTests {

	

	@Test
	public void GT_test1() {
		Coefficients cs = new Coefficients();
		
		BigInteger left = BigInteger.valueOf(100);
		BigInteger right = BigInteger.valueOf(100);
		
		assertFalse(cs.GT(left,right));
	}
	
	@Test
	public void GT_test2() {
		Coefficients cs = new Coefficients();
		
		BigInteger left = BigInteger.valueOf(100);
		BigInteger right = BigInteger.valueOf(99);
		
		assertTrue(cs.GT(left,right));
	}
	
	@Test
	public void GT_test3() {
		Coefficients cs = new Coefficients();
		
		BigInteger left = BigInteger.valueOf(99);
		BigInteger right = BigInteger.valueOf(1000);
		
		assertFalse(cs.GT(left,right));
	}
	
	@Test
	public void basicCoefficientTest_n2() throws Exception {
		Coefficients cs = new Coefficients();
		
		int n = 100;
		BigFraction bf = new BigFraction(3*n, 9*n*n); // BF reduces to 1/(3n)
		
		BigFraction outcome = bf; // makes it (3n)^2
		BigFraction expected = new BigFraction(2,3*n);
		
		/*
		 * Expect 3n in the numerator
		 */
		assertTrue(cs.detectMinusOneEdge(expected, n));
	}
	
	@Test
	public void basicCoefficientTest_n() {
		Coefficients cs = new Coefficients();
		
		int n = 100;
		BigFraction bf = new BigFraction(3*(n+1), 3*(n+1));
		
		BigFraction outcome = bf;
		BigFraction expected = new BigFraction(3*3*(n+1)*(n+1), 3*(n+1)); // BigFraction gets GCD
		
		
		/*
		 * Expect (3n)^2 in the numerator
		 */
		assertTrue(cs.detectPlusOneEdge(expected,n));
	}
	
	@Test
	public void basicCoefficientTest_complex() throws Exception {
		Coefficients cs = new Coefficients();
		
		int n = 100;
		
		int nSquaredT9 = (n+1)*(n+1)*3*3;
		BigFraction bf = new BigFraction(3*(n+1)+nSquaredT9, 27*(n+1)*(n+1)*(n+1));
		
		BigFraction outcome = bf;
		BigFraction expected = new BigFraction(1+3*(n+1),3*(n+1));
		
		/*
		 * Expect 27n^3 in the numerator
		 */
		assertTrue(cs.detectMinusOneEdge(expected,n));
	}
	
	@Test
	public void detectMinusOneEdgeTest1() {
		
		int n = 100;
		BigFraction edge = new BigFraction(3*n, 3*3*n*n); // (3n)/(3n)^2  = 1/(3n)
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}
	
	@Test
	public void detectMinusOneEdgeTest2() {
		
		int n = 100;
		BigFraction edge = new BigFraction(1, 3*n); // 1/(3n)
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}

	@Test
	public void detectMinusOneEdgeTest3() {
		
		int n = 100;
		
		long prodSquared = 3*3*n*n;
		BigFraction edge = new BigFraction(prodSquared, 3*n*prodSquared); // (3n)^2/(3n)^3 = 1/(3n)
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}
	
	@Test
	public void detectMinusOneEdgeTest4() {
		
		int n = 100;
		
		long prodSquared = 3*3*n*n;
		prodSquared += 2*3*n;
		prodSquared *= 3;
		
		BigFraction edge = new BigFraction(prodSquared, 3*n*prodSquared); // (3n)^2/(3n)^3 = 1/(3n)
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest1() {
		
		int n = 100;
		
		long prod = 3*3*n*n;
		BigFraction edge = new BigFraction(prod, prod); // (3n)^2/(3n)^2 = (3n)/(3n) = 1
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest2() {
		
		int n = 100;
		
		long prodSquared = 3*3*n*n;
		BigFraction edge = new BigFraction(prodSquared, prodSquared); // (3n)^2/(3n)^2 = (3n)/(3n) = 1
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest3() {
		
		int n = 100;
		
		long prodCubed = 3*3*3*n*n*n;
		BigFraction edge = new BigFraction(prodCubed, prodCubed); // (3n)^3/(3n)^3 = (3n)/(3n) = 1
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest4() {
		
		int n = 100;
		
		long base = 27*n*n*n;
		long prodCubed = 3*3*3*n*n*n;
		prodCubed += 3*3*n*n;
		prodCubed *= 2;
		
		BigFraction edge = new BigFraction(prodCubed, base); // Has a 1/1 term
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	
	@Test
	public void detectZeroEdgeTest5() {
		
		int n = 100;
		
		long prod = 3*3*n*n;
		BigFraction edge = new BigFraction(1, 3*n); // 1/(3n) is -1.
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectZeroEdge(edge,n));
	}
		
	@Test
	public void detectZeroEdgeTest6() {
		
		int n = 100;
		
		long prod = 3*3*n*n;
		BigFraction edge = new BigFraction(1+3*n, 3*n); // (1+3n)/(3n) is -1 and 0 edges
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest7() {
		
		int n = 100;
		
		long prod = 3*3*n*n;
		BigFraction edge = new BigFraction(2, 1); // Two 0 edges
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	
	@Test
	public void detectZeroEdgeTest8() {
		
		int n = 100;
		
		long prod = 3*3*n*n;
		BigFraction edge = new BigFraction(2, 3*n); // 2/(3n) is two -1 edges
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void detectZeroEdgeTest9() {
		
		int n = 3;
		
		BigFraction edge = new BigFraction(16, 3*n); // is 16/9 a zero edge?
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edge,n));
	}
	
	@Test
	public void getZeroEdgeNumeratorTest1(){
		int n = 3;
		
		BigInteger sixteen = BigInteger.valueOf(16);
		
		BigFraction edge = new BigFraction(16, 3*n); // is 16/9 a zero edge?
		
		Coefficients cs = new Coefficients();
		
		BigInteger num = cs.getZeroEdgeNumerator(edge, n);
		
		assertTrue(num.equals(BigInteger.ONE));
	}
	
	@Test
	public void getZeroEdgeNumeratorTest2(){
		
		Coefficients cs = new Coefficients();
		
		int n = 3;
		
		BigFraction zeroEdge = cs.zeroEdge();
		
		BigInteger one = BigInteger.ONE;
		
		BigInteger num = cs.getZeroEdgeNumerator(zeroEdge, n);
		
		assertTrue(num.equals(one));
	}
	
	@Test
	public void getZeroEdgeNumeratorTest3(){
		
		Coefficients cs = new Coefficients();
		
		int n = 3;
		
		BigFraction zeroEdges2 = cs.zeroEdge();
		zeroEdges2 = zeroEdges2.add(zeroEdges2);
		
		BigInteger two = BigInteger.ONE;
		two = two.add(two);
		
		
		BigInteger num = cs.getZeroEdgeNumerator(zeroEdges2, n);
		
		assertTrue(num.equals(two));
	}
	
	@Test
	public void getZeroEdgeNumeratorTest4(){
		
		Coefficients cs = new Coefficients();
		
		int n = 3;
		
		BigFraction zeroEdges3 = cs.zeroEdge();
		zeroEdges3 = zeroEdges3.add(cs.zeroEdge());
		zeroEdges3 = zeroEdges3.add(cs.zeroEdge());
		
		BigInteger three = BigInteger.ONE;
		three = three.add(BigInteger.ONE);
		three = three.add(BigInteger.ONE);
		
		BigInteger num = cs.getZeroEdgeNumerator(zeroEdges3, n);
		
		assertTrue(num.equals(three));
	}
	
	
	@Test
	public void countZeroEdgesTest1() {
		Coefficients cs = new Coefficients();
		
		int n = 3;
	
		BigFraction edge = cs.zeroEdge();
		
		long totalZeroEdges = cs.countZeroEdges(edge,n);
		final long expectedZeroEdges = 1;
		
		assertTrue(expectedZeroEdges == totalZeroEdges);
	}
	
	@Test
	public void countZeroEdgesTest2() {
		Coefficients cs = new Coefficients();
		
		int n = 7;
	
		BigFraction edge = cs.zeroEdge();
		edge = edge.add(cs.zeroEdge());
		
		long totalZeroEdges = cs.countZeroEdges(edge,n);
		final long expectedZeroEdges = 2;
		
		assertTrue(expectedZeroEdges == totalZeroEdges);
	}
	
	
	@Test
	public void countZeroEdgesTest3() {
		Coefficients cs = new Coefficients();
		
		int n = 20;
	
		BigFraction edge = cs.zeroEdge();
		for (int i=0; i < n; i++) {
			edge = edge.add(cs.zeroEdge());
		}
		
		long totalZeroEdges = cs.countZeroEdges(edge,n);
		final long expectedZeroEdges = n+1;
		
		assertTrue(expectedZeroEdges == totalZeroEdges);
	}
	
	@Test
	public void countZeroEdgesTest4() {
		Coefficients cs = new Coefficients();
		
		for (int n=8; n < 32; n++) {
		
			BigFraction edge = cs.zeroEdge();
			for (int i=0; i < n; i++) {
				edge = edge.add(cs.zeroEdge());
			}
		
			long totalZeroEdges = cs.countZeroEdges(edge,n);
			final long expectedZeroEdges = n+1;
		
			assertTrue(expectedZeroEdges == totalZeroEdges);
		}
	}
	
	@Test
	public void countEpsilonsInEdgeTest1() {
		Coefficients cs = new Coefficients();
		
		
		for (int n=8; n < 32; n++) {
		
			BigFraction epsilon = cs.epsilon(n);
			
			BigFraction edge = cs.zeroEdge();
			for (int i=0; i < n; i++) {
				edge = edge.add(epsilon);
			}
		
			long totalEpsilons = cs.countEpsilonsInEdge(edge,n);
			final long expectedZeroEdges = n;
		
			assertTrue(expectedZeroEdges == totalEpsilons);
		}
	}
	
	
	
	@Test
	public void removeAllZeroEdgesTest1() {
		Coefficients cs = new Coefficients();
		
		int n = 3;
	
		BigFraction edge = cs.zeroEdge();
		
		BigFraction actualEdge = cs.removeZeroEdges(edge,n);
		
		assertFalse(cs.detectZeroEdge(actualEdge, n));
	}
	
	@Test
	public void removeAllZeroEdgesTes2() {
		Coefficients cs = new Coefficients();
		
		int n = 3;
		
		BigFraction edge = cs.zeroEdge();
		edge = edge.add(cs.zeroEdge());
		
		BigFraction actualEdge = cs.removeZeroEdges(edge,n);
		
		assertFalse(cs.detectZeroEdge(actualEdge, n));
	}
	
	@Test
	public void removeAllZeroEdgesTest3() {
		Coefficients cs = new Coefficients();
		
		int n = 9;
		
		for (int i=4; i < n; i++) {
		
			BigFraction edge = cs.zeroEdge();
			edge = edge.add(cs.zeroEdge());
		
			BigFraction actualEdge = cs.removeZeroEdges(edge,i);
			
			assertFalse(cs.detectZeroEdge(actualEdge, i));
		}
	}
	
	@Test
	public void removeAllZeroEdgesTest4() {
		Coefficients cs = new Coefficients();
		
		int n = 9;
		
		for (int i=4; i < n; i++) {
		
			BigFraction edge = cs.zeroEdge();
			edge = edge.add(cs.zeroEdge());
			edge = edge.add(cs.zeroEdge());
			edge = edge.add(cs.zeroEdge());
		
			BigFraction actualEdge = cs.removeZeroEdges(edge,i);
			
			assertFalse(cs.detectZeroEdge(actualEdge, i));
		}
	}
	
	@Test
	public void removeAllZeroEdgesTest5() {
		Coefficients cs = new Coefficients();
		
		int n = 9;
		
		for (int i=4; i < n; i++) {
		
			BigFraction edge = cs.zeroEdge();
			for (int j=0; j < 10; j++) {
				edge = edge.add(cs.zeroEdge());
			}
		
			BigFraction actualEdge = cs.removeZeroEdges(edge,i);
			
			assertFalse(cs.detectZeroEdge(actualEdge, i));
		}
	}
	
	@Test
	public void removeAllZeroEdgesTest6() {
		Coefficients cs = new Coefficients();
		
		int n = 9;
		
		for (int i=4; i < n; i++) {
		
			BigFraction edge = cs.zeroEdge();
			edge = edge.add(cs.zeroEdge());
			edge = edge.add(cs.zeroEdge());
			//edge = edge.add(cs.zeroEdge());
			
			edge = edge.add(cs.posEdge(i));
			edge = edge.add(cs.negEdge(i));
			
		
			BigFraction actualEdge = cs.removeZeroEdges(edge,i);
			
			assertFalse(cs.detectZeroEdge(actualEdge, i));
			assertTrue(cs.detectMinusOneEdge(actualEdge, i));
			assertTrue(cs.detectPlusOneEdge(actualEdge, i));
		}
	}
	
	@Test
	public void detectPlusOneEdgeTest3() {
		int n = 100;
		
		long prodSquared = 3*3*(n+1)*(n+1);
		prodSquared += 3*n;
		prodSquared *= 2;
		
		BigFraction edge = new BigFraction(3*(n+1)*prodSquared, prodSquared); // Has a 3n/1 term
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectPlusOneEdge(edge,n));
		
	}
	
	@Test
	public void detectPlusOneEdgeTest4() {
		int n = 100;
		
		long prodCubed = 3*3*3*(n+1)*(n+1)*(n+1);
		prodCubed += 3*3*n*n;
		prodCubed *= 2;
		
		BigFraction edge = new BigFraction(3*(n+1)*prodCubed, prodCubed); // Has a 3n/1 term
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectPlusOneEdge(edge,n));
	}
		
	@Test
	public void detectMinusOneEdgeTest5() {
		int n = 10;
		
		BigFraction edge = new BigFraction(31, 15); // Has a 1/3n term, 62/30 = 2/30 + 60/30
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}
	
	@Test
	public void detectMinusOneEdgeTest6() {
		int n = 10;
		
		BigFraction edge = new BigFraction(31, 15); // Has a 1/3n term, 62/30 = 2/30 + 60/30
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectMinusOneEdge(edge,n));
	}
	
	@Test
	public void detectPlusOneEdgeTest7() {
		int n = 10;
		
		BigFraction edge = new BigFraction(31, 30); // Has a 1/30 + 30/30
		
		Coefficients cs = new Coefficients();
		
		assertFalse(cs.detectPlusOneEdge(edge,n));
	}
	
	@Test
	public void downTo3nDenomTest1(){
		int n = 100;
		BigFraction bf = new BigFraction(1,3*(n+1));
		
		Coefficients cs = new Coefficients();
		BigFraction actual = bf;
		
		BigFraction expected = new BigFraction(1,3*(n+1));
		
		assertTrue(0 == actual.compareTo(expected));
	}
	
	@Test
	public void downTo3nDenomTest2(){
		int n = 100;
		BigFraction bf = new BigFraction(3*(n+1),1);
		
		Coefficients cs = new Coefficients();
		BigFraction actual = bf;
		
		BigFraction expected = new BigFraction(3*3*(n+1)*(n+1),3*(n+1));
		
		assertTrue(0 == actual.compareTo(expected));
	}
	
	@Test
	public void multiEdgeTest1(){
		int n = 100;
		BigFraction edges = new BigFraction(3*(n+1)+1,1); // Two edges: +1, 0 edges
		
		Coefficients cs = new Coefficients();
		
		
		assertTrue(cs.detectPlusOneEdge(edges, n));
	}
	
	@Test
	public void multiEdgeTest2(){
		int n = 100;
		BigFraction edges = new BigFraction(3*(n+1)+1,1); // Two edges: +1, 0 edges
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(edges, n));
	}
	
	@Test
	public void multiEdgeTest3(){
		int n = 100;
		BigFraction edges = new BigFraction(3*(n+1)+1,1); // Two edges: +1, 0 edges
		
		Coefficients cs = new Coefficients();
		
		
		assertFalse(cs.detectMinusOneEdge(edges, n));
	}
	
	@Test
	public void detectPlusTwoEdgeTest1(){
		int n = 100;
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		BigFraction edges = new BigFraction(3*(n+1)+1,1); // Two edges: +1, 0 edges
		edges = edges.multiply(nTimes3);				// Two edges: +2 and +1 edges
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectPlusOneEdge(edges, n));
		assertTrue(cs.detectPlusTwoEdge(edges, n));
	}
	
	@Test
	public void detectMinusTwoEdgeTest1(){
		int n = 100;
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		BigFraction edges = new BigFraction(2,3*(n+1)); // One -1 edge 
		edges = edges.divide(nTimes3);
		Coefficients cs = new Coefficients();
		
		
		assertTrue(cs.detectMinusTwoEdge(edges, n));
	}
	
	@Test
	public void plusTwoEdgeTest1(){
		
		int n = 8;
		Coefficients cs = new Coefficients();
		
		BigFraction p2 = cs.plusTwoEdge(n);
		
	
		assertTrue(cs.detectPlusTwoEdge(p2, n));
	}
	
	@Test
	public void plusTwoEdgeTest2(){
		
		int n = 25;
		Coefficients cs = new Coefficients();
		
		for (int i = 3; i < n; i++) {
			BigFraction p2 = cs.plusTwoEdge(i);
		
	
			assertTrue(cs.detectPlusTwoEdge(p2, i));
		}
	}
	
	
	@Test
	public void minusTwoEdgeTest1(){
		
		int n = 8;
		Coefficients cs = new Coefficients();
		
		BigFraction p2 = cs.minusTwoEdge(n);
		
	
		assertTrue(cs.detectMinusTwoEdge(p2, n));
	}
	
	@Test
	public void minusTwoEdgeTest2(){
		
		int n = 25;
		Coefficients cs = new Coefficients();
		
		for (int i = 3; i < n; i++) {
			BigFraction p2 = cs.minusTwoEdge(i);
		
	
			assertTrue(cs.detectMinusTwoEdge(p2, i));
		}
	}
	
	/*
	 * assertFALSE - put in a +2 edge and try to detect a -2 edge!
	 */
	@Test
	public void minusTwoEdgeTest3(){
		
		int n = 25;
		Coefficients cs = new Coefficients();
		
		for (int i = 3; i < n; i++) {
			BigFraction p2 = cs.plusTwoEdge(i);
		
	
			assertFalse(cs.detectMinusTwoEdge(p2, i));
		}
	}
	
	@Test
	public void detectEpsilonInEdgeTest1(){
		int n = 25;
		Coefficients cs = new Coefficients();
		
		BigFraction minusOne =  cs.negEdge(n);
		BigFraction epsilon = cs.epsilon(n);
		
		BigFraction edge = minusOne.add(epsilon);
		
		assertTrue(cs.detectEpsilonInEdge(edge,n));
	}
	
	@Test
	public void detectEpsilonInEdgeTest2(){
		int n = 25;
		Coefficients cs = new Coefficients();
		
		BigFraction minusTwo =  cs.minusTwoEdge(n);
		BigFraction epsilon = cs.epsilon(n);
		
		BigFraction edge = minusTwo.add(epsilon);
		
		assertTrue(cs.detectEpsilonInEdge(edge,n));
	}
	
	@Test
	public void detectEpsilonInZeroEdgeTest1(){
		int n = 25;
		Coefficients cs = new Coefficients();
		
		BigFraction minusOne =  cs.negEdge(n);
		BigFraction edge = minusOne.add(cs.epsilon(n));
		
		BigFraction posEdge = cs.posEdge(n);
		
		BigFraction edgeProduct = edge.multiply(posEdge);
		
		assertTrue(cs.detectEpsilonInZeroEdge(edgeProduct,n));
	}
	
	@Test
	public void detectEpsilonInZeroEdgeTest2(){
		int n = 25;
		Coefficients cs = new Coefficients();
		
		BigFraction minusOne =  cs.negEdge(n);
		BigFraction edge = minusOne.add(cs.epsilon(n));
		
		BigFraction posEdge = cs.posEdge(n);
		
		BigFraction edgeProduct = edge.multiply(posEdge);
		edgeProduct = edgeProduct.add(edgeProduct);
		
		assertTrue(cs.detectEpsilonInZeroEdge(edgeProduct,n));
	}
	
	
	@Test
	public void detectEpsilonInEdgeTest3(){
		Coefficients cs = new Coefficients();
		
		int n = 30;
		for (int i=5; i < n; i++) {
			BigFraction minusOne = cs.negEdge(i);
			BigFraction epsilon = cs.epsilon(i);
			
			BigFraction edge = minusOne.add(epsilon);
			
			assertTrue(cs.detectEpsilonInEdge(edge,i));
		}
	}
	
}
