package Function;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ADTMatrix.Matrix;

public class SPL{
    
    public static String[] solve(Matrix m){

        String[] anu = new String[m.row];

        if(m.isNoSolution()) return anu;

        m = m.gaussElimination();

        if(m.isManySolution()){
            anu = m.solveManySolution();
            return anu;
        }

        
    }

}