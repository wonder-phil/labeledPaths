package g;

public class ComplexGraph extends BaseGraph {

	public ComplexGraph(int n) {
		super(n);
	}
	
	//overriding fill method to specialize the way the graph is filled
	@Override
	public void buildGraph() {
		int n = this.getTotalNodes();
		int[][] matrix = new int[n][n];
		//zeroMatrix(matrix,n, Integer.MAX_VALUE);
		
		int half = n/2;
		
		for(int i =0; i < n-1; i++) {
			matrix[i][i+1] = 1; 
		}
		matrix[n-1][n-1] = -1;
	}
	
}
