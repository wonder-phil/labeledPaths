package g;

public class SimpleRELineGraph extends BaseGraph {

	public SimpleRELineGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n, Integer.MAX_VALUE);
		
		int half = n/2;
		
		for(int i =0; i < n-1; i++) {
			matrix[i][i+1] = 1; 
		}
		matrix[n-1][n-1] = -1;
	
		
	}

}
