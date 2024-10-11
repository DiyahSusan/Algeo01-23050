package ADTMatrix;

public class TesDetKofIJ {
    public static void main(String[] args) {
        // Inisialisasi matriks 3x3
        double[][] matriks = {
            {1, 2, 2},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Membuat objek Matrix dan mengisi dengan nilai matriks
        Matrix m = new Matrix();
        m.toMatrix(matriks, 3,3);

        // Cetak matriks asli
        System.out.println("Matriks Asli:");
        printMatrix(m);

        // Baris dan kolom yang akan dihapus
        int rowToDelete = 1; // Baris ke-2 (indeks 1)
        int colToDelete = 1; // Kolom ke-2 (indeks 1)

        // Hitung determinan kofaktor dari matriks dengan baris dan kolom yang dihapus
        double det = Matrix.detKofaktorIJ(m, rowToDelete, colToDelete);
        System.out.println("\nDeterminan dengan menghilangkan baris " + (rowToDelete + 1) + " dan kolom " + (colToDelete + 1) + ": " + det);
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
