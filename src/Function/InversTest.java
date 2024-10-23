package Function;

import ADTMatrix.Matrix;
import Function.Invers;
import IO.OutputFile;

public class InversTest {

    public static void main(String[] args) {
        testInversAdjoin();
    }

    public static void testInversAdjoin() {
        System.out.println("Testing inversAdjoin...");

        // Test Case 1: 2x2 Invertible Matrix
        Matrix invertibleMatrix = new Matrix();
        invertibleMatrix.createMatrix(2, 2);
        invertibleMatrix.matrix[0][0] = 4;
        invertibleMatrix.matrix[0][1] = 7;
        invertibleMatrix.matrix[1][0] = 2;
        invertibleMatrix.matrix[1][1] = 6;

        Matrix inversedMatrixAdjoin = Invers.inversAdjoin(invertibleMatrix);
        System.out.println("Inversed 2x2 Matrix (Adjoint Method):");
        OutputFile.printMatrix(inversedMatrixAdjoin);

        // Optionally, test by multiplying the original with the inverse and check if it's an identity matrix
        Matrix resultMatrixAdjoin = new Matrix();
        resultMatrixAdjoin.createMatrix(2, 2);
        resultMatrixAdjoin = Matrix.multiplyMatrix(invertibleMatrix, inversedMatrixAdjoin);
        System.out.println("Original * Inverse (Adjoint) (Should be Identity):");
        OutputFile.printMatrix(resultMatrixAdjoin);    }
}

