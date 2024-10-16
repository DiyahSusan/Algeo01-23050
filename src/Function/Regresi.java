package Function;

import ADTMatrix.*;
import java.util.Scanner;

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

        double[] result = Matrix.multiplyVektor(XTX_invers, XT_Y);

        // menampilakn hasil koefisien regresi
        System.out.println("Koefisien regresi: ");
        System.out.println("Intersep (β0): " + result[0]);
        for (int i=1; i< result.length; i++){
            System.out.println("Koefisien β" + i + ": " + result[i]);
        }
        scanner.close();
    }

    public static void regresiKuadratikBergandaKeyboard(){
        Scanner scanner = new Scanner(System.in);

        //input jumlah observasi dan variabel independen
        System.out.println("Masukkan jumlah observasi: ");
        int n = scanner.nextInt();

        System.out.println("Masukkan jumlah variabel independen: ");
        int m = scanner.nextInt();

        //hitung jumlah fitur (intersep + linear + kuadratik + interaksi)
        int totalFitur = 1 + m + m + (m*(m-1))/2;

        
        //membuat matrix X dengan ukuran n x total fitur
        double [][] X = new double[n][totalFitur];
        double[] Y = new double[n];

        //input data variable independen
        System.out.println("Masukkan data untuk variabel independen: ");
        for (int i=0; i<n; i++){
            X[i][0] = 1; // inisialisasi untuk intersep

            //input variabel linear
            for (int j=1; j<=m; j++){
                X[i][j] = scanner.nextDouble();
            }

            //input variabel kuadratik
            for (int j=1; j <= m; j++){
                X[i][m+j] = Math.pow(X[i][j], 2);
            }

            //input variabel interaksi
            int idx = 2*m+1;
            for (int j=1; j<=m; j++){
                for (int k=j+1; k<=m; k++){
                    X[i][idx++] = X[i][j] * X[i][k];
                }
            }
        }

        //input variable dependen (Y)
        System.out.println("Masukkan data untuk variabel dependen: ");
        for (int i=0; i<n; i++){
            Y[i] = scanner.nextDouble();
        }

        //ubah ke bentuk matrix
        Matrix matrixX = new Matrix();
        matrixX.toMatrix(X, n, m+1);


        //hitung matrix transpose
        Matrix XT = new Matrix();
        XT.toMatrix(X, n, m+1);
        XT = XT.transpose(matrixX);

        //perkalian X tarnspose dengan X
        Matrix XT_X = new Matrix();
        XT_X.toMatrix(X, n, m+1);
        XT_X = Matrix.multiplyMatrix(matrixX, XT);

        //perkalian X transpose dengan Y
        double [] XT_Y = Matrix.multiplyVektor(XT, Y); 

        //hitung matrix invers dari XT_X
        Matrix XTX_invers = new Matrix();
        XTX_invers.toMatrix(X, n, m+1);
        XTX_invers = Invers.invers(XT_X); 

        double[] result = Matrix.multiplyVektor(XTX_invers, XT_Y);

        // Menampilkan hasil koefisien regresi
        System.out.println("Koefisien regresi kuadratik berganda: ");
        System.out.println("Intersep (β0): " + result[0]);

        int linearIndex = 1;
        int quadraticIndex = linearIndex + m;
        int interactionIndex = quadraticIndex + m;

        // Menampilkan koefisien untuk variabel linear
        for (int i = 1; i <= m; i++) {
            System.out.println("Koefisien linear β" + i + " (X" + i + "): " + result[linearIndex]);
            linearIndex += 1;
        }

        // Menampilkan koefisien untuk variabel kuadratik
        for (int i = 1; i <= m; i++) {
            System.out.println("Koefisien kuadratik β" + (m + i) + " (X" + i + "^2): " + result[quadraticIndex]);
            quadraticIndex += 1;
        }

        // Menampilkan koefisien untuk interaksi antar variabel
        int idx = 2 * m + 1;
        for (int j = 1; j <= m; j++) {
            for (int k = j + 1; k <= m; k++) {
                System.out.println("Koefisien interaksi β" + idx + " (X" + j + " * X" + k + "): " + result[interactionIndex]);
                interactionIndex += 1;
                idx += 1;
            }
        }
        scanner.close();

    }
}

