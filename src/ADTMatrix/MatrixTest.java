package ADTMatrix;

public class MatrixTest {
    public static void main(String[] args) {
        // Membuat matriks contoh
        double[][] data1 = {
            {0, 1, -1, 8},
            {0, -1, 2, -11},
            {0, 1, 2, -3}
        };

        double[][] data2 = {
            {1, 2, -1, 3},
            {2, 1, 3, 6},
            {3, 2, 1, 5}
        };

        // Inisialisasi matriks dengan data
        Matrix matrix1 = new Matrix();
        matrix1.toMatrix(data1, 3, 4);
        
        Matrix matrix2 = new Matrix();
        matrix2.toMatrix(data2, 3, 4);
        
        // Menerapkan eliminasi Gauss
        System.out.println("Matriks 1 sebelum eliminasi:");
        printMatrix(matrix1);
        Matrix result1 = matrix1.gaussElimination();
        System.out.println("Matriks 1 setelah eliminasi:");
        printMatrix(result1);
        
        System.out.println("\nMatriks 2 sebelum eliminasi:");
        printMatrix(matrix2);
        Matrix result2 = matrix2.gaussElimination();
        System.out.println("Matriks 2 setelah eliminasi:");
        printMatrix(result2);
    }

    // Fungsi untuk mencetak matriks
    public static void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = 0; j < matrix.getColLength(); j++) {
                System.out.printf("%6.2f ", matrix.getElement(i, j));
            }
            System.out.println();
        }
    }
}
