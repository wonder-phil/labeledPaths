package g;

public class SemiDyck3Graph extends BaseGraph{
	int m;
	int val;
	public SemiDyck3Graph(int n, int v, int mal) {
		super(n);
		val = v;
		m = mal; 
		// TODO Auto-generated constructor stub
	}
	public int getVal(){
		return this.val;
	}
	public int getM(){
		return this.m;
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		int m = this.getM();
		int val = this.getVal();
		if (n % 2 == 0 && n >= 9) {
			System.err.println("n should be odd!");
			System.out.println();
		
			System.exit(-1);
		}
		
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		//zeroMatrix(matrix,n);
		
		for(int i =0; i < m; i++) {
			matrix[i][i+1] = val; 
		}
		
		for (int i =m+1; i < n-m; i += 2) {
			matrix[i-1][i] = val;
			matrix[i][i+1] = -val;
		}
		
		for(int i = n-m-1; i < n-1; i++) {
			matrix[i][i+1] = -val; 
		}
		//matrix[n-2][n-1] = -val; 
		
		//printMatrix(matrix,n);
	}

}
