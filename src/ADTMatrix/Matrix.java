package ADTMatrix;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
//import Function.*;


public class Matrix{

    public double matrix[][];
    public int row;
    public int col;

    public double MARK = Double.NaN;

    public void toMatrix (double a[][], int row, int col){
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

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
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

    public void multiplyRow(int row, double pengali){
        int i = 0;
        while(i<this.col){

            this.matrix[row][i] *= pengali;

            i+=1;
        }
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
    public void sumMultiplyCol(int c1, int c2, double multiplier){
        int i;

        for (i=0; i<this.row; i++){
            this.matrix[i][c1] = this.matrix[i][c1] + (multiplier * this.matrix[i][c2]);
        }
    }

    //menjumlahkan semua hasil perkalian elemen dari 2 baris yang berbeda
    public void sumMultiplyRow(int r1, int r2, double multiplier){
        int i;

        for (i=0; i<this.col; i++){
            this.matrix[r1][i] = this.matrix[r1][i] + (multiplier * this.matrix[r2][i]);
        }
    }
    
    //menambahkan baris
    public static Matrix addRow(Matrix m, double[] row){
        Matrix resMatrix = new Matrix();
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

        resMatrix.toMatrix(tempMatrix, m.row+1, m.col);
        return resMatrix;
    }

    //menambahkan kolom
    public static Matrix addCol(Matrix m, double[] col){
        Matrix resMatrix = new Matrix();
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

        resMatrix.toMatrix(tempMatrix, m.row, m.col+1);
        return resMatrix;
    }

    //mengecek bila matriks memiliki banyak solusi dengan prekondisi matriks sudah berbentuk matriks oselon
    public static boolean isManySolution(Matrix m){
        boolean isAllZero;
        int i;
        isAllZero = true;

        for (i=0; i<m.col; i++){
            if(m.matrix[m.row-1][i]!=0){
                isAllZero = false;
            }
        }
        return isAllZero;
    }

    // mengecek bila matriks tidak memiliki solusi dengan prekondisi matriks sudah berbentuk matriks oselon
    public static boolean isNoSolution(Matrix m){
        boolean isAllZero;
        int i;
        isAllZero = true;

        for (i=0; i<m.col-1; i++){
            if(m.matrix[m.row-1][i]!=0){
                isAllZero = false;
            }
        }
        return (isAllZero && (m.matrix[m.row-1][m.col-1]!=0)) ;
    }

    // Eliminasi Gauss
    public Matrix gaussElimination(){
        Matrix hasil = this;

        int i = 0, j = 0, k;
        while(i<this.row){

            k = i+1;
            while(hasil.matrix[i][j] == 0 && k < this.row){
                hasil.rowSwap(hasil, i, k);
                k+=1;
            }

            if(hasil.matrix[i][j] == 0){
                j+=1;
                continue;
            }

            hasil.multiplyRow(i, 1/hasil.matrix[i][j]);

            k = i+1;
            while(k<hasil.row){

                hasil.sumMultiplyRow(j, k, (-1) * hasil.matrix[k][j]);

                k+=1;
            }

            i+=1;
            j+=1;
        }

        return hasil;
    }

}