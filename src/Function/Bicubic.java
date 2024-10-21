package Function;

import ADTMatrix.Matrix;
public class Bicubic{
    public static double BicubicSplineInterpolation(double[][] f, double x1, double y1) {
        Matrix m = new Matrix();
        m.createMatrix(16,16);

        Matrix mInvers = new Matrix();
        mInvers.createMatrix(16,16);

        Matrix mHasilKali = new Matrix();
        mHasilKali.createMatrix(16,16);

        Matrix nilaiFungsi = new Matrix();
        nilaiFungsi.createMatrix(16, 1);
        nilaiFungsi.matrix = f;

        double x = 0;
        double y = 0;
        double hasil = 0;

        int i,j,k = 0,l;

        for (i=0; i<2; i++){
            for (j=0; j<2; j++){
                x = j;
                y = i;
                for (l=0; l<16; l++){
                    if (l==0){
                        m.matrix[k][l] = 1;
                    }
                    else if (l==1){
                        m.matrix[k][l] = x;
                    } 
                    else if (l==2){
                        m.matrix[k][l] = x*x;
                    }
                    else if (l==3){
                        m.matrix[k][l] = x*x*x;
                    }
                    else if (l==4){
                        m.matrix[k][l] = y;
                    }
                    else if (l==5){
                        m.matrix[k][l] = x*y;
                    }
                    else if (l==6){
                        m.matrix[k][l] = x*x*y;
                    }
                    else if (l==7){
                        m.matrix[k][l] = x*x*x*y;
                    }
                    else if (l==8){
                        m.matrix[k][l] = y*y;
                    }
                    else if (l==9){
                        m.matrix[k][l] = x*y*y;
                    }
                    else if (l==10){
                        m.matrix[k][l] = x*x*y*y;
                    }
                    else if (l==11){
                        m.matrix[k][l] = x*x*x*y*y;
                    }
                    else if (l==12){
                        m.matrix[k][l] = y*y*y;
                    }
                    else if (l==13){
                        m.matrix[k][l] = x*y*y*y;
                    }
                    else if (l==14){
                        m.matrix[k][l] = x*x*y*y*y;
                    }
                    else if (l==15){
                        m.matrix[k][l] = x*x*x*y*y*y;
                    }
                }
                k ++;
            }
        }

        for (i=0; i<2; i++){
            for (j=0; j<2; j++){
                x = j;
                y = i;
                for (l=0; l<16; l++){
                    if (l==0 || l == 4 || l == 8 || l == 12){
                        m.matrix[k][l] = 0;
                    }
                    else if (l==1){
                        m.matrix[k][l] = 1;
                    } 
                    else if (l==2){
                        m.matrix[k][l] = 2*x;
                    }
                    else if (l==3){
                        m.matrix[k][l] = 3*x*x;
                    }
                    else if (l==5){
                        m.matrix[k][l] = y;
                    }
                    else if (l==6){
                        m.matrix[k][l] = 2*x*y;
                    }
                    else if (l==7){
                        m.matrix[k][l] = 3*x*x*y;
                    }
                    else if (l==9){
                        m.matrix[k][l] = y*y;
                    }
                    else if (l==10){
                        m.matrix[k][l] = 2*x*y*y;
                    }
                    else if (l==11){
                        m.matrix[k][l] = 3*x*x*y*y;
                    }
                    else if (l==13){
                        m.matrix[k][l] = y*y*y;
                    }
                    else if (l==14){
                        m.matrix[k][l] = 2*x*y*y*y;
                    }
                    else if (l==15){
                        m.matrix[k][l] = 3*x*x*y*y*y;
                    }
                }
                k ++;
            }
        }

        for (i=0; i<2; i++){
            for (j=0; j<2; j++){
                x = j;
                y = i;
                for (l=0; l<16; l++){
                    if (l==0 || l == 1 || l == 2 || l == 3){
                        m.matrix[k][l] = 0;
                    }
                    else if (l==4){
                        m.matrix[k][l] = 1;
                    }
                    else if (l==5){
                        m.matrix[k][l] = x;
                    }
                    else if (l==6){
                        m.matrix[k][l] = x*x;
                    }
                    else if (l==7){
                        m.matrix[k][l] = x*x*x;
                    }
                    else if (l==8){
                        m.matrix[k][l] = 2*y;
                    }
                    else if (l==9){
                        m.matrix[k][l] = 2*x*y;
                    }
                    else if (l==10){
                        m.matrix[k][l] = 2*x*x*y;
                    }
                    else if (l==11){
                        m.matrix[k][l] = 2*x*x*x*y;
                    }
                    else if (l==12){
                        m.matrix[k][l] = 3*y*y;
                    }
                    else if (l==13){
                        m.matrix[k][l] = 3*x*y*y;
                    }
                    else if (l==14){
                        m.matrix[k][l] = 3*x*x*y*y;
                    }
                    else if (l==15){
                        m.matrix[k][l] = 3*x*x*x*y*y;
                    }
                }
                k ++;
            }
        }

        for (i=0; i<2; i++){
            for (j=0; j<2; j++){
                x = j;
                y = i;
                for (l=0; l<16; l++){
                    if (l==0 || l == 1 || l == 2 || l ==3 || l == 4 || l == 8 || l == 12){
                        m.matrix[k][l] = 0;
                    }
                    else if (l==5){
                        m.matrix[k][l] = 1;
                    }
                    else if (l==6){
                        m.matrix[k][l] = 2*x;
                    }
                    else if (l==7){
                        m.matrix[k][l] = 3*x*x;
                    }
                    else if (l==9){
                        m.matrix[k][l] = 2*y;
                    }
                    else if (l==10){
                        m.matrix[k][l] = 4*x*y;
                    }
                    else if (l==11){
                        m.matrix[k][l] = 6*x*x*y;
                    }
                    else if (l==13){
                        m.matrix[k][l] = 3*y*y;
                    }
                    else if (l==14){
                        m.matrix[k][l] = 6*x*y*y;
                    }
                    else if (l==15){
                        m.matrix[k][l] = 9*x*x*y*y;
                    }
                }
                k ++;
            }
        }

        mInvers = Invers.invers(mInvers);

        mHasilKali = Matrix.multiplyMatrix(mInvers, nilaiFungsi);

        x = x1;
        y = y1;

        for (l=0; l<16; l++){
            if (l==0){
                hasil += mHasilKali.matrix[0][l] * 1;
            }
            else if (l==1){
                hasil += mHasilKali.matrix[0][l] * x;
            } 
            else if (l==2){
                hasil += mHasilKali.matrix[0][l] * x*x;
            }
            else if (l==3){
                hasil += mHasilKali.matrix[0][l] * x*x*x;
            }
            else if (l==4){
                hasil += mHasilKali.matrix[0][l] * y;
            }
            else if (l==5){
                hasil += mHasilKali.matrix[0][l] * x*y;
            }
            else if (l==6){
                hasil += mHasilKali.matrix[0][l] * x*x*y;
            }
            else if (l==7){
                hasil += mHasilKali.matrix[0][l] * x*x*x*y;
            }
            else if (l==8){
                hasil += mHasilKali.matrix[0][l] * y*y;
            }
            else if (l==9){
                hasil += mHasilKali.matrix[0][l] * x*y*y;
            }
            else if (l==10){
                hasil += mHasilKali.matrix[0][l] * x*x*y*y;
            }
            else if (l==11){
                hasil += mHasilKali.matrix[0][l] * x*x*x*y*y;
            }
            else if (l==12){
                hasil += mHasilKali.matrix[0][l] * y*y*y;
            }
            else if (l==13){
                hasil += mHasilKali.matrix[0][l] * x*y*y*y;
            }
            else if (l==14){
                hasil += mHasilKali.matrix[0][l] * x*x*y*y*y;
            }
            else if (l==15){
                hasil += mHasilKali.matrix[0][l] * x*x*x*y*y*y;
            }
        }

        return hasil;
        
    }
}
