package javachess;

import java.util.ArrayList;

public class King extends Piece {

    public int[][] posValues = {{-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-20, -30, -30, -40, -40, -30, -30, -20},
        {-10, -20, -20, -20, -20, -20, -20, -10},
        {20, 20, 0, 0, 0, 0, 20, 20},
        {20, 25, 35, 0, 0, 10, 30, 20}};

    public King() {
        super();
        name = "K";
    }

    public King(int newcolor) {
        super("K", newcolor, 800);
    }

    @Override
    public int simpValue(Location myLoc) {
        if (Main.gameStage == -1) {
            return 700 + (color == 0 ? posValues[myLoc.row][myLoc.col] : posValues[7 - myLoc.row][myLoc.col]);
        } else if (Main.gameStage == 0) {
            return 2000 + (color == 0 ? posValues[myLoc.row][myLoc.col] : posValues[7 - myLoc.row][myLoc.col]);
        } else if (Main.gameStage == 1) {
            return 10000 + (color == 0 ? posValues[myLoc.row][myLoc.col] : posValues[7 - myLoc.row][myLoc.col]);
        } else {
            System.out.println("ERROR: invalid gameStage <king:simpValue>");
            return -1;
        }
    }

    public ArrayList<Location> findMoves(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(20);
        int row = loc.row;
        int col = loc.col;

        if (b.validMove(loc, new Location(row + 1, col))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col));
        }
        if (b.validMove(loc, new Location(row, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row, col + 1));
        }
        if (b.validMove(loc, new Location(row - 1, col))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col));
        }
        if (b.validMove(loc, new Location(row, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row, col - 1));
        }
        if (b.validMove(loc, new Location(row + 1, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col + 1));
        }
        if (b.validMove(loc, new Location(row - 1, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col - 1));
        }
        if (b.validMove(loc, new Location(row + 1, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col - 1));
        }
        if (b.validMove(loc, new Location(row - 1, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col + 1));
        }

        //castling
        if (row == 7) {
            if (b.canCastle(new Location(7, 0))) {
                moves.add(new Location(row, col, true));
                moves.add(new Location(7, 0, true));
            }
            if (b.canCastle(new Location(7, 7))) {
                moves.add(new Location(row, col, true));
                moves.add(new Location(7, 7, true));
            }
        }
        if (row == 0) {
            if (b.canCastle(new Location(0, 0))) {
                moves.add(new Location(row, col, true));
                moves.add(new Location(0, 0, true));
            }
            if (b.canCastle(new Location(0, 7))) {
                moves.add(new Location(row, col, true));
                moves.add(new Location(0, 7, true));
            }
        }
        return moves;
    }
    public ArrayList<Location> findMovesNoCastle(Board b, Location loc) {
        ArrayList<Location> moves = new ArrayList(20);
        int row = loc.row;
        int col = loc.col;

        if (b.validMove(loc, new Location(row + 1, col))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col));
        }
        if (b.validMove(loc, new Location(row, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row, col + 1));
        }
        if (b.validMove(loc, new Location(row - 1, col))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col));
        }
        if (b.validMove(loc, new Location(row, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row, col - 1));
        }
        if (b.validMove(loc, new Location(row + 1, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col + 1));
        }
        if (b.validMove(loc, new Location(row - 1, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col - 1));
        }
        if (b.validMove(loc, new Location(row + 1, col - 1))) {
            moves.add(loc);
            moves.add(new Location(row + 1, col - 1));
        }
        if (b.validMove(loc, new Location(row - 1, col + 1))) {
            moves.add(loc);
            moves.add(new Location(row - 1, col + 1));
        }
        return moves;
    }
}
