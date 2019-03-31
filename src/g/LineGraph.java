package g;

public class LineGraph extends BaseGraph {

	public LineGraph(int n) {
		super(n);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getLength();
		if (n%2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = new int[n][n];
		
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
			}
		}
		
	}

}
