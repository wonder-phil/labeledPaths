package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;


import g.Coefficients;
import g.FieldMatrices;
import g.PGB_Algorithm;
import g.AncillaryFunctions;
import g.Graphs;

public class AncillaryFunctionTests {
	
	
	/*
	 * 
	 * Removes both +2 and -2
	 * 
	 */
	@Test
	public void removePlusMinusTwosTest1() {
		int n = 32;
		FieldMatrices fm = new FieldMatrices();
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		BigFraction plusTwoEdge = cs.plusTwoEdge(n);
		BigFraction minusTwoEdge = cs.minusTwoEdge(n);
		
		
		for (int i =0; i < n; i += 2) {
			for (int j=0; j < n; j += 2) {
				if ((i+j % 2 == 0))
					testMatrix.setEntry(i, j, plusTwoEdge);
				else
					testMatrix.setEntry(i, j, minusTwoEdge);
			}
		}
		
		FieldMatrix<BigFraction> newMatrix = af.removePlusTwoMinusTwo(testMatrix, n);
		
		for (int i =0; i < n; i += 2) {
			for (int j=0; j < n; j += 2) {
				if ((i+j % 2 == 0))
					assertFalse( cs.detectPlusTwoEdge(newMatrix.getEntry(i, j), n));
				else
					assertFalse( cs.detectMinusTwoEdge(newMatrix.getEntry(i, j), n));
			}
		}
		
	}
	
	/*
	 * Tests remove +2 edges ONLY
	 */
	
	@Test
	public void removePlusMinusTwosTest2() {
		int n = 12;
		FieldMatrices fm = new FieldMatrices();
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		BigFraction plusTwoEdge = cs.plusTwoEdge(n);
		
		
		for (int i =0; i < n; i++) {
			for (int j=0; j < n; j++) {
					testMatrix.setEntry(i, j, plusTwoEdge);
			}
		}
		
		FieldMatrix<BigFraction> newMatrix = af.removePlusTwoMinusTwo(testMatrix, n);
		
		for (int i =0; i < n; i++) {
			for (int j=0; j < n; j++) {
				if ((i+j % 2 == 0))
					assertFalse( cs.detectPlusTwoEdge(newMatrix.getEntry(i, j), n));
			}
		}
		
	}
	
	/*
	 * Tests remove -2 edges ONLY
	 */
	
	@Test
	public void removePlusMinusTwosTest3() {
		int n = 15;
		FieldMatrices fm = new FieldMatrices();
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		BigFraction minusTwoEdge = cs.minusTwoEdge(n);
		
		
		for (int i =0; i < n; i++) {
			for (int j=0; j < n; j++) {
					testMatrix.setEntry(i, j, minusTwoEdge);
			}
		}
		
		FieldMatrix<BigFraction> newMatrix = af.removePlusTwoMinusTwo(testMatrix, n);
		
		for (int i =0; i < n; i++) {
			for (int j=0; j < n; j++) {
				if ((i+j % 2 == 0))
					assertFalse( cs.detectMinusTwoEdge(newMatrix.getEntry(i, j), n));
			}
		}
		
	}
	
	@Test
	public void removePlusMinusOnesNEWTest1() {
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 2;
		//
		// So, 3(n+1) is 9
		// 16 is...a +1 edge, so it should be removed.
		//
		BigFraction zero = new BigFraction(0,1);
		BigFraction one = new BigFraction(1,1);
		BigFraction sixteen = new BigFraction(16,1);
		
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n+1);
		
		int i_zero = 0, i_two = 2;
		
		testMatrix.setEntry(i_zero, i_two, sixteen);
		//System.out.println(testMatrix.getEntry(i_zero, i_two).toString());
		
		af.adjustPlusTwoMinusTwo(testMatrix, n);
		
		//System.out.println(testMatrix.getEntry(i_zero, i_two).toString());
		
