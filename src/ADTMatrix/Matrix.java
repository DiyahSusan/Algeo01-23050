package ADTMatrix;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import Function.*;


public class Matrix{

    public double matrix[][];
    public int row;
    public int col;

    public double MARK = Double.NaN;

    public void toMatrix(double a[][], int row, int col){
        this.matrix = a;
        this.row = row;
        this.col = col;
    }

    //membuat matriks kosong
    public void createMatrix(int row, int col){
        double a[][];
        a = new double[row][col];

        int i = 0, j = 0;
        while(i<row){
            while(j<col){

                a[i][j] = MARK;

                j+=1;
            }

            i+=1;
        }

        this.matrix = a;
        this.row = row;
        this.col = col;
    }

    //panjang baris
    public int getRowLength(){
        return this.row;
    }

    //panjang kolom
    public int getColLength(){
        return this.col;
    }

    //mencari index baris terakhir
    public int getLastRowIndex(){
        return this.row - 1;
    }

    //mencari index kolom terakhir
    public int getLastColIndex(){
        return this.col - 1;
    }

    //mengecek apakah matrik persegi
    public boolean isSquare(){
        return this.row == this.col;
    }

    //mengambil elemen
    public double getElement(int row, int col){
        return this.matrix[row][col];
    }

    //memasukkan elemen
    public void setElement(int row, int col, double val){
        this.matrix[row][col] = val;
    }

    public void rowSwap(Matrix m, int row1, int row2){
        double temp;
        for (int i=0; i<m.col; i++){
            temp = m.getElement(row1, i);
            m.setElement(row1, i, m.getElement(row2, i));
            m.setElement(row2, i, temp);
        }
        
    }

    public static Matrix multiplyRow(Matrix m1, Matrix m2){
        int i, j, k;
        double temp;
        Matrix result;

        //inisialisasi matrix result
        result = new Matrix();
        result.createMatrix(m1.row, m2.col);

        
        //multiply
        for (i=0; i<result.row; i++){
            for (j=0; j<result.col; j++){
                temp = 0; //di set ulang untuk tiap perulangan
                for (k=0; k<m1.col; k++){
                    temp = temp + (m1.getElement(i,k) * m2.getElement(k, j));
                }
                result.setElement(i, j, temp);
            }
        }
        return result;
    }

    public static double sumRow(Matrix m, int row){
        int col;
        double sum = 0;//inisialisasi

        for (col=0; col<m.col; col++){
            sum += m.matrix[row][col];
        }
        return sum;
    }

    //menjumlahkan semua elemen pada satu kolom
    public static double sumColumn(Matrix m, int col){
        int row;
        double sum = 0;//inisialisasi

        for (row=0; row<m.row; row++){
            sum += m.matrix[row][col];
        }
        return sum;

    }

    //menjumlahkan semua hasil perkalian elemen dari 2 kolom yang berbeda
    public static double sumMultiplyCol(Matrix m, int a, int b){
        double sum;
        int i;

        for (i=0; i<m.row; i++){
            sum = sum + (m.matrix[i][a] * m.matrix[i][b]);
        }

        return sum;
    }

    //menjumlahkan semua hasil perkalian elemen dari 2 baris yang berbeda
    public static double sumMultiplyRow(Matrix m, int a, int b){
        double sum;
        int i;

        for (i=0; i<m.col; i++){
            sum = sum + (m.matrix[a][i] * m.matrix[b][i]);
        }

        return sum;
    }
    
    //menambahkan baris
    public static Matrix addRow(Matrix m, double[] row){
        Matrix resMatrix;
        double[][] tempMatrix;
        int i,j;

        tempMatrix = new double[m.row+1][m.col];

        for (i=0; i<m.row; i++){
            for (j=0; j<m.col; j++){
                tempMatrix[i][j] = m.matrix[i][j];
            }
        }

        for (j=0; j<m.col; j++){
            tempMatrix[m.row][j] = row[j];
        }

        resMatrix = new toMatrix(tempMatrix, m.row+1, m.col);
        return resMatrix;
    }

    //menambahkan kolom
    public static Matrix addCol(Matrix m, double[] col){
        Matrix resMatrix;
        double[][] tempMatrix;
        int i,j;

        tempMatrix = new double[m.row][m.col+1];

        for (i=0; i<m.row; i++){
            for (j=0; j<m.col; j++){
                tempMatrix[i][j] = m.matrix[i][j];
            }
        }

        for (i=0; i<m.row; i++){
            tempMatrix[i][m.col] = col[i];
        }

        resMatrix = new toMatrix(tempMatrix, m.row, m.col+1);
        return resMatrix;
    }

    //mengecek bila matriks memiliki banyak solusi dengan prekondisi matriks sudah berbentuk matriks oselon
    public static boolean isManySolution(Matrix m){
        boolean isAllZero;
        isAllZero = True;

        for (i=0; i<m.col; i++){
            if(m.matrix[m.row-1][i]!=0){
                isAllZero = False;
            }
        }
        return isAllZero;
    }

    //mengecek bila matriks tidak memiliki solusi dengan prekondisi matriks sudah berbentuk matriks oselon
    public static boolean isNoSolution(Matrix m){
        boolean isAllZero;
        isAllZero = True;

        for (i=0; i<m.col-1; i++){
            if(m.matrix[m.row-1][i]!=0){
                isAllZero = False;
            }
        }
        return (isAllZero && (m.matrix[m.row-1][m.col-1]!=0)) ;
    }



}