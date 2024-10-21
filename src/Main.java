import ADTMatrix.*;
import Function.*;
import IO.*;

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int cmd;
        boolean salahInput = false;
        Matrix m = new Matrix();
        String[] untukOutput;

        while(true){

            Output.header();

            salahInput = Output.pesan_salah_input(salahInput);

            System.out.print("> ");
            cmd = input.nextInt();

            if(cmd == 1){
                // SPL

                while(true){

                    Output.menu_spl();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd = input.nextInt();
                
                    if(cmd == 1){ // GAUSS
                        
                        // Cara Input
                        while(true){

                            Output.menu_input();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd = input.nextInt();

                            if(cmd >= 0 && cmd <= 2){
                                break;
                            }
                        }

                        if(cmd == 1){
                            m = Input.readMatrix();
                        }else if(cmd == 2){
                            // baca dari file
                        }

                        // Cara Output
                        while(true){

                            Output.menu_output();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd = input.nextInt();

                            if(cmd >= 1 && cmd <= 2){
                                break;
                            }
                        }

                        untukOutput = SPL.metode_gauss(m);

                        if(cmd == 2){
                            // write file
                        }

                    }else if(cmd == 2){ // GAUSS-JORDAN

                        // Cara Input
                        while(true){

                            Output.menu_input();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd = input.nextInt();

                            if(cmd >= 0 && cmd <= 2){
                                break;
                            }
                        }

                        if(cmd == 1){
                            m = Input.readMatrix();
                        }else if(cmd == 2){
                            // baca dari file
                        }

                        // Cara Output
                        while(true){

                            Output.menu_output();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd = input.nextInt();

                            if(cmd >= 1 && cmd <= 2){
                                break;
                            }
                        }

                        untukOutput = SPL.metode_gauss_jordan(m);

                        if(cmd == 2){
                            // write file
                        }

                    }else if(cmd == 3){ // INVERS

                        // invers


                    }else if(cmd == 4){ // KAIDAH CRAMER

                        // kaidah cramer

                    }else if(cmd == 0){ // KEMBALI

                        break;

                    }else{ // SALAH INPUT

                        salahInput = true;

                    }

                }

            }else if(cmd == 2){
                // Determinan
                while(true){

                    Output.menu_determinan();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd = input.nextInt();
                
                    if(cmd == 1){

                        // reduksi baris

                    }else if(cmd == 2){

                        // ekspansi kofaktor

                    }else if(cmd == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if (cmd == 3) {
                // Invers
                while(true){

                    Output.menu_matriks_balikan();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd = input.nextInt();
                
                    if(cmd == 1){

                        // adjoin

                    }else if(cmd == 2){

                        // matriks identitas

                    }else if(cmd == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if(cmd == 4){
                // Interpolasi Polinomial


            }else if(cmd == 5){
                // Bikubik


            }else if(cmd == 6){
                // Regresi
                while(true){

                    Output.menu_regresi();
                    
                    salahInput = Output.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd = input.nextInt();
                
                    if(cmd == 1){

                        // regresi linear berganda

                    }else if(cmd == 2){

                        // regresi kuadratik berganda

                    }else if(cmd == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if(cmd == 0){
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