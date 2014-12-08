package PageRank;

public class Rank {
	
	public static int NUM_NODES = 6;
	
//	private double[][] array;
	private double myDampingFactor = .85;
	
	/***/
	public void pageRank(double[][] A){
		double[][] B = initialize_B();
//		System.out.print("B matrix: \n");
//		Matrix.printMatrix(B);
		double[][] M = compute_M_array(myDampingFactor, A, B);
//		System.out.print("M matrix: \n");
//		Matrix.printMatrix(M);
		double[][] V = initialize_V();
//		System.out.print("\n V matrix: ");
//		Matrix.printMatrix(V);
		double[][] powerOfM = M;
		double[][] prevRanks = Matrix.multiply(powerOfM, V);
		boolean converged = false;
		powerOfM = Matrix.multiply(powerOfM, M);
		double[][] newRanks = Matrix.multiply(powerOfM, V);
		converged = checkConvergence(prevRanks, newRanks, .001);
		
		while(!converged){
			powerOfM = Matrix.multiply(powerOfM, M);
			prevRanks = newRanks;
			newRanks = Matrix.multiply(powerOfM, V);
			converged = checkConvergence(prevRanks, newRanks, 0.001);
		}
		System.out.print("Ranks are: \n");
		Matrix.printMatrix(Matrix.roundOffMatrix(newRanks));

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
		double[][] b = Matrix.one(NUM_NODES, NUM_NODES);
//		System.out.println("B matrix before : ");
//		Matrix.printMatrix(b);
		double d = (1.0/NUM_NODES);
		double rounded = (double) Math.round(d * 100) / 100;
//		System.out.print("1/numnodes " + rounded);
		return Matrix.dot(rounded, b);	
	}
	
	public double[][] initialize_V(){
		
		double[][] vector = Matrix.one(NUM_NODES, 1);
		double p = 1.0/NUM_NODES;
		return Matrix.dot(p, vector);
	}
	
	public boolean checkConvergence (double[][]X, double[][]Y, double errorMargin){
		double[][] diff = Matrix.subtract(X, Y);
		return Matrix.checkVals(diff, errorMargin);
		
	}
		

	
	
public static void main(String[] args){
	
	double[][] arr1 = new double[][]{
			  { 0.00, 0.00, 0.00, 0.00, 0.00, 0},
			  { 1.00, 0.00, 0.33, 0.33, 0.25, 0},
			  { 0.00, 0.33, 0.00, 0.33, 0.25, 0},
			  { 0.00, 0.33, 0.33, 0.00, 0.25, 0},
			  { 0.00, 0.33, 0.33, 0.33, 0.00, 1},
			  { 0.00, 0.00, 0.00, 0.00, 0.25, 0}
	};
	
	double[][] arr2 = new double[][]{
			  { 0, 1, 0, 0, 0, 0},
			  { 0, 0, 1, 1, 1, 0},
			  { 0, 1, 0, 1, 1, 0},
			  { 0, 1, 1, 0, 1, 0},
			  { 0, 1, 1, 1, 0, 1},
			  { 0, 0, 0, 0, 0, 0}
	};
	
	Rank r = new Rank();
	r.pageRank(arr1);
}
	
}

