package g;

public class BooleanLineGraph extends BaseGraph{

	public BooleanLineGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n%2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n, Integer.MAX_VALUE);
		
		int half = n/2;
		
		for(int i =0; i < n; i++) {
			for(int j =0; j < n; j++) {
				if(j-1 == i) {
					matrix[i][j] = 1; 
				}
			}
		}
	}

}
