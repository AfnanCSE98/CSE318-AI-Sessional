import java.util.Random;

public class Heuristic {
	static final int MAX_WEIGHT = 5;

    int h_id;

    public Heuristic(int h_id){
        this.h_id = h_id;
    }

    // Return the index of a non-empty bin to move.
	// Assumes that at least one move is possible.
	public int selectMove( board bd , int depth ){
		int bin = 0;
		try {
			bin = Minimax.minimax( bd , depth ) + 1; // index starts from 0 bt bin from 1
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bin;
	}
	
	
	public static Heuristic intToStrategy( int i ) {
		// Returns a new Heuristic corresponding to the number between 0 and 4.
		
        if(i==1)return new Heuristic(1);
        else if(i==2)return new Heuristic(2);
        else if(i==3)return new Heuristic(3);
        else return new Heuristic(1);//default case : heuristic 1
	}
	
	
	public int getHeuristicValue(board bd){
        if(h_id==1){
            int maxPlayer = bd.maxPlayer;//board.getMaxPlayer();
            int minPlayer = bd.other_player( maxPlayer );
            //heuristic-1: The evaluation function is
            //(stones_in_my_storage – stones_in_opponents_storage)
            int stones_in_my_storage = bd.stones_in_storage( maxPlayer );//board.getPlayersTotalStones( board.currentPlayer() );
            int stones_in_opponents_storage = bd.stones_in_storage( minPlayer );//board.getPlayersTotalStones( MancalaBoard.otherPlayer( board.currentPlayer() ) );
            return stones_in_my_storage - stones_in_opponents_storage;
        }
        else if(h_id==2){
            int W1 = new Random().nextInt( MAX_WEIGHT ) + 1;
            int W2 = new Random().nextInt( MAX_WEIGHT ) + 1;
            int maxPlayer = bd.maxPlayer;
            int minPlayer = bd.other_player( maxPlayer );
            //W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side –
            //stones_on_opponents_side)
            int stones_in_my_storage = bd.stones_in_storage( maxPlayer );//board.getPlayersTotalStones( board.currentPlayer() );
            int stones_in_opponents_storage = bd.stones_in_storage( minPlayer );//board.getPlayersTotalStones( MancalaBoard.otherPlayer( board.currentPlayer() ) );
            int stones_in_my_side = bd.get_total_stones_of( maxPlayer );
            int stones_in_opponents_side = bd.get_total_stones_of( minPlayer );
            return W1 * (stones_in_my_storage - stones_in_opponents_storage) + W2 * (stones_in_my_side - stones_in_opponents_side);
    
        }
        else if(h_id==3){
            int W1 = new Random().nextInt( MAX_WEIGHT ) + 1;
            int W2 = new Random().nextInt( MAX_WEIGHT ) + 1;
            int W3 = new Random().nextInt( MAX_WEIGHT ) + 1;
            int maxPlayer = bd.maxPlayer;
            int minPlayer = bd.other_player( maxPlayer );
           
            int stones_in_my_storage = bd.stones_in_storage( maxPlayer );//board.getPlayersTotalStones( board.currentPlayer() );
            int stones_in_opponents_storage = bd.stones_in_storage( minPlayer );//board.getPlayersTotalStones( MancalaBoard.otherPlayer( board.currentPlayer() ) );
            int stones_in_my_side = bd.get_total_stones_of( maxPlayer );
            int stones_in_opponents_side = bd.get_total_stones_of( minPlayer );
            int additional_move_earned = 0;
            return W1 * (stones_in_my_storage - stones_in_opponents_storage) + W2 * (stones_in_my_side - stones_in_opponents_side) + W3 * additional_move_earned;
        }
        else return -1;
    }
}
