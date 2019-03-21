/*NOTES
 * BaseGraph is a simple class for graphs with a simple fill function that is overridden by subclasses of
 * unique or specialized graphs, like the ones that are/need to be.. in ComplexGraph.java 
 * -need to implement specialization and override fill in BaseGraph  
 */
package g;

public class BaseGraph {
	private int half;
	private int length;
	private int[][] matrix; 
	
	//constructor automatically fills graphs with zeroes
	public BaseGraph(int n) {
		length = n; 
		half = n/2;
		matrix = new int[n][n];
		
		//fillgraphmatric - virtual function in abstract; technically fills by ZeroMatrix
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = 0;
			}
		}
	}
	
	public int getLength() {  //synonymous with n  
		return this.length; 
	}
	
	//this is the function that the child class will override
	public void buildGraph() {   
		int n = getLength(); 
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				this.matrix[i][j] = 0;
			}
		}
	}
	
	public int[][] getMatrix() {  
		return this.matrix; 
	}
}
