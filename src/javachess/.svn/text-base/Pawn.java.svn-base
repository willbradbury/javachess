package javachess;

import java.util.ArrayList;

public class Pawn extends Piece {

    public int[][] posValues = {{100, 100, 100, 100, 100, 100, 100, 100},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 30, 35, 35, 30, 10, 10},
        {5, 5, 10, 35, 35, 10, 5, 5},
        {5, 5, 10, 30, 30, 10, 5, 5},
        {5, -5, -5, 0, 0, -5, -5, 5},
        {-10, -20, -30, -70, -70, -20, -20, -10},
        {0, 0, 0, 0, 0, 0, 0, 0}};

    public Pawn() {
        super();
        name = "P";
    }

    public Pawn(int newcolor) {
        super("P", newcolor, 100);
    }

    @Override
    public int simpValue(Location myLoc) {
        return value + (color == 0 ? posValues[myLoc.row][myLoc.col] : posValues[7 - myLoc.row][myLoc.col]);
    }

    public ArrayList<Location> findMoves(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(8);
        int row = loc.row;
        int col = loc.col;

        if (color == 0 && b.isValid(new Location(row - 1, col))) {
            if (b.isEmpty(new Location(row - 1, col))) {
                moves.add(loc);
                moves.add(new Location(row - 1, col));
            }
            if (!b.isEmpty(new Location(row - 1, col + 1)) && b.validMove(loc, new Location(row - 1, col + 1))) {
                moves.add(loc);
                moves.add(new Location(row - 1, col + 1));
            }
            if (!b.isEmpty(new Location(row - 1, col - 1)) && b.validMove(loc, new Location(row - 1, col - 1))) {
                moves.add(loc);
                moves.add(new Location(row - 1, col - 1));
            }
            if (row == 6 && b.isEmpty(new Location(row - 2, col)) && b.isEmpty(new Location(row - 1, col))) {
                moves.add(loc);
                moves.add(new Location(row - 2, col));
            }
        } else if (color == 1 && b.isValid(new Location(row + 1, col))) {
            if (b.isEmpty(new Location(row + 1, col))) {
                moves.add(loc);
                moves.add(new Location(row + 1, col));
            }
            if (!b.isEmpty(new Location(row + 1, col + 1)) && b.validMove(loc, new Location(row + 1, col + 1))) {
                moves.add(loc);
                moves.add(new Location(row + 1, col + 1));
            }
            if (!b.isEmpty(new Location(row + 1, col - 1)) && b.validMove(loc, new Location(row + 1, col - 1))) {
                moves.add(loc);
                moves.add(new Location(row + 1, col - 1));
            }
            if (row == 1 && b.isEmpty(new Location(row + 2, col)) && b.isEmpty(new Location(row + 1, col))) {
                moves.add(loc);
                moves.add(new Location(row + 2, col));
            }
        }
        return moves;
    }
}
