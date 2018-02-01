package u;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.*; 

import g.PGB_Algorithm;

import g.Coefficients;

public class BigIntegerTests {

	/*
	 * BigInteger X.compareTo(BI) = 
	 * -1 if BI < X
	 * 0 if BI == X
	 * +1 if BI > X
	 */
	
	@Test
	public void GTorEQTest(){
		BigInteger one = BigInteger.ONE;
		BigInteger ten = BigInteger.TEN;
		
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.GTorEQ(ten,one));
	}
	
	@Test
	public void BigIntegerCompareToTest() {
		BigInteger one = BigInteger.ONE;
		BigInteger ten = BigInteger.TEN;
		
		
		assertTrue(ten.compareTo(one) >= 0);
		
		assertTrue(ten.compareTo(one) > 0);
		
		assertFalse(ten.compareTo(one) == 0);
	}
	
	
	@Test
	public void BigIntegerCompareTo297Test() {
		BigInteger Smaller297 = BigInteger.valueOf(297);
		BigInteger Bigger300 = BigInteger.valueOf(300);
		
		assertTrue(Smaller297.compareTo(Bigger300) == -1);
	}


}
