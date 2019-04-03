package g;

public class AlternatingCycleGraph extends BaseGraph{

	public AlternatingCycleGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n%2 == 1) {
			System.err.println("n should be even!");
			System.out.println();
			
			System.exit(-1);
		}
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		
		for(int i =0; i < n; i++) {
			for(int j =0; j < n; j++) {
				if(j-1 == i) {
					if (j%2 == 0) {
						matrix[i][j] = -1; 
					} else {
						matrix[i][j] = 1;
					}
				}

			}
		}
		matrix[n-1][0]= -1;
		
		//printMatrix(matrix,n);
		
	}

}
