package Function;

import ADTMatrix.Matrix;

public class BikubicTest {
    public static void main(String[] args) {
        // Create a test matrix (5x4) for interpolation
        // First 4 rows contain the 4x4 grid values
        // Last row contains the interpolation points (a,b)
        Matrix testMatrix = new Matrix();
        testMatrix.createMatrix(5, 4);  // Changed to 5x4 to include interpolation points

        // Set the 4x4 grid values
        double[][] testMatrixValues = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        // Initialize the 4x4 grid values
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                testMatrix.setElement(i, j, testMatrixValues[i][j]);
            }
        }

        // Set the interpolation points (a,b) in the last row
        // For example, to interpolate at point (0.5, 0.5):
        testMatrix.setElement(4, 0, 0.5);  // a = 0.5
        testMatrix.setElement(4, 1, 0.5);  // b = 0.5

        // Perform bicubic interpolation
        Bikubic.interpolasiBicubic(testMatrix);
    }
}