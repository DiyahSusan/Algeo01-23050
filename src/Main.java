import ADTMatrix.*;
import Function.*;
import IO.*;

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int cmd1, cmd2, cmd3 = 0;
        boolean salahInput = false;
        Matrix m = new Matrix();
        String[] untukOutput;

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
                        while(true){

                            Output.menu_input();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd3 = input.nextInt();

                            if(cmd3 >= 0 && cmd3 <= 2){
                                break;
                            }else{
                                salahInput = true;
                            }
                        }

                        if(cmd3 == 0) continue;

                        if(cmd3 == 1){
                            m = Input.readMatrix();
                        }else if(cmd3 == 2){
                            // baca dari file
                        }

                        // Cara Output
                        while(true){

                            Output.menu_output();

                            salahInput = Output.pesan_salah_input(salahInput);

                            System.out.print("> ");
                            cmd3 = input.nextInt();

                            if(cmd3 >= 1 && cmd3 <= 2){
                                break;
                            }else{
                                salahInput = true;
                            }
                        }
                    }
                
                    if(cmd2 == 1){ // GAUSS
                        
                        untukOutput = SPL.metode_gauss(m);

                        if(cmd3 == 2){
                            // write file
                        }

                    }else if(cmd2 == 2){ // GAUSS-JORDAN

                        untukOutput = SPL.metode_gauss_jordan(m);

                        if(cmd3 == 2){
                            // write file
                        }

                    }else if(cmd2 == 3){ // INVERS

                        // invers
                        //untukOutput = SPL.metode_invers(m);
                        
                        if(cmd3 == 2){
                            // write file
                        }

                    }else if(cmd2 == 4){ // KAIDAH CRAMER

                        // kaidah cramer

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