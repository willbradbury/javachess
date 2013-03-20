package javachess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 *
 * @author will
 */
public class ChessPanel extends JPanel {

    private static final int FRAME = 600;
    private static final Color BACKGROUND = new Color(204, 204, 204);
    private BufferedImage myImage;
    private Graphics myBuffer;
    public Timer t;
    private Mouse m;
    private MouseClick mc;
    public int xHigh;
    public int yHigh;
    public int xGreen;
    public int yGreen;
    public int xRed1, yRed1;
    public int xRed2, yRed2;
    public int xOffset;
    public int yOffset;
    public int mouseX;
    public int mouseY;
    public boolean calibrating;

    //constructor
    public ChessPanel() {
        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME, FRAME);
        xHigh = -1;
        yHigh = -1;
        mouseX = -1;
        mouseY = -1;
        resetColors();
        t = new Timer(1, new TimerListener());
        t.setDelay(0);
        m = new Mouse();
        addMouseMotionListener(m);
        mc = new MouseClick();
        addMouseListener(mc);
        t.start();
        setFocusable(true);
        calibrate();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void resetColors() {
        xGreen = -1;
        yGreen = -1;
        xRed1 = -1;
        yRed1 = -1;
        xRed2 = -1;
        yRed2 = -1;
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            drawChessboard(myBuffer, 4, 4, (FRAME / 8) - 1);
            repaint();
        }
    }

    public int getTrueCord(int cord) {
        int width = (FRAME / 8) - 1;
        return (cord - 4) / width;
    }

    public void calibrate() {
        t.stop();
        clear();
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font("Monospaced", Font.BOLD, 20));
        myBuffer.drawString("Please click on the dot to calibrate:", 20, 20);
        myBuffer.fillOval(300, 300, 10, 10);
        //myBuffer.fillOval(50, 50,8,8);
        //myBuffer.fillOval(70, 70,8,8);
        repaint();
        calibrating = true;
    }

    public void clear() {
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME, FRAME);
    }

    private class Mouse extends MouseMotionAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            xHigh = getTrueCord(e.getX() - xOffset);
            yHigh = getTrueCord(e.getY() - yOffset);
            if (calibrating == false) {
                drawChessboard(myBuffer, 4, 4, (FRAME / 8) - 1);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            mouseX = e.getX();
            mouseY = e.getY();
            if (calibrating) {
                xOffset = e.getX() - 305;
                yOffset = e.getY() - 305;
                calibrating = false;
                t.start();
            }
        }
    }

    public class MouseClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            if (calibrating) {
                xOffset = e.getX() - 305;
                yOffset = e.getY() - 305;
                calibrating = false;
                t.start();
            }
        }
    }

    public void drawChessboard(Graphics g, int xCord, int yCord, int squareWidth) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, FRAME, FRAME);
        for (int yCounter = 0; yCounter < 8; yCounter++) {
            for (int xCounter = 0; xCounter < 8; xCounter++) {
                if (xGreen == xCounter && yGreen == yCounter) {
                    g.setColor(Color.green.darker());
                    g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                } else if ((yRed1 == yCounter && xRed1 == xCounter) || (yRed2 == yCounter && xRed2 == xCounter)) {
                    g.setColor(Color.red);
                    g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                } else if ((xCounter + yCounter) % 2 == 0) {
                    g.setColor(new Color(255, 228, 196, 255));
                    g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);

                    if (xHigh == xCounter && yHigh == yCounter) {
                        g.setColor(new Color(222,184,135,255));
                        g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                        g.setColor(Color.black);
                        g.drawRect((xCounter * squareWidth) + xCord + 1, ((yCounter * squareWidth) + yCord + 1), squareWidth-2, squareWidth-2);
                        g.drawRect((xCounter * squareWidth) + xCord + 2, ((yCounter * squareWidth) + yCord + 2), squareWidth-4, squareWidth-4);

                    }
                } else {
                    g.setColor(new Color(107, 66, 38, 255));
                    g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                    if (xHigh == xCounter && yHigh == yCounter) {
                        g.setColor(g.getColor().darker());
                        g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
                        g.setColor(Color.black);
                        g.drawRect((xCounter * squareWidth) + xCord + 1, ((yCounter * squareWidth) + yCord + 1), squareWidth-2, squareWidth-2);
                        g.drawRect((xCounter * squareWidth) + xCord + 2, ((yCounter * squareWidth) + yCord + 2), squareWidth-4, squareWidth-4);

                    }
                }
                //g.fillRect((xCounter * squareWidth) + xCord, ((yCounter * squareWidth) + yCord), squareWidth, squareWidth);
            }
        }
        g.setColor(Color.BLACK);
        for (int counter = 0; counter <= 8; counter++) {
            g.drawLine(xCord, (yCord + (counter * squareWidth)), (xCord + (squareWidth * 8)), (yCord + (counter * squareWidth)));
        }
        for (int counter = 0; counter <= 8; counter++) {
            g.drawLine((xCord + (counter * squareWidth)), yCord, (xCord + (counter * squareWidth)), (yCord + (8 * squareWidth)));
        }
        Main.playBoard.drawBoard(g, FRAME);
    }
}
