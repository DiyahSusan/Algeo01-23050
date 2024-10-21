package Function;

import ADTMatrix.Matrix;
public class Bicubic{

    public static Matrix f (){
        int row;
                
        Matrix m = new Matrix();
        m.createMatrix(4,16);

        for (row = 0; row < 4; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (int col = 0; col < 16; col++) {
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

    public static Matrix fx (){
        int row;
        boolean a = false;
                
        Matrix m = new Matrix();
        m.createMatrix(4,16);

        for (row = 0; row < 4; row++) {
            int x = row / 2;
            int y = row % 2;
            int i = 0, j = 0;
    
            for (int col = 0; col < 16; col++) {
                m.setElement(row, col, Math.pow(x, i) * Math.pow(y, j));
                
                if(!a && i == 1){
                    i = 0;
                    a = true;
                }

                j++;

                if (j == 4) {
                    j = 0;
                    i++;
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

        int i,j,k = 0, l;
        

        System.out.println(m);

        mInvers = Invers.invers(mInvers);

        mHasilKali = Matrix.multiplyMatrix(mInvers, nilaiFungsi);

        x = x1;
        y = y1;

        for (l=0; l<16; l++){
            if (l==0){
                hasil += mHasilKali.matrix[l][0] * 1;
            }
            else if (l==1){
                hasil += mHasilKali.matrix[l][0] * x;
            } 
            else if (l==2){
                hasil += mHasilKali.matrix[l][0] * x*x;
            }
            else if (l==3){
                hasil += mHasilKali.matrix[l][0] * x*x*x;
            }
            else if (l==4){
                hasil += mHasilKali.matrix[l][0] * y;
            }
            else if (l==5){
                hasil += mHasilKali.matrix[l][0] * x*y;
            }
            else if (l==6){
                hasil += mHasilKali.matrix[l][0] * x*x*y;
            }
            else if (l==7){
                hasil += mHasilKali.matrix[l][0] * x*x*x*y;
            }
            else if (l==8){
                hasil += mHasilKali.matrix[l][0] * y*y;
            }
            else if (l==9){
                hasil += mHasilKali.matrix[l][0] * x*y*y;
            }
            else if (l==10){
                hasil += mHasilKali.matrix[l][0] * x*x*y*y;
            }
            else if (l==11){
                hasil += mHasilKali.matrix[l][0] * x*x*x*y*y;
            }
            else if (l==12){
                hasil += mHasilKali.matrix[l][0] * y*y*y;
            }
            else if (l==13){
                hasil += mHasilKali.matrix[l][0] * x*y*y*y;
            }
            else if (l==14){
                hasil += mHasilKali.matrix[l][0] * x*x*y*y*y;
            }
            else if (l==15){
                hasil += mHasilKali.matrix[l][0] * x*x*x*y*y*y;
            }
        }

        return hasil;
        
    }
}
