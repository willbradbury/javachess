package javachess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Board implements Comparable<Board>{

    public static Piece space = new Blank();
    public static Piece wPawn = new Pawn(0);
    public static Piece bPawn = new Pawn(1);
    public static Piece wBishop = new Bishop(0);
    public static Piece bBishop = new Bishop(1);
    public static Piece wKnight = new Knight(0);
    public static Piece bKnight = new Knight(1);
    public static Piece wQueen = new Queen(0);
    public static Piece bQueen = new Queen(1);
    public static Piece wRook = new Rook(0);
    public static Piece bRook = new Rook(1);
    public static Piece wKing = new King(0);
    public static Piece bKing = new King(1);
    public Piece[][] allPieces = {
        {bRook, bKnight, bBishop, bQueen, bKing, bBishop, bKnight, bRook},
        {bPawn, bPawn, bPawn, bPawn, bPawn, bPawn, bPawn, bPawn},
        {space, space, space, space, space, space, space, space},
        {space, space, space, space, space, space, space, space},
        {space, space, space, space, space, space, space, space},
        {space, space, space, space, space, space, space, space},
        {wPawn, wPawn, wPawn, wPawn, wPawn, wPawn, wPawn, wPawn},
        {wRook, wKnight, wBishop, wQueen, wKing, wBishop, wKnight, wRook}
    };
    public boolean[] castle = {true, true, true, true, true, true};
    public int depth;

    public Board(boolean[] newcastle) {
        castle = newcastle;
        depth = 0;
    }

    public Board() {
        depth = 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Arrays.deepHashCode(this.allPieces);
        hash = 17 * hash + Arrays.hashCode(this.castle);
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
        final Board other = (Board) obj;
        if (!Arrays.deepEquals(this.allPieces, other.allPieces)) {
            return false;
        }
        if (!Arrays.equals(this.castle, other.castle)) {
            return false;
        }
        return true;
    }
    public Board cloneBoard() {
        Board b = new Board();
        System.arraycopy(castle, 0, b.castle, 0, 6);
        for (int row = 0; row < 8; row++) {
            System.arraycopy(allPieces[row], 0, b.allPieces[row], 0, 8);
        }
        b.depth = depth;
        return b;
    }
    public int compareTo(Board b){
        return Evaluate.evaluateBoard(this) - Evaluate.evaluateBoard(b);
    }
    public Piece[][] getBoard() {
        return allPieces;
    }

    public Piece getPiece(Location loc) {
        return allPieces[loc.row][loc.col];
    }

    public void setPiece(Location loc, Piece p) {
        allPieces[loc.row][loc.col] = p;
    }

    public boolean validMove(Location locf, Location loct) {
        return (!(locf.row >= 8 || locf.col >= 8 || locf.row < 0 || locf.col < 0) && !(loct.row >= 8 || loct.col >= 8 || loct.row < 0 || loct.col < 0) && (allPieces[locf.row][locf.col].color != allPieces[loct.row][loct.col].color));
    }

    public boolean isEmpty(Location loc) {
        if (loc.row >= 8 || loc.col >= 8 || loc.row < 0 || loc.col < 0) {
            return false;
        }
        return allPieces[loc.row][loc.col].color == -1;
    }
    public boolean castleThrough(Location loc, ArrayList<Location[]> moves){
        if (loc.row >= 8 || loc.col >= 8 || loc.row < 0 || loc.col < 0) {
            return false;
        }
        if(allPieces[loc.row][loc.col].color != -1){
            return false;
        }
        for (Location[] move : moves) {
            if (move[1].equals(loc)) {
                return false;
            }
        }
        return true;
    }
    public boolean isValid(Location loc) {
        if (loc.row >= 8 || loc.col >= 8 || loc.row < 0 || loc.col < 0) {
            return false;
        }
        return true;
    }

    public boolean legal(Location locFrom, Location locTo) {
        ArrayList<Location> moves = getPiece(locFrom).findMoves(this, locFrom);
        for (Location loc : moves) {
            if (loc.equals(locTo) && !loc.equals(locFrom)) {
                boolean returnValue = false;
                if (loc.castle) {
                    locFrom.castle = true;
                    Board cB =  this.cloneBoard();
                    cB.move(locFrom, loc);
                    if (!cB.inCheck(cB.getPiece(locTo).color)) {
                        returnValue = true;
                    }
                } else {
                    boolean[] castleTemp = new boolean[6];
                    System.arraycopy(castle, 0, castleTemp, 0, 6);
                    Piece fromLocPiece = getPiece(locFrom);
                    Piece toLocPiece = getPiece(locTo);
                    move(locFrom, locTo);
                    if (!inCheck(getPiece(locTo).color)) {
                        returnValue = true;
                    }
                    setPiece(locFrom, fromLocPiece);
                    setPiece(locTo, toLocPiece);
                    castle = castleTemp;
                }
                return returnValue;
            }
        }
        return false;
    }

    public void move(Location locFrom, Location locTo) {
        if (locFrom.equals(new Location(0, 0)) || locTo.equals(new Location(0, 0))) {
            castle[0] = false;
        }
        if (locFrom.equals(new Location(0, 7)) || locTo.equals(new Location(0, 7))) {
            castle[1] = false;
        }
        if (locFrom.equals(new Location(7, 0)) || locTo.equals(new Location(7, 0))) {
            castle[2] = false;
        }
        if (locFrom.equals(new Location(7, 7)) || locTo.equals(new Location(7, 7))) {
            castle[3] = false;
        }
        if (locFrom.equals(new Location(0, 4)) || locTo.equals(new Location(0, 4))) {
            castle[4] = false;
        }
        if (locFrom.equals(new Location(7, 4)) || locTo.equals(new Location(7, 4))) {
            castle[5] = false;
        }
        if (locFrom.isCastle() && locTo.isCastle()) {
            //System.out.println("castling");
            int colk;
            int colr;
            if (locTo.col < locFrom.col) {
                colk = locFrom.col - 2;
                colr = colk + 1;
            } else {
                colk = locFrom.col + 2;
                colr = colk - 1;
            }

            allPieces[locFrom.row][colk] = getPiece(locFrom);
            allPieces[locTo.row][colr] = getPiece(locTo);
            allPieces[locTo.row][locTo.col] = space;
            allPieces[locFrom.row][locFrom.col] = space;

        } else {
            allPieces[locTo.row][locTo.col] = allPieces[locFrom.row][locFrom.col];
            allPieces[locFrom.row][locFrom.col] = space;
            if ((locTo.row == 7 || locTo.row == 0) && allPieces[locTo.row][locTo.col] instanceof Pawn) {
                allPieces[locTo.row][locTo.col] = allPieces[locTo.row][locTo.col].color == 0 ? wQueen : bQueen;
            }
        }
    }
    public Location[] getComputerMove(int color) {
        ArrayList<Location[]> possibleMoves = getLegalMoves(color);
        //Evaluator[] pool = new Evaluator[2 * Runtime.getRuntime().availableProcessors()]; //41:28
        Evaluator[] pool = new Evaluator[4 * Runtime.getRuntime().availableProcessors()]; //40:41
        //int length = possibleMoves.size() / pool.length;
        Iterator itr = possibleMoves.iterator();
        Location[] best = null;
        int bestV = 0;
        long start = System.currentTimeMillis();
        long length = 6*1000;
        for (int level = 2; System.currentTimeMillis()-start < length; level += 1) {
            for (int i = 0; i < pool.length; i++) {
                pool[i] = new Evaluator(itr, this, level);
            }
            for (Thread e : pool) {
                System.out.println("STARTING THREAD: " + e);
                e.start();
            }
            for (Thread e : pool) {
                try {
                    System.out.println("JOINING THREAD: " + e);
                    e.join();
                } catch (InterruptedException ex) {
                }
            }
            ArrayList<Object[]> sortedMoves = new ArrayList(possibleMoves.size());
            for (Evaluator e : pool) {
                sortedMoves.addAll(e.getList());
            }
            sort(sortedMoves);
            bestV = (Integer)sortedMoves.get(0)[0];
            possibleMoves.clear();
            for (int i=0; i <= sortedMoves.size()/3; i++) {
                possibleMoves.add((Location[]) sortedMoves.get(i)[1]);
            }
            itr = possibleMoves.iterator();
            //            int max = -1000000;
            //
            //            for (Evaluator e : pool) {
            //                System.out.println("CHECKING THREAD: " + e);
            //                if (e.getMax() > max) {
            //                    max = e.getMax();
            //                    best = e.getBest();
            //                }
            //            }
        }
        System.out.println("COMPUTER CHOSE MOVE WITH VALUE: " + bestV);
        return possibleMoves.get(0);
    }

    public void sort(ArrayList<Object[]> a) {
        System.out.print("SORTING...");
        for (int i = 0; i < a.size(); i++) {
            for (int subi = i; subi > 0; subi--) {
                if ((Integer) (a.get(subi - 1)[0]) < (Integer) (a.get(subi)[0])) {
                    swap(a, subi - 1, subi);
                } else {
                    break;
                }
            }
        }
        System.out.println("DONE SORTING");
    }

    public void swap(ArrayList<Object[]> a, int c1, int c2) {
        Object[] temp = a.get(c1);
        a.set(c1, a.get(c2));
        a.set(c2, temp);
    }

    public boolean checkMate(int color) {
        if (getLegalMoves(color).isEmpty() && inCheck(color)) {
            return true;
        }
        return false;
    }

    public boolean staleMate(int color) {
        if (getLegalMoves(color).isEmpty() && !inCheck(color)) {
            return true;
        }
        return false;
    }

    public int inCheck() {
        for (Location[] move : getMoves(0)) {
            if (getPiece(move[1]).name.equals("K")) {
                return 1;
            }
        }
        for (Location[] move : getMoves(1)) {
            if (getPiece(move[1]).name.equals("K")) {
                return 0;
            }
        }
        return -1;
    }

    public boolean inCheck(int color) {
        int antiColor = color == 0 ? 1 : 0;
        for (Location[] move : getMoves(antiColor)) {
            if (getPiece(move[1]).name.equals("K")) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Location[]> getMoves(int color) {
        ArrayList<Location[]> moves = new ArrayList(60);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (allPieces[row][col].color == color) {
                    ArrayList<Location> pieceMove = null;
                    if(allPieces[row][col] instanceof King){
                       pieceMove = allPieces[row][col].findMovesNoCastle(this,new Location(row,col));
                    }
                    else{
                       pieceMove = allPieces[row][col].findMoves(this, new Location(row, col));
                    }
                    for (int i = 1; i < pieceMove.size(); i += 2) {
                        Location[] move = new Location[2];
                        move[0] = pieceMove.get(i - 1);
                        move[1] = pieceMove.get(i);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Location[]> getLegalMoves(int color) {
        ArrayList<Location[]> moves = new ArrayList(60);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (allPieces[row][col].color == color) {
                    ArrayList<Location> pieceMove = (allPieces[row][col].findMoves(this, new Location(row, col)));
                    for (int i = 1; i < pieceMove.size(); i += 2) {
                        Location[] move = new Location[2];
                        move[0] = pieceMove.get(i - 1);
                        move[1] = pieceMove.get(i);
                        if (move[1].castle) {
                            Board cB =  this.cloneBoard();
                            cB.move(move[0], move[1]);
                            if (!cB.inCheck(cB.allPieces[move[1].row][move[1].col].color)) {
                                moves.add(move);
                            }
                        } else {
                            boolean[] castleTemp = new boolean[6];
                            System.arraycopy(castle, 0, castleTemp, 0, 6);
                            Piece fromLocPiece = allPieces[move[0].row][move[0].col];
                            Piece toLocPiece = allPieces[move[1].row][move[1].col];
                            move(move[0], move[1]);
                            if (!inCheck(color)) {
                                moves.add(move);
                            }
                            setPiece(move[0], fromLocPiece);
                            setPiece(move[1], toLocPiece);
                            castle = castleTemp;
                        }
                    }
                }
            }
        }
        return moves;
    }

    public boolean canCastle(Location loc) {
        int row = loc.row;
        int col = loc.col;
        if (!(allPieces[loc.row][loc.col] instanceof Rook)) {
            return false;
        }
        if (loc.col == 0 && loc.row == 0) {
            if (!castle[0] || inCheck(1)) {
                return false;
            }
            ArrayList<Location[]> whiteMoves = getMoves(0);
            if (!(castleThrough(new Location(row, col + 1),whiteMoves) && castleThrough(new Location(row, col + 2),whiteMoves) && castleThrough(new Location(row, col + 3),whiteMoves))) {
                return false;
            }
        }
        else if (loc.col == 0 && loc.row == 7) {
            if (!castle[2] || inCheck(0)) {
                //System.out.println("Because of movement ROOK");
                return false;
            }
            ArrayList<Location[]> blackMoves = getMoves(1);
            if (!(castleThrough(new Location(row, col + 1),blackMoves) && castleThrough(new Location(row, col + 2),blackMoves) && castleThrough(new Location(row, col + 3),blackMoves))) {
                return false;
            }
        }
        else if (loc.col == 7 && loc.row == 0) {
            if (!castle[1] || inCheck(1)) {
                return false;
            }
            ArrayList<Location[]> whiteMoves = getMoves(0);
            if (!(castleThrough(new Location(row, col - 1),whiteMoves) && castleThrough(new Location(row, col - 2),whiteMoves))) {
                return false;
            }
        }
        else if (loc.col == 7 && loc.row == 7) {
            if (!castle[3] || inCheck(0)) {
                //System.out.println("Because of movement");
                return false;
            }
            ArrayList<Location[]> blackMoves = getMoves(1);
            if (!(castleThrough(new Location(row, col - 1),blackMoves) && castleThrough(new Location(row, col - 2),blackMoves))) {
                //System.out.println("Because of pieces");
                return false;
            }
        }
        if (loc.row == 0) {
            if (!castle[4]) {
                return false;
            }
        }
        if (loc.row == 7) {
            if (!castle[5]) {
                //System.out.println("Because of movement KING");
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int r = 0; r < allPieces.length; r++) {
            System.out.println("+---+---+---+---+---+---+---+---+");
            System.out.print("|");
            for (int c = 0; c < allPieces[r].length; c++) {
                System.out.print("" + allPieces[r][c].getName() + "|");
            }
            System.out.println();
        }
        System.out.println("+---+---+---+---+---+---+---+---+");
    }

    public void drawBoard(Graphics g, int frame) {
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        for (int r = 0; r < allPieces.length; r++) {
            for (int c = 0; c < allPieces[r].length; c++) {
                if (allPieces[r][c].color == 0) {
                    g.setColor(Color.GRAY.brighter());
                } else if (allPieces[r][c].color == 1) {
                    g.setColor(Color.BLACK);
                }
                //g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                double width = (frame / 8) - 1;
                g.drawString(allPieces[r][c].name, (int) (c * width + 4 + (.5 * width) - 18), (int) ((r + 1) * width + 4 - (.5 * width) + 15));
            }
        }
    }
}
