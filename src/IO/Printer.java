package IO;
import java.util.*;

public class Printer {
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

}
