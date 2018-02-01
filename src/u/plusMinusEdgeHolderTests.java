package u;

import g.plusMinusEdgeHolder;
import g.Coefficients;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.Test;



public class plusMinusEdgeHolderTests {

	@Test
	public void plusMinusEdgeHolderTest1(){
		
		int n = 17;
		String expectedOutput = "1, ( 54 / 1 ) 0, 2, 4, 6, 8, ; 3, ( 54 / 1 ) 0, 2, 4, 6, 8, ; 5, ( 54 / 1 ) 0, 2, 4, 6, 8, ; 7, ( 54 / 1 ) 0, 2, 4, 6, 8, ; 9, ( 54 / 1 ) 0, 2, 4, 6, 8, ; ";
		
		Coefficients cs = new Coefficients();
		
		BigFraction plusOneEdge = cs.posEdge(n);
		List<Integer> toEdgeList = new ArrayList();
		List<Integer> fromEdgeList = new ArrayList();
		
		int[] t_lst = {0,2,4,6,8 };
		int[] f_lst = {1,3,5,7,9 };
		for (int i=0; i < t_lst.length; i++) {
			toEdgeList.add(t_lst[i]);
			
		}
		
		for(int i=0; i < f_lst.length; i++)
			fromEdgeList.add(f_lst[i]);
		
		plusMinusEdgeHolder pmH = new plusMinusEdgeHolder(fromEdgeList, plusOneEdge,toEdgeList);		
		String actualOutput = pmH.toString();
		
		//System.out.println(actualOutput);
		//System.out.println(expectedOutput);
		
		assertTrue(0 == expectedOutput.compareTo(actualOutput));
		
	}
	
	
}
