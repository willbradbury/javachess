package javachess;

import java.util.Comparator;

public class MoveComparator implements Comparator<Location[]> {

    Board board;

    public MoveComparator(Board b) {
        board = b;
    }

    public int compare(Location[] a1, Location[] a2) {
        //a2-a1 so that it sorts descending in terms of capture value
        int a1Val = board.allPieces[a1[1].row][a1[1].col].value;
        int a2Val = board.allPieces[a2[1].row][a2[1].col].value;
        return a2Val - a1Val;
    }
}