package Function;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ADTMatrix.Matrix;
import IO.Output;

public class SPL{

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

        System.out.println("Melakukan Eliminasi Gauss-Jordan untuk \nmendapatkan matriks eselon:");
        System.out.println("");
        Output.printMatrix(m);

        hasil = solve(m);

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

            anu[i] = String.valueOf(m.matrix[i][m.col - 1]);

            i+=1;
        }

        return anu;
    }

}