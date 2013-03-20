package javachess;

import java.util.ArrayList;

public class Rook extends Piece {

    public int[][] posValues = {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {5, 10, 10, 10, 10, 10, 10, 5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {0, 0, 0, 15, 5, 10, 0, 0}};

    public Rook() {
        super();
        name = "R";
    }

    public Rook(int newcolor) {
        super("R", newcolor, 500);
    }
    @Override
    public int simpValue(Location myLoc) {
        return value+(color==0 ? posValues[myLoc.row][myLoc.col]: posValues[7-myLoc.row][myLoc.col]);
    }
    public ArrayList<Location> findMoves(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(28);
        int row = loc.row;
        int col = loc.col;

        for (int x = 1; x <= 8; x++) {
            Location newLoc = new Location(row + x, col);
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
            Location newLoc = new Location(row, col + x);
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
            Location newLoc = new Location(row, col - x);
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
            Location newLoc = new Location(row - x, col);
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

    public boolean canCastle(Board b, Location loc) {
        return b.canCastle(loc);
    }
}
