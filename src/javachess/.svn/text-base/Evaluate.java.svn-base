package javachess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Evaluate {

    public static ConcurrentHashMap<Board, Integer> boardValues = new ConcurrentHashMap<Board, Integer>();
    public static int dictCount = 0;
    public static int alphaCount = 0;
    public static int betaCount = 0;

    public static int evaluate(Board b, Location[] move, int depth) {
        System.out.println("EVALUATING:" + move[0].toString() + "," + move[1].toString() + " AT DEPTH: " + depth);
        int eval = min(b, move, -100000000, 100000000, 0, depth);
        System.out.println("EVALUATION RESULTS IN: " + eval);
        System.out.println("BOARD DICTIONARY HAS: " + boardValues.size() + " DEFINITIONS");
        System.out.println("BOARD DICTIONARY HAS BEEN USED: " + dictCount + " TIMES");
        //System.out.println("ALPHA: "+alphaCount+"  BETA: "+betaCount);
        return eval;
    }
//    public static int evaluateNew(Board b, Location[] move, int depth){
//        int alpha = -100000000;
//        int beta = 100000000;
//        PriorityQueue<Board> q = new PriorityQueue<Board>();
//        Board min = null;
//        int minVal = 100000000;
//        Board bC = b.cloneBoard();
//        bC.move(move[0], move[1]);
//        q.add(bC);
//        while(!q.isEmpty()){
//            Board temp = q.remove();
//            if(temp.depth >= depth){
//                int val = evaluateBoard(temp);
//                if(val<minVal){
//                    minVal = val;
//                    min = temp;
//                }
//            }
//            else{
//                temp.depth++;
//                for(Location[] moveA:temp.getLegalMoves(0)){
//                    
//                }
//            }
//        }
//        return 0;
//    }

    private static int min(Board b, Location[] move, int alpha, int beta, int level, int toplevel) {
        Board c = null;
        c = b.cloneBoard();
        c.move(move[0], move[1]);

        if (level >= toplevel) {
            return evaluateBoard(c);
        }
        ArrayList<Location[]> moveOptions = c.getLegalMoves(0);
        Collections.sort(moveOptions, new MoveComparator(b));
        for (Location[] pmove : moveOptions) {
            int eval = max(c, pmove, alpha, beta, level + 1, toplevel);
            if (eval <= alpha) {
                //alphaCount+=Math.pow(20,(toplevel-level-1));
                return alpha;
            } else if (eval < beta) {
                beta = eval;
            }
        }
        return beta;
    }

    private static int max(Board b, Location[] move, int alpha, int beta, int level, int toplevel) {
        Board c = null;
        c = b.cloneBoard();
        c.move(move[0], move[1]);

        if (level >= toplevel) {
            return evaluateBoard(c);
        }
        ArrayList<Location[]> moveOptions = c.getLegalMoves(1);
        Collections.sort(moveOptions, new MoveComparator(b));
        for (Location[] pmove : moveOptions) {
            int eval = min(c, pmove, alpha, beta, level + 1, toplevel);
            if (eval >= beta) {
                //System.out.println("BETA: max");
                //betaCount+=Math.pow(20,(toplevel-level-1));
                return beta;
            } else if (eval > alpha) {
                alpha = eval;
            }
        }
        return alpha;
    }

    public static int evaluateBoard(Board b) {
        double value = 0;
        if (boardValues.containsKey(b)) {
            //System.out.println("KEY FOUND");
            dictCount++;
            return boardValues.get(b);
        }
        ArrayList<Location[]> moves = b.getLegalMoves(1);
        if (moves.isEmpty()) {
            if (b.inCheck(1)) {
                boardValues.put(b, -10000000);
                return -10000000;
            }
            boardValues.put(b, 0);
            return 0;
        }
        ArrayList<Location[]> movesWhite = b.getLegalMoves(0);
        if (movesWhite.isEmpty()) {
            if (b.inCheck(0)) {
                boardValues.put(b, 10000000);
                return 10000000;
            }
            boardValues.put(b, 0);
            return 0;
        }
        moves.addAll(movesWhite);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int color = b.getPiece(new Location(row, col)).color;
                if (color == 0) {
                    value -= b.getPiece(new Location(row, col)).fullValue(b, moves, new Location(row, col));
                } else {
                    value += b.getPiece(new Location(row, col)).fullValue(b, moves, new Location(row, col));
                }
            }
        }
        //System.out.println("board: "+value);
        boardValues.put(b, (int) value);
        return (int) value;
    }
}