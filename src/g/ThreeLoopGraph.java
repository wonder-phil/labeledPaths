package g;

public class ThreeLoopGraph extends BaseGraph {

	public ThreeLoopGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildGraph() {
		// TODO Auto-generated method stub
		int n = this.getTotalNodes();
		this.zeromatrix();
		int[][] matrix = this.getMatrix();
		
		matrix[0][1] = 1;
		matrix[1][2] = 1;
		matrix[2][3] = -1;
		matrix[3][4] = -1;
		
		int lastUp = 1;
		int lastDown = 3;
		
		matrix[lastUp][5] = +1;
		matrix[5][6] = +1;
		
		lastUp = 5;
		
		matrix[6][7] = -1;

		matrix[7][lastDown] = -1;
		
		lastDown = 7;
		
		matrix[lastUp][8] = +1;
		
		lastUp = 8;
		
		matrix[8][9] = +1;
		matrix[9][10] = -1;
		
		matrix[10][lastDown] = -1;
		
		lastDown = 10;
		
	}

}
