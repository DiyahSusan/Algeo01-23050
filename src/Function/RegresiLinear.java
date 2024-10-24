package Function;
import ADTMatrix.Matrix;
import IO.Output;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RegresiLinear{

    public static void regresiLinearKeyboard(Matrix m){
        Scanner scanner = new Scanner(System.in);
        int i;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        double [] x;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        x = new double [m.col - 1];

        //input nilai-nilai x yang ingin ditaksir
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for(i = 0; i < x.length; i++){
            x[i] = scanner.nextDouble();
        }

        //membuat matriks baru semacam SPL
        mTemp = new Matrix();
        mTemp.createMatrix(m.col, m.col + 1);

        mTemp = createSPLMatrix(m);
        
        // Melakukan Eliminasi Gauss
        mTemp = mTemp.gaussElimination();
        double[] m1 = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, m1);

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = m1[i];
                if (m1[i] > 0){
                    System.out.printf("%.4f ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f ", m1[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d ", m1[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d, ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d, ", m1[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f\n", sum);

        //Output
        int opsi = Output.opsiOutput();
        if (opsi == 1) {
            // mencetak output ke dalam bentuk file
            String nameFile = "";
            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "test/Output/" + nameFile;

                // cek apakah sudah ada file
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        return; // ngga dibikin file kalau tidak pilih y
                }
            }
            } catch (IOException err) {
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("test/Output/" + nameFile);
                file.write("f(x) = ");
                for (i = 0; i < mTemp.row; i++) {
                    if (i == 0){
                        if (m1[i] > 0){
                            file.write(String.format("%.4f", m1[i]));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+String.format("%.4f", m1[i]));
                        }
                    } else if (i > 0 && i < mTemp.row - 1){
                        result = m1[i] * x[i - 1];
                        if (m1[i] > 0){
                            file.write("+ "+ String.format("%.4f", m1[i])+"x"+Integer.toString(i));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+ String.format("%.4f", m1[i])+"x"+Integer.toString(i));
                        }
                    } else if (i == mTemp.row - 1){
                        result = m1[i] * x[i - 1];
                        if (m1[i] > 0){
                            file.write("+ "+String.format("%.4f", m1[i])+"x^"+Integer.toString(i));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+String.format("%.4f", m1[i])+"x^"+Integer.toString(i));
                        }
                    }
                }
                file.write("\nf(xk) = "+String.format("%.4f", sum));
                file.close();
            }
            catch (IOException err) {
                err.printStackTrace();
            }
            System.out.println("File berhasil dibuat!");
        }
    }

    public static void regresiLinearFile () {
        Scanner scanner = new Scanner(System.in);
        int i, j;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        Matrix m1;
        double[] m2;
        double[] x;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        // membaca file 
        String fileName = "";
        System.out.println("Masukkan nama file untuk membaca data: ");
        try {
            fileName = inputFile.readLine();
            // Membaca data dari file
            FileReader fr = new FileReader("test/Input/" + fileName);
            BufferedReader br = new BufferedReader(fr);
            
            // Membaca jumlah baris dan kolom dari file
            int rowCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
                rowCount++;
            }

            // Mengatur ulang reader untuk membaca dari awal lagi
            br.close();
            fr = new FileReader("test/Input/" + fileName);
            br = new BufferedReader(fr);

            // membuat matriks dengan ukuran yang sesuai
            m1 = new Matrix();
            String firstLine = br.readLine();
            String[] firstLineValues = firstLine.split("\\s+"); // Misalnya, nilai dipisahkan oleh spasi
            int colCount = firstLineValues.length;

            m1.createMatrix(rowCount, colCount); // Menggunakan jumlah baris dan kolom yang dibaca dari file

            // memasukkan nilai baris pertama ke dalam matriks
            for (j = 0; j < firstLineValues.length; j++) {
                m1.setElement(0, j, Double.parseDouble(firstLineValues[j]));
            }

            // membaca isi file ke dalam matriks m1
            int currentRow = 1; // baris pertama sudah dibaca
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s+");
                for (j = 0; j < values.length; j++) {
                    m1.setElement(currentRow, j, Double.parseDouble(values[j]));
                }
                currentRow++;
            }
            br.close();
        } catch (IOException err) {
            err.printStackTrace();
            return; // Keluar jika ada kesalahan
        }

        // Membuat matriks yang memisahkan variabel X dan hasil Y
        m2 = new double[m1.col - 1];
        for(i = 0; i < m2.length; i++){ 
            m2[i] = m1.getElement(m1.row - 1, i);
        }

        // membuat matriks baru untuk SPL
        mTemp = new Matrix();
        mTemp.createMatrix(m1.col, m1.col + 1);
        mTemp = createSPLMatrix(m1); // Menggunakan matriks m1

        // melakukan eliminasi Gauss
        mTemp = mTemp.gaussElimination();
        x = new double[mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, x); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = x[i];
                if (x[i] > 0){
                    System.out.printf("%.4f ", x[i]);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4f ", x[i]);
                }
            } else if (i > 0 && i < mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d ", x[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d, ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d, ", x[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f\n", sum);

        //Output
        int opsi = Output.opsiOutput();
        if (opsi == 1) {
            // mencetak output ke dalam bentuk file
            String nameFile = "";
            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "test/Output/" + nameFile;

                // cek filenya udah ada belum
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        return; //kalau ngga mau jadiin file berarti batal
                    }
                }
            } catch (IOException err) {
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("test/Output/" + nameFile);
                file.write("f(x) = ");
                for (i = 0; i < mTemp.row; i++) {
                    if (i == 0){
                        result = x[i];
                        if (x[i] > 0){
                            file.write(String.format("%.4f", x[i]));
                        } else {
                            x[i] *= -1;
                            file.write(" - "+String.format("%.4f", x[i]));
                        }
                    } else if (i > 0 && i < mTemp.row - 1){
                        result = x[i] * m2[i - 1];
                        if (x[i] > 0){
                            file.write(" + "+ String.format("%.4f", x[i]) +"x"+Integer.toString(i));
                        } else {
                            x[i] *= -1;
                            file.write(" - "+ String.format("%.4f", x[i]) +"x"+Integer.toString(i));
                        }
                    } else if (i == mTemp.row - 1){
                        result = x[i] * m2[i - 1];
                        if (x[i] > 0){
                            file.write(" + "+String.format("%.4f", x[i]) +"x"+Integer.toString(i));
                        } else {
                            x[i] *= -1;
                            file.write(" - "+String.format("%.4f", x[i]) +"x"+Integer.toString(i));
                        }
                    }
                    sum += result;
                }
                file.write("\nf(xk) = "+String.format("%.4f", sum));
                file.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
            System.out.println("File berhasil dibuat!");
        }
    }


    //convert matrix ke bentuk SPL
    private static Matrix createSPLMatrix(Matrix m) {
        Matrix mTemp = new Matrix();
        mTemp.createMatrix(m.col, m.col + 1);
        
        for (int i = 0; i < mTemp.row; i++) {
            for (int j = 0; j < mTemp.col; j++) {
                double value;
                if (i == 0 && j == 0) {
                    value = m.row;
                } else if (i == 0) {
                    value = Matrix.sumColumn(m, j - 1);
                } else if (j == 0) {
                    value = Matrix.sumColumn(m, i - 1);
                } else {
                    value = Matrix.sumMultiplyCol(m, i - 1, j - 1);
                }
                mTemp.setElement(i, j, value);
            }
        }
        return mTemp;
    }
 }

