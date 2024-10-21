package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import ADTMatrix.*;

public class Bikubic {
    // Membuat Matriks f(x,y)
    public static Matrix XforF() {
        int i, j;
        Matrix F;
        F = new Matrix();
        F.createMatrix(4, 16);  // Changed from 4,6 to 4,16 to match other methods
        int row = 0;
        int col;
        int x, y;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0;
                y = 0;
            }
            else if (row == 2) {
                x = 1;
                y = 0;
            }
            else if (row == 1) {
                x = 0;
                y = 1;
            }
            else {
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;
            for (col = 0; col < 16; col++) {
                F.setElement(row, col, Math.pow(x, i) * Math.pow(y, j));
                j++;
                if (j == 4) {
                    j = 0;
                    i++;
                }
            }
        }
        return F;
    }

    // Membuat Matriks fx(x,y)
    public static Matrix XforFx(){
        int x, y;
        int i, j;
        Matrix Fx;
        Fx = new Matrix();
        Fx.createMatrix(4, 16);
        int row = 0;
        int col;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 1){
                x = 1;
                y = 0;
            }
            else if (row == 2){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;

            for (col = 0; col < 16; col++){
                if (i == 0){
                    Fx.setElement(row, col, 0.0000);
                }
                else{
                    Fx.setElement(row, col, Math.pow(x, i-1) * Math.pow(y, j) * i);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        return Fx;
    }

    // Membuat Matriks fy(x,y)
    public static Matrix XforFy() {
        int x, y;
        int i, j;
        Matrix Fy;
        Fy = new Matrix();
        Fy.createMatrix(4, 16);  // Changed from 4,6 to 4,16 to match the loop
        int row = 0;
        int col;
        // rest of the method remains the same
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0;
                y = 0;
            }
            else if (row == 1) {
                x = 1;
                y = 0;
            }
            else if (row == 2) {
                x = 0;
                y = 1;
            }
            else {
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;
    
            for (col = 0; col < 16; col++) {
                if (j == 0) {
                    Fy.setElement(row, col, 0.0000);
                }
                else {
                    Fy.setElement(row, col, Math.pow(x, i) * Math.pow(y, j-1) * j);
                }
                i++;
                if (i > 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return Fy;
    }

    // Membuat Matriks fxy(x,y)
    public static Matrix XforFxy(){
        int x, y;
        int i, j;
        Matrix Fxy;
        Fxy = new Matrix();
        Fxy.createMatrix(4, 16);
        int row = 0;
        int col;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 1){
                x = 1;
                y = 0;
            }
            else if (row == 2){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;

            for (col = 0; col < 16; col++){
                if (i == 0 || j == 0){
                    Fxy.setElement(row, col, 0.0000);
                }
                else{
                    Fxy.setElement(row, col, Math.pow(x, i-1) * Math.pow(y, j-1) * j * i);
                }
                i++;
                if (i >= 4){
                    i = 0;
                    j++;
                }
            }
        }
        return Fxy;
    }

    // Membuat Matriks 16x16 yang berisi f, fx, fy, dan fxy
    public static Matrix matrixX(){
        Matrix X = new Matrix();
        X.createMatrix(16,16);
        int i, j;
        // mengisi nilai matriks X
        for (i = 0; i < 16; i++){
            for (j = 0; j < 16; j++){
                if (i >= 0 && i <= 3){
                    X.setElement(i, j, XforF().getElement(i, j));
                }
                else if (i >= 4 && i <= 7){
                    X.setElement(i, j, XforFx().getElement(i%4, j));
                }
                else if (i >= 8 && i <= 11){
                    X.setElement(i, j, XforFy().getElement(i%4, j));
                }
                else if (i >= 12 && i <= 15){
                    X.setElement(i, j, XforFxy().getElement(i%4, j));
                }
            }
        }
        return X;
    }
    
    public static void interpolasiBicubic (Matrix m){
        int i, j;
        Matrix m1;
        Matrix m2;
        // menginput matriks 4x4
        m1 = new Matrix();
        m1.createMatrix(4, 4);
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                m1.setElement(i, j, m.matrix[i][j]);
            }
        }

        // menginput nilai a dan b
        m2 = new Matrix();
        m2.createMatrix(1, 2);
        for(j = 0; j < 2; j++){
            m2.setElement(0, j, m.matrix[4][j]);
        }

        Matrix tempY = new Matrix();
        tempY.createMatrix(16, 1);
        Matrix X, A;
        // mengubah bentuk 4x4 menjadi 16x1
        int indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                tempY.setElement(indeks, 0, m1.getElement(i,j));
                indeks++;
            }
        }

        // A = (invers X) * Y
        X = matrixX();
        Matrix inversX = Invers.invers(X);
        A = Matrix.multiplyMatrix(inversX, tempY);

        //jumlahkan hasil perkalian A dengan a pangkat i dan b pangkat j
        Double hasil = 0.0;
        indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                hasil += A.getElement(indeks, 0) * Math.pow(m2.matrix[0][0], i) * Math.pow(m2.matrix[0][1], j);
                indeks++;
            }
        }
        // output file
        System.out.println("f(" + m2.matrix[0][0] + "," + m2.matrix[0][1]+ ") = " + String.format("%.4f", hasil));
        /*int pil3 = OutputMatrix.printMenuOutput();
        if(pil3 == 1) {
            String nameFile = "";
            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "test/Output/" + nameFile;
            }
            catch (IOException err) {
                err.printStackTrace();
            }
            try {
                FileWriter file = new FileWriter("test/Output/" + nameFile);
                file.write("f(" + Double.toString(m2.matrix[0][0]) + "," + Double.toString(m2.matrix[0][1])+ ") = " + String.format("%.4f", hasil));
                file.close();
            }
            catch(IOException err) {
                err.printStackTrace();
            }
        }*/
    }
}