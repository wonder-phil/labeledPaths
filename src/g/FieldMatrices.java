package g;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

public class FieldMatrices {
	
	public static void changeMatrix(FieldMatrix<BigFraction> M, BigFraction value) {
		int n = M.getColumnDimension();
		
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				M.setEntry(i, j, value);
			}
		}
	}
	
	/*
	 * 
	 * Given an { -1, 0, +1 } matrix, build a FieldMatrix<BigFraction> with 
	 * ONLY x and 0 values
	 * 
	 */
	public static FieldMatrix<BigFraction> copyValueMatrix(int matrix[][], int n, int x) {
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction zero =  new BigFraction(0,1);
		BigFraction one =  new BigFraction(1,1);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				
				if(0 == matrix[i][j]) {
					M.setEntry(i, j, zero);
				}
				
				if (x == matrix[i][j]) {
					M.setEntry(i, j, one);
				} 
			}
		}
		
		return M;
	}
	
	
	/*
	 * 
	 * Given a matrix of BigFractions, build a FieldMatrix<BigFraction> 
	 * 
	 */
	public static FieldMatrix<BigFraction> copyBigFractionMatrix(BigFraction matrix[][], int n) {
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction zero =  new BigFraction(0,1);
		BigFraction one =  new BigFraction(1,1);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				M.setEntry(i, j, matrix[i][j]);
			}
		}
		
		return M;
	}
	
	/*
	 * 
	 * Given an { -1, 0, +1 } matrix, build a FieldMatrix<BigFraction> with 
	 * 1/(3n), 1, 3n
	 * 
	 */
	public static FieldMatrix<BigFraction> copyFieldMatrix(int matrix[][], int n) {
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction zero =  new BigFraction(1,1);
		BigFraction one =  new BigFraction(3*(n+1),1);
		BigFraction minusOne =  new BigFraction(1,3*(n+1));
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				
				if(0 == matrix[i][j]) {
					M.setEntry(i, j, zero);
				}
				
				if (-1 == matrix[i][j]) {
					M.setEntry(i, j, minusOne);
				} 
				
				if (1 == matrix[i][j]) {
					M.setEntry(i, j, one);
				} 
			}
		}
		return M;
	}
		
		/*
		 * 
		 * Given an { -1, +1, infinity } matrix, build a FieldMatrix<BigFraction> with 
		 * 1/(3n), 3n, 0
		 * 
		 */
		public static FieldMatrix<BigFraction> altCopyFieldMatrix(int matrix[][], int n) {
			
			FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
			BigFraction infinity =  new BigFraction(0,1);
			BigFraction one =  new BigFraction(3*(n+1),1);
			BigFraction minusOne =  new BigFraction(1,3*(n+1));
			
			for (int i=0; i < n; i++) {
				for(int j=0; j < n; j++){
					
					if(0 == matrix[i][j]) {
						M.setEntry(i, j, infinity);
					}
					
					if (-1 == matrix[i][j]) {
						M.setEntry(i, j, minusOne);
					} 
					
					if (1 == matrix[i][j]) {
						M.setEntry(i, j, one);
					} 
				}
			}
		
		return M;
	}
	
	
	
	
	/*
	 * 
	 * Given an Boolean matrix, build a FieldMatrix<BigFraction> with 
	 * Boolean values
	 * 
	 */
	public static FieldMatrix<BigFraction> copyBooleanMatrix(int matrix[][], int n) {
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction zero =  new BigFraction(0,1);
		BigFraction one =  new BigFraction(1,1);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				
				if(0 == matrix[i][j]) {
					M.setEntry(i, j, zero);
				}
				
				if (1 == matrix[i][j]) {
					M.setEntry(i, j, one);
				} 
			}
		}
		
		return M;
	}
	/*
	 * 
	 * Given an integer matrix, build a FieldMatrix<BigFraction> with 
	 * { 1/(3n), 1, 3n }
	 * 
	 */
	public static FieldMatrix<BigFraction> copyRealMatrix(int matrix[][], int n) {
		
		FieldMatrix<BigFraction> M = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction zero = new BigFraction(0,1);
		/*
		 * Initial matrix has NO zero edges - zero represents infinity! 
		 */
		Coefficients cs = new Coefficients();
		
		BigFraction neutralVal =  cs.zeroEdge();
		BigFraction negVal = cs.negEdge(n);
		BigFraction posVal = cs.posEdge(n);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				
				if(-1 == matrix[i][j]) {
					M.setEntry(i, j, negVal);
				}
				
				if (0 == matrix[i][j]) { 
					M.setEntry(i, j, zero);
				} 
				if(1 == matrix[i][j]) {
					M.setEntry(i, j, posVal);
				}
			}
		}
		
		return M;
	}
	
	public static FieldMatrix<BigFraction> getIdentity(int n) {
		
		FieldMatrix<BigFraction> I = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		
		BigFraction zeroFraction = new BigFraction(0,1);
		BigFraction oneFraction = new BigFraction(1,1);
		
		changeMatrix(I,zeroFraction);
		
		for (int i =0; i < n; i++) {
			I.setEntry(i, i, oneFraction);
		}
		
		return I;
	}
	
	
	/*
	 * 
	 * Given any FieldMatrix<BigFraction> matrix, replace the AGMY(-1) edges with AGMY(-1) + 1/(3(n+1))^4
	 * Adding the 1/(3(n+1))^4 makes the algebraic matrix product non-commutative
	 *   We want the left matrices to not contain AGMY(-1) to combine with the right matrices AGMY(+1) for semiDyck languages
	 * 
	 * 
	 */
	public static FieldMatrix<BigFraction> updateMinusOneEdges(FieldMatrix<BigFraction> matrix, int n) {
		
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrix<BigFraction> M = af.cloneFieldMatrix(matrix, n);
		BigFraction minusOne =  cs.negEdge(n);
		BigFraction epsilon = cs.epsilon(n);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				if (cs.detectMinusOneEdge(M.getEntry(i, j), n)) {
					M.setEntry(i, j, M.getEntry(i, j).add(epsilon));
				} 
			}
		}
		return M;
	}
	
	/*
	 *  Count the total number of AGMY(-1 + EPSILON) edges in a matrix cell - immediately after an algebraic matrix product
	 *    where EPSILON = 1/((3*(n+1))^4)
	 */
	public static long countLeftMinusOneEdges(FieldMatrix<BigFraction> matrix, int n) {
		
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		long count = 0;
		
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				if (cs.detectEpsilonInEdge(matrix.getEntry(i, j), n)) {
					count += 1;
				} 
			}
		}
		return count;
	}
	
	/*
	 * Count the total number of AGMY(-1) edges in all matrix cells - immediately after an algebraic matrix product
	 * 
	 */
	public static long countMinusOneEdges(FieldMatrix<BigFraction> matrix, int n) {
		
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		long count = 0;
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				if (cs.detectMinusOneEdge(matrix.getEntry(i, j), n)) {
					count += 1;
				} 
			}
		}
		
		return count;
	}
	
	
	/*
	 * Remove all AGMY(0) = 1 edges MARKED with epsilon - immediately after an algebraic matrix product
	 * Assumes only 1 AGMY(0) edge.
	 * 
	 */
	public static FieldMatrix<BigFraction> removeEpsilonEdges(FieldMatrix<BigFraction> matrix, int n) {
		
		Coefficients cs = new Coefficients();
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrix<BigFraction> M = af.cloneFieldMatrix(matrix, n);
		BigFraction zeroEdge =  cs.zeroEdge();
		BigFraction epsilon = cs.epsilon(n);
		
		for (int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				BigFraction originalEdge = M.getEntry(i, j); // detectEpsilonInZeroEdge
				if (cs.detectZeroEdge(originalEdge,n)) {
					if (cs.detectEpsilonInZeroEdge(originalEdge, n)) {
						long totalEpsilons = cs.countEpsilonsInZeroEdge(originalEdge, n); // cs.countEpsilonsInEdge(originalEdge, n);
						
						for (int k=0; k < totalEpsilons; k++) {
							originalEdge = originalEdge.subtract(epsilon.multiply(3*(n+1)));
							originalEdge = originalEdge.subtract(cs.zeroEdge());
						}
						
						M.setEntry(i, j, originalEdge);
					}
				} 
			}
		}
		
		return M;
	}
	
	
	/*
	 * 
	 * The functions getZeroEdgesTo and getZeroEdgesFrom may NOT be necessary!
	 */
	
	public static List<Integer> getZeroEdgesTo(int column, FieldMatrix<BigFraction> matrix, int n) {
		Coefficients cs = new Coefficients();
		
		List<Integer> toZeroEdges = new ArrayList();
		
		
		for (int row=0; row < n; row++) {
			if (cs.detectZeroEdge(matrix.getEntry(row, column), n)) {
				toZeroEdges.add(row);
			}
		}
		
		return toZeroEdges;
	}
	
	/*
	 * 
	 * The functions getZeroEdgesTo and getZeroEdgesFrom  may NOT be necessary!
	 */
	public static List<Integer> getZeroEdgesFrom(int row, FieldMatrix<BigFraction> matrix, int n) {
		Coefficients cs = new Coefficients();
		
		List<Integer> fromZeroEdges = new ArrayList();
		
		
		for (int column=0; column < n; column++) {
			if (cs.detectZeroEdge(matrix.getEntry(row, column), n)) {
				fromZeroEdges.add(column);
			}
		}
		
		return fromZeroEdges;
	}
	
	
}
