import ADTMatrix.*;
import Function.*;
import IO.*;

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int cmd1, cmd2, cmd3;
        double[][] daftarTitik = new double[0][0]; // buat interpolasi
        boolean salahInput, adaSolusi, banyakSolusi;
        String pesanTidakAdaSolusi, ok;
        Matrix m = new Matrix();
        String[] untukOutput;

        cmd3 = 0;
        salahInput = false;
        pesanTidakAdaSolusi = "Tidak ada solusi yang memenuhi.";

        while(true){

            Output.menu_utama();
            
            salahInput = Output.pesan_salah_input(salahInput);

            System.out.print("> ");
            cmd1 = input.nextInt();

            if(cmd1 == 1){
                // SPL

                while(true){

                    Output.menu_spl();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd2 = input.nextInt();

                    // Input dan Cara Output
                    if(cmd2 >= 1 && cmd2 <= 4){
                        // Cara Input
                        cmd3 = Input.caraInput(salahInput);
                        salahInput = false;

                        if(cmd3 == 0) continue;

                        if(cmd3 == 1){
                            m = Input.readMatrix();
                        }else if(cmd3 == 2){
                            m = Input.readMatrixFile();
                        }

                        // Cara Output
                        cmd3 = Output.caraOutput(salahInput);
                        salahInput = false;
                    }

                    adaSolusi = !SPL.is_no_solution(m.copy());
                    banyakSolusi = SPL.is_many_solution(m.copy());
                
                    if(cmd2 == 1){ // GAUSS

                        if(adaSolusi){
                            untukOutput = SPL.metode_gauss(m.copy());
                        
                            if(cmd3 == 1){ // Jalup entar bikin if else untukOutput-nya kosong (ga ada solusi)
                                // write file
                                // pesan berhasil ditulis
                            }
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 2){ // GAUSS-JORDAN

                        if(adaSolusi){
                            untukOutput = SPL.metode_gauss_jordan(m.copy());
                        
                            if(cmd3 == 1){ // Jalup entar bikin if else untukOutput-nya kosong (ga ada solusi)
                                // write file
                                // pesan berhasil ditulis
                            }
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 3){ // INVERS

                        if(adaSolusi){

                            if(Invers.isInversible(m)){

                                untukOutput = SPL.metode_invers(m.copy());
                                
                                if(cmd3 == 1){
                                    // write file
                                }

                            }else{
                                
                                System.out.println("Matriks tidak memiliki balikan.");
                                System.out.println() ;

                            }

                        }else if(banyakSolusi){
                            System.out.println("Terdapat banyak solusi untuk SPL tersebut.\nMetode matriks balikan tidak dapat digunakan.\nSilakan gunakan metode lain.");
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 4){ // KAIDAH CRAMER

                        if(adaSolusi){

                            untukOutput = SPL.metode_cramer(m.copy());
                            
                            if(cmd3 == 1){
                                // write file
                            }

                        }else if(banyakSolusi){
                            System.out.println("Terdapat banyak solusi untuk SPL tersebut.\nKaidah tidak dapat digunakan.\nSilakan gunakan metode lain.");
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 0){ // KEMBALI

                        break;

                    }else{ // SALAH INPUT

                        salahInput = true;

                    }

                }

            }else if(cmd1 == 2){
                // Determinan
                while(true){

                    Output.menu_determinan();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd2 = input.nextInt();

                    // Input dan Cara Output
                    if(cmd2 >= 1 && cmd2 <= 2){
                        // Cara Input
                        cmd3 = Input.caraInput(salahInput);
                        salahInput = false;

                        if(cmd3 == 0) continue;

                        if(cmd3 == 1){
                            m = Input.readMatrix();
                        }else if(cmd3 == 2){
                            m = Input.readMatrixFile();
                        }

                        // Cara Output
                        cmd3 = Output.caraOutput(salahInput);
                        salahInput = false;
                    }
                
                    if(cmd2 == 1){

                        // reduksi baris
                        untukOutput = Determinan.metode_reduksi_baris(m.copy());

                        if(cmd3 == 1){
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 2){

                        // ekspansi kofaktor
                        untukOutput = Determinan.metode_ekspansi_kofaktor(m.copy());

                        if(cmd3 == 1){
                            // write file
                        }

                        Output.lanjut();

                    }else if(cmd2 == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if (cmd1 == 3) {
                // Invers
                while(true){

                    Output.menu_invers();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd2 = input.nextInt();

                    // Input dan Cara Output
                    if(cmd2 >= 1 && cmd2 <= 2){
                        // Cara Input
                        cmd3 = Input.caraInput(salahInput);
                        salahInput = false;

                        if(cmd3 == 0) continue;

                        if(cmd3 == 1){
                            m = Input.readMatrix();
                        }else if(cmd3 == 2){
                            m = Input.readMatrixFile();
                        }

                        // Cara Output
                        cmd3 = Output.caraOutput(salahInput);
                        salahInput = false;
                    }
                
                    if(cmd2 == 1){

                        // adjoin

                    }else if(cmd2 == 2){

                        // matriks identitas

                    }else if(cmd2 == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if(cmd1 == 4){
                // Interpolasi Polinomial

                // Input dan Cara Output
                // Cara Input
                cmd3 = Input.caraInput(salahInput);
                salahInput = false;

                if(cmd3 == 0) continue;

                if(cmd3 == 1){
                    daftarTitik = Input.readInterpolasi();
                }else if(cmd3 == 2){
                    //daftarTitik = Input.readInterpolasiFile();
                }

                // Cara Output
                cmd3 = Output.caraOutput(salahInput);
                salahInput = false;

                untukOutput = Interpolasi.interpolasi_polinomial(daftarTitik);

                if(cmd3 == 1){
                    // write file
                }

            }else if(cmd1 == 5){
                // Bikubik


            }else if(cmd1 == 6){
                // Regresi
                while(true){

                    Output.menu_regresi();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd2 = input.nextInt();
                
                    if(cmd2 == 1){

                        // regresi linear berganda

                    }else if(cmd2 == 2){

                        // regresi kuadratik berganda

                    }else if(cmd2 == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if(cmd1 == 0){
                // Keluar
                Output.pesan_keluar();
                break;

            }else{
                salahInput = true;
            }

        }

        input.close();
    }    

}