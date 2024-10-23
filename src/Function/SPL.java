package Function;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ADTMatrix.Matrix;
import IO.Output;

public class SPL{

    public static boolean is_no_solution(Matrix m){
        m.gaussElimination();
        return m.isNoSolution();
    }

    public static boolean is_many_solution(Matrix m){
        m.gaussElimination();
        return m.isManySolution();
    }

    public static String[] metode_gauss(Matrix m){
        String[] hasil;

        m.gaussElimination();

        System.out.println("Melakukan Eliminasi Gauss untuk mendapatkan matriks eselon:");
        System.out.println("");
        Output.printMatrix(m);

        hasil = solve(m);

        Output.solusi_spl(hasil);

        return hasil;
    }

    public static String[] metode_gauss_jordan(Matrix m){
        String[] hasil;

        m.gaussJordanElimination();

        System.out.println("Melakukan Eliminasi Gauss-Jordan untuk \nmendapatkan matriks eselon tereduksi:");
        System.out.println("");
        Output.printMatrix(m);

        hasil = solve(m);

        Output.solusi_spl(hasil);

        return hasil;
    }

    public static String[] metode_invers(Matrix m){

        String[] hasil = new String[m.row];
        int i, j;
        Matrix A = new Matrix(), B = new Matrix();
        A.createMatrix(m.row, m.col-1);
        B.createMatrix(m.row, 1);

        i = 0;
        while(i<m.row){

            j = 0;
            while(j<m.col-1){

                A.matrix[i][j] = m.matrix[i][j];

                j+=1;
            }

            i+=1;
        }

        i = 0;
        while(i<m.row){

            B.matrix[i][0] = m.matrix[i][m.col-1];

            i+=1;
        }

        System.out.println("Matriks dipisahkan menjadi matriks A \ndan matriks B\n");
        System.out.println("Matriks A");
        Output.printMatrix(A);
        System.out.println("Matriks B");
        Output.printMatrix(B);

        System.out.println("Matriks A diinvers");
        A = Invers.invers(A.copy());
        Output.printMatrix(A);

        System.out.println("Matriks A^-1 dikalikan dengan matriks B");
        B = Matrix.multiplyMatrix(A.copy(), B.copy());
        Output.printMatrix(B);

        i = 0;
        while(i<m.row){

            hasil[i] = String.format("%.4f", B.matrix[i][0]);

            i+=1;
        }

        Output.solusi_spl(hasil);

        return hasil;
    }

    public static String[] metode_cramer(Matrix m){
        String[] hasil = new String[m.col-1];
        int i, j;
        double determinanA;
        double[] determinanAxn = new double[m.col-1];
        Matrix A = new Matrix(), B = new Matrix(), temp = new Matrix();
        A.createMatrix(m.row, m.col-1);
        B.createMatrix(m.row, 1);
        temp.createMatrix(m.row, m.col-1);

        i = 0;
        while(i<m.row){

            j = 0;
            while(j<m.col-1){

                A.matrix[i][j] = m.matrix[i][j];

                j+=1;
            }

            i+=1;
        }

        if(!Invers.isInversible(A)){
            hasil[0] = "Matriks tidak dapat diselesaikan menggunakan Kaidah Cramer.";
            System.out.println(hasil[0]);
            System.out.println();
            return hasil;
        }

        i = 0;
        while(i<m.row){

            B.matrix[i][0] = m.matrix[i][m.col-1];

            i+=1;
        }

        System.out.println("Matriks dipisahkan menjadi matriks A \ndan matriks B\n");
        System.out.println("Matriks A");
        Output.printMatrix(A);
        System.out.println("Matriks B");
        Output.printMatrix(B);

        System.out.println("Determinan matriks A dihitung");
        determinanA = Determinan.detOBE(A.copy());
        System.out.println("det(A) = " + String.format("%.4f", determinanA));
        System.out.println();

        System.out.println("Matriks A disesuaikan menjadi matriks A_{x_1} sampai A_{x_n}");

        i = 0;
        while(i<A.row){

            temp = A.copy();

            j = 0;
            while(j<temp.row){
                temp.matrix[j][i] = B.matrix[j][0];
                j+=1;
            }

            System.out.println("Matriks A_{x_" + i + "}");
            Output.printMatrix(temp);

            determinanAxn[i] = Determinan.detOBE(temp);

            i+=1;
        }

        System.out.println("Menentukan determinan setiap matriks tersebut");
        i = 0;
        while(i<determinanAxn.length){
            System.out.println("det(A_{x_" + i + "}) = " + String.format("%.4f", determinanAxn[i]));
            i+=1;
        }
        System.out.println();

        i = 0;
        while(i<determinanAxn.length){
            hasil[i] = String.format("%.4f", determinanAxn[i] / determinanA);
            i+=1;
        }

        Output.solusi_spl(hasil);

        return hasil;
    }

    public static String[] solve(Matrix m){

        // tidak ada solusi
        String[] anu = new String[0];
        if(m.isNoSolution()) return anu;

        // banyak solusi
        anu = new String[m.col - 1];

        m = m.gaussElimination();

        if(m.isManySolution()){
            anu = m.solveManySolution();
            return anu;
        }

        // solusi unik
        m = m.gaussJordanElimination();

        int i = 0;
        while(i<m.row){

            anu[i] = String.format("%.4f", m.matrix[i][m.col - 1]);

            i+=1;
        }

        return anu;
    }

}