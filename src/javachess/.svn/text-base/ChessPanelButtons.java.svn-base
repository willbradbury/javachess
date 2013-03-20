package javachess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChessPanelButtons extends JPanel {

    public static JButton[][] buttons;
    private static JPanel buttonPanel;
    public static Board board;
    public static Location prevLoc;
    public static Location currLoc;
    public static int turn;
    public static boolean flip = true;
    public static Graphics gr;
    public static PrintWriter logger;
    public static final String VERSION = "2.4.0";
    public static int moveCount;
    public static boolean log;

    public ChessPanelButtons() {
        board = Main.playBoard;
        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(8, 8, 0, 0));
        buttons = new JButton[8][8];

        prevLoc = null;
        currLoc = null;
        turn = 0;
        gr = getGraphics();
        int confirm = JOptionPane.showConfirmDialog(null, "Log Moves? (In PGN format)", "Log Moves?", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            log = true;
        } else {
            log = false;
        }
        if (log) {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("chesslog_" + (System.currentTimeMillis() % 10000) + ".pgn"));
            chooser.setDialogTitle("Choose Log File Path");
            chooser.showSaveDialog(null);
            try {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                logger = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            } catch (IOException e) {
            }
        } else {
            logger = null;
        }
        confirm = JOptionPane.showConfirmDialog(null, "Flip Board? (So that white is at the top)", "Flip Board?", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            flip = true;
        } else {
            flip = false;
        }
        startLog();
        moveCount = 1;
        createBoard();
        updateBoard();
    }

    public static void createBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (flip) {
                    buttons[7 - row][7 - col] = makeButton(7 - row, 7 - col);
                    buttonPanel.add(buttons[7 - row][7 - col]);
                } else {
                    buttons[row][col] = makeButton(row, col);
                    buttonPanel.add(buttons[row][col]);
                }
            }
        }
    }

    public static void clearBoard() {
        prevLoc = null;
        currLoc = null;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    buttons[row][col].setBackground(new Color(255, 228, 196));
                } else {
                    buttons[row][col].setBackground(new Color(107, 66, 38));
                }
            }
        }
    }

    public static void startLog() {
        if (!log) {
            return;
        }
        logger.println("[version " + VERSION + "]");
        logger.flush();
    }

    public static void logMove(Location locFrom, Location locTo) {
        if (!log) {
            return;
        }
        String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"};
        if (turn == 0 && moveCount > 1) {
            logger.print(" " + moveCount + ".");
        } else if (turn == 0) {
            logger.print(moveCount + ".");
        } else {
            moveCount++;
        }
        if (locFrom.castle) {
            if (locTo.col == 0) {
                logger.print(" O-O-O");
            } else if (locTo.col == 7) {
                logger.print(" O-O");
            }
            return;
        }
        String move = " ";
        move += (board.getPiece(locFrom) instanceof Pawn) ? "" : board.getPiece(locFrom).name;
        boolean file = false;
        boolean rank = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (col == locFrom.col && row == locFrom.row) {
                    continue;
                }
                if (board.allPieces[row][col].name.equals(board.getPiece(locFrom).name) && board.allPieces[row][col].color == board.getPiece(locFrom).color) {
                    if (board.allPieces[row][col].findMoves(board, new Location(row, col)).contains(locTo)) {
                        if (col != locFrom.col) {
                            file = true;
                        } else if (row != locFrom.row) {
                            rank = true;
                        }
                    }
                }
            }
        }
        if (file) {
            move += files[locFrom.col];
        }
        if (rank) {
            move += (7 - locFrom.row) + 1;
        }
        if (board.getPiece(locTo).color != -1) {
            move += "x";
        }
        move += files[locTo.col];
        move += (7 - locTo.row) + 1;
        if (board.getPiece(locFrom) instanceof Pawn && (locFrom.row == 0 || locFrom.row == 7)) {
            move += "=Q";
        }
        logger.print(move);
        logger.flush();
    }

    public static void logCheck() {
        if (!log) {
            return;
        }
        logger.print("+");
        logger.flush();
    }

    public static void logMate() {
        if (!log) {
            return;
        }
        logger.print("#");
        logger.flush();
    }

    public static void set(Location loc, Color c) {
        buttons[loc.row()][loc.col()].setBackground(c);
    }

    public static void win(int color) {
        //gr.setFont(new Font("Serif",Font.BOLD,50));
        //gr.drawString("Game Over!", 25, 25);
    }

    public static void draw(int color) {
        //gr.setFont(new Font("Serif",Font.BOLD,50));
        //gr.drawString("Draw!", 40, 40);
    }

    public static void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String name = board.getPiece(new Location(row, col)).name;
                int pcolor = board.getPiece(new Location(row, col)).color;
                if (pcolor != -1) {
                    ImageIcon image = new ImageIcon(board.getClass().getResource(name + "" + pcolor + ".png"));
                    buttons[row][col].setIcon(image);
                    //System.out.println("UPDATE: "+new ImageIcon(board.getClass().getResource(name + "" + pcolor + ".png")));
                } else {
                    buttons[row][col].setIcon(null);
                }
                buttons[row][col].update(buttons[row][col].getGraphics());

