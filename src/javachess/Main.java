package javachess;

import javax.swing.JFrame;

/**
 *
 * @author Will Bradbury
 */
public class Main extends JFrame {
    public static JFrame frame;
    public static int gameStage;
    public static Board playBoard;
    
    public static void main(String[] args) {
        gameStage = -1;
        playBoard = new Board();
        //playBoard.printBoard();
        frame = new JFrame("Chess - Will Bradbury & Abi Gopal");
        frame.setSize(702, 716);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setContentPane(new ChessPanel());
        frame.setContentPane(new ChessPanelButtons());
        frame.setVisible(true);
    }
}
