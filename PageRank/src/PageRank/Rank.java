package PageRank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Rank {
	
	/**Number of nodes of the directed graph*/
	public static int NUM_NODES = 6;
	
	/**Damping factor (1-p) = 0.85
	 * p = 0.15
	 * */
	private double myDampingFactor = 0.15;
	
	/***/
	public double[][] pageRank(double[][] A){
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
		return newRanks;

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
		double d = (1.0/NUM_NODES);
		double rounded = (double) Math.round(d * 100) / 100;
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
	
	public static void printRankedOrder(double[] R){
		HashMap<Double, Integer> m = mapRanks(R);
		HashMap<Integer, String> s = mapNodes();
		Arrays.sort(R);
		System.out.println("Ranked Nodes are : ");
		for(int i = (R.length - 1); i>= 0; i--){
//			System.out.println("R[i] = " + R[i]);
//			System.out.println("R[i]'s node = " + m.get(R[i]));
			double rounded = (double) Math.round(R[i] * 100) / 100;
			System.out.println("Node " + s.get(m.get(R[i]) + 1) + " ---> " + rounded);
			
		}
		
	}
	
	public static HashMap<Integer, String> mapNodes(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(4, "D");
		map.put(5, "E");
		map.put(6, "F");
		return map;
	}
	
	public static HashMap<Double, Integer> mapRanks(double[] R){
		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		int r = R.length;
		for(int i = 0; i<r; i++){
		map.put(R[i], i);
		}
		return map;
	}
	
	
		

	
	
public static void main(String[] args){
	
	/**The transition matrix derived from the test-graph*/
	double[][] arr1 = new double[][]{
			  { 0.00, 0.00, 0.00, 0.00, 0.00, 0},
			  { 1.00, 0.00, 0.33, 0.33, 0.25, 0},
			  { 0.00, 0.33, 0.00, 0.33, 0.25, 0},
			  { 0.00, 0.33, 0.33, 0.00, 0.25, 0},
			  { 0.00, 0.33, 0.33, 0.33, 0.00, 1},
			  { 0.00, 0.00, 0.00, 0.00, 0.25, 0}
	};
	
	
	Rank r = new Rank();
	double[][] twoD = r.pageRank(arr1);
	double[] oneD = Matrix.twoD_to_oneD(twoD);
	printRankedOrder(oneD);
}
	
}

