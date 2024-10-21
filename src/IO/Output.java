package IO;
import ADTMatrix.Matrix;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Output{

    public static void header(){
        System.out.println("----------------------------------------");
        System.out.println("");
        System.out.println("           Kalkulator Matriks           ");
        System.out.println("              by Kopi Jawa              ");
        System.out.println("");
        System.out.println("----------------------------------------");
        System.out.println("");
    }

    private static void header_menu_metode() {
        System.out.println("               Menu Metode              ");
        System.out.println("----------------------------------------");
    }

    private static void header_menu_input() {
        System.out.println("               Menu Input               ");
        System.out.println("----------------------------------------");
    }

    private static void header_menu_output() {
        System.out.println("              Menu Output               ");
        System.out.println("----------------------------------------");
    }

    private static void header_menu_regresi() {
        System.out.println("          Menu Regresi Berganda         ");
        System.out.println("----------------------------------------");
    }

    public static void menu_utama() {
        header();
        System.out.println("               Menu Utama               ");
        System.out.println("----------------------------------------");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinomial");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Berganda");
        System.out.println("0. Keluar");
        System.out.println("");
    }

    public static void menu_spl(){
        header();
        header_menu_metode();
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_determinan(){
        header();
        header_menu_metode();
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_matriks_balikan(){
        header();
        header_menu_metode();
        System.out.println("1. Metode Adjoin");
        System.out.println("2. Metode Matriks Identitas");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_regresi(){
        header();
        header_menu_regresi();
        System.out.println("1. Regresi Linear Berganda");
        System.out.println("2. Regresi Kuadratik Berganda");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_input(){
        header();
        header_menu_input();
        System.out.println("1. Masukan dari Keyboard");
        System.out.println("2. Masukan dari File");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_output(){
        header();
        header_menu_output();
        System.out.println("1. Buat Output File");
        System.out.println("2. Tidak Menghasilkan Output File");
        System.out.println();
    }

    public static void pesan_keluar(){
        header();
        System.out.println("Terima kasih telah menggunakan layanan kami. Sampai jumpa!");
        System.out.println("");
    }

    public static boolean pesan_salah_input(boolean salahInput){
        if(salahInput){
            System.out.println("Masukan salah! Silakan sesuaikan dengan menu yang ada.");
            System.out.println("");
        }

        return false;
    }

    public static void solusi_spl(String[] solusi){
        
        System.out.println("Dilakukan penyelesaian dan didapatkan:");

        int len, i;
        len = solusi.length;
        
        i = 0;
        while(i<len){

            System.out.println("x_" + (i+1) + "= " + solusi[i]);

            i+=1;
        }
    }

    public static void solusi_interpolasi_polinomial(String[] solusi){
        System.out.println("Dilakukan penyelesaian dan diperoleh persamaan sebagai berikut:");
        System.out.println();

        int len, i;
        len = solusi.length;

        System.out.print("y = ");
        i = 0;
        while(i<len){

            if(i == 0) System.out.println(solusi[i] + " ");
            else System.out.println("+ " + solusi[i] + "x^" + i + " ");

            i+=1;
        }
    }

    //menampilkan matriks pada layar
    public static void printMatrix(Matrix m){
        //print matrix
        for (int i = 0; i < m.getRowLength(); i++){
            for (int j = 0; j < m.getColLength(); j++){
                System.out.printf("%.4f", m.getElement(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    // mengubah hasil invers ke file
    public static void OutputInversFile (Matrix m, int opsi){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

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
    public static void OutputDetFile (double det, int opsi){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
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
