/*
 * CS4341 Artificial Intelligence Project 1 -- Connect-N
 * Rafael Angelo rlangelo@wpi.edu
 * Daniel Benson djbenson@wpi.edu
 * This class process the messages given by the referee and makes our moves by 
 * passing them to the referee.
 */

package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {

	// Variables for our Player
	String playerName="myOtherPlayer";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
	boolean first_move = false;
	static int boardHeight, boardWidth, N, playerNumber;
	int board[][] = new int[100][100];
	int temp[][] = new int[100][100];
	int numMoves = 0;
	int secondsToPlay;
	File file;
	MiniMax result;

	// Constructor to make the debug file
	Player() throws IOException {
		this.file = new File("./Logfile.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("File was Created!\n");
		bw.close();
	}

	/* 
	 * This function is the function that process the input from the messages sent by the referee
	 * it also creates the local version of the game board, and sends our moves to the referee
	 */
	public void processInput() throws IOException {
		String s=input.readLine();
		List<String> ls=Arrays.asList(s.split(" "));
		this.result = new MiniMax();
		// Parse the messages from the Array
		// This parses the game info and creates our local board
		if (ls.size()==5) {
			// Record Game's specific board height and width
			boardWidth = Integer.parseInt(ls.get(1)); 
			boardHeight = Integer.parseInt(ls.get(0)); 
			N = Integer.parseInt(ls.get(2));
			secondsToPlay = Integer.parseInt(ls.get(4));
			createBoard(boardWidth, boardHeight);

			if (Integer.parseInt(ls.get(3)) == playerNumber){
				first_move = true;
				System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
				addMove(Integer.toString(boardWidth/2), "1", true, board, boardHeight);
				
			}
		}

		// We have been sent a move from the other player --  Add to board and make a move
		else if(ls.size()==2){ 
			addMove(ls.get(0), ls.get(1), false, board, boardHeight);
			Move myMove = new Move();
			
			int opponentPlayer = (playerNumber == 1) ? 2 : 1;
			
			
			if (secondsToPlay < 2)
			{
				myMove = this.result.getBestMove(board, playerNumber, boardWidth, boardHeight, 4);
			}
			else if (secondsToPlay > 5 && secondsToPlay < 15)
			{
				myMove = this.result.getBestMove(board, playerNumber, boardWidth, boardHeight, 5);
			}
		//	else if (secondsToPlay > 15)
		//	{
		//		myMove = this.result.getBestMove(board, playerNumber, boardWidth, boardHeight, 6);
		//	}
			else
			{
				 myMove = this.result.getBestMove(board, playerNumber, boardWidth, boardHeight, 4);
			}
			int horizontal = -1;
			int vertical = -1;
			int horizontalOpp = -1;
			int verticalOpp = -1;
			for (int i=0; i<boardWidth; i++) {
				vertical = this.result.checkVertically(board, boardWidth, boardHeight, playerNumber);
				horizontal = this.result.checkHorizontally(board, boardWidth, boardHeight, playerNumber);
				horizontalOpp = this.result.checkHorizontally(board, boardWidth, boardHeight, opponentPlayer);
				verticalOpp = this.result.checkVertically(board, boardWidth, boardHeight, opponentPlayer);
				if (vertical != -1) {
					
						myMove.nextMove = vertical;
				}
				
				else if (horizontal != -1) {
					myMove.nextMove = setHorizontal();
				}
				else if (horizontalOpp != -1)
				{
					myMove.nextMove = horizontalOpp;
				}
				else if (verticalOpp != -1)
				{
					myMove.nextMove = verticalOpp;
				}
					
			}
			
			System.out.println(Integer.toString(myMove.nextMove) + " " + "1");
			addMove(Integer.toString(myMove.nextMove), "1", true, board, boardHeight);
		}

		// Game is over
		else if(ls.size()==1){
			System.out.println("game over!!!");
		}

		// Figure out which player we are and record it
		else if(ls.size()==4){  //player1: aa player2: bb
			if (ls.get(1).equals(playerName)){
				playerNumber = 1;
			}
			else{
				playerNumber = 2;
			}
		}
		// This shouldn't happen
		else {
			System.out.println("not what I want");  
		}
	}

	// Add a move to our local version of the game board
	public void addMove(String column, String moveType, boolean myMove, int[][] board, int height) throws IOException {
		int col = Integer.parseInt(column);
		int type = Integer.parseInt(moveType);
		int row = -1;
		
		// Check if the move is a pop out
		if (type == 0) {
			for (int j=0; j < height-1; j++) {
				board[j][col] = board[j+1][col]; 
			}
			board[height-1][col] = 9;
		}
		else {
			// The move is dropping a piece
			for (int i=0; i<boardHeight; i++) {	
				// Find the first available row in the given column
				if(board[i][col]==9) {
					row = i;
					if (!myMove) {
						if (playerNumber == 1) {
							board[i][col] = 2;
							break;
						}
						else {
							board[i][col] = 1;
							break;
						}
					}
					else {
						if (playerNumber == 1) {
							board[i][col] = 1;
							break;
						}
						else {
							board[i][col] = 2;
							break;
						}
					}
				}
			}
		}
		
		// Print the local board to the debug file
		if (myMove && row >= 0) {
			printBoardToFile();
		}
		else if (!myMove && row >= 0) {
			printBoardToFile();
		}
	}

	// Create the local version of the gamee board
	public void createBoard(int width, int height) throws IOException {
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				board[i][j] = 9;
				temp[i][j] = 9;
			}
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Board was created!\n");
		bw.close();
		printBoardToFile();
	}

	// This function prints our local version of the board to a external debug file
	public void printBoardToFile() throws IOException {
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Board: \n");
		for (int i=boardHeight-1; i>=0; i--) {
			for (int j=0; j<boardWidth; j++) {	
				bw.write(board[i][j] + " ");	
			}
			bw.write(" \n");
		}
		bw.write(" \n");
		bw.close();
	}

	public int setHorizontal() throws IOException {
		int move = this.result.checkHorizontally(board, boardWidth, boardHeight, playerNumber);
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Horizontal Player here: " + move + "\n");
		bw.close();
		return move;
	}

	// Main Function to process referee input
	public static void main(String[] args) throws IOException {
		Player rp=new Player();
		System.out.println(rp.playerName);
		System.out.flush();
		while (true){
			rp.processInput(); 
		}
	}
}
