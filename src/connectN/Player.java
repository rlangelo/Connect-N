package connectN;
import java.io.*;
import java.util.Arrays;
import java.util.List;




public class Player {
	
 Move move = new Move();
 String playerName="aa";
 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
 boolean first_move=false;
 
 static int boardHeight, boardWidth;
 
 public void processInput() throws IOException{
 
  String s=input.readLine();
  List<String> ls=Arrays.asList(s.split(" "));
  if(ls.size()==2){
   System.out.println(move.getMove(ls));
  }
  else if(ls.size()==1){
   System.out.println("game over!!!");
  }
  else if(ls.size()==5){          //ls contains game info
	  boardWidth = Integer.parseInt(ls.get(1));
	  
   System.out.println(Integer.toString(boardWidth/2) +" "+ "1");  //first move
  }
  else if(ls.size()==4){  //player1: aa player2: bb
   //TODO combine this information with game information to decide who is the first player
  }
  else
   System.out.println("not what I want");  }
 
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

