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
	
	public Move getBestMove(int[][] board, int player, int width, int height)
	{
		List<int[]> legalMoves = getLegalMoves(board, width, height);
		int[] currMove;
		int currCol;
		int currRow;
		Move mov = new Move();
		mov.score = Integer.MIN_VALUE;
		for (int i=0; i<legalMoves.size();i++)
		{
			currMove = legalMoves.get(i);
			currCol = currMove[0];
			currRow = currMove[1];
			Move tempMove = minimax(board, 5, player, currCol, currRow, width, height);
			if (tempMove.score > mov.score)
			{
				mov.score = tempMove.score;
				mov.nextMove = tempMove.nextMove;
			}
		}
		return mov;
	}
	
	
	public Move minimax(int[][] board, int depth, int player, int col, int row, int width, int height)
	{
		if (depth == 0)
		{
			Move myMove = new Move();
			//myMove.score = eval(board, width, height, player);
			myMove.nextMove = col;
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
		Move tempMove = getBestMove(board, oppositePlayer, width, height);
		if (tempMove.score > alpha)
		{
			alpha = tempMove.score;
			col = tempMove.nextMove;
		}
		return tempMove;
	}
	
	
	
	
	public List<int[]> getLegalMoves(int[][] board, int width, int height)
	{
		List<int[]> legalMoves = new ArrayList<int[]>();
		int[] colRowArray;
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				if (board[i][j] == 9)
				{
					colRowArray = new int[2];
					colRowArray[0] = i;
					colRowArray[1] = j;
					legalMoves.add(colRowArray);
				}
			}
		}
		return legalMoves;
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
