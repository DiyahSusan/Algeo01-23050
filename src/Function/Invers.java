package Function;
import ADTMatrix.Matrix;
import IO.Output;

public class Invers{

    // belum tau kalau matriksnya gak invertible, ngembaliin apa
    public static Matrix invers(Matrix m){
        if (m.isSquare()){
            Matrix matriks = new Matrix();
            matriks.createMatrix(m.row, m.col);
            Matrix identitas = new Matrix();
            identitas.createMatrix(m.row, m.col);

            matriks.matrix = m.matrix;
            
            // mengisi matriks identitas

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
            while(i<matriks.row && j < matriks.col){

                k = i+1;
                while(matriks.matrix[i][j] == 0 && k < matriks.row){
                    matriks.rowSwap(i, k);
                    identitas.rowSwap(i,k);
                    k+=1;
                }

                if(matriks.matrix[i][j] == 0){
                    j+=1;
                    continue;
                }

                double pembagi = 1/matriks.matrix[i][j];
                matriks.multiplyRow(i, pembagi);
                identitas.multiplyRow(i, pembagi);

                k = i+1;
                while(k<matriks.row){
                    
                    identitas.sumMultiplyRow(k, i, (-1) * matriks.matrix[k][j]);
                    matriks.sumMultiplyRow(k, i, (-1) * matriks.matrix[k][j]);

                    k+=1;
                }

                k = i-1;
                while (k >= 0) {

                    identitas.sumMultiplyRow(k, i, (-1) * matriks.matrix[k][j]);
                    matriks.sumMultiplyRow(k, i, (-1) * matriks.matrix[k][j]);

                    k -= 1;
                }

                i+=1;
                j+=1;
            }

            identitas.cekMinNol();

            return identitas;
        }
        return m; //kalau ngga di return error

    }

    public static boolean isInversible(Matrix m){
        double determinan = Determinan.detOBE(m.copy());
        return determinan <= 0.0000000001 && determinan >= -0.0000000001;
    }
    
}