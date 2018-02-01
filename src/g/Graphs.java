package g;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.util.BigReal;

public class Graphs {
	
	
	public static void zeroMatrix(int[][] matrix, int n) {
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = 0;
			}
		}
	}
	
	public static void printMatrix(int[][] matrix, int n) {
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				System.out.print(matrix[i][j]+ " ");
			}
			System.out.println();
		}
	}

	//
	// NO 0 PATHS!
	// NO 0 PATHS!
	// NO 0 PATHS!
	//
	// All exact paths are +1 single-edge paths!
	//
	// n should be odd
	// 
	// All edges are +1 or 0.
	//
	// 0==+==>1==+==>2==+==>...(n-1)/2==+==>...==+==>n
	//
	// For example:
	// 0==+==>1==+==>2
	//
	public static int[][] BooleanLineGraph(int n) {
		
		if (n%2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = new int[n][n];
		//zeroMatrix(matrix,n, Integer.MAX_VALUE);
		
		int half = n/2;
		
		for(int i =0; i < n; i++) {
			for(int j =0; j < n; j++) {
				if(j-1 == i) {
					matrix[i][j] = 1; 
				}
			}
		}
		
		return matrix;
	}
	
	
	/*
	 * One -1 edge !
	 * 
	 */
	public static int[][] simpleRELineGraph(int n) {
		
		int[][] matrix = new int[n][n];
		//zeroMatrix(matrix,n, Integer.MAX_VALUE);
		
		int half = n/2;
		
		for(int i =0; i < n-1; i++) {
			matrix[i][i+1] = 1; 
		}
		matrix[n-1][n-1] = -1;
		
		//printMatrix(matrix,n);
		return matrix;
	}
	
	
	/*
	 * 
	 *  Linear nested graphs
	 *
	 *   n should be odd
	 *   0==+==>1==+==>2==+==>...(n-1)/2==-==>...==-==>(n-1)
	 *
	 *  For example:
	 *  0==+==>1==-==>2
	 * 
	 * 
	 */
	
	//
	//
	//
	public static int[][] lineGraph(int n) {
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
		
		return matrix;
	}
	
	/*
	 * 
	 * bigLittleGraph(9,3)
	 * 
	 * 
	 *    /\
	 *   /  \/\
	 *  /      \
	 *  
	 *  
	 *  
	 *  bigLittleGraph(13,5)
	 * 
	 * 
	 *      /\
	 *     /  \
	 *    /    \  
	 *   /      \/\  
	 *  /          \
	 * 
	 * 
	 */
	
	public static int[][] bigLittleGraph(int n, int bigBumpHeight) {
		if (n%2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = new int[n][n];
		
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
		
		return matrix;
	}
	
	/*
	 * 
	 * n == 7
	 * 
	 */
	public static void worstCaseSemiDyck_BASE_CASE(int[][] matrix, int i, int j) {


		matrix[i+0][j+1] = 1;
		matrix[i+1][j+2] = 1;
		
		matrix[i+2][j+3] = -1;
		matrix[i+3][j+4] = 1;
		
		matrix[i+4][j+5] = -1;
		matrix[i+5][j+6] = -1;
		
		//return matrix;
	}
	
	public static int calc_n(int m) {
		if (m == 1) {
			return 7;
		} else {
			return 2* calc_n(m-1) + 1;
		}
	}
	
	/*
	 *  Semi-Dyck worst case
	 *   
	 * 
	 * n = 7, or we say m = 1
	 * 
	 *   /\/\
	 *  /    \
	 *  
	 *  
	 *  n = 15, or m = 2
	 *  
	 *     /\/\  /\/\
	 *    /    \/    \
	 *   /            \
	 *
	 *  
	 */
	
	public static int[][] worstCaseSemiDyck(int m, int i, int j) {
		
		int n = calc_n(m);
		
		int[][] matrix = new int[n][n];
		
		
		recursive_wc_semiDyck(m,matrix,0,0);
	
		//printMatrix(matrix,n);
		
		return matrix;
	}
	
	public static void recursive_wc_semiDyck(int m, int [][] matrix, int i, int j) {
		if (1 == m) {
			worstCaseSemiDyck_BASE_CASE(matrix, i, j);
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
	
	
	//
	// n should be even
	//
	// NODES --->---> NODES
	//
	// Each NODEs is a cycle
	// 
	//
	public static int[][] barbellGraph(int n) {
		if (n%4 != 0) {
			System.err.println("n should be a PRODUCT of 4!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = new int[n][n];
		
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
		
		return matrix;
	}

	/*
	 * Alternating linear path
	 * 
	 * D => DDDDDD
	 * 
	 * Each right D is ()
	 */
	
	
	//
	// n should be odd
	// 0==+==>1==-==>2==+==>3==-==>...(n-1)/2==-==>==+==>...==>(n-1)
	//
	// For example:
	// 0==+==>1==-==>2
	// 0==+==>1==-==>2==+==>3==-==>4
	//
	public static int[][] alternatingLineGraph(int n) {
		
		if (n%2 == 0) {
			System.err.println("n should be odd!");
			System.out.println();
			
			System.exit(-1);
		}
		
		int[][] matrix = new int[n][n];
		zeroMatrix(matrix,n);
		
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
		
		return matrix;
	}
	

	//
	// n should be odd
	//
	//
	public static int[][] threeLoopGraph(int n) {
		
		int[][] matrix = new int[n][n];
		zeroMatrix(matrix,n);
		
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
		
		return matrix;
	}

	
	

	/*
	 * Alternating cycles
	 * 
	 */
	//Start Emily's Code
	//
	//n must be even
		//
		// 	==+===>1==-==>2==+==>...
		//0						   (n/2)
		// <==-==(n-1)<==+==
		//
		//For example:
		// ==+==>1==-==>
		//0				2
		// <==-==3<==+==
		public static int[][] alternatingCycleGraph(int n) {
				if (n%2 == 1) {
					System.err.println("n should be even!");
					System.out.println();
					
					System.exit(-1);
				}
				int[][] matrix = new int[n][n];
				//zeroMatrix(matrix,n);
				
				
				for(int i =0; i < n; i++) {
					for(int j =0; j < n; j++) {
						if(j-1 == i) {
							if (j%2 == 0) {
								matrix[i][j] = -1; 
							} else {
								matrix[i][j] = 1;
							}
						}

					}
				}
				matrix[n-1][0]= -1;
				
				//printMatrix(matrix,n);
				return matrix;
			}

		
		
		/*
		 * Cycle of one nesting
		 * 
		 */
		//n must be even
		//
		// ==+===>1==+==>2==+==>...
		//0						  (n/2)
		// <==-== (n-1) ... <==-==
		//
		//For example:
		// ==+==>1==+==>
		//0				n/2
		// <==-==<==-==
		public static int[][] cycleGraph(int n) {
			if (n%2 == 1) {
				System.err.println("n should be even!");
				System.out.println();
				
				System.exit(-1);
			}
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
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
					/*
					if(j == 0 && i == n-1) {
						matrix[i][j]= -1;
					}
					*/
					}
				}
			
			matrix[n-1][0]= -1;
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		/*
		 * 
		 */
		//n must be even
		//
		// ==+===>1==+==>2==+==>...
		//0						  (n/2)
		// <==-== (n-1) ... <==-==
		//
		//For example:
		// ==+==>1==+==>
		//0				n/2
		// <==-==<==-==
		public static int[][] cycleGraphXX(int n) {
			if (n%2 == 1) {
				System.err.println("n should be even!");
				System.out.println();
				
				System.exit(-1);
			}
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
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
			
			matrix[n-1][0]= -1;
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		//
		//n must be even
		//
		//
		public static int[][] trivialSelfCycleGraph(int val, int n) {
			if (n % 2 != 0) {
			System.err.println("n should be even!");
			System.out.println();
			
			System.exit(-1);
			}
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			for(int i =0; i < n-1; i++) {
				matrix[i][i+1] = val; 
			}
			matrix[n-1][0] = val;
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		//
		//n must be even
		//
		//
		public static int[][] basicCycleGraph(int val, int n) {
			if (n % 2 != 0) {
			System.err.println("n should be even!");
			System.out.println();
			
			System.exit(-1);
			}
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			for(int i =0; i < n-1; i++) {
				matrix[i][i+1] = val; 
			}
			matrix[n-1][0] = val;
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		/*
		 * Valid semi-Dyck (hence Dyck) linear graph
		 * 
		 * n must be odd
		 * 
		 * 0 =+=> 1 =+=> 2 =-=> 3 =+=> 4 =-=> 5 =+= > 6
		 * 
		 * 
		 * n = 7: ( () () )
		 * n = 9: ( ( () () ) )
		 * n = 11: ( ( ( () () ) ) )
		 * n = 13: ( ( ( ( () () ) ) ) )
		 * 
		 */
		public static int[][] basicSemiDyckGraph(int val, int n) {
			
			if (n % 2 == 0) {
				System.err.println("n should be odd!");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			int m = n-1;
			
			for(int i =0; i < m/2-1; i++) {
				matrix[i][i+1] = val; 
			}
			
			matrix[m/2-1][m/2] = -val;
			matrix[m/2][m/2+1] = val;
			
			for(int i = m/2+1; i < m; i++) {
				matrix[i][i+1] = -val; 
			}
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		/*
		 * Valid semi-Dyck (hence Dyck) linear graph
		 * 
		 * n must be odd
		 * 
		 * 
		 * 9:  ( () () () )
		 * 11: ( () () () () )
		 * 13: ( () () () () () )
		 * 
		 */
		public static int[][] basicSemiDyck3PlusGraph(int val, int n) {
			
			if (n % 2 == 0 && n >= 9) {
				System.err.println("n should be odd!");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			int m = n/4;
			
			for(int i =0; i < m; i++) {
				matrix[i][i+1] = val; 
			}
			
			for (int i =m; i < n-m; i += 2) {
				matrix[i-1][i] = val;
				matrix[i][i+1] = -val;
			}
			
			for(int i = n-m; i < n-1; i++) {
				matrix[i][i+1] = -val; 
			}
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		/*
		 * Valid semi-Dyck (hence Dyck) linear graph
		 * 
		 * n must be odd
		 * 
		 * n = 9, m = 1:  ( () () () )
		 * n = 11, m = 1: ( () () () ()  )
		 * n = 11, m = 2: ( ( () () () ) ) 
		 * n = 13, m = 3: ( ( ( () () () ) ) )
		 * 
		 */
		public static int[][] semiDyck3Graph(int val, int n, int m) {
			
			if (n % 2 == 0 && n >= 9) {
				System.err.println("n should be odd!");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
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
			return matrix;
		}
		
		/*
		 * Non semi-Dyck (hence Dyck) linear graph
		 * 
		 * n must be odd
		 * 
		 * 
		 * )(
		 * ))((
		 * )))(((
		 * 
		 * 
		 */
		public static int[][] nonSemiDyckGraph(int n) {

			if (n % 2 == 0) {
				System.err.println("n should be odd!");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			for(int i =0; i < n/2; i++) {
				matrix[i][i+1] = -1; 
			}
			
			for(int i = n/2; i < n-1; i++) {
				matrix[i][i+1] = 1; 
			}
			
			//printMatrix(matrix,n);
			return matrix;
		}
		
		/*
		 * Nested pairs
		 * 
		 * Linear graphs of the form:
		 * 
		 * (()) (())
		 * (()) (()) (()) (())
		 * (()) (()) (()) (())  (()) (()) (()) (())
		 * 
		 * 
		 * 
		 */
		public static int[][] nestedPairsLinearGraph(int n) {

			if (n % 2 == 0 && (n-1) % 8 == 0) {
				System.err.println("n should be odd and n-1 should be a product of 8");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			int count = 0;
			for(int i =0; i < n-1; i++) {
				if (count < 2) {
					matrix[i][i+1] = 1; 
				} else {
					matrix[i][i+1] = -1;
				}
				count++;
				if (count >= 4) {
					count = 0;
				}
			}
		
			//printMatrix(matrix,n);
		
			return matrix;
		}
		
		/*
		 * Nested Triples 
		 * 
		 * Linear graphs of the form:
		 * 
		 * ((())) ((()))
		 * ((())) ((()))  ((())) ((()))
		 * ((())) ((()))  ((())) ((()))  ((())) ((()))  ((())) ((()))
		 * 
		 * 
		 * 
		 */
		public static int[][] nestedTriplesLinearGraph(int n) {

			if (n % 2 == 0 && (n-1) % 12 == 0) {
				System.err.println("n should be even and n-1 should be a product of 12");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
			int count = 0;
			for(int i =0; i < n-1; i++) {
				if (count < 3) {
					matrix[i][i+1] = 1; 
				} else {
					matrix[i][i+1] = -1;
				}
				count++;
				if (count >= 6) {
					count = 0;
				}
			}
		
			//printMatrix(matrix,n);
		
			return matrix;
		}
		
		/*
		 * Nested Geometric series 
		 * 
		 * Linear graphs of the form:
		 * 
		 * n = 7: (()) ()
		 * n = 15: (((()))) (()) ()
		 * n = 31: (((((((()))))))) (((()))) (()) ()
		 * 
		 * 
		 * 
		 */
		public static int[][] geometricNestedLinearGraph(int n) {

			/*
			 * (n & (n-1)) == 0 is True iff n is a power of 2.
			 * 
			 */
			if (n % 2 == 1 && ((n+1) & (n+2))  == 0) {
				System.err.println("n should be odd and n+1 should be a power of 2");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			zeroMatrix(matrix,n);
			
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
		
			return matrix;
		}
		
		/*
		 * Mountain graphs
		 * 
		 * n must be odd
		 * 
		 * 
		 * 
		 * 
		 * n = 11: ( () (())  () )
		 * n = 17: ( () (()  ( () () ) ()) () )
		 * n = n_0 + 6
		 * 
		 */
		public static int[][] mountainkGraph(int n) {
			
			if (n % 2 == 0) {
				System.err.println("n should be odd!");
				System.out.println();
			
				System.exit(-1);
			}
			
			int[][] matrix = new int[n][n];
			
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
			
			//printMatrix(matrix,n);
			return matrix;
		}
}
