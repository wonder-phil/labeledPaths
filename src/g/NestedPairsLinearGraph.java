package g;

public class NestedPairsLinearGraph extends BaseGraph {

	public NestedPairsLinearGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n % 2 == 0 && (n-1) % 8 == 0) {
			System.err.println("n should be odd and n-1 should be a product of 8");
			System.out.println();
		
			System.exit(-1);
		}
		
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int count = 0;
		for(int i =0; i < n-1; i++) {
			if (count < 2) {
				matrix[i][i+1] = 1; 
			} else {
				matrix[i][i+1] = -1;
			}
			count++;
			if (count >= 4) {
				count = 0;
			}
		}
	
		//printMatrix(matrix,n);
	}

}
