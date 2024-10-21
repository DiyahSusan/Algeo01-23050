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

        while(true){

            Printer.header();

            salahInput = Printer.pesan_salah_input(salahInput);

            System.out.print("> ");
            cmd = input.nextInt();

            if(cmd == 1){
                // SPL
                while(true){

                    Printer.menu_spl();
                    
                    salahInput = Printer.pesan_salah_input(salahInput);

                    System.out.print("> ");
                    cmd = input.nextInt();
                
                    if(cmd == 1){

                        // gauss

                    }else if(cmd == 2){

                        // gauss-jordan

                    }else if(cmd == 3){

                        // invers

                    }else if(cmd == 4){

                        // kaidah cramer

                    }else if(cmd == 0){
                        break;
                    }else{
                        salahInput = true;
                    }

                }

            }else if(cmd == 2){
                // Determinan
                while(true){

                    Printer.menu_determinan();
                    
                    salahInput = Printer.pesan_salah_input(salahInput);

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

                    Printer.menu_matriks_balikan();
                    
                    salahInput = Printer.pesan_salah_input(salahInput);

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

                    Printer.menu_regresi();
                    
                    salahInput = Printer.pesan_salah_input(salahInput);

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
                Printer.pesan_keluar();
                break;

            }else{
                salahInput = true;
            }

        }

    }    

}