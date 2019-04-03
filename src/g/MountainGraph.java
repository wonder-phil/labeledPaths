package g;

public class MountainGraph extends BaseGraph{

	public MountainGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		
		if (n % 2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
		
			System.exit(-1);
		}
		
		int[][] matrix = this.getMatrix();
		
		int mid_top = n/2 -2;
		int mid_bottom = n/2+1;
		int mid = n/2;
		
		int pair = 2;
		for(int i =0; i < mid_top; i++) {
			
			
			if (pair > 0) {
				matrix[i][i+1] = +1;
			} else {
				matrix[i][i+1] = -1;
				pair = 3;
			}
			pair--;
				
		}
		
		for(int i =mid_top; i <= mid_bottom; i++) {
			if (i < mid) {
				matrix[i][i+1] = +1;
			} else {
				matrix[i][i+1] = -1;
			}
		}
		
		pair = 0;
		for(int i = mid_bottom+1; i < n-1; i++) {
			
			if (pair == 0) {
				matrix[i][i+1] = +1;
				pair = 3;
			} else {
				matrix[i][i+1] = -1;
			}
			pair--;
			
				
		}
	}

}
