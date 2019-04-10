package u;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

import g.AncillaryFunctions;
import g.FieldMatrices;
import g.Graphs;
import g.PGB_Algorithm;
import g.CycleGraph;
import g.SimpleRELineGraph;
import g.AlternatingLineGraph;
import g.MountainGraph;

public class GraphsTest {

	@Test
	public void cycleGraphTest1() {
		int n = 10;
		FieldMatrices fm = new FieldMatrices();
		
		BigFraction one = new BigFraction(3*(n+1),1);
		BigFraction minusOne = new BigFraction(1,3*(n+1));
		AncillaryFunctions af = new AncillaryFunctions();
		
		//int[][] graphMatrix = Graphs.cycleGraph(n);
		CycleGraph cyclegraph = new CycleGraph(n);
		cyclegraph.buildGraph();
		int [][] graphMatrix = cyclegraph.getMatrix();
		
		
		FieldMatrix<BigFraction> expectedOutputMatrix =  MatrixUtils.createFieldMatrix(BigFractionField.getInstance(), n, n );
		//af.setFieldMatrix(expectedOutputMatrix,n,1);
		
		FieldMatrix<BigFraction> cycleGraph = fm.altCopyFieldMatrix(graphMatrix, n);
		
		//af.printFieldMatrix(cycleGraph, n);
		
		// Anti-diagonal above the diagonal itself.
		for(int i=0; i < n/2; i++) {
			expectedOutputMatrix.setEntry(i, i+1, one);
		}
		
		for(int i= n/2; i < n-1; i++) {
			expectedOutputMatrix.setEntry(i, i+1, minusOne);
		}
		expectedOutputMatrix.setEntry(n-1, 0, minusOne);
		
		//af.printFieldMatrix(expectedOutputMatrix, n);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (cycleGraph.getEntry(i, j).compareTo(expectedOutputMatrix.getEntry(i, j)) != 0 ) {
					fail("Not Equal");
				}
			}
		}
	}
	
	
	@Test
	public void simpleRELineGraphTest1() {
		int n = 10;
		//int[][] graphMatrix = Graphs.simpleRELineGraph(n);
		SimpleRELineGraph simplerellinegraph = new SimpleRELineGraph(n);
		simplerellinegraph.buildGraph();
		int[][] graphMatrix = simplerellinegraph.getMatrix();
		
		int[][] expectedGraphMatrix = new int[n][n];
		
		for (int i = 0 ; i < n-1; i++){
			expectedGraphMatrix[i][i+1] = 1;
		}
		expectedGraphMatrix[n-1][n-1] = -1;
		
		for (int i =0; i < n; i++){
			for (int j =0; j < n ; j++){
				if (graphMatrix[i][j] != expectedGraphMatrix[i][j]){
					fail("Bad simpleRELineGraph! " + i + " " + j);
				}
			}
		}
	}
	
	@Test
	public void basicCycleGraphTest1() {
		int[][] matrix = Graphs.basicCycleGraph(-1,4);
		
		int[][] expected = { { 0,-1,0,0 }, { 0,0,-1,0 }, { 0,0,0,-1 }, { -1,0,0,0 } };
		
		for (int i=0; i < 4; i++) {
			for (int j=0; j < 4; j++) {
				assert(matrix[i][j] == expected[i][j]);
			}
		}
	}
		
	@Test
	public void basicSemiDyckGraphTest1() {

		int n = 7;
		int[][] matrix = Graphs.basicSemiDyckGraph(1,n);
		
		int[][] expectedMatrix = {
				{ 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0 }, 
				{ 0, 0, 0, -1, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 1, 0, 0 }, 
				{ 0, 0, 0, 0, 0, -1, 0 },
				{ 0, 0, 0, 0, 0, 0, -1 },
				{ 0, 0, 0, 0, 0, 0, 0 }
		};
		
		for (int i =0; i < n; i++){
			for (int j=0; j < n; j++) {
				assertTrue(matrix[i][j] == expectedMatrix[i][j]);
			}
		}
		
		/*
		System.out.println("------");
		Graphs.printMatrix(matrix, n);
		System.out.println("------");
		Graphs.printMatrix(expectedMatrix, n);
		*/
	}
	
	@Test
	public void basicSemiDyckGraphTest2() {

		int n = 9;
		int[][] matrix = Graphs.basicSemiDyckGraph(1,n);
		
		int[][] expectedMatrix = {
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, -1, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, -1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, -1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, -1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		
		for (int i =0; i < n; i++){
			for (int j=0; j < n; j++) {
				assertTrue(matrix[i][j] == expectedMatrix[i][j]);
			}
		}
	}

	@Test
	public void nonSemiDyckGraphTest1() {
		int n = 9;
		int[][] matrix = Graphs.nonSemiDyckGraph(n);
		
		int[][] expectedMatrix = {
				{ 0, -1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, -1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, -1, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		
		for (int i =0; i < n; i++){
			for (int j=0; j < n; j++) {
				assertTrue(matrix[i][j] == expectedMatrix[i][j]);
			}
		}
	}
	/*
	 *  Verify a non-semi-Dyck language linear-graph is Dyck
	 */
	@Test
	public void nonSemiDyckGraphTest2() {
		int n = 9;
		int[][] matrix = Graphs.nonSemiDyckGraph(n);
		
		AncillaryFunctions af = new AncillaryFunctions();
		PGB_Algorithm pgb_a = new PGB_Algorithm();
		FieldMatrices fm = new FieldMatrices();
		
		FieldMatrix<BigFraction> P1 = fm.copyFieldMatrix(matrix, n);
		af.updateFieldMatrix(P1, n);
		
		FieldMatrix<BigFraction> output = pgb_a.flatDyck(P1,n);
		
		int[][] expectedMatrix = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			expectedMatrix[i][i] = 1;	
		}
		
		for (int i = 0; i < n/2; i++) {
			expectedMatrix[i][n-i-1] = 1;	
		}
		
		for (int i =0; i < n; i++){
			for (int j=0; j < n; j++) {
				int actualValue = output.getEntry(i,j).intValue();
				assertTrue(actualValue == expectedMatrix[i][j]);
			}
		}
	}
	
	@Test
	public void alternatingLineGraphTest1() {
		int n = 9;
		//int[][] graphMatrix = Graphs.alternatingLineGraph(n);
		AlternatingLineGraph alternatinglinegraph = new AlternatingLineGraph(n);
		alternatinglinegraph.buildGraph();
		int[][] graphMatrix = alternatinglinegraph.getMatrix();
		
		int[][] expectedOutput;
		expectedOutput = new int[n][];
		for (int i =0; i < n; i++) {
			expectedOutput[i] = new int[n];
		}
		
		for (int i =0; i < n-1; i ++) {
			if (i % 2 == 0) {
				expectedOutput[i][i+1] = 1;
			} else {
				expectedOutput[i][i+1] = -1;
			}
		}
		
		for (int i=0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				assertTrue(expectedOutput[i][j] == graphMatrix[i][j]);
			}
		}	
	}
	
	@Test
	public void alternatingLineGraphTest2() {
		
		for (int n = 11; n < 20; n += 2) {
		
			//int[][] graphMatrix = Graphs.alternatingLineGraph(n);
			AlternatingLineGraph alternatinglinegraph = new AlternatingLineGraph(n);
			alternatinglinegraph.buildGraph();
			int[][] graphMatrix = alternatinglinegraph.getMatrix();
			
			int[][] expectedOutput;
			expectedOutput = new int[n][];
			for (int i =0; i < n; i++) {
				expectedOutput[i] = new int[n];
			}
			
			for (int i =0; i < n-1; i ++) {
				if (i % 2 == 0) {
					expectedOutput[i][i+1] = 1;
				} else {
					expectedOutput[i][i+1] = -1;
				}
			}
			
			for (int i=0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					assertTrue(expectedOutput[i][j] == graphMatrix[i][j]);
				}
			}
		}
		
	}
	
	/*
	 * The calc_n tests are for generating worst-case semi-Dyck paths
	 * 
	 * 
	 * Graph sizes:
	 * 
	 * 1, 7, 15, 31, 63, 127,...
	 */
	
	@Test
	public void calc_n_Test1() {
		
		int n = Graphs.calc_n(1);
		
		assertTrue(7 == n);
	}
	
	@Test
	public void calc_n_Test2() {
		
		int n = Graphs.calc_n(2);
		
		assertTrue(15 == n);
	}
	
	@Test
	public void calc_n_Test3() {
		
		int n = Graphs.calc_n(3);
		
		assertTrue(31 == n);
	}
	
	@Test
	public void calc_n_Test4() {
		
		int n = Graphs.calc_n(4);
		
		assertTrue(63 == n);
	}
	
	@Test
	public void calc_n_Test5() {
		
		int n = Graphs.calc_n(5);
		
		assertTrue(127 == n);
	}
		
	
	@Test
	public void worstCaseSemiDyckGraphTest1() {
		
		int m = 1;
		int n = Graphs.calc_n(m);
		int[][] matrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		
		int[][] expectedGraphOutput = {
				{ 0,1,0,0,0,0,0 },
				{ 0,0,1,0,0,0,0 },
				{ 0,0,0,-1,0,0,0 },
				{ 0,0,0,0,1,0,0 },
				{ 0,0,0,0,0,-1,0 },
				{ 0,0,0,0,0,0,-1 },
				{ 0,0,0,0,0,0,0 }
				};
		
		for (int i=0; i < n; i++) {
			for (int j =0; j < n; j++) {
				if (expectedGraphOutput[i][j] != matrix[i][j]) {
					fail(" bad worst-case semiDyck graph !");
				}
			}
		}
	}
	
	@Test
	public void worstCaseSemiDyckGraphTest2() {
		int m = 2;
		int n = Graphs.calc_n(m);
		int[][] matrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		
		int[][] expectedGraphOutput = {
				{ 0,1,0,0,0,0,0,0,0,0,0,0,0,0,0 },
				{ 0,0,1,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,1,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0 },
				{ 0,0,0,0,0,1,0,0,0,0,0,0,0,0,0 },
				{ 0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0 },
				{ 0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0 },
				{ 0,0,0,0,0,0,0,0,1,0,0,0,0,0,0 },
				{ 0,0,0,0,0,0,0,0,0,1,0,0,0,0,0 },
				{ 0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0 },
				{ 0,0,0,0,0,0,0,0,0,0,0,1,0,0,0 },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0 },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0 },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1 },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 } };
		
		for (int i=0; i < n; i++) {
			for (int j =0; j < n; j++) {
				if (expectedGraphOutput[i][j] != matrix[i][j]) {
					fail(" bad worst-case semiDyck graph !");
				}
			}
		}
	}
	
	
	@Test
	public void worstCaseSemiDyckGraphTest3() {
		int m = 3;
		
		int[][] matrix = Graphs.worstCaseSemiDyck(m, 0, 0);
		int n = Graphs.calc_n(m);
		
		int[][] expectedGraphOutput = {
				{ 0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
				{ 0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1  },
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  } };

	
		for (int i=0; i < n; i++) {
			for (int j =0; j < n; j++) {
				if (expectedGraphOutput[i][j] != matrix[i][j]) {
					fail(" bad worst-case semiDyck graph !");
				}
			}
		}
	}
	
	@Test
	public void mountainkGraphTest1() {
		
		int n = 11;
		
		//int[][] matrix = Graphs.mountainkGraph(n);
		MountainGraph mountaingraph = new MountainGraph(n);
		mountaingraph.buildGraph();
		int[][] matrix = mountaingraph.getMatrix();
		
		int[][] expectedOutput = {
				{ 0,1,0,0,0,0,0,0,0,0,0 },
				{ 0,0,1,0,0,0,0,0,0,0,0 },
				{ 0,0,0,-1,0,0,0,0,0,0,0 },
				{ 0,0,0,0,1,0,0,0,0,0,0 },
				{ 0,0,0,0,0,1,0,0,0,0,0 },
				{ 0,0,0,0,0,0,-1,0,0,0,0 },
				{ 0,0,0,0,0,0,0,-1,0,0,0 },
				{ 0,0,0,0,0,0,0,0,1,0,0 },
				{ 0,0,0,0,0,0,0,0,0,-1,0 },
				{ 0,0,0,0,0,0,0,0,0,0,-1 },
				{ 0,0,0,0,0,0,0,0,0,0,0 },
		};
		
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				if (matrix[i][j] != expectedOutput[i][j] ) {
					fail("Mountain graph!");
				}
			}
		}
		
	}
	
	@Test
	public void mountainkGraphTest2() {
		
		int n = 17;
		
		//int[][] matrix = Graphs.mountainkGraph(n);
		MountainGraph mountaingraph = new MountainGraph(n);
		mountaingraph.buildGraph();
		int[][] matrix = mountaingraph.getMatrix();	
		
		int[][] expectedOutput = {
		{ 0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		};

		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				if (matrix[i][j] != expectedOutput[i][j] ) {
					fail("Mountain graph!");
				}
			}
		}
		
	}
}
