package connectN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MiniMax {

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
				if(board[i][j]==opponentPlayer){
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
				else if(board[i][j]==player){
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
				 if(board[i][j]==opponentPlayer){
					max1++;
					max2=0;
					//bw.write("What about Opponent here?!\n");
					if(max1==3)
					{
						//bw.write("Opponent has 3 of a kind!\n");
						if (spaceLocation != -1)
						{
							if (i > 0)
							{
								bw.write("Whaddup?!" + (i-1) + " " + spaceLocation + "\n");
								if (board[i-1][spaceLocation] != 9)
								{
									bw.write("Blocking space Horizontal\n");
									bw.close();
									return spaceLocation;
								}
							}
							else {
								bw.write("Blocking space Horizontally! " + spaceLocation);
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
								if (j > 2)
								{
									if (board[i-1][j-3] != 9)
									{
										if (board[i][j-3] == 9)
										{
											bw.write("Also block opponent! Horizontal\n");
											bw.close();
											return j-3;
										}
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
						}
						else if (j == width-1)
						{
							if (i > 0)
							{
								if (board[i-1][j-3]!=9)
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
					}

				}


					else if(board[i][j]==player){
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
									bw.write("Whaddup?!" + (i-1) + " " + spaceLocation + "\n");
									if (board[i-1][spaceLocation] != 9)
									{
										bw.write("Blocking space Horizontal\n");
										bw.close();
										return spaceLocation;
									}
								}
								else {
									bw.write("Blocking space Horizontally! " + spaceLocation);
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
									if (j > 2)
									{
										if (board[i-1][j-3] != 9)
										{
											if (board[i][j-3] == 9)
											{
												bw.write("Also block opponent! Horizontal\n");
												bw.close();
												return j-3;
											}
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
							}
							else if (j == width-1)
							{
								if (i > 0)
								{
									if (board[i-1][j-3]!=9)
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
	
	public Move getBestMove(int[][] board, int player, int width, int height, int depth) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		List<int[]> legalMoves = getLegalMoves(board, width, height);
		counter++;
		bw.write(counter + ") there are: " + legalMoves.size() + " legal moves.\n");
		//bw.close();
		Move mov = new Move();
		mov.score = Integer.MIN_VALUE;
		//bw.write(mov.score);
		int[] currMove = new int[10000];
		int currCol;
		int currRow;
		for (int i=0; i<legalMoves.size();i++)
		{
			currMove = legalMoves.get(i);
			currCol = currMove[0];
			currRow = currMove[1];
			Move tempMove = minimax(board, depth, player, currCol, currRow, width, height);
			if (tempMove.score >= mov.score)
			{
				mov.score = tempMove.score;
				mov.nextMove = tempMove.nextMove;
			}
		}
		if (mov.score == 0)
		{
			Random rand = new Random();
			int max = (width/2)+1;
			int min = (width/2)-1;
			int nextNum = rand.nextInt((max-min)+1)+min;
			mov.nextMove = nextNum;
			bw.write(mov.nextMove + "\n");
			bw.close();
		}
		return mov;
	}
	
	
	public Move minimax(int[][] board, int depth, int player, int col, int row, int width, int height) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		//bw.write("It's in the minimax! Depth: " + depth + "\n");
		if (depth == 0)
		{
			Move myMove = new Move();
			myMove.score = eval(board, width, height, player);
			myMove.nextMove = col;
		//	bw.write(myMove.nextMove + "\n");
			bw.close();
			return myMove;
		}
		int alpha = -100000;
		int oppositePlayer;
		if (player == 1)
		{
			oppositePlayer = 2;
		}
		else
		{
			oppositePlayer = 1;
		}
		
		int[][] tempBoard = new int[100][100];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				tempBoard[i][j] = board[i][j];
			}
		}
		tempBoard[row][col] = player;
		List<int[]> legalMoves = getLegalMoves(tempBoard, width, height);
		int[] currMove = new int[10000];
		int currCol;
		int currRow;
		Move tempMove = new Move();
		for (int i=0; i<legalMoves.size();i++)
		{
			currMove = legalMoves.get(i);
			currCol = currMove[0];
			currRow = currMove[1];
			tempMove = minimax(tempBoard, depth-1, player, col, row, width, height);
			if (tempMove.score > alpha)
			{
				alpha = tempMove.score;
				col = tempMove.nextMove;
			}
		}
		bw.close();
		return tempMove;
	}
	
	public List<int[]> getLegalMoves(int[][] board, int width, int height)
	{
		List<int[]> legalMoves = new ArrayList<int[]>();
		int[] colRowArray;
		for (int i=0;i<width;i++)
		{
					if (board[height-1][i] == 9)
					{
						colRowArray = new int[2];
						colRowArray[0] = i;
						colRowArray[1] = getFirstEmptyRow(board, width, height, i);
						legalMoves.add(colRowArray);
					}
			
		}
		return legalMoves;
	}
	
	public int getFirstEmptyRow(int[][] board, int width, int height, int col)
	{
		for(int i=0;i<height;i++)
		{
			if (board[i][col]==9)
			{
				return i;
			}
		}
		return -1;
	}
	
	public int eval(int[][] board, int width, int height, int player)
	{
		int fours = checkForFour(board, width, height, player);
		int opponentPlayer = 0;
		if (player == 1)
		{
			opponentPlayer = 2;
		}
		else
		{
			opponentPlayer = 1;
		}
		if (fours > 0)
		{
			return 100000;
		}
		int threes = checkForThree(board, width, height, player) * 1000;
		int twos = checkForTwo(board, width, height, player) * 100;
		int opponentThree = checkForThree(board, width, height, opponentPlayer) * 1000;
		int opponentTwo = checkForTwo(board, width, height, opponentPlayer) * 100;
		
		int finalScore = threes + twos - opponentThree - opponentTwo;
		
		int opponentFours = checkForFour(board, width, height, opponentPlayer);
		if (opponentFours > 0)
		{
			return -100000;
		}
		else
		{
			return finalScore;
		}
		
	}
	
	public int checkForTwo(int[][] board, int width, int height, int player)
	{
		int horizontal = checkForTwoHorizontal(board, width, height, player);
		int vertical = checkForTwoVertical(board, width, height, player);
		int dRight = nDiagonalRight(2, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(2, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}
	
	public int checkForThree(int[][] board, int width, int height, int player)
	{
		int horizontal = checkForThreeHorizontal(board, width, height, player);
		int vertical = checkForThreeVertical(board, width, height, player);
		int dRight = nDiagonalRight(3, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(3, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}
	
	public int checkForFour(int[][] board, int width, int height, int player)
	{
		int horizontal = checkForFourHorizontal(board, width, height, player);
		int vertical = checkForFourVertical(board, width, height, player);
		int dRight = nDiagonalRight(4, board, player, width, height, 4);
		int dLeft = nDiagonalLeft(4, board, player, height, width, 4);
		return horizontal + vertical + dRight + dLeft;
	}
	
	public int checkForFourVertical(int[][] board, int width, int height, int player)
	{
		int max;
		int num = 0;
		for(int i=0;i<width;i++)
		{
			max = 0;
			for(int j=0;j<height;j++)
			{
				if (board[i][j] == player)
				{
					max++;
				}
				else
				{
					max = 0;
				}
				if (max == 4)
				{
					num++;
				}
			}
		}
		return num;
	}
	
	public int checkForFourHorizontal(int[][] board, int width, int height, int player)
	{
		int max;
		int num = 0;
		for(int i=0;i<height;i++)
		{
			max = 0;
			for(int j=0;j<width;j++)
			{
				if (board[i][j] == player)
				{
					max++;
				}
				else
				{
					max = 0;
				}
				if (max == 4)
				{
					num++;
				}
			}
		}
		return num;
	}
	
	public int checkForThreeVertical(int[][] board, int width, int height, int player)
	{
		int num = 0;
		int max;
		int spaces = 0;
		for(int i=0;i<width;i++)
		{
			max = 0;
			for(int j=0;j<height;j++)
			{
				if(board[i][j] == player && spaces == 1)
				{
					max++;
					spaces = 0;
				}
				else if(board[i][j] == player)
				{
					max++;
				}
				else if(board[i][j] == 9 && spaces == 0)
				{
					spaces++;
				}
				else
				{
					max = 0;
				}
				if (max == 3)
				{
					num++;
					max = 0;
				}
			}
		}
		return num;
	}
	
	public int checkForThreeHorizontal(int[][] board, int width, int height, int player)
	{
		int num = 0;
		int max;
		int spaces = 0;
		for(int i=0;i<height;i++)
		{
			max = 0;
			for(int j=0;j<width;j++)
			{
				if (board[i][j] == player && spaces == 1)
				{
					max++;
					spaces = 0;
				}
				else if (board[i][j] == player)
				{
					max++;
				}
				else if (board[i][j] == 9 && spaces == 0)
				{
					spaces++;
				}
				else
				{
					max = 0;
				}
				if (max == 3)
				{
					num++;
					max = 0;
				}
			}
			
		}
		return num;
	}
	
	public int checkForTwoHorizontal(int[][] board, int width, int height, int player)
	{
		int num = 0;
		int max;
		for(int i=0;i<height;i++)
		{
			max = 0;
			for (int j=0;j<width;j++)
			{
				if(board[i][j] == player)
				{
					max++;
				}
				else
				{
					max = 0;
				}
				if (max == 2)
				{
					num++;
					max = 0;
				}
			}
		}
		return num;
	}
	
	public int checkForTwoVertical(int[][] board, int width, int height, int player)
	{
		int num = 0;
		int max;
		for(int i=0;i<width;i++)
		{
			max = 0;
			for (int j=0;j<height;j++)
			{
				if(board[i][j] == player)
				{
					max++;
				}
				else
				{
					max = 0;
				}
				if (max == 2)
				{
					num++;
					max = 0;
				}
			}
		}
		return num;
	}
	
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
