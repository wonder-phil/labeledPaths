package g;


import java.math.BigInteger;
import java.math.RoundingMode;
import java.math.BigDecimal;
import org.apache.commons.math3.fraction.*;

public class Coefficients {
	
	private static final int SIGNIFICANT_DIGITS = 500;
	
	public boolean GTorEQ(BigInteger left, BigInteger right) {
		boolean rValue = false;
		if (left.compareTo(right) == 0 || left.compareTo(right) == +1) {
			rValue = true;
		}
		
		return rValue;
	}
	
	public boolean GT(BigInteger left, BigInteger right) {
		boolean rValue = false;
		if (left.compareTo(right) == +1) {
			rValue = true;
		}
		
		return rValue;
	}
		
	//
	// Zero edges are represented by 
	// 1 = (3n)^{0} 
	//
	public static BigFraction zeroEdge() {
		BigFraction bFraction = new BigFraction(1,1);
		
		return bFraction;
	}
	
	public static BigFraction posEdge(int n){
		int val = 3*(n+1);
		BigFraction bFraction = new BigFraction(val,1);
		
		return bFraction;
	}
	
	public static BigFraction negEdge(int n){
		int val = 3*(n+1);
		BigFraction bFraction = new BigFraction(1,val);
		
		return bFraction;
	}
	
	public static BigFraction plusTwoEdge(int n){
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		BigInteger plusTwoEdge = nTimes3.multiply(nTimes3);
		
		BigFraction bFraction = new BigFraction(plusTwoEdge,BigInteger.ONE);
		
		return bFraction;
	}
	
	public static BigFraction minusTwoEdge(int n){
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		BigInteger plusTwoEdge = nTimes3.multiply(nTimes3);
		
		
		BigFraction bFraction = new BigFraction(BigInteger.ONE,plusTwoEdge);
		
		return bFraction;
	}
	
	public static BigFraction epsilon(int n){
		BigFraction minusOne =  negEdge(n);
		BigFraction epsilon = minusOne;
		epsilon = epsilon.multiply(minusOne);
		epsilon = epsilon.multiply(epsilon);
		
		return epsilon;
	}
	
	public BigFraction createMinusOneEdgeONE_NUM(int n) {
		BigInteger nT3 = BigInteger.valueOf(3*(n+1));
		
		BigFraction edge = new BigFraction(BigInteger.ONE,nT3);
		
		return edge;
	}
	
	public BigFraction createZeroEdge_nSq_DENOM(int n) {
		BigInteger nT3 = BigInteger.valueOf(3*(n+1));
		BigInteger nSquaredT9 = nT3.multiply(nT3);
		
		BigFraction edge = new BigFraction(nSquaredT9, nSquaredT9);
		
		return edge;
	}
	
	public BigFraction createPlusOneEdge_nSq_DENOM(int n) {
		BigInteger nT3 = BigInteger.valueOf(3*(n+1));
		BigInteger nSquaredT9 = nT3.multiply(nT3);
		BigInteger nCubed27 = nSquaredT9.multiply(nT3);
		
		BigFraction edge = new BigFraction(nCubed27, nSquaredT9);
		
		return edge;
	}
	

	public boolean detectMinusOneEdge(BigFraction bigFraction, int n) {
		boolean rValue = false;
		
		BigDecimal nTimes3 = BigDecimal.valueOf(3*(n+1));
		
		BigDecimal bigDecimalFraction = bigFraction.bigDecimalValue(SIGNIFICANT_DIGITS,BigDecimal.ROUND_UP);
		BigDecimal fractionalPart = bigDecimalFraction.remainder(BigDecimal.ONE);
		
		BigInteger checkNumerator = fractionalPart.multiply(nTimes3).toBigInteger();
		
		// Only interested in <= 3(n+1) since we have already multiplied by 3(n+1)
		checkNumerator = checkNumerator.mod(nTimes3.toBigInteger());
		
		BigInteger nMinus1T2 = BigInteger.valueOf((n+1)*2);
		
		if (GTorEQ(nMinus1T2, checkNumerator) &&  GTorEQ(checkNumerator, BigInteger.ONE)) {
			rValue = true;
		}
		
		return rValue;
	}

	public BigInteger getZeroEdgeNumerator(BigFraction bigFraction, int n) {
		
		BigDecimal bigDecimal = bigFraction.bigDecimalValue(BigDecimal.ROUND_FLOOR);
		
		//bigDecimal = bigDecimal.setScale(0, RoundingMode.FLOOR);
		BigInteger bigInt = bigDecimal.toBigInteger();
		
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		
		BigInteger checkNumerator = bigInt.mod(nTimes3);
		
		return checkNumerator;
	}
	
