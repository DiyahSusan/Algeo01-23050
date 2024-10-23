package ADTMatrix;

import Function.*;

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

    public void rowSwap(int row1, int row2){
        double temp;
        for (int i=0; i<this.col; i++){
            temp = this.getElement(row1, i);
            this.setElement(row1, i, this.getElement(row2, i));
            this.setElement(row2, i, temp);
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

    public static double sumMultiplyCol(Matrix m, int i, int j){
        double sum = 0;
        int k;
        for (k = 0; k < m.row ;k++){
            sum += m.matrix[k][i] * m.matrix[k][j];
        }
        return sum;
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
    public boolean isManySolution(){
        boolean isAllZero;
        int i;
        isAllZero = true;

        for (i=0; i<this.col; i++){
            if(this.matrix[this.row-1][i]!=0){
                isAllZero = false;
            }
        }
        return isAllZero;
    }

    // mengecek bila matriks tidak memiliki solusi dengan prekondisi matriks sudah berbentuk matriks eselon
    public boolean isNoSolution(){
        boolean isAllZero;
        int i;
        isAllZero = true;

        for (i=0; i<this.col-1; i++){
            if(this.matrix[this.row-1][i]!=0){
                isAllZero = false;
            }
        }
        return (isAllZero && (this.matrix[this.row-1][this.col-1]!=0)) ;
    }

    public Matrix subMatrix(Matrix m, int row, int col){
        Matrix hasil = new Matrix();
        int i, j, k = 0, l = 0;
        hasil.createMatrix(m.row - 1, m.col - 1);
        for (i = 0; i < m.row; i++){
            l = 0;
            for (j = 0; j < m.col; j++){
                if ((i == row) || (j == col)){
                    continue;
                } else {
                    hasil.matrix[k][l] = m.matrix[i][j];
                    if (l < hasil.col-1){
                        l++;
                    } else {
                        k++;
                    }
                }
            }
        }
        return hasil;
    }

    //determinan matrix kofaktorIJ, determinan tanpa baris i dan kolom j
    public static double detKofaktorIJ(Matrix m, int row, int col){
        int n = m.getRowLength();
        int i, j;
        Matrix tempMatrix;
        tempMatrix = new Matrix ();
        tempMatrix.createMatrix(n-1, n-1); //membuat matrix untuk menyimoan nilai sementara

        for(i=0; i<n; i++){
            if (i == row){ //jika i = row maka dia tidak akan diikutkan ke perhitungan
                continue;
            }
            for (j=0; j<n; j++){
                if (j == col){ //jika j = col maka dia tidak akan diikutkan ke perhitungan
                    continue;
                }
                if (i<row){ //di atas baris yang ingin dihapus
                    if (j<col){ //di kiri kolom yang dihapus
                        tempMatrix.setElement(i, j, m.getElement(i, j)); //tetap
                    }
                    else{
                        tempMatrix.setElement(i, j-1, m.getElement(i,j)); // geser ke kiri
                    }
                }
                else{
                    if (j<col){ //di kiri kolom yang dihapus
                        tempMatrix.setElement(i-1, j, m.getElement(i,j)); //geser ke atas
                    }
                    else{
                        tempMatrix.setElement(i-1, j-1, m.getElement(i,j)); //geser ke atas kiri
                    }
                }
            }

        }
        return Determinan.detKofaktor(tempMatrix);
    }

    public Matrix matrixKofaktor(Matrix m){
        Matrix hasil = new Matrix();
        hasil.createMatrix(this.row, this.col);
        int i,j;

        for (i = 0; i < m.row; i++){
            for (j = 0; j < m.col; j++){
               hasil.setElement(i, j, detKofaktorIJ(m, i, j));
                if ((i+j) % 2 == 1 && hasil.getElement(i,j) != 0)
			    	hasil.setElement(i, j, (hasil.getElement(i,j) * -1));
            }
        }

        return hasil;
    }

    public Matrix Adjoin (Matrix m){
        // adjoin adalah transpose dari matrix kofaktor
        return transpose(matrixKofaktor(m));
    }

    public void cekMinNol(){
        int i = 0, j;
        while(i<this.row){

            j = 0;
            while(j<this.col){

                if(this.matrix[i][j] < 0.0000000001 && this.matrix[i][j] > -0.0000000001) this.matrix[i][j] = 0;

                j+=1;
            }

            i+=1;
        }
    }

    public String[] solveManySolution(){
        String[] anu = new String[this.col-1];
        int i = this.row - 2, j;
        while(i>=0){

            j = this.col-2;
            while(j>=0){

                if(Math.abs(this.matrix[i][j]) > (float) Math.pow(10, -10)){
                    
                }

                j-=1;
            }

            i-=1;
        }
        return anu;
    }

    // Eliminasi Gauss
    public Matrix gaussElimination(){
        Matrix hasil = this;

        int i = 0, j = 0, k;
        while(i<this.row && j < this.col){

            k = i+1;
            while(hasil.matrix[i][j] == 0 && k < this.row){
                hasil.rowSwap(i, k);
                //System.out.println("Menukar karena masih 0\n");
                k+=1;
            }

            if(hasil.matrix[i][j] == 0){
                j+=1;
                continue;
            }

            double pembagi = 1/hasil.matrix[i][j];
            hasil.multiplyRow(i, pembagi);
            //System.out.println("Membagi dengan " + pembagi);

            k = i+1;
            while(k<hasil.row){

                hasil.sumMultiplyRow(k, i, (-1) * hasil.matrix[k][j]);
                //System.out.println("Menambah row ke-" + i + " dengan row ke-" + k + " dikalikan dengan " + hasil.matrix[k][j] + "\n");

                k+=1;
            }

            i+=1;
            j+=1;
        }

        hasil.cekMinNol();

        return hasil;
    }

    public Matrix gaussJordanElimination(){
        Matrix hasil = this;

        int i = 0, j = 0, k;
        while(i<this.row && j < this.col){

            k = i+1;
            while(hasil.matrix[i][j] == 0 && k < this.row){
                hasil.rowSwap(i, k);
                //System.out.println("Menukar karena masih 0\n");
                k+=1;
            }

            if(hasil.matrix[i][j] == 0){
                j+=1;
                continue;
            }

            double pembagi = 1/hasil.matrix[i][j];
            hasil.multiplyRow(i, pembagi);
            //System.out.println("Membagi dengan " + pembagi);

            k = i+1;
            while(k<hasil.row){

                hasil.sumMultiplyRow(k, i, (-1) * hasil.matrix[k][j]);
                //System.out.println("Menambah row ke-" + i + " dengan row ke-" + k + " dikalikan dengan " + hasil.matrix[k][j] + "\n");

                k+=1;
            }

            k = i-1;
            while (k >= 0) {
                hasil.sumMultiplyRow(k, i, -hasil.matrix[k][j]);
                k -= 1;
            }

            i+=1;
            j+=1;
        }

        hasil.cekMinNol();

        return hasil;
    }

    public static void backSubstitution(Matrix matrix, double[] X) {
        int i, j;
        int n, m;
        
        n = matrix.getRowLength();
        m = matrix.getColLength();

        for (i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElement(i, m - 1);
            for (j = i + 1; j < n; j++) {
                X[i] -= matrix.getElement(i, j) * X[j];
            }
            X[i] /= matrix.getElement(i, i);
        }
    }

    public Matrix transpose(Matrix m){
        Matrix result;
        result = new Matrix();
        result.createMatrix(m.col, m.row);
        for (int i = 0; i< m.row; i++){
            for (int j = 0; j<m.col; j++){
                result.setElement(j, i, m.getElement(i,j));
            }
        }
        return result;
    }

    // Fungsi untuk perkalian matriks dengan vektor
    public static double[] multiplyVektor(Matrix m, double[] A) {
        double[] result = new double[m.row];
        for (int i = 0; i < m.row; i++) {
            result[i] = 0; //inisialisasi
            for (int j = 0; j < m.col; j++) {
                result[i] += m.getElement(i,j) * A[j];
            }
        }
        return result;
    }

    public Matrix copy(){
        Matrix hasil = new Matrix();
        hasil.createMatrix(this.row, this.col);

        int i, j;

        i = 0;
        while(i<this.row){

            j = 0;
            while(j<this.col){

                hasil.matrix[i][j] = this.matrix[i][j];

                j+=1;
            }

            i+=1;
        }

        return hasil;
    }

}