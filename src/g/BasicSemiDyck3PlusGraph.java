package g;

public class BasicSemiDyck3PlusGraph extends BaseGraph {
	int val;
	public BasicSemiDyck3PlusGraph(int n, int v) {
		super(n);
		val = v;
		// TODO Auto-generated constructor stub
	}
	
	public int getVal() {
		return this.val;
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n % 2 == 0 && n >= 9) {
			System.err.println("n should be odd!");
			System.out.println();
		
			System.exit(-1);
		}
		
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int m = n/4;
		
		for(int i =0; i < m; i++) {
			matrix[i][i+1] = val; 
		}
		
		for (int i =m; i < n-m; i += 2) {
			matrix[i-1][i] = val;
			matrix[i][i+1] = -val;
		}
		
		for(int i = n-m; i < n-1; i++) {
			matrix[i][i+1] = -val; 
		}
		
		//printMatrix(matrix,n);

	}

}
