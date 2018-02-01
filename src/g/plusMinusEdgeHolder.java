package g;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.util.BigReal;

public class plusMinusEdgeHolder {
	
	private BigFraction edge;
	private List<Integer> fromEdgeList = new ArrayList<>();
	private List<Integer> toEdgeList = new ArrayList<>();
	
	public plusMinusEdgeHolder() {
		
	}
	
	public plusMinusEdgeHolder(List<Integer> fromEdgeList, BigFraction edge, List<Integer> toEdgeList) {
		this.fromEdgeList = fromEdgeList;
		this.edge = edge;
		this.toEdgeList = toEdgeList;
	}
	
	public void printEdges() {
		String str = toString();
		
		System.out.println(str);
	}
	
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		for (Integer f : fromEdgeList) { 
			
			str.append(Integer.toString(f) + ", ");
			str.append("( "+Integer.toString(edge.getNumeratorAsInt()) + " / " + Integer.toString(edge.getDenominatorAsInt()) + " ) ");
			
			for (Integer t : toEdgeList) {	
				str.append(Integer.toString(t) + ", ");
			}
			
			str.append("; ");
		}
		return str.toString();
	}
	
	

}
