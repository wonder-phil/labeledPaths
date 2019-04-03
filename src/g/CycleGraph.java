package g;

public class CycleGraph extends BaseGraph{

	public CycleGraph(int n) {
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
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int half = n/2;
		
		for(int i =0; i < n; i++) {
			for(int j =0; j < n; j++) {
				if(j-1 == i) {
					if (i< half) {
						matrix[i][j] = 1; 
					} else {
						matrix[i][j] = -1;
					}
				}
				/*
				if(j == 0 && i == n-1) {
					matrix[i][j]= -1;
				}
				*/
				}
			}
		
		matrix[n-1][0]= -1;
		
		//printMatrix(matrix,n);
	}

}
