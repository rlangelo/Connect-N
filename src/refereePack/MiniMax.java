package refereePack;

import connectN.Move;


public class MiniMax {

	 private static int scoreBoard(int[][] board, int width, int height)
	 {
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
		 
		 if (counter[0] != 0) {
	            return 1;
	        } else if (counter[8] != 0) {
	            return 2;
	        } else {
	            return counter[5] + 2 * counter[6] + 5 * counter[7]
	                    - counter[3] - 2 * counter[2] - 5 * counter[1];
	        }

	 }
	 
	 public Move abMinimax(boolean maxOrMin, int player, int depth, int[][] board, int width, int height)
	 {
		 if (depth == 0)
		 {
			 Move mov = new Move();
			 mov.score = scoreBoard(board, width, height);
			 mov.nextMove = -1;
			 return mov;
		 }
		 else
		 {
			 int bestScore = maxOrMin ? 1 : 2;
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
				 }
				 else
				 {
					 isRowFull = true;
				 }
				 if (isRowFull)
				 {
					 continue;
				 }
				 int score = scoreBoard(board, width, height);
				 if (score == (maxOrMin ? 1 : 2))
				 {
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
			 Move mov = new Move();
			 mov.score = bestScore;
			 mov.nextMove = bestMove;
			 return mov;
		 }
		 
		 
	 }
	
}
