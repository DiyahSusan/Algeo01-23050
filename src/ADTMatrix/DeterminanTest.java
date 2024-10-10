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
        m.printMatrix();

        // Menghitung determinan dengan metode kofaktor
        double detKofaktor = m.detKofaktor();
        System.out.println("Determinan (Kofaktor): " + detKofaktor);

        // Menghitung determinan dengan metode OBE
        Matrix m2 = new Matrix();
        m2.toMatrix(matriks, 3, 3);
        // Membuat duplikasi matriks agar tidak diubah oleh metode OBE
        double detOBE = m2.detOBE();
        System.out.println("Determinan (OBE): " + detOBE);
    }

    // Fungsi untuk mencetak matriks
    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = 0; j < matrix.getColLength(); j++) {
                System.out.printf("%6.2f ", matrix.getElement(i, j));
            }
            System.out.println();
        }
    }
}
