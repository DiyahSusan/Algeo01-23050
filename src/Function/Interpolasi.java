package Function;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ADTMatrix.Matrix;

public class Interpolasi{
    public static String[] findFunction(double[][] titik, int banyakTitik){
        Matrix m = new Matrix();
        m.createMatrix(banyakTitik, banyakTitik+1);
        int i = 0, j;
        while(i<banyakTitik){

            j = 0;
            while(j<banyakTitik){

                m.matrix[i][j] = Math.pow(titik[i][0], j);

                j+=1;
            }

            m.matrix[i][j] = titik[i][1];
            i+=1;
        }

        i = 0;
        while(i<m.row){

            j = 0;
            while(j<m.col){

                System.out.print(m.matrix[i][j]);
                System.err.print(" ");

                j+=1;
            }

            System.out.print("\n");

            i+=1;
        }

        return SPL.solve(m);
    }

    public static double findY(String[] function, double x){
        double y = 0;
        int i = 0;
        while(i<function.length){

            y+=Double.parseDouble(function[i]) * Math.pow(x, i);

            i+=1;
        }
        return y;
    }
}