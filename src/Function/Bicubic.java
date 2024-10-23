package Function;

import ADTMatrix.Matrix;
public class Bicubic{

    public static Matrix f (Matrix m){
        int row, col;

        for (row = 0; row < 4; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (col = 0; col < 16; col++) {
                m.setElement(row, col, Math.pow(x, i) * Math.pow(y, j));
                
                j++;
                if (j == 4) {
                    j = 0;
                    i++;
                }
            }
        }
        
        return m;
    }

    public static Matrix fx (Matrix m){
        int row, col;
                
        for (row = 4; row < 8; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (col = 0; col < 16; col++){
                if (i == 0){
                    m.setElement(row, col, 0.0000);
                }
                else{
                    m.setElement(row, col, Math.pow(x, i-1) * Math.pow(y, j) * i);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        
        return m;
    }

    public static Matrix fy (Matrix m){
        int row, col;

        for (row = 8; row < 12; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (col = 0; col < 16; col++){
                if (i == 0){
                    m.setElement(row, col, 0.0000);
                }
                else{
                    m.setElement(row, col, Math.pow(x, i) * Math.pow(y, j-1) * j);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        
        return m;
    }

    public static Matrix fxy (Matrix m){
        int row, col;
        
        for (row = 12; row < 16; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (col = 0; col < 16; col++){
                if (i == 0){
                    m.setElement(row, col, 0.0000);
                }
                else{
                    m.setElement(row, col, Math.pow(x, i-1) * Math.pow(y, j-1) * i * j);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        
        return m;
    }

    public static double BicubicSplineInterpolation(double[][] f, double x1, double y1) {
        Matrix m = new Matrix();
        m.createMatrix(16,16);

        Matrix mInvers = new Matrix();
        mInvers.createMatrix(16,16);

        Matrix mHasilKali = new Matrix();
        mHasilKali.createMatrix(16,1);

        Matrix nilaiFungsi = new Matrix();
        nilaiFungsi.createMatrix(16, 1);
        
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nilaiFungsi.matrix[idx][0] = f[i][j];
                idx++;
            }
        }

        double x = 0.0;
        double y = 0.0;
        double hasil = 0.0;
        
        int i,j;

        System.out.println(m);

        mInvers = Invers.invers(m);

        mHasilKali = Matrix.multiplyMatrix(mInvers, nilaiFungsi);

        x = x1;
        y = y1;
/*
        for (i=0; i<4; i++){
            for (j=0; j<4; j++){
                hasil += mHasilKali.getElement(i, j);
            }
        }
*/
        return hasil;
        
    }
}
