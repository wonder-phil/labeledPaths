package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;
import g.Coefficients;

public class flatSemiDyckTests {

	/*
	 * Builds positive half of anti-diagonals
	 * 
	 * utility function
	 */
	public FieldMatrix<BigFraction> halfAntiDiagonal(int initial_row, int final_col, FieldMatrix<BigFraction> mainMatrix, int n) {
		BigFraction one = new BigFraction(1,1);
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> newMainMatrix = mainMatrix.copy();//fm.copyBigFractionMatrix(mainMatrix, n);
		
		for (int i= initial_row, j = final_col-1; i < initial_row + (final_col -  initial_row)/2; i++, j --) {
			mainMatrix.setEntry(i,j, one);
		}
		
		return 	mainMatrix;
	}
	
	//@Ignore
	@Test
	public void halfAntiDiagonalTest1() {
		int n = 15;
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		testMatrix  = halfAntiDiagonal(0, n, testMatrix,n);
		
		for (int i = 0, j = n-1; i < n/2; i++, j--) {
			expectedOutput.setEntry(i, j, one);
			
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == testMatrix.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)));
			}
		}	
	}
	
	//@Ignore
	@Test
	public void halfAntiDiagonalTest2() {
		int n = 3;
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		testMatrix  = halfAntiDiagonal(0, n, testMatrix,n);
		//af.printFieldMatrix(testMatrix, n);
		
		for (int i = 0, j = n-1; i < n/2; i++, j--) {
			expectedOutput.setEntry(i, j, one);
			
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == testMatrix.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)));
			}
		}	
	}
	
	//@Ignore
	@Test
	public void halfAntiDiagonalTest3() {
		int n = 15;
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		//AncillaryFunctions af = new AncillaryFunctions();
		
		/*
		 * One case
		 */
		testMatrix = halfAntiDiagonal(6, 15, testMatrix, n);
		//af.printFieldMatrix(testMatrix, n);
		
		/*
		 * One case
		 */
		
		
		for (int i = 6, j = 14; i < 10; i++, j--) {
			expectedOutput.setEntry(i, j, one);
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == testMatrix.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)));
			}
		}	
	}
	
	//@Ignore
	@Test
	public void halfAntiDiagonalTest4() {
		int n = 15;
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		FieldMatrix<BigFraction> testMatrix = fm.getIdentity(n);
		
		//AncillaryFunctions af = new AncillaryFunctions();
		
		/*
		 * Two cases
		 */
		testMatrix = halfAntiDiagonal(0, 6, testMatrix,n );
		testMatrix = halfAntiDiagonal(6, 15, testMatrix, n);
		//af.printFieldMatrix(testMatrix, n);
		
		/*
		 * Two cases
		 */
		
		for (int i = 0, j = 5; i < 6/2; i++, j--) {
			expectedOutput.setEntry(i, j, one);
		}
		
		for (int i = 6, j = 14; i < 10; i++, j--) {
			expectedOutput.setEntry(i, j, one);
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == testMatrix.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)));
			}
		}	
	}
	
	
	/*
	 * All Boolean line graphs are NOT semi-Dyck and NOT Dyck
	 */
	//@Ignore
	@Test
	public void BooleanLineGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		for (int n =3; n < 16; n += 2) {
			int[][] graphMatrix = Graphs.BooleanLineGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> identity = fm.getIdentity(n);
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
			
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == identity.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	/*
	 * All Boolean line graphs are NOT semi-Dyck and NOT Dycl
	 */
	//@Ignore
	@Test
	public void 	nonSemiDyckGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		for (int n =3; n < 16; n += 2) {
			int[][] graphMatrix = Graphs.	nonSemiDyckGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> identity = fm.getIdentity(n);
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
			
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == identity.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	/*
	 *  LineGraphs are linear graphs that are both Dyck and semi-Dyck
	 *  
	 *  Examples
	 *  
	 *  + -
	 *  + + - -
	 *  + + + - - -
	 *  + + + + - - - -
	 *
	 */
	//@Ignore
	@Test
	public void lineGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		BigFraction onePath = new BigFraction(1,1);
		
		for (int n =3; n < 16; n += 2) {
			int[][] graphMatrix = Graphs.lineGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
			
			for (int i=0; i < n/2; i++) {
				expectedOutput.setEntry(i, n-i-1, onePath);
			}
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
				
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	/*
	 *  Semi-Dyck has fewer paths than the Dyck case
	 *  
	 *  Cycles with labels
	 *  0->()()->0
	 *  0->()()()()->0
	 *  0->()()()()()->0
	 *  
	 */
	//@Ignore
	@Test
	public void alternatingCycleGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		for (int n =4; n < 20; n += 2) {
			
			int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
			
			af.updateFieldMatrix(P1, n);
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

			for (int i=0; i < n; i += 2){
				for (int j=0; j < n; j += 2) {		
					expectedOutput.setEntry(i, j, new BigFraction(1,1));	
				}
			}
			
			//af.printFieldMatrix(output, n);
			
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	//@Ignore
	@Test
	public void nestedPairsLinearGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		for (int n =9; n < 40; n += 8) {
			
			int[][] graphMatrix = Graphs.nestedPairsLinearGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
			
			af.updateFieldMatrix(P1, n);
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

			for (int i=0; i < n; i += 4){
				for (int j= i+4; j < n; j += 4) {		
					expectedOutput.setEntry(i, j, new BigFraction(1,1));	
				}
			}
			
			for (int i=1; i < n; i += 4){
				expectedOutput.setEntry(i, i+2, new BigFraction(1,1));	
			}
			
			/*
			af.printFieldMatrix(output, n);
			System.out.println("-------");
			af.printFieldMatrix(expectedOutput, n);
			*/
			
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	/*
	 * Nested Triples 
	 * 
	 * Linear graphs of the form:
	 * 
	 * ((())) ((()))
	 * ((())) ((()))  ((())) ((()))
	 *
	 */
	//@Ignore
	@Test
	public void nestedTriplesLinearGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		for (int n =13; n < 52; n += 12) {
			
			int[][] graphMatrix = Graphs.nestedTriplesLinearGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
			
			af.updateFieldMatrix(P1, n);
			FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

			for (int i=0; i < n; i += 6){
				for (int j= i+6; j < n; j += 6) {		
					expectedOutput.setEntry(i, j, new BigFraction(1,1));	
				}
			}
			
			for (int i=1; i < n; i += 6){
				expectedOutput.setEntry(i, i+4, new BigFraction(1,1));	
				expectedOutput.setEntry(i+1, i+3, new BigFraction(1,1));	
			}
			
			/*
			af.printFieldMatrix(output, n);
			System.out.println("-------");
			af.printFieldMatrix(expectedOutput, n);
			*/
			
			for (int i=0; i<n; i++) {
				for (int j=0; j< n; j++) {
					assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
				}
			}	
		}
	}
	
	/*
	 * Basic Geometric test
	 *  
	 */
	//@Ignore
	@Test
	public void geometricNestedLinearGraphTest1() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		Coefficients cs = new Coefficients();
		
		int n = 7;
		BigFraction negEdge = cs.negEdge(n);
		BigFraction posEdge = cs.posEdge(n);
		
		int[][] graphMatrix = Graphs.geometricNestedLinearGraph(n);
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		af.updateFieldMatrix(P1, n);
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

		/*
		 * Good to have one hand-rolled
		 * 
		 */
		expectedOutput.setEntry(0, 4, new BigFraction(1,1));
		expectedOutput.setEntry(0, n-1, new BigFraction(1,1));
		expectedOutput.setEntry(1, 4-1, new BigFraction(1,1));
		expectedOutput.setEntry(4, n-1, new BigFraction(1,1));		
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
			}
		}	
	}
	
	/*
	 * Basic Geometric test
	 *  
	 */
	//@Ignore
	@Test
	public void geometricNestedLinearGraphTest2() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		Coefficients cs = new Coefficients();
		
		int n = 7;
		
		BigFraction one = new BigFraction(1,1);
		
		int[][] graphMatrix = Graphs.geometricNestedLinearGraph(n);
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		af.updateFieldMatrix(P1, n);
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

		expectedOutput  = halfAntiDiagonal(0, 5, expectedOutput,n);
		expectedOutput  = halfAntiDiagonal(4, 7, expectedOutput,n);
		
		/* 
		 * from MM:
		 */
		expectedOutput.setEntry(0, 6, one);
		
		//af.printFieldMatrix(expectedOutput, n);
		
		/*
		expectedOutput.setEntry(0, 4, new BigFraction(1,1));
		expectedOutput.setEntry(0, n-1, new BigFraction(1,1));
		expectedOutput.setEntry(1, 4-1, new BigFraction(1,1));
		expectedOutput.setEntry(4, n-1, new BigFraction(1,1));		
		*/
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
			}
		}	
	}
	
	/*
	 * Basic Geometric test
	 *  
	 */
	//@Ignore
	@Test
	public void geometricNestedLinearGraphTest3() {
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		Coefficients cs = new Coefficients();
		
		int n = 15;
		BigFraction one = new BigFraction(1,1);
		
		
		int[][] graphMatrix = Graphs.geometricNestedLinearGraph(n);
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		af.updateFieldMatrix(P1, n);
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);

		expectedOutput  = halfAntiDiagonal(0, 9, expectedOutput,n);
		expectedOutput  = halfAntiDiagonal(8, 13, expectedOutput,n);
		expectedOutput  = halfAntiDiagonal(12, 15, expectedOutput,n);
		
		/*
		 * Matrix multiplication 
		 */
		expectedOutput.setEntry(0, 12, one);
		expectedOutput.setEntry(0, n-1, one);
		expectedOutput.setEntry(8, n-1, one);
		
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("-------");
		af.printFieldMatrix(expectedOutput, n);
		 */
		
		for (int i=0; i<n; i++) {
			for (int j=0; j< n; j++) {
				assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(output.getEntry(i, j)));
			}
		}	
	}
	

	


}
