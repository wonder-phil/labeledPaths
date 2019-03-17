package g;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

public class PGB_Algorithm {
	
	
	private final static boolean DEBUG_ON_0 = false; //true; //  
	private final static boolean DEBUG_ON = false; // true; //     
	private final static boolean DEBUG_2_ON = false; //   true; //
	private final static boolean DEBUG_3_ON = false; // true; //  
	private final static boolean DEBUG_4_ON = true; //false; //  
	
	
	public static void main(String[] args){
		
		int n = 13; //17;
		//int[][] matrix = Graphs.alternatingLineGraph(n); // Graphs.lineGraph(n); // 
		//int[][] graphMatrix = Graphs.alternatingCycleGraph(n);
		//int[][] graphMatrix = Graphs.cycleGraph(n);
		
		//Graphs.alternatingLineGraph(n); 
		//int[][] graphMatrix = Graphs.lineGraph(n); // //Graphs.lineGraph(n);
		//int[][] graphMatrix = Graphs.cycleGraph(n);
		
		int[][] graphMatrix = Graphs.basicSemiDyckGraph(1,n);
		
		
		//Graphs.BooleanLineGraph(n)
		
		FieldMatrices fm = new FieldMatrices();
		
		
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		FieldMatrix<BigFraction> saveInput = fm.copyFieldMatrix(graphMatrix, n);
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		if (DEBUG_ON){
			System.out.println("The New Matrix is");
			af.printFieldMatrix(P1, n);
			System.out.println("-----------");
		}
		
		//FieldMatrix<BigFraction> output = Dyck(P1,graphMatrix,n);
		FieldMatrix<BigFraction> output = flatSemiDyck(P1,n);
		
		if (DEBUG_4_ON){
			af.printFieldMatrix(output, n);
		}
		
		if (DEBUG_3_ON){
			af.printFieldMatrix(output, n);
			System.out.println("---------------plus-one-next--------------");
			get_PM_Edges(output,saveInput,n);
			
			System.out.println("---------------MM-output-next--------------");
			
			FieldMatrix<BigFraction> allEdges = output.multiply(saveInput);
			//allEdges = allEdges.multiply(arg0)
			
		}
	}

	/*
	 * Finds all exact Dyck paths 
	 */
	public static FieldMatrix<BigFraction> Dyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = PN.copy();
		FieldMatrix<BigFraction> saveInput = P1.copy();
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output =  P1.copy();
	
		
		
		for (int i=0; i < Math.ceil(Math.log((double) n)/Math.log(2.0)); i++) { // Math.ceil(Math.log((double) n)/Math.log(2.0))
		
			output = pgb_a.flatDyck(output,n);
	
			output = output.add(saveInput);
			//output = output.add(fm.getIdentity(n));
			output = output.multiply(output);
			output = af.removeZeroEdges(output, n);
			output = af.semiNormalize(output, n);
			
		}
		
		/*
		 * 
		 */
		output = output.multiply(output);
		output = af.semiNormalize(output, n);
		output = output.add(fm.getIdentity(n));
		/*
		 * 
		 * 
		 */
		
