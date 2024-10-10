package ADTMatrix;

public class DeterminanTest {
    public static void main(String[] args) {
        // Contoh Matriks Persegi 3x3
        double[][] matriks = {
            {1, 2, 3},
            {0, 4, 5},
            {1, 0, 6}
        };

        Matrix m = new Matrix();
        m.toMatrix(matriks, 3, 3);

        System.out.println("Matriks:");
        printMatrix(m);

        // Menghitung determinan dengan metode kofaktor
        double detKofaktor = Determinan.detKofaktor(m);
        System.out.println("Determinan (Kofaktor): " + detKofaktor);

        // Menghitung determinan dengan metode OBE
        Matrix m2 = new Matrix();
        m2.toMatrix(matriks, 3, 3);
        // Membuat duplikasi matriks agar tidak diubah oleh metode OBE
        double detOBE = Determinan.detOBE(m2);
        System.out.println("Determinan (OBE): " + detOBE);
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
