package g;

public class NestedTriplesLinearGraph extends BaseGraph{

	public NestedTriplesLinearGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n % 2 == 0 && (n-1) % 12 == 0) {
			System.err.println("n should be even and n-1 should be a product of 12");
			System.out.println();
		
			System.exit(-1);
		}
		
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int count = 0;
		for(int i =0; i < n-1; i++) {
			if (count < 3) {
				matrix[i][i+1] = 1; 
			} else {
				matrix[i][i+1] = -1;
			}
			count++;
			if (count >= 6) {
				count = 0;
			}
		}
	
		//printMatrix(matrix,n);

	}

}
