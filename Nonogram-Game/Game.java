import java.util.Scanner;
public class Game
{

  private int diff;
  private int lives=3;
  private Scanner scanner = new Scanner(System.in);
  private boolean play;
  private int row;
  private int col;
  private int grid;
  private int re;
  
  
  
  // String box = Character.toString((char));
  private Board[][] playBoard;
  private Board[][] actualBoard;
  private Board [][] generalBoard;

  private Board[][] skullBoard;
  private Board[][] skull2Board;
  private Board[][] general2Board;

  public Game(Scanner sc, int d, int l)
  {
    re =1;
    scanner = sc;
    grid = 1;
    diff = d;
    lives = l;
    play = true;
    row = 0; 
    col = 0;
    playBoard = new Board[8][8];
    actualBoard = new Board[8][8];
    generalBoard = new Board[8][8];

    skullBoard = new Board[8][8];
    skull2Board = new Board[8][8];
    general2Board = new Board[8][8];
    
    for(int r = 0; r < 8; r++)
    {
      for(int c = 0; c < 8; c++)
      {
        playBoard[r][c] = new Board();
        actualBoard[r][c] = new Board();

        skullBoard[r][c] = new Board();
        skull2Board[r][c] = new Board();

        generalBoard[r][c] = new Board();
        general2Board[r][c] = new Board();
      } 
    }
  }




  public void play()
  {
    System.out.println("Nonogram is a game where you can create your own pixel art using clues from a grid. The grid shown below has numbers on the sides, meaning that there are that many filled squares in that row/column. Give it a try!");
    System.out.println("The play board will display the rows, columns, and the shape you choose to place. The general board will display the number clues! \nYou also have 3 lives!\n");
    
    System.out.println("Pick 1 (Creeper Board) or 2 (Bevo Board)");
    grid = validate(1, 2);

    System.out.println("This is the play board: ");
    if(grid==1){
      print();
      createActualBoard();
    }
    else{
      printSkull();
      createSkullBoard();
    }

    
    while(play)
    {
      System.out.print("Choose a row from 0 to " + playBoard[0].length + ": ");
      row = validate(0, playBoard[0].length);
      System.out.print("Choose a col from 0 to " + playBoard[0].length + ": ");
      col = validate(0, playBoard[0].length);
      if(grid==1)
      {
        checkTaken(row, col);
        compareCreeperBoard(row, col);
        print();
      }
      else if(grid==2)
      {
        checkSkullTaken(row, col);
        compareSkullBoard(row, col);
        printSkull();
      }
      System.out.println("Lives: " + lives);
    }

    if(lives>0)
    {
      System.out.println("You figured out the picture! Congrats!");
      replay();
    }
    else if(lives==0){
      System.out.println("You ran out of lives! Type 1 to try again!");
      replay();
    }

  }

    public void print(){
    System.out.println("    0   1   2   3   4   5   6   7");
    System.out.println("  - - - - - - - - - - - - - - - - -");
    int rCount = 0;
    int cCount = 0;
    for(int i = 0; i < 8; i++){
      if(rCount == 1){
        rCount = 0;
        System.out.println("  - - - - - - - - - - - - - - - - -");
      }
      System.out.print(i + " ");
      if(i == 0){
        System.out.print("| ");
      }
      for(int j = 0; j < 8; j++){
        if(cCount == 1){
          cCount = 0;
          System.out.print("| ");
        }
        System.out.print(playBoard[i][j] + " ");
        cCount++;
      }
      System.out.println("| ");
      rCount++;
    }
    System.out.println("  - - - - - - - - - - - - - - - - -");

    System.out.println("general board:");
    System.out.println("        2   2 2");
    System.out.println("    0 2 3 3 3 3 2 0");
    System.out.println("  - - - - - - - - - -");
    for(int r = 0; r < 8; r++){ 
      for(int c = 0; c < 8; c++){
        if(c == 0){
        System.out.print(r + " | ");
        }
        if(c==0)
        {
          System.out.print(generalBoard[r][c] + " ");
        }
        else
        {
          System.out.print(generalBoard[r][c] + " ");
        }
      }
      if(r==0)
        System.out.println("| 0");
      else if(r==1)
        System.out.println("| 0");
      else if(r==2)
        System.out.println("| 2 2");
      else if(r==3)
        System.out.println("| 2 2");
      else if(r==4)
        System.out.println("| 2");
      else if(r==5)
        System.out.println("| 4");
      else if(r==6)
        System.out.println("| 4");
      else
        System.out.println("| 1 1");
    }
    System.out.println("  - - - - - - - - - -");
    System.out.println("    0 1 2 3 4 5 6 7");

  }

