package g;

public class TrivialSelfCycleGraph extends BaseGraph{
	int val;
	public TrivialSelfCycleGraph(int n, int v) {
		super(n);
		// TODO Auto-generated constructor stub
		val = v;
	}
	public int getVal() {
		return this.val;
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		int val = this.getVal();
		if (n % 2 != 0) {
			System.err.println("n should be even!");
			System.out.println();
			
			System.exit(-1);
			}
			this.zeromatrix();
			int[][] matrix = this.getMatrix();
			//zeroMatrix(matrix,n);
			
			for(int i =0; i < n-1; i++) {
				matrix[i][i+1] = val; 
			}
			matrix[n-1][0] = val;
			
			//printMatrix(matrix,n);
	}

}
