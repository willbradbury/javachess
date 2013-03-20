package javachess;

import java.util.ArrayList;

public abstract class Piece {

    int color; //'white' = 0, 'black' = 1
    public String name;
    public int value;
    public Piece() {
        color = -1;
        name = " ";
        value = 0;
    }

    public Piece(String newname, int newcolor, int newValue) {
        color = newcolor;
        name = newname;
        value = newValue;
    }

    public String getName() {
        return name;
    }

    public int simpValue(Location myLoc) {
        return value;
    }

    public double fullValue(Board b, ArrayList<Location[]> moves, Location myLoc) {
        int pvalue = simpValue(myLoc);
        int score = pvalue;
        int subScore1 = 0;
        int subScore2 = 0;
        boolean def = false;
        int count = 0;
        int count2 = 0;
        for (Location[] move : moves) {
            Location fromLoc = move[0];
            Location toLoc = move[1];
            if (fromLoc.equals(myLoc)) {
                subScore1 += (simpValue(toLoc)-value)/2;
                if (b.allPieces[toLoc.row][toLoc.col].color != -1 && b.allPieces[toLoc.row][toLoc.col].color != this.color) {
                    subScore1 += b.allPieces[toLoc.row][toLoc.col].value;
                    count++;
                }
            }
            else if (toLoc.equals(myLoc)) {
                Piece pie = b.allPieces[fromLoc.row][fromLoc.col];
                if (pie.color == this.color) {
                    subScore2 += value - pie.value;
                    def = true;
                    count2++;
                } else {
                    subScore2 -= value - pie.value;
                    count2--;
                }
            }
        }
        subScore1+=count*200;
        subScore2+=count2*500;
        if (!def) {
            return (score*3)/4 + subScore1 * .1 + subScore2 * .02;
        } else {
            return score + subScore1 * .1 + subScore2 * .02;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.color;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.color != other.color) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    public boolean canCastle() {
        return false;
    }

    public abstract ArrayList<Location> findMoves(Board b, Location loc);
    public ArrayList<Location> findMovesNoCastle(Board b, Location loc){return null;}
}
