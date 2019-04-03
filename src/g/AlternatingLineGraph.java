package g;

public class AlternatingLineGraph extends BaseGraph {

	public AlternatingLineGraph(int n) {
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
		
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		int half = n/2;
		
		for(int i =0; i < n; i++) {
			for(int j =0; j < n; j++) {
				if(j-1 == i) {
					if(i % 2 == 0) {
						matrix[i][j] = 1; 
					} else {
						matrix[i][j] = -1;
					}
				}
			}
		}
		
	}

}
