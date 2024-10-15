package Function;

import ADTMatrix.Matrix;

public class TestSPL {
    public static void main(String[] args) {
        // Contoh sistem persamaan linear dengan satu solusi
        // 2x + 3y + z = 1
        // 4x + 5y + 2z = 2
        // 6x + 7y + 3z = 3

        double[][] data = {
            {1, 1, 1, 2},
            {1, 2, 4, 3},
            {1, 3, 9, 5}
        };

        // Membuat objek matriks
        Matrix m = new Matrix();
        m.toMatrix(data, 3, 4);

        // Mencetak matriks untuk referensi
        System.out.println("Matriks:");
        int i = 0, j;
        while(i<m.row){

            j = 0;
            while(j<m.col){

                System.out.print(m.matrix[i][j] + " ");

                j+=1;
            }

            System.out.print("\n");

            i+=1;
        }

        // Memanggil metode solve
        String[] solusi = SPL.solve(m);

        // Menampilkan hasil
        if (solusi.length == 0) {
            System.out.println("Tidak ada solusi.");
        } else {
            System.out.println("Solusi:");
            for (String s : solusi) {
                System.out.println(s);
            }
        }
    }
}
