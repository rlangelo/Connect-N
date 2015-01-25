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
		file = new File("./Logfile2.txt");
		if (!file.exists())
		{
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
	}
	 public int scoreBoard(int[][] board, int width, int height, int player) throws IOException
	 {
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				if (board[i][j] == 9)
				{
					board[i][j] = 0;
				}
			}
		}
		 int[] counter = {0, 0, 0, 0, 0, 0, 0, 0, 0};

		 //horizontal
		 for (int y = 0; y < height; y++) {
	            int score = board[y][0] + board[y][1] + board[y][2];
	            for (int x = 3; x < width; x++) {
	                score += board[y][x];
	                counter[score + 4]++;
	                score -= board[y][x - 3];

	            }
	      }
		 //vertical
		 for (int x = 0; x < width; x++) {
	            int score = board[0][x] + board[1][x] + board[2][x];
	            for (int y = 3; y < height; y++) {
	                score += board[y][x];
	                counter[score + 4]++;
	                score -= board[y - 3][x];
	            }
	        }
		 //down-right (and up-left) diagonals
		 for (int y = 0; y < height - 3; y++) {
	            for (int x = 0; x < width - 3; x++) {
	                int score = 0;
	                for (int idx = 0; idx < 4; idx++) {
	                    score += board[y+idx][x+idx];
	                }
	                counter[score + 4]++;
	            }
	            
	        }
		 //up-right (and down-left) diagonals
		 for (int y = 3; y < height; y++) {
	            for (int x = 0; x < width - 3; x++) {
	                int score = 0;
	                for (int idx = 0; idx < 4; idx++) {
	                    score += board[y-idx][x+idx];
	                }
	                counter[score + 4]++;
	            }
		 }
		 bw.write(counter[0] + " " + counter[1] +" " + counter[2] +" " + counter[3] +" " + counter[4] +" " + counter[5] +
				 " " + counter[6] +" " + counter[7] +" " + counter[8] +"\n");
		 if (counter[0] != 0) {
			 	bw.write("1\n");
			 	bw.close();
	            return 1;
	        } else if (counter[8] != 0) {
	        	bw.write("2\n");
	        	bw.close();
	            return 2;
	        } else {
	            int total = counter[5] + 2 * counter[6] + 5 * counter[7]
	                    - counter[3] - 2 * counter[2] - 5 * counter[1];
	            bw.write(total + "\n");
	            bw.close();
	            return total;
	        }

	 }
	 
	 public Move abMinimax(boolean maxOrMin, int player, int depth, int[][] board, int width, int height) throws IOException
	 {
		 FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		 BufferedWriter bw = new BufferedWriter(fw);
		 for (int i=0;i<height;i++)
		 {
			 int block = checkHorizontal(player, i, width, board);
			 if (block != -1)
			 {
				 
				 Move mov = new Move();
				 mov.nextMove = block;
				 mov.score = 0;
				 bw.close();
				 return mov;
			 }
		 }
		 for (int i=0;i<width;i++)
		 {
			 int block = checkVertical(player, i, height, board);
			 if (block != -1)
			 {
				 Move mov = new Move();
				 mov.nextMove = block;
				 mov.score = 0;
				 bw.close();
				 return mov;
			 }
		 }
		 if (depth == 0)
		 {
			 bw.write("It's in depth 0");
			 bw.close();
			 Move mov = new Move();
			 mov.score = scoreBoard(board, width, height, player);
			 mov.nextMove = -1;
			 return mov;
		 }
		 else
		 {
			 //bw.write("It's in the else!\n");
			 int bestScore = 0;
			 if (maxOrMin)
			 {
				 bestScore = player;
			 }
			 else{
				 if (player == 1)
				 {
					 bestScore = 2;
				 }
				 else{
					 bestScore = 1;
				 }
			 }
			 if (player == 2)
			 {
				 player -= 1;
			 }
			// bw.write("this is the best score: " + bestScore + "\n");
			 int bestMove = -1;
			 for (int col = 0; col < width; col++)
			 {
				 if (board[0][col] != 9)
				 {
					 continue;
				 }
				 boolean isRowFull;
				 if (board[height-1][col] == 9)
				 {
					 isRowFull = false;
			//		 bw.write("Row is not full!\n");
				 }
				 else
				 {
					 isRowFull = true;
			//		 bw.write("Row is full!\n");
				 }
				 if (isRowFull)
				 {
					 continue;
				 }
			//	 bw.write("Let's evaluate the score!\n");
				 int score = scoreBoard(board, width, height, player);
				// bw.write("This is the eval score: " + score + "\n");
				 //bw.close();
				 if (score == (maxOrMin ? 1 : 2))
				 {
				//	 bw.write("is it in here?\n");
					 bestMove = col;
					 bestScore = score;
					 break;
				 }
				 int moveInner, scoreInner;
				 Move inner;
				 if (depth > 1)
				 {
					 if (player == 1)
					 {
						inner = abMinimax(!maxOrMin, 2, depth - 1, board, width, height);
					 }
					 else
					 {
						 inner = abMinimax(!maxOrMin, 1, depth - 1, board, width, height);
					 }
					 moveInner = inner.nextMove;
					 scoreInner = inner.score;
					// bw.write("What would be the column: " + moveInner + "\n");
				 }
				 else
				 {
					 moveInner = -1;
					 scoreInner = score;
					 
				 }
				 if (maxOrMin)
				 {
					 if (scoreInner >= bestScore)
					 {
						 bestScore = scoreInner;
						 bestMove = col;
					 }
				 }
				 else
				 {
					 if (scoreInner <= bestScore)
					  {
						 bestScore = scoreInner;
						 bestMove = col;
					  }
				 }
			 }
		 
			 Random rand = new Random();
			 int randomNum = rand.nextInt((width-1) + 1);
			 int randomNum2 = rand.nextInt(3 + 1);
			 Move mov = new Move();
			 mov.score = bestScore;
			 mov.nextMove = bestMove + randomNum;
			 if (mov.nextMove == -1)
			 {
				 mov.nextMove = randomNum2;
			 }
			// bw.write("This is the next column we will play: " + mov.nextMove + " and this is the depth we are: " + depth + "\n");
			 bw.close();
			 return mov;
			 
		 }
	 }
			 
		 
	 
	 
	 public int checkHorizontal(int player, int row, int width, int[][] board)
	 {
		 for (int i=0; i<width;i++)
		 {
			 if (board[row][i] == player)
			 {
				 if (i < width-3)
				 {
					 if (board[row][i++] == player && board[row][i+2] == player)
					 {
						 if (board[row][i+3] == 9)
						 {
							 return i+3;
						 }
						 else if (i > 0 && board[row][i-1] == 9)
						 {
							 return i-1;
						 }
						 else
						 {
							 return -1;
						 }
					 }
				 }
				 else if (i == width-3)
				 {
					 if (board[row][i++] == player && board[row][i+2] == player)
					 {
						 if (board[row][i-1] == 9)
						 {
							 return i-1;
						 }
					 }
				 }
			 }
		 }

		 return -1;
	 }
	 
	 public int checkVertical(int player, int col, int height, int[][] board)
	 {
		 for (int i=0;i<height;i++)
		 {
			 if (board[i][col] == player)
			 {
				 if (i < height-3)
				 {
					 if (board[i++][col] == player && board[i+2][col] == player)
					 {
						 if (board[i+3][col] == 9)
						 {
							 return i+3;
						 }
						 else if (i>0 && board[i-1][col] == 9)
						 {
							 return i-1;
						 }
						 else
						 {
							return -1;
						 }
					 }
				 }
				 else if (i == height-3)
				 {
					 if (board[i++][col] == player && board[i+2][col] == player)
					 {
						 if (board[i-1][col] == 9)
						 {
							 return i-1;
						 }
					 }
				
				 }
			 }
		 }
		 return -1;
	 }
}
