package u;

import static org.junit.Assert.*;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;
import g.MountainGraph;
import g.BaseGraph;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;

/*
 * Mountain graphs require TWO invocations of flatDyck or flatSemiDyck
 * 
 */
public class mountainTests {

	@Test
	public void mountainTest1() {
		int n = 11;
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();

		
		//int[][] graphMatrix = Graphs.mountainkGraph(n);
		MountainGraph mountainGraph = new MountainGraph(n);
		mountainGraph.buildGraph();
		int[][] graphMatrix = mountainGraph.getMatrix();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		af.printFieldMatrix(output, n);

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		//fail("Not yet implemented");
	}

/*	
	@Test
	public void mountainTest2() {
		int n = 17;
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();

		
		int[][] graphMatrix = Graphs.mountainkGraph(n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		af.printFieldMatrix(output, n);

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void mountainTest3() {
		int n = 23;
		
		BigFraction one = new BigFraction(1,1);
		
		FieldMatrices fm = new FieldMatrices();
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();

		
		int[][] graphMatrix = Graphs.mountainkGraph(n);
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(graphMatrix, n);
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.mySemiDyck(P1,n);
		
		af.printFieldMatrix(output, n);

		FieldMatrix<BigFraction> expectedOutput = fm.getIdentity(n);
		
		
		fail("Not yet implemented");
	}
	*/
}
