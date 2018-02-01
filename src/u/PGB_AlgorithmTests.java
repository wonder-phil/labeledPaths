package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Ignore;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;
import g.Coefficients;

public class PGB_AlgorithmTests {
	
	@Test
	public void barbellrGraphTest1() {
		int n = 12;
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int[][] graphMatrix = Graphs.barbellGraph(n);
		
		/*
		af.printIntMatrix(graphMatrix, n);
		*/
		
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		BigFraction one = new BigFraction(1,1);
		
		// Anti-diagonal above the diagonal itself.
		for(int i=1; i <= (n-1)/2; i++) {
			expectedOutputMatrix.setEntry(i, n-i, one);
		}
		expectedOutputMatrix.setEntry(0, 9, one);
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("--expected output:");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	/*
	 *  RE graphs are not computable since we delete the +1 and -1 edges in each iteration
	 *  
	 *  So, the graph 0==+==>1==+==>2==-==>2  (cycle from 2 to 2 with a -1 edge)
	 *  has the -1 edge deleted after the cycle.
	 */
	@Test
	public void simpleRELineGraphTest1() {
		int n = 3;
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int[][] graphMatrix = Graphs.simpleRELineGraph(n);
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		BigFraction one = new BigFraction(1,1);
		
		// Anti-diagonal above the diagonal itself.
		for(int i=1; i < n-1; i++) {
			expectedOutputMatrix.setEntry(i, n -1, one);
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("--expected output:");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void linearGraphTest1() {
		int n = 3;
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int[][] graphMatrix = Graphs.lineGraph(n);
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		BigFraction one = new BigFraction(1,1);
		
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < (n-1)/2; i++) {
			expectedOutputMatrix.setEntry(i, n - i -1, one);
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("--expected output:");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}

	/* Test:
	 * 
	 * (())
	 * 
	 */
	@Test
	public void linearGraphTest2() {
		int n = 5;
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int[][] graphMatrix = Graphs.lineGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		BigFraction one = new BigFraction(1,1);
		
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < (n-1)/2; i++) {
			expectedOutputMatrix.setEntry(i, n - i -1, one);
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/*
		af.printFieldMatrix(output, n);
		System.out.println("-----and-expected-output:");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void linearGraphTest3() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		for (int n = 3; n < 50; n += 2) {
			int[][] graphMatrix = Graphs.lineGraph(n);
			FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
			BigFraction one = new BigFraction(1,1);
		
			// Anti-diagonal above the diagonal itself.
			for(int i=0; i < (n-1)/2; i++) {
				expectedOutputMatrix.setEntry(i, n - i -1, one);
			}
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++) {
					if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
						fail("Not Equal");
					}
				}
			}
		}
	}
	
	@Test
	public void alternatingLineGraphTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3;
		int[][] graphMatrix = Graphs.alternatingLineGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < (n-1)/2; i++) {
			expectedOutputMatrix.setEntry(i, n - i -1, one);
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/*
		af.printFieldMatrix(output, n);
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingLineGraphTest2() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 5;
		int[][] graphMatrix = Graphs.alternatingLineGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// complex-inclusive-Anti-diagonal above the diagonal itself.
		for(int i=0; i < n-2; i++) {
			for(int j = 2+i; j < n; j += 2) {
				expectedOutputMatrix.setEntry(i, j, one);	
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/*
		af.printFieldMatrix(output, n);
		System.out.println("------expected:");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		 
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}

	
	@Test
	public void alternatingLineGraphTest3() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		for (int n = 3; n < 50; n += 2) {
			int[][] graphMatrix = Graphs.alternatingLineGraph(n);
			FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
			BigFraction one = new BigFraction(1,1);
		
			// complex-inclusive-Anti-diagonal above the diagonal itself.
			for(int i=0; i < n-2; i++) {
				for(int j = 2+i; j < n; j += 2) {
					expectedOutputMatrix.setEntry(i, j, one);	
				}
			}
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			
			//af.printFieldMatrix(output, n);
			
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++) {
					if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
						fail("Not Equal");
					}
				}
			}
		}
	}
	
	
	
	
	@Test
	public void alternatingCycleGraphTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 4;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest2() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 8;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest3() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 12;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest4() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 14;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest5() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 16;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/* 
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest6() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 32;
		int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n; i++) {
			for(int j = i % 2; j < n; j += 2){
				expectedOutputMatrix.setEntry(i, j, one);
			}
		}
		
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		/*
		af.printFieldMatrix(output, n);
		System.out.println("---------");
		af.printFieldMatrix(expectedOutputMatrix, n);
		*/
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void alternatingCycleGraphTest7() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		for (int n = 16; n < 20; n += 2){
				
			int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
			FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
			BigFraction one = new BigFraction(1,1);
		
			// Anti-diagonal above the diagonal itself.
			for(int i=0; i < n; i++) {
				for(int j = i % 2; j < n; j += 2){
					expectedOutputMatrix.setEntry(i, j, one);
				}
			}
			
			af.updateFieldMatrix(P1, n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			
			/* 
			af.printFieldMatrix(output, n);
			System.out.println("--------- n: "+n + "  and expected output:");
			af.printFieldMatrix(expectedOutputMatrix, n);
			System.out.println("xxxxxxxxx");
			*/
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++) {
					if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
						fail("Not Equal:" + n);
					}
				}
			}
		}
	}
	
	@Test
	public void cycleGraphTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n=4; 
		int[][] graphMatrix = Graphs.cycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// semi-Anti-diagonal above the diagonal itself.
		for(int i=1; i < n; i++) {
			expectedOutputMatrix.setEntry(i, n - i, one);
		}
	
		//af.printFieldMatrix(expectedOutputMatrix, n);
		af.updateFieldMatrix(P1, n);
		
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		/*
		System.out.println("--------");
		af.printFieldMatrix(output, n);
		*/
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void cycleGraphTest2() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n=6; 
		int[][] graphMatrix = Graphs.cycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// semi-Anti-diagonal above the diagonal itself.
		for(int i=1; i < n; i++) {
			expectedOutputMatrix.setEntry(i, n - i, one);
		}
	
		af.updateFieldMatrix(P1, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
	
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	@Test
	public void cycleGraphTest3() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n=8; 
		int[][] graphMatrix = Graphs.cycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// semi-Anti-diagonal above the diagonal itself.
		for(int i=1; i < n; i++) {
			expectedOutputMatrix.setEntry(i, n - i, one);
		}
	
		af.updateFieldMatrix(P1, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
	
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutputMatrix, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}
	}
	
	@Test
	public void cycleGraphTest4() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n=10; 
		int[][] graphMatrix = Graphs.cycleGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// semi-Anti-diagonal above the diagonal itself.
		for(int i=1; i < n; i++) {
			expectedOutputMatrix.setEntry(i, n - i, one);
		}
	
		af.updateFieldMatrix(P1, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
	
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutputMatrix, n);
					
					fail("Not Equal");
				}
			}
		}
	}
	
	
	@Test
	public void cycleGraphTest5() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		for (int n=4; n < 12; n += 2){
			int[][] graphMatrix = Graphs.cycleGraph(n);
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
			BigFraction one = new BigFraction(1,1);
		
			// semi-Anti-diagonal above the diagonal itself.
			for(int i=1; i < n; i++) {
				expectedOutputMatrix.setEntry(i, n - i, one);
			}
		
			af.updateFieldMatrix(P1, n);
		
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++) {
					if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
						fail("Not Equal");
					}
				}
			}
		}
	}
	
	
	
	//@Ignore
	@Test
	public void threeLoopGraphTest1() {
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		int n=11; 
		//
		// This is a fixed size graph with n=11 nodes
		//
		int[][] graphMatrix = Graphs.threeLoopGraph(n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
	
		BigFraction one = new BigFraction(1,1);
	
		// semi-Anti-diagonal above the diagonal itself.
		
		expectedOutputMatrix.setEntry(1,3, one);
		expectedOutputMatrix.setEntry(0,4, one);
		
		expectedOutputMatrix.setEntry(5,7, one);
		expectedOutputMatrix.setEntry(8,10, one);

	
		af.updateFieldMatrix(P1, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
	
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutputMatrix, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}
	}
	
	
	//@Ignore
	@Test
	public void doubleLoopGraphTest1() {
		int n = 6;
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		AncillaryFunctions af = new AncillaryFunctions();
		
		int[][] matrixNegativeEdges = Graphs.basicCycleGraph(-1,n);
		int[][] matrixPositiveEdges = Graphs.basicCycleGraph(+1,n);
		
		FieldMatrices fm = new FieldMatrices();
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		FieldMatrix<BigFraction> M1 = fm.altCopyFieldMatrix(matrixNegativeEdges, n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(matrixPositiveEdges, n);
		
		M1 = M1.add(P1);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(M1,n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++) {
				if (i % 2 == 0) {
					if (j% 2 == 0){
						expectedOutputMatrix.setEntry(i, j, new BigFraction(1,1));	
					} else {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(0,1));	
					}
					
				} else {
					if (j%2 == 1) {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(1,1));
					} else {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(0,1));
					}
				}
			}
		}
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutputMatrix, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}	
	}
	
	
	//@Ignore
	@Test
	public void doubleLoopGraphTest2() {
		int n = 20;
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		AncillaryFunctions af = new AncillaryFunctions();
		
		int[][] matrixNegativeEdges = Graphs.basicCycleGraph(-1,n);
		int[][] matrixPositiveEdges = Graphs.basicCycleGraph(+1,n);
		
		FieldMatrices fm = new FieldMatrices();
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		FieldMatrix<BigFraction> M1 = fm.altCopyFieldMatrix(matrixNegativeEdges, n);
		FieldMatrix<BigFraction> P1 = fm.altCopyFieldMatrix(matrixPositiveEdges, n);
		
		M1 = M1.add(P1);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(M1,n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++) {
				if (i % 2 == 0) {
					if (j% 2 == 0){
						expectedOutputMatrix.setEntry(i, j, new BigFraction(1,1));	
					} else {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(0,1));	
					}
					
				} else {
					if (j%2 == 1) {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(1,1));
					} else {
						expectedOutputMatrix.setEntry(i, j, new BigFraction(0,1));
					}
				}
			}
		}
		//expectedOutputMatrix.setEntry(0,3,new BigFraction(8,1));
		//System.out.println(expectedOutputMatrix);
		//System.out.println(output);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (output.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					System.out.println(n + ": " + i + "  " + j);
					af.printFieldMatrix(output, n);
					System.out.println("---expected--output---");
					af.printFieldMatrix(expectedOutputMatrix, n);
					
					fail("Not Equal: " + i + " " + j);
				}
			}
		}	
	}
	
	//@Ignore
	@Test
	public void get_PM_EdgesTest1() {
	
		int n = 8;
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		
		int[][] graphMatrix = Graphs.cycleGraph(n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		//
		//
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		//
		//
		
		BigFraction[][] expectedOutputFractions ={
			{ new BigFraction(1,1), new BigFraction(27,1), new BigFraction(0,1),   new BigFraction(0,1),  new BigFraction(0,1), new BigFraction(0,1),  new BigFraction(0,1), new BigFraction(27,1) }, 
			{ new BigFraction(1,27),  new BigFraction(1,1), new BigFraction(27,1), new BigFraction(0,1),   new BigFraction(0,1), new BigFraction(0,1), new BigFraction(27,1), new BigFraction(1,1) }, 
			{ new BigFraction(0,1), new BigFraction(1,27),  new BigFraction(1,1), new BigFraction(27,1), new BigFraction(0,1), new BigFraction(27,1),  new BigFraction(1,1), new BigFraction(1,27) }, 
			{ new BigFraction(0,1),   new BigFraction(0,1), new BigFraction(1,27), new BigFraction(1,1), new BigFraction(27,1), new BigFraction(1,1), new BigFraction(1,27), new BigFraction(0,1) }, 
			{ new BigFraction(0,1),   new BigFraction(0,1), new BigFraction(0,1), new BigFraction(1,27),new BigFraction(1,1), new BigFraction(1,27),  new BigFraction(0,1),  new BigFraction(0,1) }, 
			{ new BigFraction(0,1),   new BigFraction(0,1),  new BigFraction(1,27), new BigFraction(1,1), new BigFraction(27,1),  new BigFraction(1,1), new BigFraction(1,27), new BigFraction(0,1) },
			{ new BigFraction(0,1),  new BigFraction(1,27), new BigFraction(1,1), new BigFraction(27,1),  new BigFraction(0,1), new BigFraction(27,1),  new BigFraction(1,1), new BigFraction(1,27) }, 
			{ new BigFraction(1,27), new BigFraction(1,1), new BigFraction(27,1), new BigFraction(0,1),   new BigFraction(0,1),  new BigFraction(0,1), new BigFraction(27,1),  new BigFraction(1,1) }
		};
		
		FieldMatrix<BigFraction> expectedOutput = fm.copyBigFractionMatrix(expectedOutputFractions, n);
	
		FieldMatrix<BigFraction> pmOneMatrices = pgb_a.get_PM_Edges(output,saveInput,n);
	
		for (int i=0; i < n; i++){
			for(int j=0; j < n; j++){
				assertTrue(expectedOutput.getEntry(i, j).compareTo(pmOneMatrices.getEntry(i, j)) == 0);
			}
		}
	}
	
	/*
	 * Finds +/- edges for lineGraphs:
	 * 
	 * For odd n:
	 * 
	 * 0==+==>1==+==>2...(n-1)/2==-==>[(n-1)/2 -1]==-==>...==-==>(n-1)
	 * 
	 */
	//@Ignore
	@Test
	public void get_PM_EdgesTest2() {
	
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		Coefficients cs = new Coefficients();

		for (int n = 3; n < 20; n += 2) {

			BigFraction posEdge = cs.posEdge(n);
			BigFraction negEdge = cs.negEdge(n);
	
			int[][] graphMatrix = Graphs.lineGraph(n);
			
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
			
			af.updateFieldMatrix(P1, n);
			af.updateFieldMatrix(saveInput, n);
			//
			//
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			//
			//
			
			int m = (n-3)/2;
			FieldMatrix<BigFraction> expectedOutput = output.copy(); // includes all +/- edges
			expectedOutput = expectedOutput.add(saveInput);
			
			for (int i=0; i < m; i++) {
				expectedOutput.setEntry(i, n-2-i, posEdge);
			}
		
			for (int i=0; i < m; i++) {
				expectedOutput.setEntry(i+1, n-1-i, negEdge);
			}
			
			FieldMatrix<BigFraction> pmOneMatrices = pgb_a.get_PM_Edges(output,saveInput,n);
			
			for (int i=0; i < n; i++){
				for(int j=0; j < n; j++){
					assertTrue(expectedOutput.getEntry(i, j).compareTo(pmOneMatrices.getEntry(i, j)) == 0);
				}
			}
		}
	}
	
	/*
	 * 
	 * Plus/minus finder test for alternating line graphs:
	 * 
	 * n should be odd
	 * 0==+==>1==-==>2==+==>3==-==>...(n-1)/2==-==>==+==>...==>(n-1)
	 *
	 * For example:
	 * 0==+==>1==-==>2
	 * 0==+==>1==-==>2==+==>3==-==>4
	 * 
	 */
	//@Ignore
	@Test
	public void get_PM_EdgesTest3() {
	
		int n = 3;
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		Coefficients cs = new Coefficients();
		
		BigFraction posEdge = cs.posEdge(n);
		BigFraction negEdge = cs.negEdge(n);
		
		
		int[][] graphMatrix = Graphs.alternatingLineGraph(n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		//
		//
		//af.printFieldMatrix(P1,n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		//af.printFieldMatrix(output,n);
		//
		//
		int m = (n-1)/2;
		//FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n); // output; //
		
		FieldMatrix<BigFraction> expectedOutput = output.copy(); // includes all +/- edges
		expectedOutput = expectedOutput.add(saveInput);
		
		for (int i=0; i <= m; i++) {
			if (i%2 == 0) {
				expectedOutput.setEntry(i, m+i, posEdge);
			} else {
				expectedOutput.setEntry(i, m+i, negEdge);
			}
		}
	
		//af.printFieldMatrix(expectedOutput,n);
		
		FieldMatrix<BigFraction> pmOneMatrices = pgb_a.get_PM_Edges(output,saveInput,n);
	
		//af.printFieldMatrix(pmOneMatrices,n);
		
		for (int i=0; i < n; i++){
			for(int j=0; j < n; j++){
				assertTrue(expectedOutput.getEntry(i, j).compareTo(pmOneMatrices.getEntry(i, j)) == 0);
			}
		}
	}
	
	/*
	 * 
	 * Plus/minus finder test for alternating line graphs:
	 * 
	 * n should be odd
	 * 0==+==>1==-==>2==+==>3==-==>...(n-1)/2==-==>==+==>...==>(n-1)
	 *
	 * For example:
	 * 0==+==>1==-==>2
	 * 0==+==>1==-==>2==+==>3==-==>4
	 * 
	 */
	//@Ignore
	@Test
	public void get_PM_EdgesTest4() {

		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		Coefficients cs = new Coefficients();
		
		for (int n=3; n < 4; n += 2) {
	
			BigFraction posEdge = cs.posEdge(n);
			BigFraction negEdge = cs.negEdge(n);
			
			
			int[][] graphMatrix = Graphs.alternatingLineGraph(n);
			
			FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
			FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
			
			af.updateFieldMatrix(P1, n);
			af.updateFieldMatrix(saveInput, n);
			//
			//
			//af.printFieldMatrix(P1,n);
			
			FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
			
			//af.printFieldMatrix(output,n);
			//
			//
			int m = (n-1)/2;
			//FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n); // output; //
			
			FieldMatrix<BigFraction> expectedOutput = output.copy(); // includes all +/- edges
			expectedOutput = expectedOutput.add(saveInput);
			
			for (int i=0; i <= m; i++) {
				if (i%2 == 0) {
					expectedOutput.setEntry(i, m+i, posEdge);
				} else {
					expectedOutput.setEntry(i, m+i, negEdge);
				}
			}
		
			//af.printFieldMatrix(expectedOutput,n);
			
			FieldMatrix<BigFraction> pmOneMatrices = pgb_a.get_PM_Edges(output,saveInput,n);
		
			//af.printFieldMatrix(pmOneMatrices,n);
			
			for (int i=0; i < n; i++){
				for(int j=0; j < n; j++){
					assertTrue(expectedOutput.getEntry(i, j).compareTo(pmOneMatrices.getEntry(i, j)) == 0);
				}
			}
		}
	}
	

	
	
	
}
