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
 
 int board[][] = new int[10][10];
 
 public void processInput() throws IOException{
  String s=input.readLine();
  List<String> ls=Arrays.asList(s.split(" "));
  if(ls.size()==5){          //ls contains game info
   boardWidth = Integer.parseInt(ls.get(1)); //Save board width for this game
   boardHeight = Integer.parseInt(ls.get(0)); //Same for board height
	

   if (Integer.parseInt(ls.get(3)) == playerNumber)
   {
	   first_move = true;
	   System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
   }
   else
   {
	   System.out.println("0" +" "+ "1");  //first move
   }
   createBoard(boardWidth, boardHeight);
  }
  else if(ls.size()==2){ 
	 System.out.println(ls.get(0)+" "+ls.get(1));
	 //addToBoard(ls.get(0));
  }
  else if(ls.size()==1){
   System.out.println("game over!!!");
  }
  else if(ls.size()==4){  //player1: aa player2: bb
   if (ls.get(1).equals(playerName))
   {
	   playerNumber = 1;
   }
   else
   {
	   playerNumber = 2;
   }
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

public void createBoard(int width, int height) throws IOException
{
	   File file = new File("/Users/rafaelangelo/Documents/workspace/Connect-N/bin/errorLog.txt");
	   

	   for (int i=0;i<width;i++)
	   {
		   for (int j=0;j<height;j++)
		   {
			   board[i][j] = 9;
		   }
	   }
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Board was Created!");
		bw.close();
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
 

