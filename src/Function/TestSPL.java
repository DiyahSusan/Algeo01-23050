package Function;

import ADTMatrix.Matrix;
import IO.Output;

public class TestSPL {
    public static void main(String[] args) {
        // Example system of linear equations represented in augmented matrix form
        // For example: 
        // 2x + 3y + z + 4w = 10
        // 4x + 9y + 3z + 2w = 20
        // 1x + 2y + 5z + 3w = 15
        // 3x + 2y + 4z + 1w = 12
        double[][] data = {
            {2, 3, 1, 4, 10},
            {4, 9, 3, 2, 20},
            {1, 2, 5, 3, 15},
            {3, 2, 4, 1, 12}
        };

        Matrix m = new Matrix();
        m.createMatrix(data.length, data[0].length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                m.matrix[i][j] = data[i][j];
            }
        }

        System.out.println("--------------------------------");
        Output.printMatrix(m.copy());
        System.out.println("--------------------------------");

        // Test for unique solution using Gaussian elimination
        System.out.println("Testing unique solution using Gauss:");
        String[] hasilGauss = SPL.metode_gauss(m.copy());
        System.out.println("Gauss Method Results:");
        for (String s : hasilGauss) {
            System.out.println(s);
        }
        System.out.println("--------------------------------");
        Output.printMatrix(m.copy());
        System.out.println("--------------------------------");

        // Test unique solution using Gauss-Jordan elimination
        System.out.println("\nTesting unique solution using Gauss-Jordan:");
        String[] hasilGaussJordan = SPL.metode_gauss_jordan(m.copy());
        System.out.println("Gauss-Jordan Method Results:");
        for (String s : hasilGaussJordan) {
            System.out.println(s);
        }

        System.out.println("--------------------------------");
        Output.printMatrix(m.copy());
        System.out.println("--------------------------------");

        // Test inverse method (ensure the matrix is invertible)
        System.out.println("\nTesting inverse method:");
        String[] hasilInvers = SPL.metode_invers(m.copy());
        System.out.println("Inverse Method Results:");
        for (String s : hasilInvers) {
            System.out.println(s);
        }
        
        System.out.println("--------------------------------");
        Output.printMatrix(m.copy());
        System.out.println("--------------------------------");

        // Test Cramer's rule (ensure determinant is non-zero)
        System.out.println("\nTesting Cramer's rule:");
        String[] hasilCramer = SPL.metode_cramer(m.copy());
        System.out.println("Cramer's Rule Results:");
        for (String s : hasilCramer) {
            System.out.println(s);
        }
    }
}