		Coefficients cs = new Coefficients();
		assertTrue(cs.detectZeroEdge(testMatrix.getEntry(i_zero, i_two), n));
	}


	@Test
	public void MakeBooleanTest() {
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 10;
		BigFraction value = new BigFraction(999,8);
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		fm.changeMatrix(M, value);
		
		af.makeBoolean(M, n);
		
		for(int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				assertTrue(M.getEntry(i,j).compareTo(one) == 0);
			}
		}
	}
	
	@Test
	public void normalizeNegOneEdgeTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 10;
		BigFraction negOneEdge = new BigFraction(1,3*(n+1)); // 1/3n
		BigFraction expected = new BigFraction(1,3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, negOneEdge);
		}
		
		//System.out.println(M);
		
		BigFraction norm = new BigFraction(1,1);
		af.normalize(M, n, norm);
		
		//System.out.println(M);
		
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expected.getNumerator()));
		}	
	}
	
	@Test
	public void normalizeZeroMinusOneEdgeTest2() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 10;
		BigFraction zeroMinusOneEdge = new BigFraction(1+3*(n+1), 3*(n+1)); // (1+3n)/3n = 1/3n + 3n/3n
		BigFraction expected = new BigFraction(1+3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroMinusOneEdge);
		}
		
		//System.out.println(M);
		
		BigFraction norm = new BigFraction(1,1);
		af.normalize(M, n, norm);
		
		//System.out.println(M);
		
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expected.getNumerator()));
		}	
	}
	
	
	@Test
	public void normalizeMEdgeTest3() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 10;
		BigFraction zeroPlusOneEdge = new BigFraction(1+3*(n+1), 3*(n+1)); // (1+3n)/3n
		zeroPlusOneEdge = zeroPlusOneEdge.multiply(2);
		
		BigFraction expected = new BigFraction(1+3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroPlusOneEdge);
		}
		
		//System.out.println(M);
		
		BigFraction norm = new BigFraction(1,1);
		af.normalize(M, n, norm);
		
		//System.out.println(M);
		
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expected.getNumerator()));
		}	
	}
	
	@Test
	public void removeZeroEdgesTest1() {
		AncillaryFunctions af = new AncillaryFunctions();
		
		int n = 10;
		BigFraction zeroMinusOneEdge = new BigFraction(1+3*(n+1), 3*(n+1)); // (1+3n)/3n
		BigFraction expectedNoEdge = new BigFraction(0, 1);
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, one ); // represents 0 edge
		}
		
		af.removeZeroEdges(M, n);
		
		//System.out.println("--------");
		//System.out.println(M);
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).compareTo(expectedNoEdge));
		}	
	}
	
	
	@Test
	public void removePlusMinusOnesTest1() {
	
		AncillaryFunctions af = new AncillaryFunctions();
	
		int n = 10;
		BigFraction zeroMinusOneEdge = new BigFraction(1+3*(n+1), 3*(n+1)); // (1+3n)/3n
		BigFraction expectedZeroEdge = new BigFraction(3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroMinusOneEdge ); // 0 and -1 edges down the diagonal
		}
		
		//System.out.println(M);
		af.removePlusMinusOnes(M, n);
		
		//System.out.println("--------");
		//System.out.println(M);
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expectedZeroEdge.getNumerator()));
		}	
	}
	
	@Test
	public void removePlusMinusOnesTest2() {
	
		AncillaryFunctions af = new AncillaryFunctions();
	
		int n = 10;
		BigFraction zeroPlusOneEdge = new BigFraction(9*(n+1)*(n+1)+3*(n+1), 3*(n+1)); // (9n^2+3n)/3n
		BigFraction expectedZeroEdge = new BigFraction(3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroPlusOneEdge ); // 0 and -1 edges down the diagonal
		}
		
		//System.out.println(M);
		af.removePlusMinusOnes(M, n);
		
		//System.out.println("--------");
		//System.out.println(M);
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expectedZeroEdge.getNumerator()));
		}	
	}
	
	@Test
	public void removePlusMinusOnesTest3() {
	
		AncillaryFunctions af = new AncillaryFunctions();
	
		int n = 10;
		BigFraction zeroPlusOneMinusOneEdge = new BigFraction(9*(n+1)*(n+1)+3*(n+1)+1, 3*(n+1)); // (9n^2+3n)/3n
		BigFraction expectedZeroEdge		= new BigFraction(3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroPlusOneMinusOneEdge ); // 0 and -1 edges down the diagonal
		}
		
		//System.out.println(M);
		af.removePlusMinusOnes(M, n);
		
		//System.out.println("--------");
		//System.out.println(M);
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expectedZeroEdge.getNumerator()));
		}	
	}
	
	@Test
	public void removePlusMinusOnesTest4() {
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		
		int n_p1 = 4;
		//
		// So, 3(n+1) is 12
		//  and 16 should be a 0 edge, which should be removed
		//
		BigFraction zero = new BigFraction(0,1);
		BigFraction one_reps_zero_edge = new BigFraction(1,1);
		BigFraction sixteen = new BigFraction(16,1);
		
		
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n_p1-1);
		
		int i_zero = 0, i_two = 2;
		
		testMatrix.setEntry(i_zero, i_two, sixteen);
		
		af.removePlusMinusOnes(testMatrix, n_p1-1);
		
		//System.out.println(testMatrix.getEntry(i_zero, i_two).toString());
		Coefficients cs = new Coefficients();
		
		assertTrue(cs.detectZeroEdge(testMatrix.getEntry(i_zero, i_two),n_p1-1));
	}
	
	@Test
	public void getOnesOnlyFieldMatrixTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		
		BigFraction fortySix = new BigFraction(46,1);
		BigFraction zero_edge = new BigFraction(1,1);
		
		int n = 14;
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		FieldMatrix<BigFraction> expectedMatrix = fm.getIdentity(n);
		
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
				testMatrix.setEntry(i, j, fortySix);
				expectedMatrix.setEntry(i, j, zero_edge);
			}	
		}
		
		testMatrix = af.getOnesOnlyFieldMatrix(testMatrix,n);
		
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
				if (0 != testMatrix.getEntry(i, j).compareTo(expectedMatrix.getEntry(i, j)))
					fail("Bad getOnesOnlyFieldMatrix !");
			}	
		}
	}
	
	@Test
	public void getOnesOnlyFieldMatrixTest2() {
	
		AncillaryFunctions af = new AncillaryFunctions();
	
		int n = 10;
		BigFraction zeroPlusOneMinusOneEdge = new BigFraction(9*(n+1)*(n+1)+3*(n+1)+1, 3*(n+1)); // ( 9(n+1)^2+3(n+1) +1 )/ [3(n+1)]
		BigFraction expectedZeroEdge		= new BigFraction(3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroPlusOneMinusOneEdge ); //  -1, 0, +1 edges down the diagonal
		}
		
		//System.out.println(M);
		M = af.getOnesOnlyFieldMatrix(M, n);
		
		//System.out.println("--------");
		//System.out.println(M);
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).getNumerator().compareTo(expectedZeroEdge.getNumerator()));
		}	
	}
	
	@Test
	public void cloneFieldMatrixTest1() {
	
		AncillaryFunctions af = new AncillaryFunctions();
	
		int n = 10;
		BigFraction zeroPlusOneMinusOneEdge = new BigFraction(9*n*n+3*n+1, 3*n); // (9n^2+3n)/3n
		BigFraction expectedZeroEdge		= new BigFraction(3*n, 3*n);
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		for(int i=0; i < n; i++){
			M.setEntry(i, i, zeroPlusOneMinusOneEdge ); //  -1, 0, +1 edges down the diagonal
		}
		
		FieldMatrix<BigFraction> clone = af.cloneFieldMatrix(M, n);
		
		for(int i=0; i < n; i++){
			assertTrue(0 == M.getEntry(i, i).compareTo(clone.getEntry(i, i)));
		}	
	}
	
	//
	// clobber the matrix that is cloned
	//   searching for memory issues
	//
	@Test
	public void cloneFieldMatrixTest2() {
	
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
	
		int n = 10;
		BigFraction zeroPlusOneMinusOneEdge = new BigFraction(9*n*n+3*n+1, 3*(n+1)); // (9n^2+3n)/3n
		BigFraction expectedZeroEdge		= new BigFraction(3*(n+1), 3*(n+1));
		
		FieldMatrix<BigFraction> I = fm.getIdentity(100);
		FieldMatrix<BigFraction> other_I = fm.getIdentity(100);
		
		FieldMatrix<BigFraction> clone = af.cloneFieldMatrix(I, n);
		I = null;
		//
		//
		//
		//
		
		for(int i=0; i < n; i++){
			assertTrue(0 == other_I.getEntry(i, i).compareTo(clone.getEntry(i, i)));
		}	
	}
	
	
	
}
