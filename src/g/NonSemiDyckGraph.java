package g;

public class NonSemiDyckGraph extends BaseGraph {

	public NonSemiDyckGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n % 2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
		
			System.exit(-1);
		}
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		for(int i =0; i < n/2; i++) {
			matrix[i][i+1] = -1; 
		}
		
		for(int i = n/2; i < n-1; i++) {
			matrix[i][i+1] = 1; 
		}
	}

}
