import ADTMatrix.*;
import Function.*;
import IO.*;

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int cmd1, cmd2, cmd3;
        boolean salahInput, adaSolusi, banyakSolusi;
        String pesanTidakAdaSolusi;
        Matrix m = new Matrix();
        String[] untukOutput;

        cmd3 = 0;
        salahInput = false;
        pesanTidakAdaSolusi = "Tidak ada solusi yang memenuhi.";

        while(true){

            Output.header();

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

                    adaSolusi = SPL.is_no_solution(m);
                    banyakSolusi = SPL.is_many_solution(m);
                
                    if(cmd2 == 1){ // GAUSS

                        if(adaSolusi){
                            untukOutput = SPL.metode_gauss(m.copy());
                        
                            if(cmd3 == 2){ // Jalup entar bikin if else untukOutput-nya kosong (ga ada solusi)
                                // write file
                                // pesan berhasil ditulis
                            }
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                    }else if(cmd2 == 2){ // GAUSS-JORDAN

                        if(adaSolusi){
                            untukOutput = SPL.metode_gauss_jordan(m.copy());
                        
                            if(cmd3 == 2){ // Jalup entar bikin if else untukOutput-nya kosong (ga ada solusi)
                                // write file
                                // pesan berhasil ditulis
                            }
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                    }else if(cmd2 == 3){ // INVERS

                        if(adaSolusi){

                            untukOutput = SPL.metode_invers(m.copy());
                            
                            if(cmd3 == 2){
                                // write file
                            }

                        }else if(banyakSolusi){
                            System.out.println("Terdapat banyak solusi untuk SPL tersebut.\nMetode matriks balikan tidak dapat digunakan.\nSilakan gunakan metode lain.");
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

                    }else if(cmd2 == 4){ // KAIDAH CRAMER

                        if(adaSolusi){

                            untukOutput = SPL.metode_cramer(m.copy());
                            
                            if(cmd3 == 2){
                                // write file
                            }

                        }else if(banyakSolusi){
                            System.out.println("Terdapat banyak solusi untuk SPL tersebut.\nKaidah tidak dapat digunakan.\nSilakan gunakan metode lain.");
                        }else{
                            System.out.println(pesanTidakAdaSolusi);
                            // write file
                        }

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
                
                    if(cmd2 == 1){

                        // reduksi baris

                    }else if(cmd2 == 2){

                        // ekspansi kofaktor

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