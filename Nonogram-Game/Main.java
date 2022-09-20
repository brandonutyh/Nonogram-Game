import java.util.Scanner;
import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to Ricky and Brandon's Nonogram Game!\nPress Enter to continue.");
    
    String start = sc.nextLine();
    
    
    if(start!=null){
      Game easyGame = new Game(sc, 1, 3);
      easyGame.play();
    }

    
  }
}