package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;



public class deepWordsSemiDyckTests {


	
	/*
	 * Semi-Dyck case
	 * 
	 * bigLittleGraph(9,3)
	 *   
	 *    /\
	 *   /  \/\
	 *  /      \
	 *  
	 *  
	 */
	
	@Test
	public void bigLittleGraphTest1() {
	

		int n = 9;	
		int m = 3;
		BigFraction one = new BigFraction(1,1);
		
		int[][] graphMatrix = Graphs.bigLittleGraph(n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.semiDyck(P1, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		expectedOutput.setEntry(0,n-1, one);	
		expectedOutput.setEntry(1,n-2, one);	
		
		expectedOutput.setEntry(1,n-4, one);	
		expectedOutput.setEntry(2,n-5, one);
		
		expectedOutput.setEntry(n-m-1,n-2, one);
		
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
	 * Semi-Dyck case
	 * 
	 *  bigLittleGraph(13,5)
	 * 
	 * 
	 *      /\
	 *     /  \
	 *    /    \  
	 *   /      \/\  
	 *  /          \
	 *  
	 *  
	 */
	
	@Test
	public void bigLittleGraphTest2() {
	

		int n = 13;	
		int m = 5;
		BigFraction one = new BigFraction(1,1);
		
		int[][] graphMatrix = Graphs.bigLittleGraph(n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.semiDyck(P1, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		expectedOutput.setEntry(0,n-1, one);	
		expectedOutput.setEntry(1,n-2, one);	
		
		for (int i =1; i < n-m-2; i++) {
			expectedOutput.setEntry(i,n-m+2-i, one);	
		}
		
		expectedOutput.setEntry(n-m+1,n-2, one);
		
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
	 * Semi-Dyck case
	 * 
	 * using FLAT-Semi-DYCK algorithm !!!
	 * 
	 * Flat-semi-Dyck algorithm
	 *   
	 *    /\
	 *   /  \/\
	 *  /      \
	 *  
	 *  
	 *  This test verifies the exact 0 path from 0 to n-1 (8 here) is not found
	 *  using the flatSemiDyck algorithm
	 *  
	 *  
	 */
	
	@Test
	public void bigLittle_FLAT_FAIL_GraphTest1() {
	

		int n = 9;	
		int m = 3;
		BigFraction one = new BigFraction(1,1);
		
		int[][] graphMatrix = Graphs.bigLittleGraph(n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		/*
		 * 
		 * The FLAT semi Dyck algorithm does not find the exact 0 path from 0 to n-1:   M[0,n-1]
		 *   
		 *   So, we comment out:
		 *   
		 *   expectedOutput.setEntry(0,n-1, one);
		 * 
		 */
			
		expectedOutput.setEntry(1,n-2, one);	
		
		expectedOutput.setEntry(1,n-4, one);	
		expectedOutput.setEntry(2,n-5, one);
		
		expectedOutput.setEntry(n-m-1,n-2, one);
		
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
	 * a FAIL test - 
	 * 
	 * Shows does FLAT-SemiDyck does NOT find the exact 0 path from 0 to n-1 
	 * 
	 * 
	 * see semiDyckWorstCaseTest2 for the correct case with semiDyck
	 * 
	 */
	@Test
	public void semiDyckWorstCaseTest1() {
		
		int m = 1;
	
		int n = Graphs.calc_n(m);
		int[][] graphMatrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1, n);
		
		//af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		/*
		 * 
		 * NO expectedOutput.setEntry(0, n-1, one);
		 * 
		 * 
		 */
		for (int i=1; i < n-1; i += 2){
			for (int j=3; j < n -1; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
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
	
	@Test
	public void semiDyckWorstCaseTest2() {
		
		int m = 1;
	
		BigFraction one = new BigFraction(1,1);
		
		int n = Graphs.calc_n(m);
		int[][] graphMatrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.semiDyck(P1, n);
		
		//af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
			
		
		for (int i=1; i < n-1; i += 2){
			for (int j=3; j < n -1; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
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
	 * 
	 * seems correct - have to update the expected output!
	 */
	
	@Test
	public void semiDyckWorstCaseTest3() {
		
		int m = 2;
	
		BigFraction one = new BigFraction(1,1);
		
		int n = Graphs.calc_n(m);
		int[][] graphMatrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1,n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.semiDyck(P1, n);
		
		af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
			
		
		for (int i=1; i < n-1; i += 2){
			for (int j=3; j < n -1; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
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
