/*
 * CS4341 Artificial Intelligence Project 1 -- Connect-N
 * Rafael Angelo rlangelo@wpi.edu
 * Daniel Benson djbenson@wpi.edu
 * This file contains the minimax class and its helper methods. 
 * It takes a board and obtains the best move depending on the depth and current board state
 * This move is then return to the player class and passed to the referee to make our move
 */

package connectN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MiniMax {

	// Some Globals
	File file;
	int counter = 0;
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

	// This function checks to see if there is a place in which the player or opponent will win with
	// n pieces in a row vertially. It returns -1 if this is not the case or the number of the column if it is
	public int checkVertically(int[][] board, int width, int height, int player) throws IOException {
		//check each column, vertically
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		int max1=0;
		int max2=0;
		int opponentPlayer = (player == 1) ? 2 : 1;

		for(int j=0;j<width;j++) {
			max1=0;
			max2=0;
			for(int i=0;i<height;i++) {
				/*if(board[i][j]==opponentPlayer) {
					max1++;
					max2=0;
					if(max1==3) {
						if (i != height-1) {
							if (board[i+1][j] == 9) {
								return j;
							}
						}
					}

				}
				*/
				 if(board[i][j]==player) {
					max1=0;
					max2++;
					if(max2==3) {
						if (i != height-1){
							if (board[i+1][j] == 9){
								return j;
							}
						}
					}
				}
				else {
					max1=0;
					max2=0;
				}
			}
		} 
		return -1;
	}

	// This function checks to see if there is a place in which the player or opponent will win with
	// n pieces in a row horizontally. It returns -1 if this is not the case or the number of the column if it is
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
			for(int j=0;j<width;j++) {
			/*	if(board[i][j]==opponentPlayer) {
					max1++;
					max2=0;
					if(max1==3) {
						if (spaceLocation != -1) {
							if (i > 0){
								bw.write("Whaddup?!" + (i-1) + " " + spaceLocation + "\n");
								if (board[i-1][spaceLocation] != 9){
									return spaceLocation;
								}
							}
							else {
								return spaceLocation;
							}
						}
						if (j != width-1) {
							if (i > 0) {
								if (board[i-1][j+1] != 9) {
									if (board[i][j+1] == 9)	{
										return j+1;
									}
								}
								if (j > 2) {
									if (board[i-1][j-3] != 9) {
										if (board[i][j-3] == 9)	{
											return j-3;
										}
									}
								}
							}
							else if (i == 0) {
								if (board[i][j+1] == 9) {
									return j+1;
								}
								else if (j > 2)	{
									if (board[i][j-3] == 9)	{
										return j-3;
									}
								}
							}
						}
						else if (j == width-1) {
							if (i > 0) {
								if (board[i-1][j-3]!=9)	{
									if (board[i][j-3] == 9)	{
										return j-3;
									}
								}
							}
						}
					}
				}*/
				if(board[i][j]==player){
					max1=0;
					max2++;
					if(max2==3)	{
						if (spaceLocation != -1) {
							if (i > 0) {
								bw.write("Whaddup?!" + (i-1) + " " + spaceLocation + "\n");
								if (board[i-1][spaceLocation] != 9)	{
									return spaceLocation;
								}
							}
							else {
								return spaceLocation;
							}
						}
						if (j != width-1) {
							if (i > 0){
								if (board[i-1][j+1] != 9){
									if (board[i][j+1] == 9){
										return j+1;
									}
								}
								if (j > 2){
									if (board[i-1][j-3] != 9){
										if (board[i][j-3] == 9){
											return j-3;
										}
									}
								}
							}
							else if (i == 0){
								if (board[i][j+1] == 9){
									return j+1;
								}
								else if (j > 2)	{
									if (board[i][j-3] == 9)	{
										return j-3;
									}
								}
							}
						}
						else if (j == width-1){
							if (i > 0){
								if (board[i-1][j-3]!=9){
									if (board[i][j-3] == 9)	{
										return j-3;
									}
								}
							}
						}
					}
				}
				else if (((max1 == 1 || max1 == 2) && spaceLocation == -1) || ((max2 == 1 || max2 == 2) &&
						spaceLocation == -1)){
					spaceLocation = j;
				}
				else{
					max1=0;
					max2=0;
					spaceLocation = -1;
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
						if(max1==3){
							if (spaceLocation != -1){
								if (board[height-y-1][x+1] != 9){
									return x+1;
								}
							}
							if (board[height-1-y-1][x-1] == 9 && board[height-1-y-2][x-1] == 9){
								return x-1;
							}		
						}		 
					}
					else if(board[height-1-y][x]==opponentPlayer){
						max1=0;
						max2++;
						if(max2==3){
							if (board[height-y-1][x+1] != 9){
								return x+1;
							}
							if (board[height-1-y-1][x-1] == 9 && board[height-1-y-2][x-1] == 9){
								return x-1;
							}
						}
					}
					else if (((max2 == 1 || max2 == 2) && spaceLocation == -1) || (max1 == 1 || max1 == 2) && spaceLocation == -1){
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

	// This function is called by the player which calls the minimax function and 
	// and uses the eval function to find the best move for the current board state. It 
	// is then returned back to the player to move
	public Move getBestMove(int[][] board, int player, int width, int height, int depth) throws IOException{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		List<int[]> legalMoves = getLegalMoves(board, width, height);
		counter++;
		Move mov = new Move();
		mov.score = Integer.MIN_VALUE;
		int[] currMove = new int[10000];
		int currCol;
		int currRow;
		for (int i=0; i<legalMoves.size();i++){
			currMove = legalMoves.get(i);
			currCol = currMove[0];
			currRow = currMove[1];
			Move tempMove = minimax(board, depth, player, currCol, currRow, width, height);
			if (tempMove.score >= mov.score){
				mov.score = tempMove.score;
				mov.nextMove = tempMove.nextMove;
			}
		}
		if (mov.score == 0){
			Random rand = new Random();
			int max = (width/2)+1;
			int min = (width/2)-1;
			int nextNum = rand.nextInt((max-min)+1)+min;
			mov.nextMove = nextNum;
		}
		return mov;
	}

	// This function is called by the getBestMove function to find the best move. It recursively calls
	// to a given depth level stepping through the tree of possible board states. It then returns the move with 
	// the highest score
	public Move minimax(int[][] board, int depth, int player, int col, int row, int width, int height) throws IOException {
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		if (depth == 0){
			Move myMove = new Move();
			myMove.score = eval(board, width, height, player);
			myMove.nextMove = col;
			bw.close();
			return myMove;
		}
		int alpha = -100000;
		int oppositePlayer;
		if (player == 1){
			oppositePlayer = 2;
		}
		else{
			oppositePlayer = 1;
		}

		int[][] tempBoard = new int[100][100];
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				tempBoard[i][j] = board[i][j];
			}
		}
		tempBoard[row][col] = player;
		List<int[]> legalMoves = getLegalMoves(tempBoard, width, height);
		int[] currMove = new int[10000];
		int currCol;
		int currRow;
		Move tempMove = new Move();
		for (int i=0; i<legalMoves.size();i++){
			currMove = legalMoves.get(i);
			currCol = currMove[0];
			currRow = currMove[1];
			tempMove = minimax(tempBoard, depth-1, player, col, row, width, height);
			if (tempMove.score > alpha){
				alpha = tempMove.score;
				col = tempMove.nextMove;
			}
		}
		bw.close();
		return tempMove;
	}

	// This function takes ina board and returns a a list of possible legal moves to be evaluated
	public List<int[]> getLegalMoves(int[][] board, int width, int height){
		List<int[]> legalMoves = new ArrayList<int[]>();
		int[] colRowArray;
		for (int i=0;i<width;i++){
			if (board[height-1][i] == 9){
				colRowArray = new int[2];
				colRowArray[0] = i;
				colRowArray[1] = getFirstEmptyRow(board, width, height, i);
				legalMoves.add(colRowArray);
			}
		}
		return legalMoves;
	}

	// This function steps through a board and finds the first open row in the given column and 
	// returns the row number
	public int getFirstEmptyRow(int[][] board, int width, int height, int col){
		for(int i=0;i<height;i++){
			if (board[i][col]==9){
				return i;
			}
		}
		return -1;
	}

	// This function performs our heuristic evaluation on a given board state. It returns the
	// score back to the minimax function
	public int eval(int[][] board, int width, int height, int player){
		int fours = checkForFour(board, width, height, player);
		int opponentPlayer = 0;
		if (player == 1){
			opponentPlayer = 2;
		}
		else{
			opponentPlayer = 1;
		}
		if (fours > 0){
			return 100000;
		}
		int threes = checkForThree(board, width, height, player) * 1000;
		int twos = checkForTwo(board, width, height, player) * 100;
		int opponentThree = checkForThree(board, width, height, opponentPlayer) * 1000;
		int opponentTwo = checkForTwo(board, width, height, opponentPlayer) * 100;

		int finalScore = threes + twos - opponentThree - opponentTwo;

		int opponentFours = checkForFour(board, width, height, opponentPlayer);
		if (opponentFours > 0){
			return -100000;
		}
		else{
			return finalScore;
		}

	}

	// This function checks for two in a row of all varieties
	public int checkForTwo(int[][] board, int width, int height, int player){
		int horizontal = checkForTwoHorizontal(board, width, height, player);
		int vertical = checkForTwoVertical(board, width, height, player);
		int dRight = nDiagonalRight(2, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(2, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}

	// This function checks for three in a row of all varieties
	public int checkForThree(int[][] board, int width, int height, int player){
		int horizontal = checkForThreeHorizontal(board, width, height, player);
		int vertical = checkForThreeVertical(board, width, height, player);
		int dRight = nDiagonalRight(3, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(3, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}

	// This function checks for four in a row of all varieties
	public int checkForFour(int[][] board, int width, int height, int player){
		int horizontal = checkForFourHorizontal(board, width, height, player);
		int vertical = checkForFourVertical(board, width, height, player);
		int dRight = nDiagonalRight(4, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(4, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}

	// This function checks for four vertical pieces of the same player in a row
	public int checkForFourVertical(int[][] board, int width, int height, int player){
		int max;
		int num = 0;
		for(int i=0;i<width;i++){
			max = 0;
			for(int j=0;j<height;j++){
				if (board[i][j] == player){
					max++;
				}
				else{
					max = 0;
				}
				if (max == 4){
					num++;
				}
			}
		}
		return num;
	}

	// This function checks for four pieces in a row horizontally of the same player 
	public int checkForFourHorizontal(int[][] board, int width, int height, int player){
		int max;
		int num = 0;
		for(int i=0;i<height;i++){
			max = 0;
			for(int j=0;j<width;j++){
				if (board[i][j] == player){
					max++;
				}
				else{
					max = 0;
				}
				if (max == 4){
					num++;
				}
			}
		}
		return num;
	}

	// This function checks for three pieces in a row vertically of the same player
	public int checkForThreeVertical(int[][] board, int width, int height, int player){
		int num = 0;
		int max;
		int spaces = 0;
		for(int i=0;i<width;i++){
			max = 0;
			for(int j=0;j<height;j++){
				if(board[i][j] == player && spaces == 1){
					max++;
					spaces = 0;
				}
				else if(board[i][j] == player){
					max++;
				}
				else if(board[i][j] == 9 && spaces == 0){
					spaces++;
				}
				else{
					max = 0;
				}
				if (max == 3){
					num++;
					max = 0;
				}
			}
		}
		return num;
	}

	// This function checks for three pieces in a row horizonatally of the same player
	public int checkForThreeHorizontal(int[][] board, int width, int height, int player){
		int num = 0;
		int max;
		int spaces = 0;
		for(int i=0;i<height;i++){
			max = 0;
			for(int j=0;j<width;j++){
				if (board[i][j] == player && spaces == 1){
					max++;
					spaces = 0;
				}
				else if (board[i][j] == player){
					max++;
				}
				else if (board[i][j] == 9 && spaces == 0){
					spaces++;
				}
				else{
					max = 0;
				}
				if (max == 3){
					num++;
					max = 0;
				}
			}
		}
		return num;
	}

	// This function checks for two pieces in a row horizontally of the same player
	public int checkForTwoHorizontal(int[][] board, int width, int height, int player){
		int num = 0;
		int max;
		for(int i=0;i<height;i++){
			max = 0;
			for (int j=0;j<width;j++){
				if(board[i][j] == player){
					max++;
				}
				else{
					max = 0;
				}
				if (max == 2){
					num++;
					max = 0;
				}
			}
		}
		return num;
	}

	// This function checks for two pieces in a row vertically of the same player
	public int checkForTwoVertical(int[][] board, int width, int height, int player){
		int num = 0;
		int max;
		for(int i=0;i<width;i++){
			max = 0;
			for (int j=0;j<height;j++){
				if(board[i][j] == player){
					max++;
				}
				else{
					max = 0;
				}
				if (max == 2){
					num++;
					max = 0;
				}
			}
		}
		return num;
	}

	// This function checks for a given number of pieces in row diagonally of the same player
	int nDiagonalRight(int n, int[][] board, int playerNumber, int width, int height, int connectN){
		int max = 0;
		int num = 0;
		int opponentNumber = (playerNumber==1) ? 2 : 1;
		for(int i=0; i < (width-connectN); i++){
			for (int j=0; j<(height-connectN); j++){
				if (n == 2) {
					if(board[i][j] == playerNumber &&
							board[i+1][j+1] == playerNumber){
						num++;
					}
				}
				if (n == 3){
					if(board[i][j] == playerNumber &&
							board[i+1][j+1] == playerNumber &&
							board[i+2][j+2] == playerNumber){
						num++;
					}
				}
				if(n == 4){
					if(board[i][j] == playerNumber &&
							board[i+1][j+1] == playerNumber &&
							board[i+2][j+2] == playerNumber &&
							board[i+3][j+3] == playerNumber){
						num++;
					}
				}
			}
		}
		return num;
	}

	// This function checks for n pieces in a row diagonally of the same player
	int nDiagonalLeft(int n, int[][] board, int playerNumber, int height, int width, int connectN){
		int max = 0;
		int num = 0;
		int opponentNumber = (playerNumber==1) ? 2 : 1;
		for(int i=width; i < (width-connectN); i--){
			for (int j=0; j<(height-connectN); j++){
				if (n == 2) {
					if(board[i][j] == playerNumber &&
							board[i-1][j+1] == playerNumber){
						num++;
					}
				}
				if (n == 3){
					if(board[i][j] == playerNumber &&
							board[i-1][j+1] == playerNumber &&
							board[i-2][j+2] == playerNumber){
						num++;
					}
				}
				if(n == 4){
					if(board[i][j] == playerNumber &&
							board[i-1][j+1] == playerNumber &&
							board[i-2][j+2] == playerNumber &&
							board[i-3][j+3] == playerNumber){
						num++;
					}
				}
			}
		}
		return num;
	}

	// This function checks whether a given column is full and returns a boolean true or false
	public boolean isColFull(int column, int board[][], int height)	{
		if (board[height-1][column] == 9){
			return false;
		}
		else{
			return true;
		}
	}
}
