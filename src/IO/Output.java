package IO;
import ADTMatrix.Matrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Output{
    public static Scanner input = new Scanner(System.in);
    BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

    public static void header(){
        System.out.println("----------------------------------------");
        System.out.println("");
        System.out.println("           Kalkulator Matriks           ");
        System.out.println("              by Kopi Jawa              ");
        System.out.println("");
        System.out.println("----------------------------------------");
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

    public static void menu_invers(){
        header();
        header_menu_metode();
        System.out.println("1. Metode Adjoin");
        System.out.println("2. Metode Matriks Identitas");
        System.out.println("0. Kembali");
        System.out.println("");
    }

    public static void menu_bicubic(){
        header();
        header_menu_metode();
        System.out.println("1. Metode Matriks Identitas");
        System.out.println("2. Metode Determinan dan Kofaktor");
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

        if(solusi[0] == null){
            System.out.println("Tidak ada solusi yang memenuhi.");
            System.out.println();
            return;
        }
        
        System.out.println("Dilakukan penyelesaian dan didapatkan:");

        int len, i;
        len = solusi.length;
        
        i = 0;
        while(i<len){

            System.out.println("x_" + (i+1) + " = " + solusi[i]);

            i+=1;
        }

        System.out.println();
    }

    public static void output_spl_file(String[] solusi){
        int i;
        String hasil;
        
        hasil = "Dilakukan penyelesaian dan didapatkan:\n";
        i = 0;
        while(i<solusi.length){

            hasil+=("x_" + (i + 1) + " = " + solusi[i] + "\n");

            i+=1;
        }

        Scanner scanner = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        
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
                    scanner.close();
                    return; //kalau ngga mau jadiin file berarti batal
                }
            }
            
            
        } catch (IOException err) {
            err.printStackTrace();
        }
        try {
            FileWriter file = new FileWriter("test/Output/" + nameFile);
            file.write(hasil);
            file.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        System.out.println("File berhasil dibuat!");
        
    }

    public static String solusi_interpolasi_polinomial(String[] solusi){
        System.out.println("Dilakukan penyelesaian dan diperoleh persamaan dan koordinat sebagai berikut:");
        System.out.println();

        int len, i;
        String jawaban;

        len = solusi.length;

        jawaban = "y = ";
        i = 0;
        while(i<len){

            if(i == 0) jawaban += (solusi[i] + " ");
            else jawaban += ("+ " + solusi[i] + "x^" + i + " ");

            i+=1;
        }

        System.out.println(jawaban);
        System.out.println();
        return jawaban;
    }

    //menampilkan matriks pada layar
    public static void printMatrix(Matrix m){
        //print matrix
        for (int i = 0; i < m.getRowLength(); i++){
            for (int j = 0; j < m.getColLength(); j++){
                System.out.printf("%.4f ", m.getElement(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int opsiOutput(){
        menu_output();

        //input opsi
        System.out.print("> ");
        int opsi = input.nextInt();
        //cek validasi
        while (opsi < 1 || opsi > 2){
            System.out.println("Opsi tidak tersedia! Silahkan masukkan opsi yang sesuai.");
            System.out.print("> ");
            opsi = input.nextInt();
        }

        return opsi;
    }

    public static void fileBicubic(String hasil){
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in)); 
        String nameFile = "";
        System.out.println("Masukkan nama file: ");
        try {
            nameFile = inputFile.readLine();
            String path = "test/Output/" + nameFile;

            // cek apakah sudah ada file
            File file = new File(path);
            if (file.exists()) {
                System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                char choice = input.next().charAt(0);
                if (choice != 'y' && choice != 'Y') {
                    System.out.println("Output dibatalkan.");
                    return; // ngga dibikin file kalau tidak pilih y
            }
        }
        } catch (IOException err) {
            err.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("test/Output/" + nameFile);
            file.write("f(x,y) = ");
            
            file.write(hasil);
            file.close();
        }
        catch (IOException err) {
            err.printStackTrace();
        }

    }

    // // mengubah hasil invers ke file
    // public static void OutputInversFile (Matrix m, int opsi){
    //     Scanner input = new Scanner(System.in);
    //     BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

    //     if (opsi == 1){
    //         String newfileName = "";
    //         System.out.print("Masukkan nama file: ");
    //         try{
    //             newfileName = inputFile.readLine();
    //             String path = "Test/Output" + newfileName;
    //         }
    //         catch(IOException err){
    //             err.printStackTrace();
    //         }
    //         try{
    //             FileWriter file = new FileWriter("Test/Output" + newfileName);
    //             int i, j;
    //             Matrix newMatrix = new Matrix ();
    //             newMatrix.createMatrix(m.getRowLength(), m.getColLength());
    //             for (i = 0; i < m.getRowLength(); i++){
    //                 for (j = 0; j < m.getColLength(); j++){
    //                     newMatrix.setElement(i, j, m.getElement(j, j));
    //                 }
    //             }
    //             // ngga ada invers, maka hanya akan ada tulisan invers tidak ada di dalam file
    //             if (m.getElement(0, 0) == Double.POSITIVE_INFINITY || m.getElement(0, 0) == Double.NEGATIVE_INFINITY){
    //                 file.write("Invers tidak ada.");
    //                 file.close();
    //             }
    //             // di konvert ke file
    //             else{
    //                 for (i = 0; i < m.getRowLength(); i++){
    //                     for (j = 0; j < m.getColLength(); j++){
    //                         String tempString = String.format("%.4f", m.getElement(i, j));
    //                         file.write(tempString + " ");
    //                     }
    //                     file.write("\n");
    //                 }
    //                 file.close();
    //             }
    //         }
    //         catch(IOException err){
    //             err.printStackTrace();
    //         }
    //     }
    
    // }
    

    //mengubah hasil determinan ke bentuk file
    
    public static void OutputDetFile (String det, int opsi){
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
                        scanner.close();
                        return; //kalau ngga mau jadiin file berarti batal
                    }
                }
                
                
            } catch (IOException err) {
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("test/Output/" + nameFile);
                file.write("Determinan: " + String.format(det));
                file.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
            System.out.println("File berhasil dibuat!");
        }
    }
    

    public static int caraOutput(boolean salahInput){
        Scanner input = new Scanner(System.in);
        int hasil;
        while(true){

            menu_output();

            salahInput = pesan_salah_input(salahInput);

            System.out.print("> ");
            hasil = input.nextInt();

            if(hasil >= 1 && hasil <= 2){
                break;
            }else{
                salahInput = true;
            }
        }
        return hasil;
    }

    public static String lanjut(){
        Scanner input = new Scanner(System.in);
        String anu;

        System.out.print("Masukkan apa pun untuk lanjut> ");
        anu = input.nextLine();
        System.out.println();

        return anu;
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
                System.out.println("File berhasil dibuat!");

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
            System.out.println("File berhasil dibuat!");
        }
    
    }

    public static void output_string_file(String s){
        Scanner scanner = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        
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
                    scanner.close();
                    return; //kalau ngga mau jadiin file berarti batal
                }
            }
            
            
        } catch (IOException err) {
            err.printStackTrace();
        }
        try {
            FileWriter file = new FileWriter("test/Output/" + nameFile);
            file.write(s);
            file.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        System.out.println("File berhasil dibuat!");
        
    }
}
