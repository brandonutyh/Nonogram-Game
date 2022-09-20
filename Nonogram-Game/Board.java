public class Board{
  private String marker;
  private boolean taken;
  private int width;
  private int height;
  private String actualBoard [][];
  private String playBoard [][];
  
  public Board()
  {
    marker = " ";
    taken = false;
    
  }
  
  //Board that users play on
  public void playBoard(int l, int w){
    for(int r=0; r<l; r++){
      for(int c=0; c<w; c++){
        playBoard[r][c]= " ";
      }
    }
  }
  
  public void setMarker(String m)
  {
    marker = m;
    taken = true;
  }

  public String getMarker()
  {
    return marker;
  }
  
  public int gWidth()
  {
    return width;
  }
  
  public int gHeight()
  {
    return height;
  }

  public String toString(){
    return marker;
  }

}