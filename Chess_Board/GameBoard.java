// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameBoard extends JPanel {
    // Might not need this depending on how we implment code
    private Spot grid[][];
    private static Spot prevSpot, curSpot;
    private int turn = 1; // Whose turn it is
    private String moveinterp;

    public GameBoard() {
        setLayout(new GridLayout(8, 8));
        grid = new Spot[8][8];

        for (int row = 0; row < 8; ++row)
            for (int col = 0; col < 8; ++col)
                add(grid[row][col] = new Spot(row, col));

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
    }

    // Gotta figure out how to just make it a square, probably gonna ask Myers
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    private class MouseHandler implements MouseListener {
        public void mouseExited(MouseEvent e) {
            /* System.out.println("Moved out of spot"); */ }

        public void mouseEntered(MouseEvent e) {
            /* System.out.println("Moved into spot"); */ }

        // Moves the chess pieces around the board, haven't added the game rules to
        // restrict pieces (Nick)
        public void mouseClicked(MouseEvent e) {
            // Having logic issues in clicked as pressed and released are also called
            System.out.println("Clicked");
        }

        // Dragging events
        public void mousePressed(MouseEvent e) {
            prevSpot = (Spot) getComponentAt(getMousePosition());
            System.out.println("Pressed at " + prevSpot.getrow() + prevSpot.getColumn());
            if (!prevSpot.isAvailable() && prevSpot.returnPieceTeam() == turn)
                possibleMoves();
        }

        public void mouseReleased(MouseEvent e)
        {
            try
            {
                curSpot = (Spot) getComponentAt(getMousePosition());
                System.out.println("Released at " + curSpot.getrow() + curSpot.getColumn()); // DEBUG

                // Used to place piece or deselect it
                if (!prevSpot.isAvailable()) {
                    System.out.println(((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41)));
                    if (curSpot != prevSpot && ((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41))) 
                            {
                        String middle = " ";
                        if (curSpot.isAvailable())
                            middle = " - ";
                        else if (curSpot.getPieceOn().getTeamNum() != prevSpot.getPieceOn().getTeamNum())
                            middle = " x ";
                        curSpot.placePiece(prevSpot);
                        turn *= -1;
                        moveinterp = curSpot.toString() + middle + prevSpot.toString();
                    }
                    clearHighlights();
                }
                prevSpot = curSpot = null;
            }
            catch (Exception except)
            {
                clearHighlights();
            }
        }
    }

    public void possibleMoves() {
        // Current spot
        grid[prevSpot.getrowNumber() - 1][prevSpot.getColumn() - 1]
                .setBorder(BorderFactory.createLineBorder(Color.yellow));

        // All possible moves piece could do
        Vector<Integer> moveableSpots = prevSpot.getPieceOn().getPossibleMoves();
        // Used to see if spots are allowed if it's path is blocked by a piece
        boolean Continuous = true;
        for (int count = 0; count < moveableSpots.size(); count += 2) {
            boolean availableSpace = grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1)- 1].isAvailable();

            // Knights don't get blocked
            if (prevSpot.getPieceOn().getName() == "Knight")
                Continuous = true;

            // Block of code checks logic with moveableSpots to see if it's a VALID move
            // (Pawn special move)
            if (prevSpot.getPieceOn().getName() == "Pawn") 
            {
                if (moveableSpots.elementAt(count + 1) == prevSpot.getColumn() && availableSpace) 
                {
                    System.out.println((moveableSpots.elementAt(count) - 1) + " , " + (moveableSpots.elementAt(count + 1) - 1));
                    grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                } 
                else if (!availableSpace && moveableSpots.elementAt(count + 1) != prevSpot.getColumn()&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
                {
                    grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                }
            }
            else if (moveableSpots.size() != 0 && availableSpace && Continuous)
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
            else if (!availableSpace && Continuous&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& !availableSpace&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& availableSpace) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = true;
            } 
            else
                Continuous = false;
        }
    }

    public void clearHighlights() {
        // resets the background color to its original color
        // needs to happen after they click off of a piece
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public int getTurn() {
        return turn == 1 ? 1 : 2;
    }

    public String getMoveInterp() {
        return moveinterp == null ? "" : moveinterp;
    }

}
