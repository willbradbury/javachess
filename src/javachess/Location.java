
package javachess;

/********
 * <0,0> is top left cord. in matrix
 * row is top to bottom
 * col is left to right
 * castle is for kings to pass castle as a move
 ********/
public class Location{
    public int col;
    public int row;
    public boolean castle;
    
    //constructors
    public Location(){
        col = 0;
        row = 0;
        castle = false;
    }
    public Location(int row, int col){
        this.col = col;
        this.row = row;
        castle = false;
    }
    public Location(int row,int col,boolean castle){
        this.col=col;
        this.row=row;
        this.castle = castle;
    }
    public Location(Location l){
        col = l.row;
        row = l.col;
        castle = l.castle;
    }
    
    //set methods
    public void setRow(int colLoc){
        col = colLoc;
    }
    public void setCol(int rowLoc){
        row = rowLoc;
    }
    public void setCastle(boolean castle){
        this.castle = castle;
    }
    
    //coprow method
    public void coprow(Location l){
        col = l.col;
        row = l.row;
        castle = l.castle;
    }
    
    //get methods
    public int col(){
        return col;
    }
    public int row(){
        return row;
    }
    public boolean isCastle(){
        return castle;
    }
    public boolean equals(Location loc){
        return(loc.row==row && loc.col==col);
    }
    @Override
    public boolean equals(Object o){
        return equals((Location)o);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.col;
        hash = 67 * hash + this.row;
        hash = 67 * hash + (this.castle ? 1 : 0);
        return hash;
    }
    @Override
    public String toString(){
        return "<"+row+","+col+">";
    }
}
