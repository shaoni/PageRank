package PageRank;
public class Matrix {

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = Math.random();
        return C;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }
    
    
 // return m-by-n matrix of 1s
    public static double[][] one(int m, int n) {
        double[][] I = new double[m][n];
        for (int i = 0; i < m; i++)
        	for (int j = 0; j < n; j++)
        		I[i][j] = 1;
        return I;
    }
    
    

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }
    
    
 // return x.T
    public static double[][] dot(double x, double[][] y) {
        int m = y.length;
        int n = y[0].length;
        double d;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
//            	d = x * y[i][j];
//        		double rounded = (double) Math.round(d * 100) / 100;
//                C[i][j] = rounded;
            	C[i][j] = x * y[i][j];
            }
        }
        return C;
    }
    
        // return C = A^T
    public static double[][] transpose(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[j][i] = A[i][j];
        return C;
    }

    // return C = A + B
    public static double[][] add(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // return C = A - B
    public static double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    
    
    // print a matrix
    public static void printMatrix(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++){
        	for (int j = 0; j < n; j++)
                System.out.print(A[i][j] + " ");
        	System.out.print("\n");
//            System.out.print("\n");
        }
    }
    
 // round-off a matrix
    public static double[][] roundOffMatrix(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++){
        	for (int j = 0; j < n; j++)
        		C[i][j] = (double) Math.round((A[i][j]) * 100) / 100;
        }
        return C;
    }
    
 // return C = A - B
    public static boolean checkVals(double[][] A, double err) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i][j] > Math.abs(err))
                		return false;
        return true;
    }
    
    

    // return C = A * B
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        double d;
        for (int i = 0; i < mA; i++)
        {
//        	System.out.println("i: " + i);
            for (int j = 0; j < nB; j++)
            {
//            	System.out.print("j: " + j + " ");
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
            }
        }
        return C;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += (A[i][j] * x[j]);
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += (A[i][j] * x[i]);
        return y;
    }

    // test client
    public static void main(String[] args) {
//        System.out.println("D");
//        System.out.println("--------------------");
//        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
////        StdArrayIO.print(d);
//        System.out.println();
//
//        System.out.println("I");
//        System.out.println("--------------------");
//        double[][] c = Matrix.identity(5);
////        StdArrayIO.print(c);
//        System.out.println();
//
//        System.out.println("A");
//        System.out.println("--------------------");
//        double[][] a = Matrix.random(5, 5);
////        StdArrayIO.print(a);
//        System.out.println();
//
//        System.out.println("A^T");
//        System.out.println("--------------------");
//        double[][] b = Matrix.transpose(a);
////        StdArrayIO.print(b);
//        System.out.println();
//
//        System.out.println("A + A^T");
//        System.out.println("--------------------");
//        double[][] e = Matrix.add(a, b);
////        StdArrayIO.print(e);
//        System.out.println();
//
//        System.out.println("A * A^T");
//        System.out.println("--------------------");
//        double[][] f = Matrix.multiply(a, b);
////        sStdArrayIO.print(f);
//        System.out.println();
    }
}
