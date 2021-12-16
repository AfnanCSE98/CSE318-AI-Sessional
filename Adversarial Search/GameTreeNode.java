

import java.util.ArrayList;

/// any minimax problem has to implement this interface properly to use Minimax algorithm class
public interface GameTreeNode {
	// heuristic value to be returned
	double heuristicValue();
	
	// list of successors to be generated & returned maxPlayer / minPlayer also to be managed here
	ArrayList< GameTreeNode > successors();
	
	// returns true for leaf nodes
	boolean isTerminal();
	
	// returns true if this player to be maximized, false to minimize
	boolean isMaximizing();
	
	// compare two objects to check whether they are same or not
	boolean equals( GameTreeNode o );
}
