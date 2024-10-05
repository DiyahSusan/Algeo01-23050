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

    public void toMatrix(double a[][], int row, int col){
        this.matrix = a;
        this.row = row;
        this.col = col;
    }

    public void createMatrix(int row, int col){
        double a[][];
        a = new double[row][col];

        int i = 0, j = 0;
        while(i<row){

            j = 0;
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

    public int getRowLength(){
        return this.row;
    }

    public int getColLength(){
        return this.col;
    }

    public int getLastRowIndex(){
        return this.row - 1;
    }

    public int getLastColIndex(){
        return this.col - 1;
    }

    public boolean isSquare(){
        return this.row == this.col;
    }

    public double getElement(int row, int col){
        return this.matrix[row][col];
    }

    public void setElement(int row, int col, double val){
        this.matrix[row][col] = val;
    }

}