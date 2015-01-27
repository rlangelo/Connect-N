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
	
	public int checkVertically(int[][] board, int width, int height, int player) throws IOException {
		//check each column, vertically
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		int max1=0;
		int max2=0;
		int opponentPlayer = (player == 1) ? 2 : 1;

		for(int j=0;j<width;j++){
			max1=0;
			max2=0;
			for(int i=0;i<height;i++){
				if(board[i][j]==player){
					max1++;
					max2=0;
					if(max1==3)
					{
						//bw.write("There are three 2s in line.\n");
						if (i != height-1)
						{
							if (board[i+1][j] == 9)
							{
								bw.write("Winning with vertical!");
								bw.close();
								return j;

							}
						}
					}

				}
				else if(board[i][j]==opponentPlayer){
					max1=0;
					max2++;
					if(max2==3)
					{
						//bw.write("Opponent has 3 in line. \n");
						if (i != height-1)
						{
							if (board[i+1][j] == 9)
							{
								bw.write("Blocking Opponent vertical \n");
								bw.close();
								return j;
							}
						}
					}
				}
				else{
					max1=0;
					max2=0;
				}
			}
		} 
		bw.close();
		return -1;

	}

	public int checkHorizontally(int[][] board, int width, int height, int player) throws IOException {
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		int max1=0;
		int max2=0;
		int spaceLocation = -1;		
		int opponentPlayer = (player == 1) ? 2 : 1;
		//check each row, horizontally
		for(int i=0;i<height;i++){
			max1=0;
			max2=0;
			//bw.write("In Horizontal\n");
			for(int j=0;j<width;j++){
				if(board[i][j]==player){
					max1++;
					max2=0;
					//bw.write("What about here?! " + max1 + "\n");
					if(max1==3)
					{
						//bw.write("I have 3 of a kind!\n");
						if (spaceLocation != -1)
						{
							if (i != 0)
							{
								if (board[i-1][spaceLocation] != 9)
								{
									bw.write("Winning space Horizontally! " + spaceLocation);
									bw.close();
									return spaceLocation;
								}
							}
							else
							{
								bw.write("Winning space Horizontally! " + spaceLocation);
								bw.close();
								return spaceLocation;
							}
						}
						if (j != width-1)
						{
							if (board[i][j+1] == 9)
							{
								if (i>0)
								{
									if (board[i-1][j+1] != 9)
									{
										bw.write("Winning Horizontally!\n");
										bw.close();
										return j+1;
									}
								}
								else {
									bw.write("Winning Horizontally!\n");
									bw.close();
									return j+1;
								}
										
							}
							else if (board[i][j-3] == 9)
							{
								if (i > 0)
								{
									if (board[i-1][j-3] != 9)
									{
										bw.write("Winning as well Horizontally!\n");
										bw.close();
										return j-3;
									}
								}
								else
								{
								bw.write("Winning as well Horizontally!\n");
								bw.close();
								return j-3;
								}
							}
						}
						else if (j == width-1)
						{
							if (i > 0){
								if (board[i-1][j-3] != 9){
									if (board[i][j-3] == 9)
									{
										bw.write("Also winning horizontally!\n");
										bw.close();
										return j-3;
									}
								}
							}
							else {
								if (board[i][j-3] == 9)
								{
									bw.write("Also winning horizontally!\n");
									bw.close();
									return j-3;
								}
							}
						}
					}
				}


					else if(board[i][j]==opponentPlayer){
						max1=0;
						max2++;
						//bw.write("What about Opponent here?!\n");
						if(max2==3)
						{
							//bw.write("Opponent has 3 of a kind!\n");
							if (spaceLocation != -1)
							{
								if (i > 0)
								{
									if (board[i-1][spaceLocation] != 9)
									{
										bw.write("Blocking space Horizontal\n");
										bw.close();
										return spaceLocation;
									}
								}
								else {
								bw.write("Winning space Horizontally! " + spaceLocation);
								bw.close();
								return spaceLocation;
								}
							}
							if (j != width-1)
							{
								if (i > 0)
								{
									if (board[i-1][j+1] != 9)
									{
										if (board[i][j+1] == 9)
										{
											bw.write("Blocking opponent! Horizontal\n");
											bw.close();
											return j+1;
										}
									}
									else if (j > 2)
									{
										
										if (board[i][j-3] == 9)
										{
											bw.write("Also block opponent! Horizontal\n");
											bw.close();
											return j-3;
										}
									}
								}
								else if (i == 0)
								{
									if (board[i][j+1] == 9)
									{
										bw.write("Blocking opponent! Horizontal\n");
										bw.close();
										return j+1;
									}
									
								}
								
								else if (j > 2)
								{
									if (board[i][j-3] == 9)
									{
										bw.write("Also block opponent! Horizontal\n");
										bw.close();
										return j-3;
									}
								}
							}
							else if (j == width-1)
							{
								if (board[i][j-3] == 9)
								{
									bw.write("Blocking as well! Horizontal\n");
									bw.close();
									return j-3;
								}
							}
						}

					}
					else if (((max1 == 1 || max1 == 2) && spaceLocation == -1) || ((max2 == 1 || max2 == 2) &&
							spaceLocation == -1))
					{
						
						spaceLocation = j;
					}
					else{
						max1=0;
						max2=0;
					}
			} 
		}
		bw.close();
		return -1;
	}
	/*
	public int checkDiagonally1(int[][] board, int width, int height, int player, int N) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		int max1=0;
		int max2=0;
		int spaceLocation = -1;		
		int opponentPlayer = (player == 1) ? 2 : 1;
		int upper_bound=height-1+width-1-(N-1);
		
		 for(int k=N-1;k<=upper_bound;k++){			
			 max1=0;
			 max2=0;
			 int x,y;
			 if(k<width) 
				 x=k;
			 else
				 x=width-1;
			 y=-x+k;
			 while(x>=0  && y<height){
					// System.out.println("k: "+k+", x: "+x+", y: "+y);
					if(board[height-1-y][x]==player){
						max1++;
						max2=0;
						if(max1==3)
						{
							if (spaceLocation != -1)
							{
								if (board[height-y-1][x+1] != 9)
								{
									return x+1;
								}
							}
							if (board[height-1-y-1][x-1] == 9 && board[height-1-y-2][x-1] == 9)
							{
								return x-1;
							}
							
						}
							 
					}
					else if(board[height-1-y][x]==opponentPlayer){
						max1=0;
						max2++;
						if(max2==3)
						{
							if (board[height-y-1][x+1] != 9)
							{
								return x+1;
							}
							if (board[height-1-y-1][x-1] == 9 && board[height-1-y-2][x-1] == 9)
							{
								return x-1;
							}
						}
							
					}
					else if (((max2 == 1 || max2 == 2) && spaceLocation == -1) || (max1 == 1 || max1 == 2) && spaceLocation == -1)
					{
						spaceLocation = 1;
					}
					else{
						max1=0;
						max2=0;
					}
					x--;
					y++;
				}	 
				 
			 }
		
		return -1;
	}
	*/
	// This function checks to see if there the state is a winning state
	boolean isWin(int width, int height, int[][] gameBoard) throws IOException {
		boolean win = false;

		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		//bw.write("Is win was called\n");
		bw.write(gameBoard[0][0] + "\n");
		bw.write(gameBoard[5][0] + " bottom corner\n");
		//check for win horizontally
		for (int row=0; row<height; row++) {
		    for (int col=0; col<width-3; col++) { //0 to 3
				if (gameBoard[row][col] == gameBoard[row][col+1] &&
				    gameBoard[row][col] == gameBoard[row][col+2] &&
				    gameBoard[row][col] == gameBoard[row][col+3] &&
				    gameBoard[row][col] != 9) {
					bw.write("Wins horizontally!\n");
				    win = true;
				}
			}
		}
		
		//check for win vertically
		for (int row=0; row<height-3; row++) { //0 to 2
		    for (int col=0; col<width; col++) {
		    	//bw.write(gameBoard[row][col] + " " + gameBoard[row+1][col] + " " + gameBoard[row+2][col] + " " +
		    	//		gameBoard[row+3][col] + "\n");
				if (gameBoard[row][col] == gameBoard[row+1][col] &&
				    gameBoard[row][col] == gameBoard[row+2][col] &&
				    gameBoard[row][col] == gameBoard[row+3][col] &&
				    gameBoard[row][col] != 9) {
					bw.write("Wins Vertically!\n");
				    win = true;
				}
		    }
		}
		bw.close();
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
	
	// This function will preform the Minimax Algorithm
	public Move getMiniMax(int[][] board, int depth, int limit, boolean flag, int boardWidth, int boardHeight, int player){
		int oldValue, value, storedValue;
		int[] nextMove=new int[] {0,0};
		int max = Integer.MAX_VALUE;

		depth = depth + 1;
		
		// If min is selected, find the min
		if (flag) {
			value = Integer.MAX_VALUE;
			for (int i=0; i<=boardHeight; i++) {
				for (int j=0; j<=boardWidth; j++) {
					if (isOKMove(board, i, j, boardWidth, boardHeight)) {
						oldValue = value;
						storedValue = board[i][j];
						int opponentNumber = (player == 1) ? 2 : 1;
						board[i][j] = opponentNumber;
						value = java.lang.Math.min(value, getMiniMax(board, depth, limit, !flag, boardWidth, 
								boardHeight, player).score);
						board[i][j] = storedValue;
						if ((depth == 1) && (value < oldValue)) {
							nextMove[0] = i;
							nextMove[1] = j;
						}
					}
				}
			}
			//return new int[] {nextMove[0],nextMove[1],beta};
			Move mov = new Move();
			mov.nextMove = nextMove[1];
			mov.score = nextMove[2];
			return mov;
		}
		// If the minimum is not selected, then find the maximum
		else {
			value = Integer.MIN_VALUE;
			for (int i=0; i<=boardHeight; i++) {
				for (int j=0; j<=boardWidth; j++) {
					if (isOKMove(board, i, j, boardWidth, boardHeight)) {
						oldValue = value;
						storedValue = board[i][j];
						board[i][j] = player;
						value = java.lang.Math.max(value, getMiniMax(board, depth, limit, !flag, boardWidth, 
								boardHeight, player).score);
						board[i][j] = storedValue;
						if ((depth == 1) && (value > oldValue)) {	   
							nextMove[0] = i;
							nextMove[1] = j;
						}
					}
				}
			}
			
			//return new int[] {nextMove[0],nextMove[1],alpha};
			Move mov = new Move();
			mov.nextMove = nextMove[1];
			mov.score = nextMove[2];
			return mov;
		}//end else
	}
	
	public boolean isColFull(int column, int board[][], int height)
	{
		if (board[height-1][column] == 9)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
