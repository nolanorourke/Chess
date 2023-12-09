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

import Chess_Pieces.Pieces;
import Chess_Pieces.Queen;

public class GameBoard extends JPanel
{
    // Might not need this depending on how we implment code
    private Spot grid[][];
    private static Spot prevSpot, curSpot;
    private int turn = 1; // Whose turn it is
    private String moveinterp;
    private boolean Check = false;
    private Pieces pieceChecking;
    private Vector<Integer> checkGrid = new Vector<Integer>();

    public GameBoard()
    {
        setLayout(new GridLayout(8, 8));
        grid = new Spot[8][8];

        for (int row = 0; row < 8; ++row)
            for (int col = 0; col < 8; ++col)
                add(grid[row][col] = new Spot(row, col));

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
    }

    // Gotta figure out how to just make it a square, probably gonna ask Myers
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }

    private class MouseHandler implements MouseListener {
        public void mouseExited(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}

        // Clicked not allowed
        public void mouseClicked(MouseEvent e) {}

        // Dragging events
        public void mousePressed(MouseEvent e)
        {
            prevSpot = (Spot) getComponentAt(getMousePosition());
            System.out.println("Pressed at " + prevSpot.getrow() + prevSpot.getColumn());
            if (!prevSpot.isAvailable() && prevSpot.returnPieceTeam() == turn)
                highlightMoveableSpots(possibleMoves());
        }

        public void mouseReleased(MouseEvent e)
        {
            try
            {
                curSpot = (Spot) getComponentAt(getMousePosition());
                System.out.println("Released at " + curSpot.getrow() + curSpot.getColumn()); // DEBUG

                // Used to place piece or deselect it
                if (!prevSpot.isAvailable())
                {
                    if (curSpot != prevSpot && ((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41))) 
                    {
                        String middle = " ";
                        if (curSpot.isAvailable())
                            middle = " - ";
                        else if (curSpot.getPieceOn().getTeamNum() != prevSpot.getPieceOn().getTeamNum())
                            middle = " x ";
                        curSpot.placePiece(prevSpot);
                        if (Check)
                        {
                            Check = false;
                        }
                        turn *= -1;
                        moveinterp = curSpot.toString() + middle + prevSpot.toString();

                        if (curSpot.getPieceOn().getName() == "Pawn" && ((curSpot.returnPieceTeam() == 1 && curSpot.getrow() == 'A') || curSpot.returnPieceTeam() == -1 && curSpot.getrow() == 'H'))
                        {
                            System.out.println("PAWN CHANGE");
                            curSpot.replacePiece(new Queen(curSpot.returnPieceTeam(), curSpot));
                        }

                        checkForCheck();
                        if (Check)
                        {
                            curSpot.getPieceOn().setCheckingPiece(true);
                            pieceChecking = curSpot.getPieceOn();
                        }
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



    
    public void checkForCheck()
    {
        Vector<Integer> moveSpots = curSpot.getPieceOn().getPossibleMoves(),
        validmoves = new Vector<Integer>();
        
        boolean Continuous = true;
        System.out.println("Beginning of checkForCheck");
        for (int count = 0; count < moveSpots.size(); count += 2)
        {
            boolean availableSpace = grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1)- 1].isAvailable();

            // Knights don't get blocked
            if (curSpot.getPieceOn().getName() == "Knight")
                Continuous = true;

            // Block of code checks logic with moveSpots to see if it's a VALID move
            // (Pawn special move)
            if (curSpot.getPieceOn().getName() == "Pawn") 
            {
                if (moveSpots.elementAt(count + 1) == curSpot.getColumn() && availableSpace && Continuous) 
                {
                    validmoves.add(moveSpots.elementAt(count) - 1);
                    validmoves.add(moveSpots.elementAt(count + 1) - 1);
                }
                else if (moveSpots.elementAt(count + 1) == curSpot.getColumn() && !availableSpace)
                {
                    Continuous = false;
                }
                else if (!availableSpace && moveSpots.elementAt(count + 1) != curSpot.getColumn()&& grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1].returnPieceTeam() != curSpot.returnPieceTeam()) 
                {
                    validmoves.add(moveSpots.elementAt(count) - 1);
                    validmoves.add(moveSpots.elementAt(count + 1) - 1);
                }
            }
            else if (moveSpots.size() != 0 && availableSpace && Continuous)
            {
                validmoves.add(moveSpots.elementAt(count) - 1);
                validmoves.add(moveSpots.elementAt(count + 1) - 1);
            }
            else if (!availableSpace && Continuous && grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1].returnPieceTeam() != curSpot.returnPieceTeam()) 
            {
                validmoves.add(moveSpots.elementAt(count) - 1);
                validmoves.add(moveSpots.elementAt(count + 1) - 1);
                Continuous = false;
                // For when the spot behind it is still in the path of the king
                if (grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1].getPieceOn().getName() == "King")
                {
                    System.out.println("Added back king space");
                    validmoves.add(moveSpots.elementAt(count + 2) - 1);
                    validmoves.add(moveSpots.elementAt(count + 3) - 1);
                }
            } 
            else if (curSpot.getPieceOn().nextToPiece(grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1])&& !availableSpace&& grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1].returnPieceTeam() != curSpot.returnPieceTeam()) 
            {
                validmoves.add(moveSpots.elementAt(count) - 1);
                validmoves.add(moveSpots.elementAt(count + 1) - 1);
                Continuous = false;
            }
            else if (curSpot.getPieceOn().nextToPiece(grid[moveSpots.elementAt(count) - 1][moveSpots.elementAt(count + 1) - 1])&& availableSpace) 
            {
                validmoves.add(moveSpots.elementAt(count) - 1);
                validmoves.add(moveSpots.elementAt(count + 1) - 1);
                Continuous = true;
            } 
            else
                Continuous = false;
        }

        System.out.println("CHECKING FOR CHECK...");
        System.out.println(validmoves.size()/2);

        for (int count = 0; count < validmoves.size() && Check == false; count += 2)
        {
            if (curSpot.getPieceOn().nextToPiece(grid[validmoves.elementAt(count)][validmoves.elementAt(count + 1)]))
                checkGrid.clear();

                checkGrid.add(validmoves.elementAt(count));
                checkGrid.add(validmoves.elementAt(count + 1));
            if (grid[validmoves.elementAt(count)][validmoves.elementAt(count + 1)].hasPieceOn() && grid[validmoves.elementAt(count)][validmoves.elementAt(count + 1)].getPieceOn().getName() == "King")
            {
                Check = true;
                if (validmoves.size() > count + 3)
                {
                    checkGrid.add(validmoves.elementAt(count + 2));
                    checkGrid.add(validmoves.elementAt(count + 3));
                }
            }
        }
    }



    public Vector<Integer> possibleMoves()
    {

        // All possible moves piece could do
        Vector<Integer> moveableSpots = prevSpot.getPieceOn().getPossibleMoves(),
                        validMoves = new Vector<Integer>();
        // Used to see if spots are allowed if it's path is blocked by a piece
        boolean Continuous = true;
        for (int count = 0; count < moveableSpots.size(); count += 2)
        {
            boolean availableSpace = grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1)- 1].isAvailable();

            // Knights don't get blocked
            if (prevSpot.getPieceOn().getName() == "Knight")
                Continuous = true;

            // Block of code checks logic with moveableSpots to see if it's a VALID move
            // (Pawn special move)
            if (prevSpot.getPieceOn().getName() == "Pawn")
            {
                if (moveableSpots.elementAt(count + 1) == prevSpot.getColumn() && availableSpace && Continuous)
                {
                    validMoves.add(moveableSpots.elementAt(count) - 1);
                    validMoves.add(moveableSpots.elementAt(count + 1) - 1);
                }
                else if (moveableSpots.elementAt(count + 1) == prevSpot.getColumn() && !availableSpace)
                    Continuous = false;
                else if (!availableSpace && moveableSpots.elementAt(count + 1) != prevSpot.getColumn()
                        && grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
                {
                    validMoves.add(moveableSpots.elementAt(count) - 1);
                    validMoves.add(moveableSpots.elementAt(count + 1) - 1);
                }
            }
            else if (moveableSpots.size() != 0 && availableSpace && Continuous)
            {
                validMoves.add(moveableSpots.elementAt(count) - 1);
                validMoves.add(moveableSpots.elementAt(count + 1) - 1);
            }
            else if (!availableSpace && Continuous&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                validMoves.add(moveableSpots.elementAt(count) - 1);
                validMoves.add(moveableSpots.elementAt(count + 1) - 1);
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& !availableSpace&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                validMoves.add(moveableSpots.elementAt(count) - 1);
                validMoves.add(moveableSpots.elementAt(count + 1) - 1);
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& availableSpace) 
            {
                validMoves.add(moveableSpots.elementAt(count) - 1);
                validMoves.add(moveableSpots.elementAt(count + 1) - 1);
                Continuous = true;
            } 
            else
                Continuous = false;
        }

        return validMoves;
    }




    public void highlightMoveableSpots(Vector<Integer> moves)
    {
        // Current spot
        grid[prevSpot.getrowNumber() - 1][prevSpot.getColumn() - 1].setBorder(BorderFactory.createLineBorder(Color.yellow));

        if (!Check && prevSpot.getPieceOn().getName() != "King")
            for (int count = 0; count < moves.size(); count += 2)
                grid[moves.elementAt(count)][moves.elementAt(count + 1)].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
        else if (!Check && prevSpot.getPieceOn().getName() == "King")
        {
            System.out.println("CHECKING FOR ILLEGAL MOVES");
            for (int count = 0; count < moves.size(); count += 2)
                if (moveAbleSpot(grid[moves.elementAt(count)][moves.elementAt(count + 1)]))
                    grid[moves.elementAt(count)][moves.elementAt(count + 1)].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
        }
        else if (Check && prevSpot.getPieceOn().getName() == "King")
        {
            System.out.println("KING PLUS CHECK");
            for (int count = 0; count < moves.size(); count += 2)
            {
                boolean inPath = false, illegalMove = false;
                for (int count2 = 0; count2 < checkGrid.size(); count2 += 2)
                    if (checkGrid.elementAt(count2) == moves.elementAt(count) && checkGrid.elementAt(count2 + 1) == moves.elementAt(count + 1))
                        inPath = true;
                    else if (!moveAbleSpot(grid[moves.elementAt(count)][moves.elementAt(count + 1)]))
                        illegalMove = true;
                if (!inPath && !illegalMove)
                        grid[moves.elementAt(count)][moves.elementAt(count + 1)].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
            }
        }
        else if (Check)
            for (int count = 0; count < moves.size(); count += 2)
                for (int count2 = 0; count2 < checkGrid.size(); count2 += 2)
                {
                    System.out.println(moves.elementAt(count));
                    System.out.println(moves.elementAt(count + 1));
                    if (checkGrid.elementAt(count2) == moves.elementAt(count) && checkGrid.elementAt(count2 + 1) == moves.elementAt(count + 1))
                    {
                        grid[moves.elementAt(count)][moves.elementAt(count + 1)].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                    }
                }
    }



    public boolean moveAbleSpot (Spot movingInto)
    {
        Spot temp = prevSpot;
        int currentTeamNumber = prevSpot.returnPieceTeam();
        for (int row = 0; row < 8; ++row)
            for (int col = 0; col < 8; ++col)
            {
                if (grid[row][col].hasPieceOn() && grid[row][col].returnPieceTeam() != currentTeamNumber)
                {
                    prevSpot = grid[row][col];
                    Vector<Integer> moves = possibleMoves();
                    for (int count = 0; count < moves.size(); count+=2)
                    {
                        if (movingInto.getrowNumber() - 1 == moves.elementAt(count) && movingInto.getColumn() - 1 == moves.elementAt(count + 1))
                        {
                            prevSpot = temp;
                            System.out.println("MOVEABLESPOT IS FALSE");
                            return false;
                        }
                    }
                }
            }
        prevSpot = temp;
        return true;
    }

    public void clearHighlights()
    {
        // resets the background color to its original color
        // needs to happen after they click off of a piece
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public int getTurn() { return turn == 1 ? 1 : 2; }

    public String getMoveInterp() { return moveinterp == null ? "" : moveinterp; }

}
