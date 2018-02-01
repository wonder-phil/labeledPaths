package u;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import org.apache.commons.math3.fraction.*;

public class BigFractionTest {

	@Test
	public void basicBigFractionTest() {
		
		int n = 100;
		BigInteger one = BigInteger.ONE;
		BigFraction number = new BigFraction(n,n);
		
		/*
		 * BigFraction GCDs down its values
		 */
		
		assertTrue(number.getNumerator().compareTo(one)  == 0);
	}
	
	
	@Test
	public void prodBigFractionTest() {
		
		int n = 100;
		
		BigInteger many = BigInteger.valueOf(9999);
		BigFraction number = new BigFraction(n,n);
		
		/*
		 * BigFraction GCDs down its values
		 */
		number = number.multiply(many);
		assertTrue(number.getNumerator().compareTo(many)  == 0);
	}
}
