/*NOTES
 * BaseGraph is a simple class for graphs with a simple fill function that is overridden by subclasses of
 * unique or specialized graphs, like the ones that are/need to be.. in ComplexGraph.java 
 * -need to implement specialization and override fill in BaseGraph  
 */
package g;

public abstract class BaseGraph {
	private int half;
	private int totalNodes; //numvert, totalNodes
	private int[][] matrix;
	
	//constructor automatically fills graphs with zeroes
	public BaseGraph(int n) {
		totalNodes = n; 
		half = n/2;
		matrix = new int[n][n];
	}
	
	public int getTotalNodes() {  //synonymous with n  
		return this.totalNodes; 
	}
	
	//this is the function that the child class will override
	public void zeromatrix() {
		int n = getTotalNodes(); 
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				this.matrix[i][j] = 0;
			}
		}
	}
	public abstract void buildGraph();
	
	public int[][] getMatrix() {  
		return this.matrix; 
	}
}
