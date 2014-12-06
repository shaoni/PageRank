package PageRank;

public class Rank {
	
	private double[][] array;
	private double myDampingFactor = .85;
	
	/***/
	public double[][] pageRank(){
		double[][] B = initialize_B();
		double[][] M = compute_M_array(myDampingFactor, array, B);
		return M;

	}
	/** [ M = (1 - p).A  + p.B ] 
	 * 
	 * (1 - p) = .85
	 * */
	
	public double[][] compute_M_array(double df, double[][] A, double[][] B){
		double[][] arr1 = Matrix.dot(df, A);
		double[][] arr2 = Matrix.dot((1-df), B);
		return Matrix.add(arr1, arr2);
	}
	
	public double[][] initialize_B(){
		double[][] b = Matrix.identity(6);
		return Matrix.dot((1/6), b);	
	}

	
	
public static void main(String[] args){
	
	double[][] arr = new double[][]{
			  { 0, 1, 0, 0, 0, 0},
			  { 0, 0, 1, 1, 1, 0},
			  { 0, 1, 0, 1, 1, 0},
			  { 0, 1, 1, 0, 1, 0},
			  { 0, 1, 1, 1, 0, 1},
			  { 0, 0, 0, 0, 0, 0}
	
	double[][] r = this.pageRank(.85, )
}
	
}
}
