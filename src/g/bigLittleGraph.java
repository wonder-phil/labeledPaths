package g;

public class bigLittleGraph extends BaseGraph{
	int bigBumpHeight;
	public bigLittleGraph(int n, int bigBump) {
		super(n);
		// TODO Auto-generated constructor stub
		bigBumpHeight = bigBump;
	}
	public int getbigBumpHeight() {  //synonymous with n  
		return this.bigBumpHeight; 
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
		
		int half = bigBumpHeight/2;
		
		for(int i =0; i < n-1; i++) {
			if (i < bigBumpHeight) {
				matrix[i][i+1] = 1;
			} 
			if (bigBumpHeight <= i && i < 2*bigBumpHeight) {
				matrix[i][i+1] = -1;
			}
			if (i == 2*bigBumpHeight-1) {
				matrix[i][i+1] = 1;
			}
			if (2*bigBumpHeight <= i) {
				matrix[i][i+1] = -1;
			}
		}
	}

}
