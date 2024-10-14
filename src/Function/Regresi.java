package Function;

import java.util.Scanner;
import ADTMatrix.*;

public class Regresi {
    public static void regresiLinearBergandaKeyboard(){
        Scanner scanner = new Scanner(System.in);

        //input jumlah observasi dan variabel independen
        System.out.println("Masukkan jumlah observasi: ");
        int n = scanner.nextInt();

        System.out.println("Masukkan jumlah variabel independen: ");
        int m = scanner.nextInt();

        
        //membuat matrix X dengan ukuran n x (m+1), kolom pertama untuk intersep
        double [][] X = new double[n][m+1];
        double[] Y = new double[n];

        //input data variable independen
        System.out.println("Masukkan data untuk variabel independen: ");
        for (int i=0; i<n; i++){
            X[i][0] = 1; // inisialisasi untuk intersep
            for (int j=1; j<=m; j++){
                X[i][j] = scanner.nextDouble();
            }
        }

        //input variable dependen (Y)
        System.out.println("Masukkan data untuk variabel dependen: ");
        for (int i=0; i<n; i++){
            Y[i] = scanner.nextDouble();
        }

        //ubah ke bentuk matrix
        Matrix matrixX;
        matrixX = new Matrix();
        matrixX.toMatrix(X, n, m+1);


        //hitung matrix transpose
        Matrix XT;
        XT = new Matrix();
        XT.toMatrix(X, n, m+1);
        XT = XT.transpose(matrixX);

        //perkalian X tarnspose dengan X
        Matrix XT_X;
        XT_X = new Matrix();
        XT_X.toMatrix(X, n, m+1);
        XT_X = Matrix.multiplyMatrix(matrixX, XT);

        //perkalian X transpose dengan Y
        double [] XT_Y = Matrix.multiplyVektor(XT, Y); 

        //hitung matrix invers dari XT_X
        Matrix XTX_invers;
        XTX_invers = new Matrix();
        XTX_invers.toMatrix(X, n, m+1);
        XTX_invers = Invers.invers(XT_X); 

        double[] result = new double[m+1];
        result = Matrix.multiplyVektor(XTX_invers, XT_Y);

        // menampilakn hasil koefisien regresi
        System.out.println("Koefisien regresi: ");
        System.out.println("Intersep (β0): " + result[0]);
        for (int i=1; i< result.length; i++){
            System.out.println("Koefisien β" + i + ": " + result[i]);
        }
        scanner.close();
    }
}

