package g;

public class GeometricNestedLinearGraph extends BaseGraph{

	public GeometricNestedLinearGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub

		/*
		 * (n & (n-1)) == 0 is True iff n is a power of 2.
		 * 
		 */
		int n = this.getTotalNodes();
		if (n % 2 == 1 && ((n+1) & (n+2))  == 0) {
			System.err.println("n should be odd and n+1 should be a power of 2");
			System.out.println();
		
			System.exit(-1);
		}
		
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int m = (n+1)/2;
		int half = m/2;
		
		int iterations = (int) (Math.log(n+1) / Math.log(2));
		
		int position = 0;
		
		for (int i = 0; i < iterations -1; i++) {
			
		
			for(int j =0; j < half; j++) {
				matrix[position + j][position + j+1] = 1; 
			}
			
			for(int j =half; j < 2*half; j++) {
				matrix[position + j][position + j+1] = -1; 
			}
			
			position += 2*half;
			half = half/2;
		}
	
		//printMatrix(matrix,n);
	}

}
