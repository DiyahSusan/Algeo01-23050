package Function;
import ADTMatrix.Matrix;

public class Invers{

    // belum tau kalau matriksnya gak invertible, ngembaliin apa
    public static Matrix Invers(Matrix m){
        if (m.isSquare()){
            Matrix hasil = this;
            Matrix identitas;
            
            // membuat matriks identitas
            identitas.createMatrix(m.row, m.col);

            int i = 0, j = 0, k;
            for (i = 0; i < m.row; i++){
                for (j = 0; j < m.col; j++){
                    if (i == j){
                        identitas.matrix[i][j] = 1;
                    } else {
                        identitas.matrix[i][j] = 0;
                    }
                }
            }
            
            i = 0; 
            j = 0;
            //melakukan eliminasi gauss jordan pada matrix awal dan melakukan langkah yang sama pada matrix identitas
            while(i<this.row && j < this.col){

                k = i+1;
                while(hasil.matrix[i][j] == 0 && k < this.row){
                    hasil.rowSwap(i, k);
                    identitas.rowSwap(i,k);
                    k+=1;
                }

                if(hasil.matrix[i][j] == 0){
                    j+=1;
                    continue;
                }

                double pembagi = 1/hasil.matrix[i][j];
                hasil.multiplyRow(i, pembagi);
                identitas.multiplyRow(i, pembagi);

                k = i+1;
                while(k<hasil.row){

                    hasil.sumMultiplyRow(k, i, (-1) * hasil.matrix[k][j]);
                    identitas.sumMultiplyRow(k, i, (-1) * identitas.matrix[k][j]);

                    k+=1;
                }

                k = i-1;
                while (k >= 0) {
                    hasil.sumMultiplyRow(k, i, -hasil.matrix[k][j]);
                    identitas.sumMultiplyRow(k, i, -identitas.matrix[k][j]);

                    k -= 1;
                }

                i+=1;
                j+=1;
            }

            identitas.cekMinNol();

            return identitas;
        }

    }
    
}