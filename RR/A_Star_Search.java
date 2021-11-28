/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Rayhan
 */
public class A_Star_Search {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int [][] matrix;

        int n;
        File file = new File("inputs.txt");
        Scanner in;
        boolean path=false;
        in = new Scanner(System.in);
        n=in.nextInt();
        matrix=new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        
        for (int i = 3; i >= 1; i--) {
            
            System.out.print("Using  Heuristics: ");
            if(i==3)
            {
                System.out.println("Linear Conflicts\n\n");
            }
            else if(i==2)
            {
                System.out.println("Manhattan Distance\n\n");                
            }
            else
            {
                System.out.println("Hamming Distance\n\n");                
            }
            Search_Runner sr = new Search_Runner(matrix, n);
            sr.heuristics_index=i;
            State s=new State(sr.initialState.matrix,n);
            //System.out.println(s.hashCode());
            //System.out.println(sr.initialState.hashCode());
            Node Result = sr.Search();
            if (Result == null) {
                System.out.println("There");
            } else {
                System.out.println("Result: " + Result.f_cost + " " + Result.g_cost + " " + Result.h_cost);
                if(path)
                {
                    sr.printpath(Result);
                }
            }
        }
        

    }

}
