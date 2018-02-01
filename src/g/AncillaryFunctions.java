package g;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

public class AncillaryFunctions {

	
	public void copyIntMatrix(int[][] to, int[][] from, int n) {
		
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	public void copyIntVALUEMatrix(int[][] to, int[][] from, int n, int value) {
		
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				if (value == from[i][j] || 0 == from[i][j]) {
					to[i][j] = from[i][j];
				}
			}
		}
	}
	
	public void updateFieldMatrix(FieldMatrix<BigFraction> matrix, int n){
		
		BigFraction one = new BigFraction(1,1);
		BigFraction zero = new BigFraction(0,1);
	
		for(int i=0; i < n; i++) {
			for(int j=0; j < n; j++){
				if (0 == matrix.getEntry(i, j).compareTo(one)) {
					matrix.setEntry(i, j, zero);
				}
			}
		}
	
	}
	
	public void printFieldMatrix(FieldMatrix<BigFraction> matrix, int n){
		for(int i=0; i < n; i++) {
			for(int j=0;j < n; j++){
				if (0.0 == matrix.getEntry(i, j).doubleValue()) {
					System.out.printf("%7d, ", 0);
				} else {
					System.out.printf("%.6f", matrix.getEntry(i, j).doubleValue());
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}
	
	public void printSpecialFieldMatrix(FieldMatrix<BigFraction> matrix, int n){
		Coefficients cs = new Coefficients();
		for(int i=0; i < n; i++) {
			for(int j=0;j < n; j++){
				if (cs.detectZeroEdge(matrix.getEntry(i, j), n )) {
					System.out.print("1 ");
				} else {
					System.out.print("--");
				}
			}
			System.out.println();
		}
	}
	
	public void printIntMatrix(int[][] matrix, int n){
		for(int i=0; i < n; i++) {
			for(int j=0;j < n; j++){
				System.out.printf("%4d", matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void makeBoolean(FieldMatrix<BigFraction> M, int n){
		BigFraction one = new BigFraction(1,1);
		BigFraction zero = new BigFraction(0,1);
		for(int i=0; i < n; i++) {
			for(int j=0; j < n; j++) {
				if (0 != M.getEntry(i, j).compareTo(zero)) {
					
					M.setEntry(i, j,one);
				}
			}
		}
	}
	
	/*
	 * 
	 * Mutates the input matrix M
	 * 
	 * Finds one-edges and replaces them with BigFraction(1,1)
	 *   These are [3*(n+1)]^0 edges.
	 * 
	 * Takes an INT value - may represent infinity
	 */
	public static FieldMatrix<BigFraction> getOnesOnlyFieldMatrix(FieldMatrix<BigFraction> M, int n){
		BigFraction one = new BigFraction(3*(n+1),3*(n+1));
		BigFraction zero = new BigFraction(0,3*(n+1));
		
		Coefficients cs = new Coefficients();
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
				if (cs.detectZeroEdge(M.getEntry(i, j),n)) {  // .compareTo(one)
					M.setEntry(i, j, one);
				} else {
					M.setEntry(i, j, zero);
				}
			}
		}
		
		return M;
	}
		
	
	/*
	 * This function creates a copy!
	 */
	public static FieldMatrix<BigFraction> cloneOnesOnlyFieldMatrix(FieldMatrix<BigFraction> M, int n){
		FieldMatrix<BigFraction> cloneM = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction one = new BigFraction(1,1);
		BigFraction zero = new BigFraction(0,1);
		Coefficients cs = new Coefficients();
		
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
				if (cs.detectZeroEdge(M.getEntry(i, j),n)) {
					cloneM.setEntry(i, j, one);
				} else {
					cloneM.setEntry(i, j, zero);
				}
			}
		}
		
		return cloneM;
	}
	
	/*
	 * This function creates a copy!
	 */
	public static FieldMatrix<BigFraction> cloneFieldMatrix(FieldMatrix<BigFraction> M, int n){
		FieldMatrix<BigFraction> cloneM = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
					cloneM.setEntry(i, j, M.getEntry(i, j));
			}
		}
		
		return cloneM;
	}
	
	/*
	 * Takes an INT value - may represent infinity
	 */
	public static FieldMatrix<BigFraction> setFieldMatrix(FieldMatrix<BigFraction> M, int n, int value){
		
		for (int i = 0; i < n; i++) {
			for (int j=0; j < n; j++){
				M.setEntry(i, j, new BigFraction(value));
			}
		}
		
		return M;
	}
	
	/*
	 * 
	 * Must subtract away +1 and -1 edges since there can be two edges from i-->j for any i,j
	 *   one may be a +1 and the other may be a -1
	 *   
	 */
	public static FieldMatrix<BigFraction> removePlusMinusOnes(FieldMatrix<BigFraction> M, int n){
		BigFraction pos = new BigFraction((n+1)*3,1);
		BigFraction neg = new BigFraction(1,(n+1)*3);
		BigFraction zero = new BigFraction(1,1);
		
		Coefficients cs = new Coefficients();
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				BigFraction newFraction = bigFraction;
				
				//
				// If there is a +1 edge from i to j, then add a -1 edge
				// If there is a -1 edge from i to j, then add a +1 edge
				//
				
				if (cs.detectMinusOneEdge(bigFraction, n)) { // || cs.detectMinusTwoEdge(bigFraction, n) 
					newFraction = newFraction.subtract(cs.negEdge(n));
				}
				
				if (cs.detectPlusOneEdge(bigFraction, n) ) { //  || cs.detectPlusTwoEdge(bigFraction, n)
					newFraction = newFraction.subtract(cs.posEdge(n));
				}
				
				M.setEntry(i, j, newFraction);
			}
		}		
		return M;
	}
	
	/*
	 * 
	 * Removes zero edges: these are edges (3(n+1))^{0} = 1.0
	 *   
	 *   Removes 1.0 values: replaces them with 0
	 *   
	 */
	public static FieldMatrix<BigFraction> removeZeroEdges(FieldMatrix<BigFraction> M, int n){

		Coefficients cs = new Coefficients();
		BigFraction noEdge = new BigFraction(0,1);
		BigFraction one = new BigFraction(1,1);
	
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				bigFraction.reduce();
				
				if (cs.detectZeroEdge(bigFraction, n)) { 
					bigFraction = bigFraction.subtract(one);
					M.setEntry(i, j, noEdge);
				}
			}
		}		
		return M;
	}
	
	/*
	 * Removes +2 and -2 edges from a FieldMatrix 
	 *
	 * Must subtract away +1 and -1 edges since there can be two edges from i-->j for any i,j
	 *   one may be a +1 and the other may be a -1
	 *   
	 */
	public static FieldMatrix<BigFraction> removePlusTwoMinusTwo(FieldMatrix<BigFraction> M, int n){
		Coefficients cs = new Coefficients();
		BigFraction plusTwoEdge = cs.plusTwoEdge(n);
		BigFraction minusTwoEdge = cs.minusTwoEdge(n);
		
		BigFraction zero = new BigFraction(1,1);
		BigFraction noEdge = new BigFraction(0,1);
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				BigFraction newFraction = noEdge;
				
				//
				// If there is a +2 edge from i to j, then remove it
				// If there is a -2 edge from i to j, then remove it
				//
				
				if (cs.detectMinusTwoEdge(bigFraction, n) ){ // if -2, then make -1
					bigFraction = bigFraction.subtract(minusTwoEdge);
				}
				
				if (cs.detectPlusTwoEdge(bigFraction, n) ) { // if +2, then make +1
					bigFraction = bigFraction.subtract(plusTwoEdge);
				}
				
				
				M.setEntry(i, j, bigFraction);
			}
		}
		return M;
	}
	
	/*
	 * Divide +2 and -2 edges by 2 
	 *
	 */
	
	public static FieldMatrix<BigFraction> adjustPlusTwoMinusTwo(FieldMatrix<BigFraction> M, int n){
		BigFraction pos = new BigFraction((n+1)*3,1);
		BigFraction neg = new BigFraction(1,(n+1)*3);
		
		BigFraction zero = new BigFraction(1,1);
		BigFraction noEdge = new BigFraction(0,1);
		
		Coefficients cs = new Coefficients();
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				BigFraction newFraction = noEdge;
				
				//
				// If there is a +2 edge from i to j, then add a -1 edge
				// If there is a -2 edge from i to j, then add a +1 edge
				//
				
				if (cs.detectMinusTwoEdge(bigFraction, n) ){ // if -2, then make -1
					newFraction = newFraction.add(neg);
				}
				
				if (cs.detectPlusTwoEdge(bigFraction, n) ) { // if +2, then make +1
					newFraction = newFraction.add(pos);
				}
				
				if (cs.detectZeroEdge(bigFraction, n) ) { //  if BigFraction(1,1), then add a BigFraction(1,1)
					newFraction = newFraction.add(zero);
				}
				
				M.setEntry(i, j, newFraction);
			}
		}		
		return M;
	}
	
	
	public static FieldMatrix<BigFraction> adjustMultiplesOfZeroEdges(FieldMatrix<BigFraction> M, int n){
	
		BigFraction zero = new BigFraction(1,1);
		
		Coefficients cs = new Coefficients();
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				
				
				//
				// If there is a product of a zero edge, then make it a zero edge
				// else leave it alone
				
				if (cs.detectZeroEdge(bigFraction, n)) { //  if BigFraction(1,1), then add a BigFraction(1,1)
					BigInteger productZeroEdgesNumerator = cs.getZeroEdgeNumerator(bigFraction, n);
					BigFraction subtractMe = new BigFraction(productZeroEdgesNumerator, bigFraction.getDenominator());
					bigFraction = bigFraction.subtract( subtractMe);
					//
					// make sure it still represents a zero edge
					//
					bigFraction = bigFraction.add(zero);
				}
				
				M.setEntry(i, j, bigFraction);
			}
		}		
		return M;
	}
	
	/*
	 * Semi-Normalize
	 * 
	 * Given values from { 1, 1/(3n), 3n}^2 = { 1, 1/(3n), 1/(3n)^2, 3n, 2(3n), (3n)^2...}
	 * and even length +1 and -1 edges
	 * normalize them back to { 1, 1/(3n), 3n}
	 */
	public static FieldMatrix<BigFraction> semiNormalize(FieldMatrix<BigFraction> M, int n){
		BigFraction pos = new BigFraction((n+1)*3,1);
		BigFraction neg = new BigFraction(1,(n+1)*3);
		BigFraction zero = new BigFraction(1,1);
		
		Coefficients cs = new Coefficients();
		
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);//.multiply(3*n);  //cs.balance(M.getEntry(i,j),n);
				/*
				 * bigFraction has a 1 for the denominator
				 */
				BigFraction nFraction = new BigFraction(0,1);
				
				if (cs.detectZeroEdge(bigFraction, n)) {
					nFraction = nFraction.add(cs.zeroEdge());
				}
				
				if (cs.detectMinusOneEdge(bigFraction, n) ){ // || cs.detectMinusTwoEdge(bigFraction, n) 
					nFraction = nFraction.add(cs.negEdge(n));
				}
				
				if (cs.detectPlusOneEdge(bigFraction, n) ) { //  || cs.detectPlusTwoEdge(bigFraction, n)
					nFraction = nFraction.add(cs.posEdge(n));
				}
				
				M.setEntry(i, j, nFraction);
			}
		}
		
		return M;
	}


	
	/*
	 * Given values from { 1, 1/(3n), 3n}^2 = { 1, 1/(3n), 1/(3n)^2, 3n, 2(3n), (3n)^2...}
	 * normalize them back to { 1, 1/(3n), 3n}
	 */
	public static FieldMatrix<BigFraction> normalize(FieldMatrix<BigFraction> M, int n, BigFraction norm){
		BigFraction pos = new BigFraction((n+1)*3,1);
		BigFraction neg = new BigFraction(1,(n+1)*3);
		BigFraction zero = new BigFraction(1,1);
		
		Coefficients cs = new Coefficients();
		
		FieldMatrix<BigFraction> rM = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				/*
				 * bigFraction has a 1 for the denominator
				 */
				BigFraction nFraction = new BigFraction(0,1);
				
				if (cs.detectZeroEdge(bigFraction, n)) {
					nFraction = nFraction.add(cs.zeroEdge());
				}
				
				if (cs.detectMinusOneEdge(bigFraction, n)){
					nFraction = nFraction.add(cs.negEdge(n));
				}
				
				if (cs.detectPlusOneEdge(bigFraction, n)) {
					nFraction = nFraction.add(cs.posEdge(n));
				}
				
					M.setEntry(i, j, nFraction);
			}
		}
		return rM;
	}
	
	
	public static FieldMatrix<BigFraction> removeZeroEdgesWithEpsilons(FieldMatrix<BigFraction> M, int n, BigFraction norm){
		
		Coefficients cs = new Coefficients();
		
		BigFraction minusOne =  new BigFraction(1,3*(n+1));
		BigFraction epsilon = minusOne;
		epsilon = epsilon.multiply(minusOne);
		epsilon = epsilon.multiply(epsilon);
		
		FieldMatrix<BigFraction> rM = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n);
		for (int i = 0 ; i < n; i++) {
			for (int j = 0 ; j < n; j++) {
				
				BigFraction bigFraction = M.getEntry(i, j);
				/*
				 * bigFraction has a 1 for the denominator
				 */
				BigFraction nFraction = new BigFraction(0,1);
				
				if (cs.detectZeroEdge(bigFraction, n)) {
					if (cs.detectEpsilonInEdge(M.getEntry(i, j), n)) {
						nFraction = nFraction.subtract(cs.zeroEdge());
						nFraction = nFraction.subtract(epsilon);
					}
				}
								
		
				M.setEntry(i, j, nFraction);
			}
		}
		return rM;
	}
	
}
