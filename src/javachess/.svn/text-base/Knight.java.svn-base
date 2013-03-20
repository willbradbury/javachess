package javachess;

import java.util.ArrayList;

public class Knight extends Piece {

    public int[][] posValues = {{-50, -40, -30, -30, -30, -30, -40, -50},
        {-40, -20, 0, 0, 0, 0, -20, -40},
        {-30, 0, 10, 15, 15, 10, 0, -30},
        {-30, 5, 15, 20, 20, 15, 5, -30},
        {-30, 0, 15, 20, 20, 15, 0, -30},
        {-30, 5, 10, 15, 15, 10, 5, -30},
        {-40, -20, 0, 5, 5, 0, -20, -40},
        {-50, -50, -30, -30, -30, -30, -50, -50}};

    public Knight() {
        super();
        name = "N";
    }

    public Knight(int newcolor) {
        super("N", newcolor, 300);
    }
    @Override
    public int simpValue(Location myLoc) {
        return value+(color==0 ? posValues[myLoc.row][myLoc.col]: posValues[7-myLoc.row][myLoc.col]);
    }
    public ArrayList<Location> findMoves(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(16);
        int row = loc.row;
        int col = loc.col;

        if (b.validMove(loc, new Location(row - 2, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row - 2, col - 1));
        }
        if (b.validMove(loc, new Location(row + 2, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row + 2, col - 1));
        }
        if (b.validMove(loc, new Location(row - 1, col - 2))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col - 2));
        }
        if (b.validMove(loc, new Location(row + 1, col - 2))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col - 2));
        }
        if (b.validMove(loc, new Location(row - 1, col + 2))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col + 2));
        }
        if (b.validMove(loc, new Location(row + 1, col + 2))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col + 2));
        }
        if (b.validMove(loc, new Location(row - 2, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row - 2, col + 1));
        }
        if (b.validMove(loc, new Location(row + 2, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row + 2, col + 1));
        }

        return moves;
    }
}
