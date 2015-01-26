package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Player {

	// Variables for our Player
	String playerName="myOtherPlayer";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
	boolean first_move = false;
	static int boardHeight, boardWidth, N, playerNumber;
	int board[][] = new int[100][100];
	int temp[][] = new int[100][100];
	int numMoves = 0;
	File file;
	MiniMax result;

	// Constructor to make the debug file
	Player() throws IOException {
		this.file = new File("./Logfile.txt");
		if (!file.exists())
		{
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

		int moveVal;
		String s=input.readLine();
		List<String> ls=Arrays.asList(s.split(" "));
		this.result = new MiniMax();
		// Parse the messages from the Array
		// This parses the game info and creates our local board
		if(ls.size()==5){
			// Record Game's specific board height and width
			boardWidth = Integer.parseInt(ls.get(1)); 
			boardHeight = Integer.parseInt(ls.get(0)); 
			createBoard(boardWidth, boardHeight);

			if (Integer.parseInt(ls.get(3)) == playerNumber){
				first_move = true;
				System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
				addMove(Integer.toString(boardWidth/2), true, board);
			}
		}

		// We have been sent a move from the other player --  Add to board and make a move
		else if(ls.size()==2){ 
			addMove(ls.get(0), false, board);
			int myMove = 6;
			int horizontal = -1;
			for (int i=0;i<boardWidth;i++)
			{
				myMove = this.result.checkVertically(board, boardWidth, boardHeight, playerNumber);
				horizontal = this.result.checkHorizontally(board, boardWidth, boardHeight, playerNumber);
				if (myMove == -1 && horizontal == -1)
				{
					myMove = 6;
				}
				else
				{
					if (myMove == -1)
					{
						myMove = horizontal;
					}
				}

			}
			//if it's a win condition play there!
			/*
			outerLoop: 
			for (int i=0;i<boardHeight;i++){
				for (int j=0;j<boardWidth;j++){
					if (this.result.isOKMove(temp, i, j, boardWidth, boardHeight)){
						temp[i][j] = playerNumber;
						if (this.result.isWin(boardWidth, boardHeight, temp)){
						//	System.out.println(Integer.toString(j) + " " + "1");
							temp[i][j] = 9;
							myMove = j;
							break outerLoop;
							//addMove(Integer.toString(j), true, board);
							//return;
						}
					}
					temp[i][j] = 9;
				}
			}
			//if it's a lose condition also play there
			outerLoop:
			for (int i=0;i<boardHeight;i++){
				for (int j=0;j<boardWidth;j++){
					if (this.result.isOKMove(temp, i, j, boardWidth, boardHeight)){
						int opponentNumber = (playerNumber == 1) ? 2 : 1;
						temp[i][j] = opponentNumber;
						bw.write("What about here? " + opponentNumber + "\n");	
						if (this.result.isWin(boardWidth, boardHeight, temp)){
					//		System.out.println(Integer.toString(j) + " " + "1");
					//		//addMove(Integer.toString(j), true, board);
							//return;
							bw.write("Does it come here? Roll a j: " + j + "\n");
							myMove = j;
							temp[i][j] = 9;
							break outerLoop;
						}
					}
					temp[i][j] = 9;
				}
			}
			//Find minimax.
			//Move mov = new Move();
			//mov = this.result.getMiniMax(board, 0, 5, true, boardHeight, boardWidth, playerNumber);
			//int nextMoveColumn = mov.nextMove;
			//moveVal = mov.score;
			//System.out.println(Integer.toString(nextMoveColumn) + " " + "1");*/
			//bw.close();
			System.out.println(Integer.toString(myMove) + " " + "1");
			
			addMove(Integer.toString(myMove), true, board);
			
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
		else {
			System.out.println("not what I want");  
		}
	}

	// Add a move to our local version of the game board
	public void addMove(String column, boolean myMove, int[][] board) throws IOException {
		int col = Integer.parseInt(column);
		int row = -1;
		for (int i=0;i<boardHeight;i++)

			if(board[i][col]==9){
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


		if (myMove && row >= 0) {
			printBoardToFile();
		}
		else if (!myMove && row >= 0) {
			printBoardToFile();
		}
	}

	// Create the local version of the gane board
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

	public void printBoardToFile() throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Board: \n");
		for (int i=boardHeight-1;i>=0;i--){
			for (int j=0; j<boardWidth; j++){	
				bw.write(board[i][j] + " ");	
			}
			bw.write(" \n");
		}
		bw.write(" \n");
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		Player rp=new Player();
		System.out.println(rp.playerName);
		System.out.flush();
		while (true){
			rp.processInput(); 
		}
	}

}
