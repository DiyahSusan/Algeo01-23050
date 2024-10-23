package Function;

import ADTMatrix.Matrix;

public class TestInvers {
    public static void main(String[] args) {
        // Test with an invertible matrix
        System.out.println("Testing with an invertible matrix:");
        double[][] invertibleData = {
            {1, 2, 3},
            {0, 1, 4},
            {5, 6, 0}
        };

        Matrix invertibleMatrix = createMatrix(invertibleData);
        printMatrix(invertibleMatrix);
        Matrix invertedMatrix = Invers.invers(invertibleMatrix.copy());
        System.out.println("Original Matrix:");
        printMatrix(invertibleMatrix);
        System.out.println("Inverted Matrix:");
        printMatrix(invertedMatrix);

        // Test with a non-invertible matrix (e.g., rows are linearly dependent)
        System.out.println("\nTesting with a non-invertible matrix:");
        double[][] nonInvertibleData = {
            {1, 2, 3},
            {2, 4, 6},
            {3, 6, 9}
        };

        Matrix nonInvertibleMatrix = createMatrix(nonInvertibleData);
        Matrix nonInvertedMatrix = Invers.invers(nonInvertibleMatrix);
        System.out.println("Original Matrix:");
        printMatrix(nonInvertibleMatrix);
        System.out.println("Attempt to invert non-invertible matrix (should handle gracefully):");
        printMatrix(nonInvertedMatrix);
    }

    private static Matrix createMatrix(double[][] data) {
        Matrix matrix = new Matrix();
        matrix.createMatrix(data.length, data[0].length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                matrix.matrix[i][j] = data[i][j];
            }
        }
        return matrix;
    }

    private static void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.col; j++) {
                System.out.printf("%.2f ", matrix.matrix[i][j]);
            }
            System.out.println();
        }
    }
}