//                buttons[row][col].setText(name);
//                buttons[row][col].setFont(new Font("Times New Roman", Font.BOLD, 45));
//                if (board.getPiece(new Location(row, col)).color == 1) {
//                    buttons[row][col].setForeground(Color.BLACK);
//                } else {
//                    buttons[row][col].setForeground(Color.GRAY.brighter());
//                }
            }
        }
    }

    public static void disableBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    public static void enableBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].setEnabled(true);
            }
        }
    }

    private static JButton makeButton(int row, int col) {
        String name = board.getPiece(new Location(row, col)).name;
        int pcolor = board.getPiece(new Location(row, col)).color;
        //JButton button = new JButton(name);
        JButton button = null;
        if (pcolor == -1) {
            button = new JButton();
        } else {
            ImageIcon image = new ImageIcon(board.getClass().getResource(name + "" + pcolor + ".png"));
            button = new JButton(image);
            System.out.println(new ImageIcon(board.getClass().getResource(name + "" + pcolor + ".png")));
        }
        //button.setFont(new Font("Times New Roman", Font.BOLD, 45));
        Color color = null;
        if ((row + col) % 2 == 0) {
            color = new Color(255, 228, 196);
        } else {
            color = new Color(107, 66, 38);
        }
        button.setBackground(color);
//        if (board.getPiece(new Location(row, col)).color == 1) {
//            button.setForeground(Color.BLACK);
//        } else {
//            button.setForeground(Color.GRAY.brighter());
//        }
        button.addActionListener(new PieceListener(row, col));
        return button;
    }

    public static class PieceListener implements ActionListener {

        int row;
        int col;

        PieceListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            //System.out.println(Main.frame.getSize());
            if (prevLoc == null || currLoc != null) {
                clearBoard();
                if (board.getPiece(new Location(row, col)).color == turn) {
                    //System.out.println(turn);
                    prevLoc = new Location(row, col);
                    set(prevLoc, Color.GREEN.darker());
                } else {
                    prevLoc = null;
                    set(new Location(row, col), Color.RED);
                }
            } else {

                currLoc = new Location(row, col);
                if (board.legal(prevLoc, currLoc)) {
                    if (board.getPiece(prevLoc) instanceof King && board.getPiece(currLoc) instanceof Rook && board.getPiece(prevLoc).color==board.getPiece(currLoc).color) {
                        prevLoc.setCastle(true);
                        currLoc.setCastle(true);
                    }
                    if (log) {
                        logMove(prevLoc, currLoc);
                    }
                    board.move(prevLoc, currLoc);

                    //board.printBoard();
                    if (board.checkMate(turn == 0 ? 1 : 0)) {
                        disableBoard();
                        if (log) {
                            logMate();
                        }
                        win(turn);
                    } else if (board.staleMate(turn == 0 ? 1 : 0)) {
                        disableBoard();
                        draw(turn);
                    } else {
                        if (log && board.inCheck(turn == 0 ? 1 : 0)) {
                            logCheck();
                        }
                    }
                    updateBoard();
                    if (turn == 0) {
                        turn = 1;
                    } else {
                        turn = 0;
                    }
                    set(prevLoc, Color.YELLOW);
                    set(currLoc, Color.YELLOW);
                    updateBoard();
                    disableBoard();
                    Location[] move = board.getComputerMove(turn);
                    clearBoard();
                    if (log) {
                        logMove(move[0], move[1]);
                    }
                    board.move(move[0], move[1]);
                    //board.printBoard();

                    set(move[0], Color.YELLOW);
                    set(move[1], Color.YELLOW);
                    updateBoard();
                    if (board.checkMate(turn == 0 ? 1 : 0)) {
                        if (log) {
                            logMate();
                        }
                        win(turn);
                    } else if (board.staleMate(turn == 0 ? 1 : 0)) {
                        draw(turn);
                    } else {
                        enableBoard();
                        if (log && board.inCheck(turn == 0 ? 1 : 0)) {
                            logCheck();
                        }
                    }

                    if (turn == 0) {
                        turn = 1;
                    } else {
                        turn = 0;
                    }
                } else {
                    set(currLoc, Color.RED);
                    set(prevLoc, Color.RED);
                }
            }
            updateBoard();
        }
    }
}