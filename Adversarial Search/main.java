import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class main{

	private static boolean human_vs_AI=false;
	public static final boolean DEBUG = false;
	public static final int MAX_DEPTH = 15;
	private static final int nMaxStages = 150;
	
	private static Heuristic selectStrategy(int n){
		return Heuristic.intToStrategy(n);
	}
	
	
	private static int play(Heuristic s0 , Heuristic s1 , int MAX_DEPTH ) {
		board bd = new board( s0 , s1 , MAX_DEPTH );
		
        System.out.println(bd);
		int round = 0;
		while (!bd.is_game_over() && round < nMaxStages){
			System.out.println("------------" + round + "--------------");
			int currentPlayer = bd.currentPlayer;
			System.out.println("Player " + currentPlayer + "\'s move.");
			int bin = bd.move();
			if (bin <= 0) break;
			System.out.println("Player " + currentPlayer + " selects "
					                                      + bd.stonesMoved + " stones from bin " + bin );
			System.out.println(bd);
			System.out.println("\n--------------------------\n" );
			round++;
		}
		System.out.println( "Final board configuration:\n" );

		System.out.println( bd );
		if (bd.get_bin( 0 , 0 ) == bd.get_bin( 1 , 0 )) {
			System.out.println( "The game ends in a tie!" );
			return -1;
		} else if (bd.get_bin( 0 , 0 ) > bd.get_bin( 1 , 0 )) {
			System.out.println( "Player0 wins!" );
			return 0;
		} else {
			System.out.println( "Player1 wins!" );
			return 1;
		}
	}


	private static int play_human(Heuristic s0 , int MAX_DEPTH ) {
		//Heuristic s1 = new Heuristic(1);
		board bd = new board(s0 , null , MAX_DEPTH );
		
        System.out.println(bd);
		int round = 0;
		boolean turnAI = true;
		int bin;
		int currentPlayer = 0;
		Scanner sc = new Scanner(System.in);

		while (!bd.is_game_over() && round < nMaxStages){
			System.out.println("------------" + round + "--------------");
			currentPlayer = bd.currentPlayer;
			if(currentPlayer == 0)System.out.println("AI\'s move\n");
			else System.out.println("Player " + currentPlayer + "\'s move.");
			
			if(turnAI && currentPlayer==0){
				bin = bd.move();
				if(currentPlayer == 1)turnAI = false;
			}
			else {
				System.out.println("Select bin[1/2/3/4/5/6](right to left)\n");
				bin = sc.nextInt();
				bd.move(bin);
				if(currentPlayer == 0)turnAI = true;
			}
			
			if (bin <= 0) break;
			if(currentPlayer == 0)System.out.println("AI selects " + bd.stonesMoved + " stones from bin " + bin);
			else System.out.println("Player " + currentPlayer + " selects " + bd.stonesMoved + " stones from bin " + bin );
			
			System.out.println(bd);
			System.out.println("\n--------------------------\n" );
			round++;
		}
		System.out.println( "Final board configuration:\n" );

		System.out.println( bd );
		if (bd.get_bin( 0 , 0 ) == bd.get_bin( 1 , 0 )) {
			System.out.println( "The game ends in a tie!" );
			return -1;
		} else if (bd.get_bin( 0 , 0 ) > bd.get_bin( 1 , 0 )) {
			if(human_vs_AI)System.out.println( "AI wins!" );
			else System.out.println( "Player0 wins!" );
			return 0;
		} else {
			System.out.println( "Player1 wins!" );
			return 1;
		}
	}

    public static void main(String[] args ) throws IOException {
		int h[] = { 0 , 0 };
		Scanner scanner = new Scanner( System.in );
		System.out.println("AI vs AI(1)\nor\nHuman vs AI(2)\n");
		int choice = scanner.nextInt();
		if (choice==2){
			//human vs AI
			human_vs_AI = true;
			System.out.println("Choose heuristic[1/2/3] for AI(player 0)");
			int h_id = scanner.nextInt();
			Heuristic s0 = new Heuristic(h_id);
			play_human(s0, 10);
		} 
		else{
			
            System.out.println("Choose heuristic[1/2/3] for player 0");
            h[0] = scanner.nextInt();
            System.out.println("Choose heuristic[1/2/3] for player 1");
            h[1] = scanner.nextInt();
			System.out.println("Choose depth");
            int depth = scanner.nextInt();
            play(selectStrategy(h[0]) , selectStrategy(h[1]) , depth);
		}
	}

}