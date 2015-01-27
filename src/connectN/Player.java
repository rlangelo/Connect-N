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
		String s=input.readLine();
		List<String> ls=Arrays.asList(s.split(" "));
		this.result = new MiniMax();
		// Parse the messages from the Array
		// This parses the game info and creates our local board
		if(ls.size()==5){
			// Record Game's specific board height and width
			boardWidth = Integer.parseInt(ls.get(1)); 
			boardHeight = Integer.parseInt(ls.get(0)); 
			N = Integer.parseInt(ls.get(2));
			createBoard(boardWidth, boardHeight);

			if (Integer.parseInt(ls.get(3)) == playerNumber){
				first_move = true;
				System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
				addMove(Integer.toString(boardWidth/2), true, board);
				
			}
		}

		// We have been sent a move from the other player --  Add to board and make a move
		else if(ls.size()==2){ 
			addMove(ls.get(0), ls.get(1), false, board, boardHeight);
			Random rand = new Random();
			int myMove = rand.nextInt(7);
			int horizontal = -1;
			int diagonal1 = -1;
			for (int i=0;i<boardWidth;i++)
			{
				myMove = this.result.checkVertically(board, boardWidth, boardHeight, playerNumber);
				horizontal = this.result.checkHorizontally(board, boardWidth, boardHeight, playerNumber);
				if (myMove == -1)
				{
					if (horizontal != -1)
					{
						myMove = setHorizontal();
					}
					//else if (diagonal1 != -1)
					//{
					//	myMove = diagonal1;
					//}
				}
				
			}
			if (myMove == -1)
			{
				myMove = rand.nextInt(7);
				while (this.result.isColFull(myMove, board, boardHeight))
				{
					myMove = rand.nextInt(7);
				}
			}
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
	public void addMove(String column, String moveType, boolean myMove, int[][] board, int height) throws IOException {
		int col = Integer.parseInt(column);
		int type = Integer.parseInt(moveType);
		int row = -1;
		for (int i=0;i<boardHeight;i++)
		{	
			// Check if the move is a pop out
			if (type == 0){
				for (int i=0; i < height-1; i++){
					board[i][col] = board[i+1][col]; 
				}
				board[height-1][col] = 9;
			}
			else {
				// The move is dropping a piece
				// Find the first available row in the given column
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
			}


			if (myMove && row >= 0) {
				printBoardToFile();
			}
			else if (!myMove && row >= 0) {
				printBoardToFile();
			}
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
	
	public int setHorizontal() throws IOException
	{
		int move = this.result.checkHorizontally(board, boardWidth, boardHeight, playerNumber);
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Horizontal Player here: " + move + "\n");
		bw.close();
		return move;
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
