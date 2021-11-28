import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Afnan
 */

 public class a_star{
      public static void main(String[] args){
          Scanner in = new Scanner(System.in);
          System.out.println("Enter Grid Size");
          int k = in.nextInt();
          int [][]mat  = new int[k+1][k+1];
          System.out.println("Enter Intial Board State");
          
          for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                mat[i][j] = in.nextInt();
            }
          }

          for(int i=3 ; i>=1 ; i--){
              if(i==1){
                  System.out.println("Heuristics : Hamming distance");
              }
              else if(i==2){
                  System.out.println("Heuristics : Manhattan Distance");
              }
              else{
                  System.out.println("Heuristics : Linear Conflicts");
              }

              board_solver bs = new board_solver(mat , k);
              bs.set_heuristic_id(i);
              Node goal = bs.solve();
              if(goal != null){
                  bs.print_path(goal);
              }
          }
      }
 }