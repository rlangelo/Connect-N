package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import refereePack.Board;

public class Player {
	
 // Variables for our Player
 String playerName="aa";
 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
 boolean first_move = false;
 static int boardHeight, boardWidth, N, playerNumber;
 int board[][] = new int[10][10];
 int numMoves = 0;
 
 // File to write to for debugging
 File file = new File("errorLog.txt");
 // if file doesnt exists, then create it
	if (!file.exists()) {
		file.createNewFile();
	}
 
 /* 
  * This function is the function that process the input from the messages sent by the referee
  * it also creates the local version of the game board, and sends our moves to the referee
  */
 public void processInput() throws IOException{
  String s=input.readLine();
  List<String> ls=Arrays.asList(s.split(" "));
  
  // Parse the messages from the Array
  // This parses the game info and creates our local board
  if(ls.size()==5){
   // Record Game's specific board height and width
   boardWidth = Integer.parseInt(ls.get(1)); 
   boardHeight = Integer.parseInt(ls.get(0)); 

   if (Integer.parseInt(ls.get(3)) == playerNumber){
	   first_move = true;
	   System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
   }
   else{
	   System.out.println("0" +" "+ "1");  //first move
   }
   createBoard(boardWidth, boardHeight);
  }
  
  // We have been sent a move from the other player --  Add to baord and make a move
  else if(ls.size()==2){ 
	 System.out.println(ls.get(0)+" "+ls.get(1));
	 addToBoard(ls.get(0));
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
  else 
   System.out.println("not what I want");  
 }
  
 // Add a move to our local version of the game board
 public void addToBoard(String column) throws IOException {
		int col = Integer.parseInt(column);
		for (int i=0;i<boardHeight;i++)
			if(board[i][col]==9){
				if (playerNumber == 1){
					board[i][col] = 2;
				}
				else{
					board[i][col] = 1;
				}
			}
		}	
	}

 // Create the local version of the gane board
public void createBoard(int width, int height) throws IOException {
   for (int i=0; i<width; i++){
	   for (int j=0; j<height; j++){
		   board[i][j] = 9;
	   }
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
  while (true){
	  rp.processInput(); 
  }
 }
 
}
