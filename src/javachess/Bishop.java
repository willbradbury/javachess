package javachess;

import java.util.ArrayList;

public class Bishop extends Piece {

    public int[][] posValues = {{-20, -10, -10, -10, -10, -10, -10, -20},
        {-10, 0, 0, 0, 0, 0, 0, -10},
        {-10, 0, 5, 10, 10, 5, 0, -10},
        {-10, 5, 5, 10, 10, 5, 5, -10},
        {-10, 0, 10, 10, 10, 10, 0, -10},
        {-10, 10, 10, 10, 10, 10, 10, -10},
        {-10, 5, 0, 0, 0, 0, 5, -10},
        {-40, -20, -30, -20, -20, -30, -20, -40}};

    public Bishop() {
        super();
        name = "B";
    }

    public Bishop(int newcolor) {
        super("B", newcolor, 330);
    }
    @Override
    public int simpValue(Location myLoc) {
        return value+(color==0 ? posValues[myLoc.row][myLoc.col]: posValues[7-myLoc.row][myLoc.col]);
    }
    public ArrayList<Location> findMoves(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(32);
        int row = loc.row;
        int col = loc.col;

        for (int x = 1; x <= 8; x++) {
            Location newLoc = new Location(row - x, col - x);
            if (b.validMove(loc, newLoc)) {
                moves.add(loc);
                moves.add(newLoc);
                if (!b.isEmpty(newLoc)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int x = 1; x <= 8; x++) {
            Location newLoc = new Location(row + x, col + x);
            if (b.validMove(loc, newLoc)) {
                moves.add(loc);
                moves.add(newLoc);
                if (!b.isEmpty(newLoc)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int x = 1; x <= 8; x++) {
            Location newLoc = new Location(row - x, col + x);
            if (b.validMove(loc, newLoc)) {
                moves.add(loc);
                moves.add(newLoc);
                if (!b.isEmpty(newLoc)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int x = 1; x <= 8; x++) {
            Location newLoc = new Location(row + x, col - x);
            if (b.validMove(loc, newLoc)) {
                moves.add(loc);
                moves.add(newLoc);
                if (!b.isEmpty(newLoc)) {
                    break;
                }
            } else {
                break;
            }
        }
        return moves;
    }
}
