package g;

public class BarbellGraph extends BaseGraph{

	public BarbellGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		if (n%4 != 0) {
			System.err.println("n should be a PRODUCT of 4!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = this.getMatrix();
		
		int quarter = n/4;
		
		for(int i=0; i < quarter -1; i++) {
			matrix[i][i+1] = 1; 
		}
		matrix[quarter-1][0] = 1; 
		matrix[quarter-1][quarter] = 1; 
		
		for(int i = quarter; i < 3*quarter ; i++){
			if (i < 2*quarter){
				matrix[i][i+1] = +1;
			} else {
				matrix[i][i+1] = -1;
			}
		}
		
		for(int i=3*quarter; i < n-1; i++) {
			matrix[i][i+1] = -1; 
		}
		matrix[n-1][3*quarter] = -1;
	}

}
