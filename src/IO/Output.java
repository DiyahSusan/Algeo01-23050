package IO;
import ADTMatrix.Matrix;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Output{

    //menampilkan matriks pada layar
    public static void printMatrix(Matrix m){
        //print matrix
        for (int i = 0; i < m.getRowLength(); i++){
            for (int j = 0; j < m.getColLength(); j++){
                System.out.printf("%.4f", m.getElement(i, j));
            }
            System.out.println();
        }
    }

    //opsi output
    public static int opsiOutput(){
        Scanner input = new Scanner (System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader((System.in)));
        System.out.println("------------------------------------------------------------");
        System.out.println("                       OPSI OUTPUT                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Buat Output File");
        System.out.println("2. Tidak Menghasilkan Output File");
        System.out.println("------------------------------------------------------------");

        //input opsi
        int opsi = input.nextInt();
        //cek validasi
        while (opsi < 1 || opsi > 2){
            System.out.println("Opsi tidak tersedia! Silahkan masukkan opsi yang sesuai.");
            opsi = input.nextInt();
        }
        return opsi;
    }

    // mengubah hasil invers ke file
    public static void OutputInversFile (Matrix m){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int opsi = opsiOutput();

        //validasi opsi
        while (opsi < 1 || opsi > 2){
            opsi = input.nextInt();
        }
        if (opsi == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "Test/Output" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("Test/Output" + newfileName);
                int i, j;
                Matrix newMatrix = new Matrix ();
                newMatrix.createMatrix(m.getRowLength(), m.getColLength());
                for (i = 0; i < m.getRowLength(); i++){
                    for (j = 0; j < m.getColLength(); j++){
                        newMatrix.setElement(i, j, m.getElement(j, j));
                    }
                }
                // ngga ada invers, maka hanya akan ada tulisan invers tidak ada di dalam file
                if (m.getElement(0, 0) == Double.POSITIVE_INFINITY || m.getElement(0, 0) == Double.NEGATIVE_INFINITY){
                    file.write("Invers tidak ada.");
                    file.close();
                }
                // di konvert ke file
                else{
                    for (i = 0; i < m.getRowLength(); i++){
                        for (j = 0; j < m.getColLength(); j++){
                            String tempString = String.format("%.4f", m.getElement(i, j));
                            file.write(tempString + " ");
                        }
                        file.write("\n");
                    }
                    file.close();
                }
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    
    }
    

    //mengubah hasil determinan ke bentuk file
    public static void OutputDetFile (double det){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int opsi = opsiOutput();
        if (opsi == 1){
            String newFileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newFileName = inputFile.readLine();
                String path = "Test/Output" + newFileName;
            }
            catch (IOException err){
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("Test/Output" + newFileName);
                file.write("Determinan: " + String.format("%.4f", det));
                file.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    
}
