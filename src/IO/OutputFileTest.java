package IO;
import ADTMatrix.Matrix;
import IO.OutputFile;

public class OutputFileTest {
    public static void main(String[] args) {
        // Membuat matriks 3x3 sebagai contoh
        Matrix matrix = new Matrix();
        matrix.createMatrix(3, 3);
        
        // Inisialisasi elemen matriks
        double[][] elements = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        
        // Set elemen ke dalam matriks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix.setElement(i, j, elements[i][j]);
            }
        }

        // Menampilkan matriks di layar (uji printMatrix)
        System.out.println("Matriks:");
        OutputFile.printMatrix(matrix);

        // Uji output matriks ke file
        System.out.println("Menguji output matriks ke file...");
        OutputFile.OutputFile(matrix, 1);  // Pilih opsi 1 untuk menyimpan ke file

        // Uji output determinan ke file
        double determinant = 0; // Misalnya determinan matriks ini (determinannya nol untuk matriks ini)
        System.out.println("Menguji output determinan ke file...");
        OutputFile.OutputDetFile(determinant, 1);  // Pilih opsi 1 untuk menyimpan ke file
    }
}
