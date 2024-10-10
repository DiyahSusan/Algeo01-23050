package ADTMatrix;
//import ADTMatrix.Matrix;


public class Determinan {

    // mencari determinan dengan metode kofaktor
    public double detKofaktor(Matrix m){
        Matrix tempMatrix;
        int i, j, k;
        double x;
        double [][] temp; //matrix kosong
        //inisialisasi
        double det = 0;

        int row = m.getRowLength();
        int col = m.getColLength();

        //cek apakah matrix persegi
        if (m.isSquare()){
            if (row == 1 || col == 1){ //satu kali satu
                return m.matrix[0][0];
            }
            else{

                //kofaktor baris pertama
                for (i=0; i<col; i++){
                    //membuat matriks kofaktor
                    tempMatrix = new Matrix();
                    temp = new double [row-1][col-1];
                    for (j=1; j<col; j++){
                        for (k=0; k < row; k++){

                            x = m.matrix[j][k];

                            //menentukan lokasinya di matrix kofaktor
                            if (k>i){
                                temp[j-1][k-1] = x;
                            }
                            else if (k<i){
                                temp[j-1][k] = x;
                            }
                        }
                    }
                    tempMatrix.toMatrix(temp, row-1, col-1);
                    det += Math.pow(-1,i) * m.matrix[0][i] * detKofaktor(tempMatrix);  
                }
                return det;
            }
        }
        else {
            return m.MARK;
        }
    }

    //mencari determinan dengan OBE
    public double detOBE(Matrix m){
        int row = m.getRowLength();
        int col = m.getColLength();
        double det = 1; //inisialisasi
        boolean swapped = false;
        int i,j,k;

        // mengubah matrix mnejadi segitiga atas
        for (i=0; i < row; i++){
            //jika elemen diagonal 0, cari baris bawahnya untuk ditukar
            if(m.matrix[i][i] == 0){
                j = i + 1;
                while (j<row && m.matrix[j][i] == 0){
                    j++;
                }

                if (j<row){
                    //tukar baris
                    for (k=0; k<col; k++){
                        double temp = m.matrix[i][k];
                        m.matrix[i][k] = m.matrix[j][k];
                        m.matrix[j][k] = temp;
                    }
                    det *= -1; //pertukaran baris mengubah tanda
                    swapped = true;
                }
                else{
                    return 0; //jika seluruh kolom di bawah diagonal 0, determinan = 0
                }
            }

            //eliminasi untuk elemen di bawah diagonal
            for (j=i+1; j<row; j++){
                double factor = m.matrix[j][i] / m.matrix[i][i];
                for (k=i; k<col; k++){
                    m.matrix[j][k] -= factor * m.matrix[i][k];
                }
            }
        }
        //hitung detreminan dengan mengalikan elemen diagonal
        for (i=0; i<row; i++){
            det *= m.matrix[i][i];
        }

        return det;
    }
}

