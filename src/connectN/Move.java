package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Move {

	public Move(String lastMove)
	{
		
	}
	
	public String getMove(List<String> lastMove) {
		
		//List<String> ls=Arrays.asList(lastMove.split(" "));
		int opponentCol = Integer.parseInt(lastMove.get(0));
		String opponentOp = lastMove.get(1);
		int myColumn = opponentCol + 1;
		String myMove = Integer.toString(myColumn) + " " + "1";
		return myMove;
	}
	
}