  public void printSkull()
  {
    System.out.println("    0   1   2   3   4   5   6   7");
    System.out.println("  - - - - - - - - - - - - - - - - -");
    int rCount = 0;
    int cCount = 0;
    for(int i = 0; i < 8; i++){
      if(rCount == 1){
        rCount = 0;
        System.out.println("  - - - - - - - - - - - - - - - - -");
      }
      System.out.print(i + " ");
      if(i == 0){
        System.out.print("| ");
      }
      for(int j = 0; j < 8; j++){
        if(cCount == 1){
          cCount = 0;
          System.out.print("| ");
        }
        System.out.print(skullBoard[i][j] + " ");
        cCount++;
      }
      System.out.println("| ");
      rCount++;
    }
    System.out.println("  - - - - - - - - - - - - - - - - -");

    System.out.println("General Board:");
    System.out.println("      1         1");
    System.out.println("    1 2 4 6 6 4 2 1");
    System.out.println("  - - - - - - - - - -");
    for(int r = 0; r < 8; r++){ 
      for(int c = 0; c < 8; c++){
        if(c == 0){
        System.out.print(r + " | ");
        }
        if(c==0)
        {
          System.out.print(general2Board[r][c] + " ");
        }
        else
        {
          System.out.print(general2Board[r][c] + " ");
        }
      }
      if(r==0)
        System.out.println("| 1 1");
      else if(r==1)
        System.out.println("| 1 1");
      else if(r==2)
        System.out.println("| 4");
      else if(r==3)
        System.out.println("| 6");
      else if(r==4)
        System.out.println("| 6");
      else if(r==5)
        System.out.println("| 4");
      else if(r==6)
        System.out.println("| 2");
      else
        System.out.println("| 2");
    }
    System.out.println("  - - - - - - - - - -");
    System.out.println("    0 1 2 3 4 5 6 7");

  }

