package Function;

import ADTMatrix.Matrix;
import IO.Output;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RegresiKuadratik {

    public static void RegresiKuadratikKeyboard(Matrix m) {
        Scanner scanner = new Scanner(System.in);
        int i;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        double[] x;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        // Memperluas array untuk menampung nilai kuadrat
        x = new double[m.getColLength() - 1]; // Ambil panjang kolom dikurangi 1 untuk menampung variabel x

        // Input nilai-nilai x yang ingin ditaksir
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for (i = 0; i < x.length; i++) {
            x[i] = scanner.nextDouble();
        }

        // Membuat matriks SPL untuk persamaan kuadratik
        mTemp = createSPLKuadratik(m);

        // // Melakukan Eliminasi Gauss
        // mTemp = mTemp.gaussElimination();

        // mengubah matrix mnejadi segitiga atas
        int j,k;
        for (i=0; i < mTemp.row; i++){
            //jika elemen diagonal 0, cari baris bawahnya untuk ditukar
            if(mTemp.matrix[i][i] == 0){
                j = i + 1;
                while (j<mTemp.row && mTemp.matrix[j][i] == 0){
                    j++;
                }

                if (j<mTemp.row){
                    //tukar baris
                    for (k=0; k<mTemp.col; k++){
                        double temp = mTemp.matrix[i][k];
                        mTemp.matrix[i][k] = mTemp.matrix[j][k];
                        mTemp.matrix[j][k] = temp;
                    }
                }
                // else{
                //     return 0; //jika seluruh kolom di bawah diagonal 0, determinan = 0
                // }
            }

            //eliminasi untuk elemen di bawah diagonal
            for (j=i+1; j<mTemp.row; j++){
                double factor = mTemp.matrix[j][i] / mTemp.matrix[i][i];
                for (k=i; k<mTemp.col; k++){
                    mTemp.matrix[j][k] -= factor * mTemp.matrix[i][k];
                }
            }
        }

        double[] m1 = new double[mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, m1);

        // Menampilkan persamaan kuadratik
        System.out.print("f(x1, x2) = ");
        for (i = 0; i < m1.length; i++) {
            if (i == 0) {
                // a0 (konstanta)
                result = m1[i];
                if (m1[i] > 0) {
                    System.out.printf("%.4f ", m1[i]);
                } else {
                    System.out.printf("- %.4f ", Math.abs(m1[i]));
                }
            } else if (i == 1) {
                // a1 * x1 (linear untuk x1)
                result = m1[i] * x[0];  // x[0] adalah x1
                if (m1[i] > 0) {
                    System.out.printf("+ %.4fx1 ", m1[i]);
                } else {
                    System.out.printf("- %.4fx1 ", Math.abs(m1[i]));
                }
            } else if (i == 2) {
                // a2 * x2 (linear untuk x2)
                result = m1[i] * x[1];  // x[1] adalah x2
                if (m1[i] > 0) {
                    System.out.printf("+ %.4fx2 ", m1[i]);
                } else {
                    System.out.printf("- %.4fx2 ", Math.abs(m1[i]));
                }
            } else if (i == 3) {
                // a3 * x1^2 (kuadratik untuk x1)
                result = m1[i] * x[0] * x[0];
                if (m1[i] > 0) {
                    System.out.printf("+ %.4fx1^2 ", m1[i]);
                } else {
                    System.out.printf("- %.4fx1^2 ", Math.abs(m1[i]));
                }
            } else if (i == 4) {
                // a4 * x2^2 (kuadratik untuk x2)
                result = m1[i] * x[1] * x[1];
                if (m1[i] > 0) {
                    System.out.printf("+ %.4fx2^2 ", m1[i]);
                } else {
                    System.out.printf("- %.4fx2^2 ", Math.abs(m1[i]));
                }
            } else if (i == 5) {
                // a5 * x1 * x2 (interaksi antara x1 dan x2)
                result = m1[i] * x[0] * x[1];
                if (m1[i] > 0) {
                    System.out.printf("+ %.4fx1x2 ", m1[i]);
                } else {
                    System.out.printf("- %.4fx1x2 ", Math.abs(m1[i]));
                }
            }
            sum += result;  // Menambahkan hasil tiap iterasi ke sum
        }
        System.out.printf(" f(xk) = %.4f\n", sum);

        // System.out.print("f(x) = ");
        // for (i = 0; i < m1.length; i++) {
        //     if (i == 0) {
        //         result = m1[i];
        //         if (m1[i] > 0) {
        //             System.out.printf("%.4f ", m1[i]);
        //         } else {
        //             System.out.printf("- %.4f ", Math.abs(m1[i]));
        //         }
        //     } else if (i == 1) {
        //         result = m1[i] * x[0];
        //         if (m1[i] > 0) {
        //             System.out.printf("+ %.4fx ", m1[i]);
        //         } else {
        //             System.out.printf("- %.4fx ", Math.abs(m1[i]));
        //         }
        //     } else {
        //         result = m1[i] * x[0] * x[0];
        //         if (m1[i] > 0) {
        //             System.out.printf("+ %.4fx^2 ", m1[i]);
        //         } else {
        //             System.out.printf("- %.4fx^2 ", Math.abs(m1[i]));
        //         }
        //     }
        //     sum += result;
        // }
        System.out.printf("\nf(xk) = %.4f\n", sum);

        // Output
        int opsi = Output.opsiOutput();
        if (opsi == 1) {
            // Mencetak output ke dalam bentuk file
            String nameFile = "";
            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "test/Output/" + nameFile;

                // Cek apakah file sudah ada
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        return; // Jika tidak memilih 'y', batalkan
                    }
                }

                try (FileWriter fileWriter = new FileWriter(path)) {
                    fileWriter.write("f(x) = ");
                    for (i = 0; i < m1.length; i++) {
                        if (i == 0) {
                            fileWriter.write(String.format("%.4f ", m1[i]));
                        } else if (i == 1) {
                            fileWriter.write(String.format("+ %.4fx ", m1[i]));
                        } else {
                            fileWriter.write(String.format("+ %.4fx^2 ", m1[i]));
                        }
                    }
                    fileWriter.write(String.format("\nf(xk) = %.4f\n", sum));
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    // Membuat matriks SPL untuk persamaan kuadratik
    public static Matrix createSPLKuadratik(Matrix m) {
        int n = 3; // Jumlah koefisien (a0, a1, ..., an)
        Matrix mTemp = new Matrix();
        mTemp.createMatrix(n, n + 1); // Membuat matriks SPL

        double[] sumX = new double[2 * 2 + 1];
        double[] sumY = new double[n];

        // Menghitung sumX untuk berbagai pangkat x (sumX[i] = sum x^i)
        for (int i = 0; i <= 2 * 2; i++) {
            sumX[i] = sumPowerCol(m, 0, i); // sum x^i
        }

        // Menghitung sumY untuk berbagai pangkat x dikalikan y (sumY[i] = sum x^i * y)
        for (int i = 0; i < n; i++) {
            sumY[i] = sumXPowerMultiplyY(m, 0, 1, i); // sum x^i * y
        }

        // Mengisi matriks SPL berdasarkan sumX dan sumY
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mTemp.setElement(i, j, sumX[i + j]);
            }
            mTemp.setElement(i, n, sumY[i]);
        }

        return mTemp;
    }

    public static double sumPowerCol(Matrix m, int col, int power) {
        double sum = 0.0;
        for (int i = 0; i < m.getRowLength(); i++) {
            double value = m.getElement(i, col);
            sum += Math.pow(value, power);
        }
        return sum;
    }

    public static double sumXPowerMultiplyY(Matrix m, int xCol, int yCol, int power) {
        double sum = 0.0;
        for (int i = 0; i < m.getRowLength(); i++) {
            double xValue = m.getElement(i, xCol);
            double yValue = m.getElement(i, yCol);
            sum += Math.pow(xValue, power) * yValue;
        }
        return sum;
    }
}
