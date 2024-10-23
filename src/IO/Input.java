package IO;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

import ADTMatrix.Matrix;

public class Input {
    public static Scanner scanner = new Scanner(System.in);
    public static Matrix readMatrix() {
        int i,j, row, col;
        double[][] m;
        Matrix hasil = new Matrix();

        System.out.print("Masukkan jumlah baris: ");
        row = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        col = scanner.nextInt();

        m = new double[row][col];
        System.out.println("Masukkan elemen matriks: ");
        for(i = 0; i < row; i++){
            for(j = 0; j < col; j++){
                m[i][j] = scanner.nextDouble();
            }
        }

        hasil.toMatrix(m, row, col);
        return hasil;
    }

    public static double[][] readInterpolasi(){
        int i, j;
        int n;
        double [][] m;

        System.out.print("Masukkan derajat polinom: ");
        n = scanner.nextInt();

        m = new double[n + 1][2];
        System.out.println("Masukkan titik x dan y: ");
        for(i = 0; i < n + 1; i++){
            for(j = 0; j < 2; j++){
                m[i][j] = scanner.nextDouble();
            }
        }
        return m;
    }

    public static double[][] readBicubic(){
        int i,j, x, y;
        double [][] m;

        m = new double[5][4];
        System.out.println("Masukkan nilai fungsi yang diketahui: ");
        System.out.println("**Ekspektasi format**");
        System.out.println("[f(0,0),   f(1,0),   f(0,1),   f(1,1)  ]");
        System.out.println("[fx(0,0),  fx(1,0),  fx(0,1),  fx(1,1) ]");
        System.out.println("[fy(0,0),  fy(1,0),  fy(0,1),  fy(1,1) ]");
        System.out.println("[fxy(0,0), fxy(1,0), fxy(0,1), fxy(1,1)]");
        for(i = 0; i < 4 + 1; i++){
            for(j = 0; j < 4; j++){
                m[i][j] = scanner.nextDouble();
            }
        }

        System.out.print("Masukkan nilai x: ");
        x = scanner.nextInt();
        System.out.print("Masukkan nilai y: ");
        y = scanner.nextInt();

        m[4][0] = x;
        m[4][2] = y;

        return m;
    }

    public static double[][] readRegresi(){
        int i, j;
        int x, m;
        double [][] matrix;

        System.out.print("Masukkan jumlah peubah (x): ");
        x = scanner.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = scanner.nextInt();

        matrix = new double[m][x + 1];
        System.out.println("Masukkan titik x dan y: ");
        for(i = 0; i < m; i++){
            for(j = 0; j < x + 1; j++){
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }
    public static Matrix readMatrixFile(){
        int i;
        Matrix m;
    
        System.out.print("Masukkan nama file: ");
        String file = scanner.nextLine();
        String path = "test/Input/" + file;
        System.out.println(path);

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String s;
            String[] x;
            double[] y;
            double[][] mTemp;

            s = br.readLine();
            
            x = s.split("\\s+");
            y = new double[x.length];

            for(i = 0; i < y.length; i++){
                y[i] = Double.parseDouble(x[i]);
            }

            mTemp = new double[1][x.length];
            for(i = 0; i < y.length; i++){
                mTemp[0][i] = y[i];
            }
             
            m = new Matrix();
            m.createMatrix(1, y.length);
            m.matrix = mTemp;

            while((s = br.readLine()) != null){
                x = s.split("\\s+");
                y = new double [x.length];
                for(i = 0; i < x.length; i++){
                    y[i] = Double.parseDouble(x[i]);
                }
                if(x.length < m.col){
                    double[] z = new double[m.col];
                    for(i = 0; i < m.col;i++){
                        if(i >= y.length){
                            z[i] = 0;
                        }else{
                            z[i] = y[i];
                        }
                    }
                    m = Matrix.addRow(m, z);
                }else{
                    m = Matrix.addRow(m, y);
                }
            }
            br.close();
            return m;
            
        }catch(Exception ex){
            System.out.println("File tidak ditemukan");
            System.out.println("Mengembalikan matriks kosong");
            m = new Matrix();
            m.createMatrix(1,1);
            return m;
        }
    }

    public static int caraInput(boolean salahInput){
        int hasil;
        while(true){

            Output.menu_input();

            salahInput = Output.pesan_salah_input(salahInput);

            System.out.print("> ");
            hasil = scanner.nextInt();

            if(hasil >= 0 && hasil <= 2){
                break;
            }else{
                salahInput = true;
            }
        }
        return hasil;
    }
}
