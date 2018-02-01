package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;

public class simpleDeepWordsTests {

	/*
	 * 
	 * 
	 *   These tests use the programs: 
	 *   flatDyck or flatSemiDyck or myDyck (two invocations of flatDyck) or mySemiDyck (two invocations of flatSemiDyck)
	 * 
	 *   That is one or two invocations of flatDyck or flatSemiDyck
	 * 
	 */
	//@Ignore
	@Test
	public void Dyck3GraphTest1() {
		
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
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/*
		af.printFieldMatrix(saveInput, n);
		System.out.println("-------");
		af.printFieldMatrix(output, n);
		*/
		
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
	 * Compare with: semiDyck3GraphTest
	 *  (( () () () ))
	 */
	//@Ignore
	@Test
	public void Dyck3GraphTest2() {
		int n = 11;	
		int m = 2;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		output = output.add(saveInput);
		output = output.multiply(output);
		output = pgb_a.flatDyck(output,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i = m; i < n -m; i++){
			for (int j = m; j < n -m; j++) {
				if ((i + j) % 2 ==0 && j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
		expectedOutput.setEntry(1,n-2, one);
		expectedOutput.setEntry(0,n-1, one);
		
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
	 * Dyck:
	 *  (( (( () () () )) ))
	 */
	//@Ignore
	@Test
	public void Dyck3GraphTest3() {
		int n = 15;	
		int m = 4;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		output = output.add(saveInput);
		output = output.multiply(output);
		output = pgb_a.flatDyck(output,n);
		
		
		/*
		af.printFieldMatrix(saveInput, n);
		System.out.println("-------");
		af.printFieldMatrix(output, n);
		*/
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i = m; i < n -m; i++){
			for (int j = m; j < n -m; j++) {
				if ((i + j) % 2 ==0 && j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		for (int i = 0; i < m; i++) {
			expectedOutput.setEntry(i,n-i-1, one);
		}
		
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	 * Dyck:
	 * (( (( (( () () () )) )) ))
	 */
	//@Ignore
	@Test
	public void Dyck3GraphTest4() {
		int n = 19;	
		int m = 6;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		output = output.add(saveInput);
		output = output.multiply(output);
		output = pgb_a.flatDyck(output,n);
		
		
		/*
		af.printFieldMatrix(saveInput, n);
		System.out.println("-------");
		af.printFieldMatrix(output, n);
		*/
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i = m; i < n -m; i++){
			for (int j = m; j < n -m; j++) {
				if ((i + j) % 2 ==0 && j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		for (int i = 0; i < m; i++) {
			expectedOutput.setEntry(i,n-i-1, one);
		}
		
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	 * Dyck:
	 * (( ... (( (( (( () () () )) )) )) ... ))
	 */
	//@Ignore
	@Test
	public void Dyck3GraphTest5() {
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		//for (int n = 19, m = 6; n < 40; n += 4, m += 2) {
		for (int n = 19, m = 6; n < 40; n += 2, m += 1) {
		
			int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
	
			
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
			
			af.updateFieldMatrix(P1, n);
			af.updateFieldMatrix(saveInput, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			
			output = output.add(saveInput);
			output = output.multiply(output);
			output = pgb_a.flatDyck(output,n);
			
			
			/*
			af.printFieldMatrix(saveInput, n);
			System.out.println("-------");
			af.printFieldMatrix(output, n);
			*/
			
			FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
			
			for (int i = m; i < n -m; i++){
				for (int j = m; j < n -m; j++) {
					if ((i + j) % 2 ==0 && j > i) {
						expectedOutput.setEntry(i, j, one);	
					}
				}
			}
			for (int i = 0; i < m; i++) {
				expectedOutput.setEntry(i,n-i-1, one);
			}
			
			
			//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
			//System.out.println(expectedOutputMatrix);
			//System.out.println(output);
			
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
	


	/* 
	 * 
	 * This is a very basic test
	 * 
	 * With n=3, it verifies there is no semi-Dyck path
	 * 
	 */
	//@Ignore
	@Test
	public void simpleSemiDyckTest1() {
		int n = 3;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		
		/*
		af.printFieldMatrix(saveInput, n);
		System.out.println("-------");
		af.printFieldMatrix(output, n);
		*/
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++) {
				if (i == j) {
						expectedOutput.setEntry(i, j, new BigFraction(1,1));	
					} else {
						expectedOutput.setEntry(i, j, new BigFraction(0,1));	
					}
				}
			}
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	
	

	
	//@Ignore
	@Test
	public void simpleSemiDyckTest2() {
		int n = 5;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		// af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++) {
				if (i == j) {
						expectedOutput.setEntry(i, j, new BigFraction(1,1));	
					} else {
						expectedOutput.setEntry(i, j, new BigFraction(0,1));	
					}
				}
			}
			expectedOutput.setEntry(0, 2, new BigFraction(1,1));	
			expectedOutput.setEntry(0, 4, new BigFraction(1,1));	
			expectedOutput.setEntry(2, 4, new BigFraction(1,1));	
	
		
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
	
	//@Ignore
	@Test
	public void simpleSemiDyckTest3() {
		int n = 13;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		// af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++) {
				if (i == j) {
						expectedOutput.setEntry(i, j, new BigFraction(1,1));	
					} else {
						expectedOutput.setEntry(i, j, new BigFraction(0,1));	
					}
				}
			}
			expectedOutput.setEntry(0, 12, new BigFraction(1,1));	
			expectedOutput.setEntry(2, 10, new BigFraction(1,1));	
			expectedOutput.setEntry(4, 6, new BigFraction(1,1));
			expectedOutput.setEntry(4, 8, new BigFraction(1,1));
			expectedOutput.setEntry(6, 8, new BigFraction(1,1));
	
		
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
	 * n = 7:  ( () () )
	 */
	//@Ignore
	@Test
	public void simpleSemiDyckTest4() {
		int n = 7;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);

		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		
		
		saveInput = saveInput.add(output);
		
		
		for (int i=0; i < n; i++) {
			saveInput.setEntry(i, i, new BigFraction(0,1));
		}
		//af.printFieldMatrix(saveInput, n);
		
		saveInput = saveInput.multiply(saveInput);
		output = pgb_a.flatSemiDyck(saveInput,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);	
		for (int i=1; i < n-1; i++){
			for (int j=3; j < n-1; j++) {
				if ((i + j) % 2 == 0 && j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
		//af.printFieldMatrix(output, n);
		
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

	//@Ignore
	@Test
	public void simpleSemiDyckTest5() {
		int n = 9;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < 2; i++) {
			expectedOutput.setEntry(i, n-1-i, one);
		}
		
		for (int i=2; i < n - 4; i += 2){
			for (int j=4; j < n -2; j += 2) {
				if (j>i) {
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
	

	//@Ignore
	@Test
	public void simpleSemiDyckTest6() {
		
		BigFraction one = new BigFraction(1,1);
		
		int n = 11;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < 3; i++) {
			expectedOutput.setEntry(i, n-1-i, one);
		}
		
		for (int i=3; i < n - 5; i += 2){
			for (int j=5; j < n -3; j += 2) {
				if (j>i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	 * n = 13: ( ( ( ( () () ) ) ) )
	 */
	//@Ignore
	@Test
	public void simpleSemiDyckTest7() {
		
		BigFraction one = new BigFraction(1,1);
		
		int n = 13;	
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i=0; i < 4; i++) {
			expectedOutput.setEntry(i, n-1-i, one);
		}
		
		for (int i=4; i < n-3; i += 2){
			for (int j=6; j < n-3; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, one);	
				}
			}
		}
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	 * n = 9:  ( () () () )
	 */
	//@Ignore
	@Test
	public void basicSemiDyck3PlusGraphTest1() {
		
		BigFraction one = new BigFraction(1,1);
		
		int n = 9;	
		int[][] graphMatrix = Graphs.basicSemiDyck3PlusGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
		
		for (int i=1; i < n-1; i += 2){
			for (int j=3; j < n-1; j += 2) {
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
	 * n = 11: ( () () () () )
	 */
	//@Ignore
	@Test
	public void basicSemiDyck3PlusGraphTest2() {
		
		BigFraction one = new BigFraction(1,1);
		
		int n = 11;	
		int[][] graphMatrix = Graphs.basicSemiDyck3PlusGraph(1,n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
	
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
		expectedOutput.setEntry(1, n-2, one);
		
		for (int i=1; i < n-1; i += 2){
			for (int j=3; j < n-1; j += 2) {
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
	 * mySemiDyck(P1,n) !
	 * 
	 * n = 9, m = 1:  ( () () () )
	 * 
	 */
	//@Ignore
	@Test
	public void semiDyck3GraphTest1() {
		int n = 9;	
		int m = 1;
		
		BigFraction one = new BigFraction(1,1);
		
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
		
		for (int i=1; i < n-1; i += 2){
			for (int j=1; j < n-1; j += 2) {
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
	
	//@Ignore
	@Test
	public void semiDyck3GraphTest2() {
		int n = 11;	
		int m = 1;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		//
		//FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		//
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
		
		for (int i=1; i < n; i += 2){
			for (int j=3; j < n; j += 2) {
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

	//@Ignore
	@Test
	public void semiDyck3GraphTest3() {
		int n = 11;	
		int m = 2;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		BigFraction one = new BigFraction(1,1);
		//af.printIntMatrix(graphMatrix, n);
		
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		//
		//FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		//
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		//af.printFieldMatrix(output, n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		expectedOutput.setEntry(0, n-1, one);
		expectedOutput.setEntry(1, n-2, one);
		
		for (int i=m; i < n-m+1; i += 2){
			for (int j=m+2; j < n-m; j += 2) {
				if (j > i) {
					expectedOutput.setEntry(i, j, one);
				}
			}
		}
		
		
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
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
	 * mySemiDyck !!!
	 * 
	 */
	//@Ignore
	@Test
	public void semiDyck3GraphTest4() {
		int n = 13;	
		int m = 3;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		BigFraction one = new BigFraction(1,1);
		//af.printIntMatrix(graphMatrix, n);
		
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i= 0; i < m; i++) {
			expectedOutput.setEntry(i, n-i-1, one);
		}
		
		for (int i=m; i < n-m; i += 2){
			for (int j=m+2; j < n -m; j +=2) {
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
	 * DYCK Dyck Dyck test
	 * 
	 */
	//@Ignore
	@Test
	public void semiDyck3GraphTest5() {
		int n = 11;	
		int m = 2;
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		BigFraction one = new BigFraction(1,1);
		//af.printIntMatrix(graphMatrix, n);
		
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i= 0; i < m; i++) {
			expectedOutput.setEntry(i, n-i-1, one);
		}
		
		for (int i=m; i < n-m; i += 2){
			for (int j=m+2; j < n -m; j += 2) {
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
	 * mySemiDyck !!
	 * 
	 * 
	 */
	//@Ignore
	@Test
	public void semiDyck3GraphTest6() {
		int n = 13;	
		int m = 3;
		
		int[][] graphMatrix = Graphs.semiDyck3Graph(1,n,m);
		
		BigFraction one = new BigFraction(1,1);
		//af.printIntMatrix(graphMatrix, n);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		//
		//FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		//
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		
		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		for (int i= 0; i < m; i++) {
			expectedOutput.setEntry(i, n-i-1, one);
		}
		
		for (int i=3; i < n-3; i += 2){
			for (int j=5; j < n -3; j += 2) {
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
