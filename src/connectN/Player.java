package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import refereePack.Board;

public class Player {
	
 String playerName="aa";
 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
 boolean first_move=false;
 
 static int boardHeight, boardWidth, N, playerNumber;
 
 int board[][];
 
 public void processInput() throws IOException{
 
  String s=input.readLine();
  List<String> ls=Arrays.asList(s.split(" "));
  if(ls.size()==5){          //ls contains game info
   boardWidth = Integer.parseInt(ls.get(1));
   boardHeight = Integer.parseInt(ls.get(0));
   playerNumber = Integer.parseInt(ls.get(3));
   N = Integer.parseInt(ls.get(2));
   System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
   createBoard(boardWidth, boardHeight);
  }
  else if(ls.size()==2){ 
	 System.out.println(ls.get(0)+" "+ls.get(1));
	 addToBoard(ls.get(0));
  }
  else if(ls.size()==1){
   System.out.println("game over!!!");
  }
  else if(ls.size()==4){  //player1: aa player2: bb
   //TODO combine this information with game information to decide who is the first player
  }
  else 
   System.out.println("not what I want");  }
  
 
 public void addToBoard(String column) throws IOException {
		int col = Integer.parseInt(column);
		for (int i=0;i<boardHeight;i++)
		{
			if(board[i][col]==9)
			{
				if (playerNumber == 1)
				{
					board[i][col] = 2;
				}
				else
				{
					board[i][col] = 1;
				}
			}
		}
	}

public void createBoard(int width, int height)
{
	   for (int i=0;i<width;i++)
	   {
		   for (int j=0;j<height;j++)
		   {
			   board[i][j] = 9;
		   }
	   }
}
 
 public static void main(String[] args) throws IOException {
  Player rp=new Player();
  System.out.println(rp.playerName);
  System.out.flush();
  rp.processInput();
  rp.processInput();
  rp.processInput();
  rp.processInput();
  rp.processInput();
  rp.processInput();
  rp.processInput();
 }
 
}
 

