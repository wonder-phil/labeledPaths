/*NOTES
 * This is the Original concept of the Object graph 
 * -Need to split this and implement this over to BaseGraph.java and ComplexGraph.java
*/
package g;
import java.util.Map;
import java.util.HashMap;
public class DemoOODgraph {
	Map<String,Integer> typemap; 
	private int half;
	private int length;
	//private String Type; 
	private int[][] matrix; 
	
	public DemoOODgraph(int n) {
		length = n; 
		half = n/2;
		matrix = new int[n][n];
		//typemap = new HashMap<String, Integer>();
		//typemap.put(SimpleRELineGraph, )
	}
	
	public int getLength() {           //synonymous with n 
		return length; 
	}
	/* LETS US KNOW WHAT TYPE GRAPH -Might be useless when 3rd parties make their own graph
	public void setType(String name) {
		this.Type = name;
	}
	public String getType() {           //
		return Type; 
	}
	*/
	public void fillSimpleRELineGraph() {   //fillgraphmatric - virtual function in abstract,             
		int n = getLength(); 
		for(int i =0; i < n-1; i++) {
			matrix[i][i+1] = 1; 
		}
		matrix[n-1][n-1] = -1;
	}
	public int[][] getMatrix() {  // to add  to abstract
		return matrix; 
	}


}
