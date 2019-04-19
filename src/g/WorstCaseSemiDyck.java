package g;

public class WorstCaseSemiDyck extends BaseGraph {
	int m;
	int i;
	int j;

	public WorstCaseSemiDyck(int n, int point1, int point2) {
		super(n);
		// TODO Auto-generated constructor stub
		i = point1;
		j = point2;

	}
	
	public int getI() {
		return this.i;
		
	}
	public int getJ() {
		return this.j;
		
	}
	
	public int calc_n(int m) {
		if (m == 1) {
			return 7;
		} else {
			return 2* calc_n(m-1) + 1;
		}
	}
	
	public void worstCaseSemiDyck_BASE_CASE(int[][] matrix, int i, int j) {


		matrix[i+0][j+1] = 1;
		matrix[i+1][j+2] = 1;
		
		matrix[i+2][j+3] = -1;
		matrix[i+3][j+4] = 1;
		
		matrix[i+4][j+5] = -1;
		matrix[i+5][j+6] = -1;
		
		int[][] m = this.getMatrix();
		m = matrix;
		
		//return matrix;
	}
	
	public void recursive_wc_semiDyck(int m, int[][] matrix, int i, int j ) {
		if (1 == m) {
			this.worstCaseSemiDyck_BASE_CASE(matrix, i, j);
		} else {
			int n = Graphs.calc_n(m);
			int newN = Graphs.calc_n(m-1);
			
			matrix[i][j+1] = 1;
			recursive_wc_semiDyck(m-1,matrix,i+1,j+1);
			
			matrix[newN-1+i][newN+j] = -1;
			
			recursive_wc_semiDyck(m-1,matrix,newN+i,newN+j);
			
			matrix[2*newN-2+i][2*newN-1+j] = -1;
			
			matrix[n-2][n-1] = -1;
		}
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = calc_n(m);
		
		int[][] matrix = this.getMatrix();
		
		
		recursive_wc_semiDyck(m,matrix,0,0);
	
		//printMatrix(matrix,n);
		
		//return matrix;
	}

	private void worstCaseSemiDyck_BASE_CASE() {
		// TODO Auto-generated method stub
		
	}

}
