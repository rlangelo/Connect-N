package connectN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;



public class MiniMax {

	File file;
	MiniMax() throws IOException
	{
		file = new File("./Logfile.txt");
		if (!file.exists())
		{
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
	}
	
	//public int miniMax(int[][] board, int depth, int limit, int alpha, int beta, boolean flag)
	//{
		
//	}

	// This function checks to see if there the state is a winning state
	boolean isWin(int width, int height, int[][] gameBoard) {
		boolean win = false;

		//check for win horizontally
		for (int row=0; row<height; row++) {
		    for (int col=0; col<width-3; col++) { //0 to 3
				if (gameBoard[row][col] == gameBoard[row][col+1] &&
				    gameBoard[row][col] == gameBoard[row][col+2] &&
				    gameBoard[row][col] == gameBoard[row][col+3] &&
				    gameBoard[row][col] != 9) {
				    win = true;
				}
			}
		}
		
		//check for win vertically
		for (int row=0; row<height-3; row++) { //0 to 2
		    for (int col=0; col<width; col++) {
				if (gameBoard[row][col] == gameBoard[row+1][col] &&
				    gameBoard[row][col] == gameBoard[row+2][col] &&
				    gameBoard[row][col] == gameBoard[row+3][col] &&
				    gameBoard[row][col] != 9) {
				    win = true;
				}
		    }
		}
		
		//check for win diagonally (upper left to lower right)
		for (int row=0; row<height-3; row++) { //0 to 2
		    for (int col=0; col<width-3; col++) { //0 to 3
				if (gameBoard[row][col] == gameBoard[row+1][col+1] &&
				    gameBoard[row][col] == gameBoard[row+2][col+2] &&
				    gameBoard[row][col] == gameBoard[row+3][col+3] &&
				    gameBoard[row][col] != 9) {
				    win = true;
				}
		    }
		}
		
		//check for win diagonally (lower left to upper right)
		for (int row=3; row<height; row++) { //3 to 5
		    for (int col=0; col<width-3; col++) { //0 to 3
				if (gameBoard[row][col] == gameBoard[row-1][col+1] &&
				    gameBoard[row][col] == gameBoard[row-2][col+2] &&
				    gameBoard[row][col] == gameBoard[row-3][col+3] &&
				    gameBoard[row][col] != 9) {
				    win = true;
				}
		    }
		}
		return win;
	}
	
	// This function checks to see if a given move is allowed
	public boolean isOKMove(int[][] board, int row, int col, int width, int height) {
		// The move is legal if the following statements are met
		if (row < height && col < width) {
		    if (row >= 0 && col >= 0) {
				if (board[row][col] == 9) {
				    if (row == height-1) {
				    	return true;
				    }
				    if (board[row+1][col] != 9){
				    	return true;
				    }
				}
		    }
		}
		return false;
    }
	
}
