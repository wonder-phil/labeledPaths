package u;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

import g.FieldMatrices;
import g.Graphs;
//import g.MMFractionalExample;

public class MatrixTests {

	@Test
	public void IdentityTest() {
		FieldMatrices fm = new FieldMatrices();
		
		BigFraction zeroFraction = new BigFraction(0,1);
		BigFraction oneFraction = new BigFraction(1,1);
		
		int n = 100;
		FieldMatrix<BigFraction> I = fm.getIdentity(n);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (i == j) {
					assertTrue(I.getEntry(i, j).equals(oneFraction));
				} else {
					assertTrue(I.getEntry(i, j).equals(zeroFraction));
				}
			}
		}
	}
	
	@Test
	public void IdentityMultiplicationTest() {
		
		int n = 100;
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> I = fm.getIdentity(n);
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction value = new BigFraction(7,3);
		fm.changeMatrix((FieldMatrix<BigFraction>) M, value);
		
		FieldMatrix<BigFraction> PROD = M.multiply(I);
		
		assertTrue(PROD.equals(M));	
	}
	
	/*
	 * Count[0,1] increases by powers of 2 for each MM
	 */
	@Test
	public void Count2MultiplicationTest() {
		
		int n = 100;
		FieldMatrices fm = new FieldMatrices();
	
		FieldMatrix<BigFraction> Count = fm.getIdentity(n);
		BigFraction oneFraction = new BigFraction(1,1);
		Count.setEntry(0, 1, oneFraction);
		
		BigInteger LIMIT = BigInteger.valueOf(5);
		for (int i =0; i < LIMIT.intValue(); i++) {
			Count = Count.multiply(Count);
		}
		
		//MMFractionalExample.printMatrixByRows(Count);
		
		BigInteger two = BigInteger.valueOf(2);
		BigFraction pow = new BigFraction(two.pow(LIMIT.intValue()).intValue(), 1);
		assertTrue(Count.getEntry(0, 1).equals(pow));	
	}
	
	
	/*
	 * Count[0,1] increases by one for each MM
	 */
	@Test
	public void CountMultiplicationTest() {
		
		int n = 100;
		FieldMatrices fm = new FieldMatrices();
	
		FieldMatrix<BigFraction> I = fm.getIdentity(n);
		FieldMatrix<BigFraction> Count = fm.getIdentity(n);
		BigFraction oneFraction = new BigFraction(1,1);
		Count.setEntry(0, 1, oneFraction);
		
		int limitValue = 5;
		BigInteger LIMIT = BigInteger.valueOf(limitValue);
		for (int i =0; i < LIMIT.intValue(); i++) {
			Count = Count.multiply(Count);
		}
		
		int myLimitPower = (int) Math.pow(2.0, limitValue);
		BigFraction fracLimit = new BigFraction(myLimitPower, 1);
		
		//MMFractionalExample.printMatrixByRows(Count);
	
		assertTrue(Count.getEntry(0, 1).equals(fracLimit));	
	}

}