  public void compareSkullBoard(int r, int c){
    if(skull2Board[r][c].getMarker().equals("X"))
    {
      skullBoard[row][col].setMarker("X");
      general2Board[row][col].setMarker("X");
    }
    else if(!skull2Board[r][c].getMarker().equals("X"))
    {
      lives--;
      System.out.println("The square you selected was not correct, try picking another one.");
      skullBoard[r][c].setMarker("O");
      general2Board[r][c].setMarker("O");
          if(lives==0){
          System.out.println("You ran out of lives, try again later.");
          play = false;
        }
    }
      int count=0;
      for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
          if(skullBoard[i][j].getMarker().equals("X")){
            count++;
          }
        }
      }
      if(count == 28){
        play=false;
      }
  }
  public void compareCreeperBoard(int r, int c){
       if(actualBoard[r][c].getMarker().equals("X")){
        playBoard[row][col].setMarker("X");
        generalBoard[row][col].setMarker("X");
      }
      else if(!actualBoard[r][c].getMarker().equals("X")){
        lives--;
        System.out.println("The square you selected was not correct, try picking another one.");
        playBoard[r][c].setMarker("O");
        generalBoard[r][c].setMarker("O");
          if(lives==0){
          System.out.println("You ran out of lives, try again later.");
          play = false;
        }
      }
      int count=0;
      for(int i=0; i<actualBoard.length; i++){
        for(int j=0; j<actualBoard[0].length; j++){
          if(playBoard[i][j].getMarker().equals("X")){
            count++;
          }
        }
      }
      if(count == 20){
        play=false;
      }
  }

  public void checkSkullTaken(int r, int c)
  {
    if(skullBoard[r][c].getMarker().equals("X"))
    {
      System.out.println("This spot was already taken! Pick an empty spot.");
    }
      System.out.println("Row: " + r);
      System.out.println("Column: " + c);
  }
  public void checkTaken(int r, int c){
    if(playBoard[r][c].getMarker().equals("X")){
      System.out.println("This spot was already taken! Pick an empty spot.");
    }
      System.out.println("Row: " + r);
      System.out.println("Column: " + c);
  }
  
  // public void replaySkull()
  // {

  // }
  public void replay(){
    System.out.println("Would you like to play again? Type 1 to play again, type 2 to exit:");
    re = validate(1,2);

    if(re==1){
      play=true;
      lives=3;
      grid=-1;
      for(int r=0; r<8; r++){
        for(int c=0; c<8; c++){
          if(playBoard[r][c].getMarker().equals("X") || playBoard[r][c].getMarker().equals("O") || skullBoard[r][c].getMarker().equals("X") || skullBoard[r][c].getMarker().equals("O")){
            playBoard[r][c].setMarker(" ");
            generalBoard[r][c].setMarker(" ");
            skullBoard[r][c].setMarker(" ");
            general2Board[r][c].setMarker(" ");
          }
        }
      }
    play();
    }
    
  }

  public void createSkullBoard()
  {
    for(int r = 0; r < 8; r++)
    {
      for(int c= 0; c < 8; c++)
      {
        if(r==0 && (c==0 || c==7))
          skull2Board[r][c].setMarker("X");
        else if(r==1 && (c==1 || c==6))
          skull2Board[r][c].setMarker("X");
        else if(r==2 && (c==2||c==3||c==4||c==5))
          skull2Board[r][c].setMarker("X");
        else if(r==3 && (c==1||c==2||c==3||c==4||c==5||c==6))
          skull2Board[r][c].setMarker("X");
        else if(r==4 && (c==1||c==2||c==3||c==4||c==5||c==6))
          skull2Board[r][c].setMarker("X");
        else if(r==5 && (c==2||c==3||c==4||c==5))
          skull2Board[r][c].setMarker("X");
        else if(r==6 && (c==3||c==4))
          skull2Board[r][c].setMarker("X");
        else if(r==7 && (c==3||c==4))
          skull2Board[r][c].setMarker("X");
        else
          skull2Board[r][c].setMarker(" ");

      }
    }
  }

  public void createActualBoard()
  {
    for(int r = 0; r < 8; r++)
    {
      for(int c= 0; c < 8; c++)
      {
        if(r==2 && (c==1||c==2||c==5||c==6))
          actualBoard[r][c].setMarker("X");
        else if(r==3 && (c==1||c==2||c==5||c==6))
          actualBoard[r][c].setMarker("X");
        else if(r==4 && (c==3||c==4))
          actualBoard[r][c].setMarker("X");
        else if(r==5 && (c==2||c==3||c==4||c==5))
          actualBoard[r][c].setMarker("X");
        else if(r==6 && (c==2||c==3||c==4||c==5))
          actualBoard[r][c].setMarker("X");
        else if(r==7 && (c==2||c==5))
          actualBoard[r][c].setMarker("X");
        else
          actualBoard[r][c].setMarker(" ");
      }
    }
  }
  
  
  public int validate(int min, int max)
  {
    boolean doLoop = true;
    while(doLoop)
    {
      String filler = scanner.next();
      try{
        if(Integer.parseInt(filler) > max || Integer.parseInt(filler) < min)
        {
          System.out.print("Enter an integer (" + min + "-" + max + "): ");
        }
        else{
          doLoop = false;
          return Integer.parseInt(filler);
        }
      }
      catch(Exception e){
          System.out.print("Enter an integer (" + min + "-" + max + "): ");
      }  
    }
    return -1;
  }
}