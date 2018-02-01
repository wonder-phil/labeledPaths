package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;

public class borderCasesTests {

	/*
	 * This shows the flatDyck algorithm finds the shortest path on   ( () () () )
	 *   
	 */
	@Test
	public void flatDyckTest1() {
	
	/*
	 * n = 9, m = 1:  ( () () () )
	 */
		int n = 9;	
		int m = 1;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=1; i < n; i++){
			for (int j=0; j < n-1; j++) {
				if ( (i+j) % 2 == 0 && j > i) {
					expectedOutput.setEntry(i, j, new BigFraction(1,1));		
				}
			}
		}
		expectedOutput.setEntry(0, n-1, new BigFraction(1,1));	
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutput, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}	
	}
	
	/*
	 * This is a test that a FAILURE OCCURS
	 *  The graph does NOT allow flatDyck to find the exact 0 path: 0-->10 
	 */
	@Test
	public void flatDyck_FAIL_Test2() {
	
	/*
	 * n = 11, m = 1:  ( () () () () )
	 */
		int n = 11;	
		int m = 1;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=1; i < n; i++){
			for (int j=0; j < n-1; j++) {
				if ( (i+j) % 2 == 0 && j > i) {
					expectedOutput.setEntry(i, j, new BigFraction(1,1));		
				}
			}
		}
		
		/*
		 * 
		 * The next expectedOutput is commented out - though it represents a VALID exact 0 path
		 *    the flatDyck algorithm does NOT detect it!!
		 * 
		 */  
		//expectedOutput.setEntry(0, n-1, new BigFraction(1,1));	
		/*
		 * 
		 */
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutput, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}	
	}

	/*
	 * This is a test that a FAILURE OCCURS
	 *  The graph does NOT allow flatSemiDyck to find the exact 0 path: 0-->6 
	 */
	@Test
	public void flatSemiDyck_FAIL_Test2() {
	
	/*
	 * n = 11, m = 1:  ( () () )
	 */
		int n = 7;	
		int m = 1;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=1; i < n; i += 2){
			for (int j=1; j < n; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, new BigFraction(1,1));		
				}
			}
		}
		
		/*
		 * 
		 * The next expectedOutput is commented out - though it represents a VALID exact 0 path
		 *    the flatSemiDyck algorithm does NOT detect it!!
		 * 
		 */  
		//expectedOutput.setEntry(0, n-1, new BigFraction(1,1));	
		/*
		 * 
		 */
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutput.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutput, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}	
	}
	
	
	

}
