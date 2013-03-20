package javachess;

import java.util.Iterator;
import java.util.ArrayList;

public class Evaluator extends Thread {

    Iterator itr;
    int max;
    Location[] best;
    Board b;
    int depth;
    ArrayList list;

    public Evaluator(Iterator itr, Board board, int depth) {
        System.out.println("Creating evaluator");
        this.itr = itr;
        max = -1000000;
        b = board;
        this.depth = depth;
        list = new ArrayList();
    }

    @Override
    public void run() {
        while(itr.hasNext()){
            int eval = 0;
            Location[] move = null;
            try{
            move = (Location[]) itr.next();
            }catch(Exception e){}
            eval = Evaluate.evaluate( b.cloneBoard(), move, depth);
            Object[] piece = {new Integer(eval), move};
            list.add(piece);
            if (eval > max) {
                max = eval;
                best = move;
            }
        }
        if(best!=null){
        System.out.println("FOUND BEST MOVE: "+best[0]+", "+best[1]+":"+max);}
    }

    public int getMax() {
        return max;
    }

    public Location[] getBest() {
        return best;
    }
    
    public ArrayList<Object[]> getList(){
        return list;
    }
}