package IO;

import ADTMatrix.Matrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.*;

public class OutputFile {
    //menampilkan matriks pada layar
    public static void printMatrix(Matrix m){
        //print matrix
        for (int i = 0; i < m.getRowLength(); i++){
            for (int j = 0; j < m.getColLength(); j++){
                System.out.printf("%.2f", m.getElement(i, j));
                System.out.printf(" ");
            }
            System.out.printf("\n");
        }
        System.out.println();
    }

    // mengubah matriks ke file
    public static void OutputFile (Matrix m, int opsi){
        Scanner scanner = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        if (opsi == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "test/Output/" + newfileName;

                // cek apakah sudah ada file
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        return; // ngga dibikin file kalau tidak pilih y
                    }
                }
            }
            
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("test/Output/" + newfileName);
                int i, j;
                for (i = 0; i < m.getRowLength(); i++){
                    for (j = 0; j < m.getColLength(); j++){
                        double value = m.getElement(i, j);
                        file.write(String.format("%.2f", value));
                        file.write(" ");
                    }
                    file.write("\n");
                }
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    
    }
    

    //mengubah hasil determinan ke bentuk file
    public static void OutputDetFile (double det, int opsi){
        Scanner scanner = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        if (opsi == 1){
            // mencetak output ke dalam bentuk file
            String nameFile = "";
            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "test/Output/" + nameFile;

                // cek filenya udah ada belum
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        return; //kalau ngga mau jadiin file berarti batal
                    }
                }
            } catch (IOException err) {
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("test/Output/" + nameFile);
                file.write("Determinan: " + String.format("%.2f", det));
                file.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
    
}
