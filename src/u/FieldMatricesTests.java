package u;

import static org.junit.Assert.*;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

import g.Coefficients;
import g.FieldMatrices;
import g.Graphs;
import g.AncillaryFunctions;

import org.junit.Test;

public class FieldMatricesTests  {
	

	@Test
	public void changeMatrixAssignment() {
		 int n = 10;
		 
		 
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		FieldMatrices fm = new FieldMatrices();
		
		BigFraction value = new BigFraction(7,3);
		
		
		fm.changeMatrix((FieldMatrix<BigFraction>) M, value);
		
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				assertTrue(M.getEntry(i, j).equals(value));
			}
		}
	}
	
	@Test
	public void getZeroEdgesToTest1(){
		
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(12,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(0,1), }		};
				

		FieldMatrix<BigFraction> fieldMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		int secondColumnIndex = 1;
		
		List<Integer> column1 = fm.getZeroEdgesTo(secondColumnIndex, fieldMatrix, n);
		
		/*
		 * No zeroEdges to column index 1
		 * 
		 *  new BigFraction(12,1)
		 *  new BigFraction(12,1)
		 *  new BigFraction(1,12)
		 *  
		 */
		
		assertTrue(0 == column1.size());
		
	}
	
	
	@Test
	public void getZeroEdgesToTest2(){
		
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(12,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(0,1), }		};
				

		FieldMatrix<BigFraction> fieldMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		int initialColumnIndex = 0;
		
		List<Integer> column1 = fm.getZeroEdgesTo(initialColumnIndex, fieldMatrix, n);
		
		List<Integer> expectedColumn1_output = new ArrayList();
		expectedColumn1_output.add(0);
		expectedColumn1_output.add(2);
		
		Set<Integer> intersect = new HashSet<Integer>(column1);
	    intersect.retainAll(expectedColumn1_output);
		
	    /*
	     * Check: there are two of the same elements in both the expected and actual
	     * 
	     * Column 0:
	     * new BigFraction(1,1)
	     * new BigFraction(12,1)
	     * new BigFraction(1,1)
	     * 
	     */
	    		
		assertTrue(2 == intersect.size());
	}
	
	
	@Test
	public void getZeroEdgesFromTest1(){
		
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(12,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(1,1), }		};
				

		FieldMatrix<BigFraction> fieldMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		int endRowIndex = 2;
		
		List<Integer> column1 = fm.getZeroEdgesFrom(endRowIndex, fieldMatrix, n);
		
		List<Integer> expectedRow2_output = new ArrayList();
		expectedRow2_output.add(0);
		expectedRow2_output.add(2);
		
		Set<Integer> intersect = new HashSet<Integer>(column1);
	    intersect.retainAll(expectedRow2_output);
		
	    /*
	     * Check: there are two of the same elements in both the expected and actual
	     * 
	     * Row 2:
	     * new BigFraction(1,1), new BigFraction(1,12),  new BigFraction(1,1)
	     * 
	     */
	    		
		assertTrue(2 == intersect.size());
	}
	
	@Test
	public void updateMinusOneEdgesTest1(){
		
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,12), },
				{ new BigFraction(1,12), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(1,1), }		};
		FieldMatrix<BigFraction> fieldMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		BigFraction epsilon = new BigFraction(1,3*(n+1)*3*(n+1));
		epsilon = epsilon.multiply(epsilon);
		
		BigFraction[][] expectedOutput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,12).add(epsilon), },
				{ new BigFraction(1,12).add(epsilon), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12).add(epsilon), new BigFraction(1,1), }		};
		
		FieldMatrix<BigFraction> output = fm.updateMinusOneEdges(fieldMatrix, n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++){
				assertTrue(0 == expectedOutput[i][j].compareTo(output.getEntry(i, j)));
			}
		}
	}
	
	
	@Test
	public void countMinusOneEdgesTest1() {
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12, and so AGMY(-1) is 1/(3(n+1)) = 1/12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,12), },
				{ new BigFraction(1,12), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(1,12+12), }		};
		
		FieldMatrix<BigFraction> inputMatrix = fm.copyBigFractionMatrix(actualInput,n);
		final long expectedCount = 3; 
		
		long actualCount = fm.countMinusOneEdges(inputMatrix, n);
		
		assertTrue(expectedCount == actualCount);
	}
	
	@Test
	public void countMinusOneEdgesTest2() {
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12, and so AGMY(-1) is 1/(3(n+1)) = 1/12
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(12,1), },
				{ new BigFraction(12,1), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,1), }		};
		
		FieldMatrix<BigFraction> inputMatrix = fm.copyBigFractionMatrix(actualInput,n);
		final long expectedCount = 0; 
		
		long actualCount = fm.countMinusOneEdges(inputMatrix, n);
		
		assertTrue(expectedCount == actualCount);
	}
	
	@Test
	public void countLeftMinusOneEdgesTest1(){
		
		Coefficients cs = new Coefficients();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
				
		BigFraction epsilon = cs.epsilon(n);
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,12).add(epsilon), },
				{ new BigFraction(1,12).add(epsilon), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12).add(epsilon), new BigFraction(1,1), }		};
		FieldMatrix<BigFraction> inputMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		final long expectedCount = 3; 
		
		long actualCount = fm.countLeftMinusOneEdges(inputMatrix, n);
		
		assertTrue(expectedCount == actualCount);
	}
	
	@Test
	public void countLeftMinusOneEdgesTest2(){
		
		Coefficients cs = new Coefficients();
		FieldMatrices fm = new FieldMatrices();
		
		int n = 3; // so 3(n+1) = 12
				
		BigFraction epsilon = cs.epsilon(n);
		
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1), new BigFraction(12,1), new BigFraction(1,12) },
				{ new BigFraction(1,12+12), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(1,1), }		};
		FieldMatrix<BigFraction> inputMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		final long expectedCount = 0; 
		
		long actualCount = fm.countLeftMinusOneEdges(inputMatrix, n);
		
		assertTrue(expectedCount == actualCount);
	}
	
	
	@Test
	public void removeEpsilonEdgesTest1(){
		
		Coefficients cs = new Coefficients();
		FieldMatrices fm = new FieldMatrices();

		int n = 3; // so 3(n+1) = 12
		BigFraction epsilon = cs.epsilon(n);	
		
		/*
		 * Generating [ AGMY(-1) + epsilon ]*AGMY(+1) = 1 + epsilon*(3(n+1)
		 */
		BigFraction epsilonM3T = epsilon.multiply(new BigFraction(3*(n+1),1));
		
		BigFraction[][] actualInput = { 
				{ new BigFraction(1,1).add(epsilonM3T), new BigFraction(12,1), new BigFraction(1,12) },
				{ new BigFraction(1,12+12), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(1,1).add(epsilonM3T), }		};
		FieldMatrix<BigFraction> inputMatrix = fm.copyBigFractionMatrix(actualInput,n);
		
		BigFraction[][] expectedoutputFractions = { 
				{ new BigFraction(0,1), new BigFraction(12,1), new BigFraction(1,12) },
				{ new BigFraction(1,12+12), new BigFraction(12,1), new BigFraction(0,1), },
				{ new BigFraction(1,1), new BigFraction(1,12), new BigFraction(0,1), }		};
		FieldMatrix<BigFraction> expectedOutput = fm.copyBigFractionMatrix(expectedoutputFractions,n);
		
		FieldMatrix<BigFraction> outputMatrix = fm.removeEpsilonEdges(inputMatrix, n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++){
				assertTrue(0 == expectedOutput.getEntry(i, j).compareTo(outputMatrix.getEntry(i, j)));
			}
		}
	}
	
	@Test
	public void removeEpsilonEdgesTest2(){
		
		Coefficients cs = new Coefficients();
		FieldMatrices fm = new FieldMatrices();

		int n = 17; // so 3(n+1) = 12
		BigFraction epsilon = cs.epsilon(n);		
		
		/*
		 * Generating [ AGMY(-1) + epsilon ]*AGMY(+1) = 1 + epsilon*(3(n+1))
		 */
		BigFraction epsilonM3T = epsilon.multiply(new BigFraction(3*(n+1),1));
		
		BigFraction zeroPlusEpsilon = cs.zeroEdge().add(epsilonM3T);
		BigFraction zeroZeroPlusEpsilon =  zeroPlusEpsilon.add(cs.zeroEdge());
		
		FieldMatrix<BigFraction> inputMatrix = fm.getIdentity(n);
		FieldMatrix<BigFraction> expectedOutputMatrix = fm.getIdentity(n);
		
		for(int i=0; i < n; i++) {
			inputMatrix.setEntry(i, i, zeroZeroPlusEpsilon);
			inputMatrix.setEntry(i, n-i-1, zeroZeroPlusEpsilon);
			
			expectedOutputMatrix.setEntry(i, i, cs.zeroEdge());
			expectedOutputMatrix.setEntry(i, n-i-1, cs.zeroEdge());
		}
		
		FieldMatrix<BigFraction> actualOutputMatrix = fm.removeEpsilonEdges(inputMatrix, n);
		
		for (int i=0; i < n; i++){
			for (int j=0; j < n; j++){
				assertTrue(0 == expectedOutputMatrix.getEntry(i, j).compareTo(actualOutputMatrix.getEntry(i, j)));
			}
		}
	}
	

	
		
}