		return output;
	}
	
	/*
	 * Finds all exact semiDyck paths 
	 */
	public static FieldMatrix<BigFraction> semiDyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = PN.copy();
		FieldMatrix<BigFraction> saveInput = P1.copy();
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
		
		FieldMatrix<BigFraction> output =  P1.copy();
	
		for (int i=0; i < Math.ceil(Math.log((double) n)/Math.log(2.0)); i++) { 
		
			output = pgb_a.flatSemiDyck(output,n);
			
			output = af.getOnesOnlyFieldMatrix(output, n);
			output = output.add(saveInput);
			output = output.multiply(output);
			output = af.removeZeroEdges(output, n);
			output = af.semiNormalize(output, n);
		}
		
		
		FieldMatrix<BigFraction> leftMatrix = fm.updateMinusOneEdges(output,n);
		output = leftMatrix.multiply(output);
		output = fm.removeEpsilonEdges(output, n);
		output = af.semiNormalize(output, n);
		
		output = output.add(fm.getIdentity(n));
		
		return output;
	}
	
	/*
	 * myDyck runs flatDyck two times
	 */
	
	public static FieldMatrix<BigFraction> myDyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = PN.copy();
		FieldMatrix<BigFraction> saveInput = P1.copy();
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
	
		output = output.add(saveInput);
		output = output.multiply(output);
		output = pgb_a.flatDyck(output,n);
		
		return output;
	}
	
	
	/*
	 * mySemiDyck runs flatSemiDyck two times
	 */
	
	
	public static FieldMatrix<BigFraction> mySemiDyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		FieldMatrices fm = new FieldMatrices();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		
		FieldMatrix<BigFraction> P1 = PN.copy();
		FieldMatrix<BigFraction> saveInput = P1.copy();
		
		af.updateFieldMatrix(P1, n);
		af.updateFieldMatrix(saveInput, n);
	
		FieldMatrix<BigFraction> output = pgb_a.flatSemiDyck(P1,n);
		
		output = af.getOnesOnlyFieldMatrix(output, n);
		output = output.add(saveInput);
		output = output.multiply(output);
		output = af.removeZeroEdges(output, n);
		output = af.semiNormalize(output, n);
		
		
		output = pgb_a.flatSemiDyck(output,n);
		
		return output;
	}
	
	/*
	 * Main Algorithm - for special Dyck labeled graphs
	 * with grammars:
	 * 	D => RR | T
	 *  R => RR | T | \emptyset
	 *  T =>  aTa^{-1} |   a^{-1}Ta  | \emptyset
	 * 
	 * 
	 */
	public static FieldMatrix<BigFraction> flatDyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> ID_Matrix = fm.getIdentity(n);
		FieldMatrix<BigFraction> New_Matrix = fm.getIdentity(n);
		New_Matrix = New_Matrix.add(PN);
		
		FieldMatrix<BigFraction> returnMatrix = null;
		
		if (DEBUG_2_ON){
			System.out.println("xxx-input-matrix-xxx");
			af.printFieldMatrix(New_Matrix, n);
			System.out.println("xxxxxxxxxxx");
		}
		
		for (int ell=1; ell < Math.ceil(Math.log(n)/Math.log(2)); ell++) {
			
			New_Matrix = New_Matrix.multiply(New_Matrix);
			
			if (DEBUG_ON){
				System.out.println("=======A=========after Matrix Mult");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======B=========");
			}
	
			New_Matrix = af.removePlusMinusOnes(New_Matrix, n);
			New_Matrix = af.adjustPlusTwoMinusTwo(New_Matrix, n);
			//
			
			if (DEBUG_ON){
				System.out.println("=======C=========");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======D=========");
			}

			FieldMatrix<BigFraction> Z = af.cloneOnesOnlyFieldMatrix(New_Matrix, n);
			//
			
			if (DEBUG_ON){
				System.out.println("=======Get ones only=========");
				af.printFieldMatrix(Z, n);
				System.out.println("=======Done Get ones only=========");
			}
			
			returnMatrix = af.cloneOnesOnlyFieldMatrix(Z,n);
			
			New_Matrix = New_Matrix.multiply(Z);
			Z = Z.multiply(New_Matrix);
			New_Matrix = af.adjustMultiplesOfZeroEdges(Z, n);
			
			
			if (DEBUG_ON){
				System.out.println("=======after multiply M <- Z M Z=========");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======End after multiply M <- Z M Z=========");
			}
		}
		
		if (DEBUG_ON){
			System.out.println("Done ");
		}
		
		return returnMatrix;
	}	
	
	
	/*
	 * Main Algorithm - for special semi-Dyck labeled graphs
	 * with grammars:
	 * 	D => RR | T
	 *  R => RR | T | \emptyset
	 *  T =>  aTa^{-1} |    \emptyset
	 * 
	 * 
	 */
	
	public static FieldMatrix<BigFraction> flatSemiDyck(FieldMatrix<BigFraction> PN, int n) {
		
		AncillaryFunctions af = new AncillaryFunctions();
		
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> ID_Matrix = fm.getIdentity(n);
		FieldMatrix<BigFraction> New_Matrix = fm.getIdentity(n);
		New_Matrix = New_Matrix.add(PN);

		FieldMatrix<BigFraction> returnMatrix = null;
		
		if (DEBUG_2_ON){
			System.out.println("xxx-input-matrix-xxx");
			af.printFieldMatrix(New_Matrix, n);
			System.out.println("xxxxxxxxxxx");
		}
		
		for (int ell=1; ell < Math.ceil(Math.log(n)/Math.log(2)); ell++) {
			
			/*
			 * Make non-commutative from the left
			 */
			FieldMatrix<BigFraction> leftMatrix = fm.updateMinusOneEdges(New_Matrix,n);
			New_Matrix = leftMatrix.multiply(New_Matrix);
			
			if (DEBUG_ON_0){
				System.out.println("=======A=========after Matrix Mult");
				System.out.println("=======leftMatrix=========");
				af.printFieldMatrix(leftMatrix, n);
				System.out.println("=======New_Matrix=========");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======B=========");
			}
	
			
			New_Matrix = fm.removeEpsilonEdges(New_Matrix, n);
			//System.out.println("=======removed-epsilon-edges=========");
			//af.printFieldMatrix(New_Matrix, n);
			
			New_Matrix = af.removePlusMinusOnes(New_Matrix, n);
			New_Matrix = af.adjustPlusTwoMinusTwo(New_Matrix, n);
			//
			
			if (DEBUG_ON){
				System.out.println("=======C=========");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======D=========");
			}

			FieldMatrix<BigFraction> Z = af.cloneOnesOnlyFieldMatrix(New_Matrix, n);
			//
			
			if (DEBUG_ON){
				System.out.println("=======Get ones only=========");
				af.printFieldMatrix(Z, n);
				System.out.println("=======Done Get ones only=========");
			}
			
			returnMatrix = af.cloneOnesOnlyFieldMatrix(Z,n);
			
			New_Matrix = New_Matrix.multiply(Z);
			Z = Z.multiply(New_Matrix);
			New_Matrix = af.adjustMultiplesOfZeroEdges(Z, n);
			
			
			if (DEBUG_ON){
				System.out.println("=======after multiply M <- Z M Z=========");
				af.printFieldMatrix(New_Matrix, n);
				System.out.println("=======End after multiply M <- Z M Z=========");
			}
		}
		
		return returnMatrix;
	}	
	
	
	/*
	 * plusOneEdges is given a complete 0 label-cost reachability matrix: zeroMatrix
	 * The main algorithm Dyck or semi-Dyck has run and produced the zeroMatrix
	 * 
	 * plusOneEdges computes the +1 reachability matrix and returns it
	 * 
	 */
	public static FieldMatrix<BigFraction> get_PM_Edges(FieldMatrix<BigFraction> allZeroEdgesMatrix, FieldMatrix<BigFraction> origionalInput, int n) {
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		Coefficients cs = new Coefficients();                                                                // UNNESSARY INSTANCE
		
		FieldMatrix<BigFraction> nonCyclePMOneAndZero = MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		BigFraction AGMY_zero =  new BigFraction(1,1);
		
		//af.printFieldMatrix(allZeroEdgesMatrix,n);
		FieldMatrix<BigFraction> tempAllZeros = af.cloneFieldMatrix(allZeroEdgesMatrix, n);
		
		FieldMatrix<BigFraction> P1 = tempAllZeros.multiply(origionalInput);
		
		P1 = P1.multiply(allZeroEdgesMatrix);
		/*
		 * Without considering cycles - or +/- edges between pairs of 0 edges
		 */
		
		
		P1 = P1.add(allZeroEdgesMatrix);
		
		//System.out.println("------------ 3(n+1) = " +  3*(n+1));
		//af.printFieldMatrix(P1,n);

		return P1;
	}
	
	
}