	/*
	 * Count the number of 0 edges in any BigFraction edge
	 */
	public long countZeroEdges(BigFraction bigFraction, int n) {
		
		Coefficients cs = new Coefficients();
		BigFraction zeroEdge = cs.zeroEdge();
		bigFraction = bigFraction.reduce();
		
		long totalZeroEdges = 0;
		
		BigInteger numerator = cs.getZeroEdgeNumerator(bigFraction, n);
		
		while (cs.detectZeroEdge(bigFraction, n)) {
			totalZeroEdges ++;
			bigFraction = bigFraction.subtract(zeroEdge);
		}
		
		return totalZeroEdges;
	}

	
	/*
	 * Remove all zero edges from a BigFraction edge
	 * 
	 * Depends on least-common divisors in BigFractions
	 *   if denom = 1, then no -1 edges
	 *   
	 *   if denom <> 1, then 
	 *      num = actualNum - denom*(num/denom - denom)
	 *          
	 * 
	 */
	public BigFraction removeZeroEdges(BigFraction bigFraction, int n) {
		
		Coefficients cs = new Coefficients();
		
		BigInteger numeratorDivDenom = bigFraction.getNumerator().divide(bigFraction.getDenominator());

		BigFraction newEdge = bigFraction;
		
		if (0 == bigFraction.getDenominator().compareTo(BigInteger.ONE)) {
				long numberOfZeroEdges = cs.countZeroEdges(bigFraction, n);
				newEdge = new BigFraction(bigFraction.getNumerator().subtract(BigInteger.valueOf(numberOfZeroEdges)),BigInteger.ONE);
		} else {
			if (GT(numeratorDivDenom, BigInteger.ZERO)) {
				BigInteger actualNumerator = bigFraction.getNumerator();
				BigInteger actualDenominator = bigFraction.getDenominator();
				newEdge = new BigFraction(actualNumerator.subtract(actualDenominator.multiply(numeratorDivDenom.subtract(actualDenominator))), actualDenominator);
				
			}
		}
		
		return newEdge;
	}
	
	public boolean detectZeroEdge(BigFraction bigFraction, int n) {
		boolean rValue = false;
		
		BigInteger checkNumerator = getZeroEdgeNumerator(bigFraction,n);
		BigInteger nMinus1T3 = BigInteger.valueOf((n+1)*3);
		
		if (GTorEQ(nMinus1T3, checkNumerator) &&  GTorEQ(checkNumerator, BigInteger.ONE)) {
			rValue = true;
		}
		
		return rValue;
	}
	
	public boolean detectPlusOneEdge(BigFraction bigFraction, int n) {
		boolean rValue = false;
		
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		
		bigFraction = bigFraction.divide(nTimes3);
		
		BigDecimal bigDecimal = bigFraction.bigDecimalValue(BigDecimal.ROUND_FLOOR);
		BigInteger checkNumerator = bigDecimal.toBigInteger();
		
		// only interested in the integer part <= 3n since we have already divided by 3n
		checkNumerator = checkNumerator.mod(nTimes3);
		
		
		BigInteger nMinus1T2 = BigInteger.valueOf((n+1)*2);
		
		if (GTorEQ(nMinus1T2, checkNumerator) &&  GTorEQ(checkNumerator, BigInteger.ONE)) {
			rValue = true;
		}
		
		return rValue;
	}
	
	/*
	 * +2 edge detection
	 */
	public boolean detectPlusTwoEdge(BigFraction bigFraction, int n) {

		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		bigFraction = bigFraction.divide(nTimes3);
		
		return detectPlusOneEdge(bigFraction,n);	
	}
	
	/*
	 * -2 edge detection
	 */
	public boolean detectMinusTwoEdge(BigFraction bigFraction, int n) {

		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		bigFraction = bigFraction.multiply(nTimes3);
		
		return detectMinusOneEdge(bigFraction,n);	
	}
	
	/*
	 * Zero edges from AGMY(-1)*AGMY(+1) are not semi-Dyck
	 * detectEpsilonInEdge prevents commutativity for semi-Dyck languages
	 * 
	 * epsilon = 1/((3*(n+1))^4)
	 */
	public boolean detectEpsilonInZeroEdge(BigFraction bigFraction, int n) {

		Coefficients cs = new Coefficients();
	
		
		/*
		 * [ AGMY(-1)+epsilon ]*[ AGMY(+1) ] =
		 * (1/(3(n+1)) + epsilon)*(3(n+1)) = 
		 * 1 + epsilon * 3*(n+1)
		 * 
		 */
		
		bigFraction = bigFraction.multiply(new BigFraction(3*(n+1),1));
		
		
		//bigFraction = bigFraction.multiply(3*(n+1)*3*(n+1));
		
		return detectMinusTwoEdge(bigFraction,n);	
	}
	
	/*
	 * detectEpsilonInEdge prevents commutativity for semi-Dyck languages
	 * 
	 * epsilon = 1/((3*(n+1))^4)
	 */
	public boolean detectEpsilonInEdge(BigFraction bigFraction, int n) {

		//Coefficients cs = new Coefficients();
		
		BigInteger nTimes3 = BigInteger.valueOf(3*(n+1));
		BigInteger epsilon = nTimes3.multiply(nTimes3);
		bigFraction = bigFraction.multiply(epsilon);
		
		return detectMinusTwoEdge(bigFraction,n);	
	}
	
	/*
	 * countEpsilonsInEdge counts epsilons in BigFraction edges
	 * 
	 * epsilon = 1/((3*(n+1))^4)
	 */
	public long countEpsilonsInEdge(BigFraction bigFraction, int n) {

		Coefficients cs = new Coefficients();
		
		long totalEpsilons = 0;
		
		BigFraction epsilon = cs.epsilon(n);
		
		while(detectEpsilonInEdge(bigFraction,n)) {
			totalEpsilons ++;
			bigFraction = bigFraction.subtract(epsilon);
		}
		
		return totalEpsilons;	
	}
	
	/*
	 * countEpsilonsInEdge counts epsilons in BigFraction edges
	 * 
	 * epsilon = 1/((3*(n+1))^4)
	 */
	public long countEpsilonsInZeroEdge(BigFraction bigFraction, int n) {

		Coefficients cs = new Coefficients();
		
		long totalEpsilons = 0;
		
		bigFraction = bigFraction.multiply(cs.negEdge(n));
		BigFraction epsilon = cs.epsilon(n);
		
		while(detectEpsilonInEdge(bigFraction,n)) {
			totalEpsilons ++;
			bigFraction = bigFraction.subtract(epsilon);
		}
		
		return totalEpsilons;	
	}
	
}
 
