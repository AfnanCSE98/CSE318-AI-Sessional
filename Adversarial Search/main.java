import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class main{

    private static final int nbins = 6;
	private static final int nstones = 4;
	public static final boolean DEBUG = false;
	public static final int MAX_DEPTH = 15;
	private static final int nMaxStages = 150;
	private static final boolean PRINT_BOARD_CONFIG = true;

    public static int playLoop( Heuristic s0 , Heuristic s1 , int MAX_DEPTH ) {
		if (DEBUG) try {
			System.setOut( new PrintStream( "out.log" ) );
			System.setErr( new PrintStream( "err.log" ) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//MancalaBoard.printBoardConfiguration();
		return play( nbins , nstones , s0 , s1 , MAX_DEPTH );
//		System.setOut( ps );
	}
	
	private static Heuristic selectStrategy( int n ) {
		return Heuristic.intToStrategy( n );
	}
	
	
	///======================== IO Methods =======================================////
//================================================================================
	
	private static int play( int bins , int stones , Heuristic s0 , Heuristic s1 , int MAX_DEPTH ) {
		board bd = new board( s0 , s1 , MAX_DEPTH );
		
        if (PRINT_BOARD_CONFIG) System.out.println( bd );
		int round = 0;
		while (!bd.is_game_over() && round < nMaxStages) {
			if (PRINT_BOARD_CONFIG) System.out.println( "------------" + round + "--------------" );
			int currentPlayer = bd.currentPlayer;
			if (PRINT_BOARD_CONFIG) System.out.println( "Player " + currentPlayer + "\'s move." );
			int bin = bd.move();
			if (bin <= 0) break;
			if (PRINT_BOARD_CONFIG) System.out.println( "Player " + currentPlayer + " selects "
					                                      + bd.stonesMoved + " stones from bin " + bin );
			if (PRINT_BOARD_CONFIG) System.out.println( bd );
			if (PRINT_BOARD_CONFIG) System.out.println( "\n\n\n--------------------------\n\n\n" );
			round++;
		}
		System.out.println( "Final board configuration:\n" );

		System.out.println( bd );
		if (bd.get_bin( 0 , 0 ) == bd.get_bin( 1 , 0 )) {
			if (PRINT_BOARD_CONFIG) System.out.println( "The game ends in a tie!" );
			return -1;
		} else if (bd.get_bin( 0 , 0 ) > bd.get_bin( 1 , 0 )) {
			if (PRINT_BOARD_CONFIG) System.out.println( "Player0 wins!" );
			return 0;
		} else {
			if (PRINT_BOARD_CONFIG) System.out.println( "Player1 wins!" );
			return 1;
		}
	}

    public static void main( String[] args ) throws IOException {
		int h[] = { 0 , 0 };
		if (DEBUG) {
			//statPrint();
		} else {
			Scanner scanner = new Scanner( System.in );
            System.out.println("Choose heuristic[1/2/3] for player 0");
            h[0] = scanner.nextInt();
            System.out.println("Choose heuristic[1/2/3] for player 1");
            h[1] = scanner.nextInt();
			System.out.println("Choose depth");
            int depth = scanner.nextInt();
            playLoop( selectStrategy( h[0] ) , selectStrategy( h[1] ) , depth );
		}
	}

}