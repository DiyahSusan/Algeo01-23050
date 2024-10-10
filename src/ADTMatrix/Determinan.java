package ADTMatrix;
//import ADTMatrix.Matrix;

public class Determinan {
    // mencari determinan dengan metode kofaktor
    public static double detKofaktor(Matrix m){
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
    public static double detOBE(Matrix m){
        double det = 1;
        int i, j, k, l;
        double x, y;
        int row = m.getRowLength();
        int col = m.getColLength();
        
        //apakah matrix persegi
        if(m.isSquare()){
            if (row==1 || col==1){
                return m.matrix[0][0];
            }
            else{
                for (i=0; i<row; i++){
                //jika elemen diagonal 0, cari baris di bawahnya untuk ditukar
                    if (m.matrix[i][i] == 0){
                        j = i+1;
                        while(j<row && m.matrix[j][i] == 0){
                            j++;
                        }
                        //jika  sudah ketemu bukan 0, lakukan pertukaran baris
                        if (j<row){
                            //tukar baris
                            for (k=0; k<row; k++){
                                double temp = m.matrix[i][k];
                                m.matrix[i][k] = m.matrix[j][k];
                                m.matrix[j][k] = temp;
                            }
                            det *= -1; //dikali -1 karena ditukar
                        }
                        else{
                            det = 0;
                            break;
                        }
                    }

                    //normalisasi baris untuk menjadikan elemen diagonal = 1
                    x = m.matrix[i][i];
                    det *= x;
                    if (x != 0){
                        for (j=0; j<row; j++){
                            m.matrix[i][j] /= x;
                        }
                    }

                    //eliminasi kolom di bawah elemen diagonal
                    for (k=i+1; k<row; k++){
                        y = m.matrix[k][i];
                        for (l=0; l<row; l++){
                            m.matrix[k][l] -= m.matrix[i][l] * y;
                        }
                    return det;
                        
                    }

                }
            }
        
        }
        else{
            return m.MARK;
        }
        return 0;
        
    }
}

