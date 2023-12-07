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
    private int turn = 1;                   // Whose turn it is

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
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);


        // int width = getWidth();
        // int height = getHeight();
        // if(width < height)
        //     height = width;
        // else if(width > height)
        //     width = height;
    
        // setSize(width, height);
    }


    private class MouseHandler implements MouseListener
    {
        public void mouseExited (MouseEvent e) { /*System.out.println("Moved out of spot");*/ }
            
        public void mouseEntered(MouseEvent e) { /*System.out.println("Moved into spot");*/ }

        // Moves the chess pieces around the board, haven't added the game rules to restrict pieces (Nick)
        public void mouseClicked (MouseEvent e)
        {
            // Having logic issues in clicked as pressed and released are also called
            System.out.println("Clicked");
        }

        // Dragging events
        public void mousePressed(MouseEvent e)
        {
            prevSpot = (Spot) getComponentAt(getMousePosition());
            System.out.println("Pressed at " + prevSpot.getrow() + prevSpot.getColumn());

            if (!prevSpot.isAvailable() && prevSpot.returnPieceTeam() == turn)
                possibleMoves();
        }

        public void mouseReleased(MouseEvent e)
        {
            curSpot = (Spot) getComponentAt(getMousePosition());
            System.out.println("Released at " + curSpot.getrow() + curSpot.getColumn());            // DEBUG
            
            // Used to place piece or deselect it
            if (!prevSpot.isAvailable())
            {
                System.out.println(((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41)));
                if (curSpot != prevSpot && ((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41)))
                {
                    curSpot.placePiece(prevSpot);
                    turn *= -1;
                }
                clearHighlights();
            }

            prevSpot = curSpot = null;
        }
    }

    public void possibleMoves()
    {
        // Current spot
        grid[prevSpot.getrowNumber() - 1][prevSpot.getColumn() - 1].setBorder(BorderFactory.createLineBorder(Color.yellow));

        // All possible moves piece could do
        Vector< Integer > moveableSpots = prevSpot.getPieceOn().getPossibleMoves();
        // Used to see if spots are allowed if it's path is blocked by a piece
        boolean Continuous = true;
        for (int count = 0; count < moveableSpots.size(); count += 2)
        {
            boolean availableSpace = grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].isAvailable();

            // Knights don't get blocked
            if (prevSpot.getPieceOn().getName() == "Knight")
                Continuous = true;

            // Block of code checks logic with moveableSpots to see if it's a VALID move
            if (moveableSpots.size() != 0 && availableSpace && Continuous)
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
            else if (!availableSpace && Continuous && grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam())
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
        }

    }


    public void showPossibleMoves(King k) throws Exception// NEED EXCEPTION HANDLING FOR OUT OF BOUNDS
    {
        int x = k.getCurrentSpot().getrowNumber();
        int y = k.getCurrentSpot().getcolumn();
        try {

        } catch (Exception exception) {

        }
    }

    public void showPossibleMoves(Queen q) throws Exception {
        int x = q.getCurrentSpot().getrowNumber() - 1;
        int y = q.getCurrentSpot().getcolumn() - 1;
        try {

        } catch (Exception exception) {

        }

    }

    public void showPossibleMoves(Bishop b) throws Exception {
        int x = b.getCurrentSpot().getrowNumber() - 1;
        int y = b.getCurrentSpot().getcolumn() - 1;
        try {

        } catch (Exception exception) {

        }

    }

    public void showPossibleMoves(Knight k) throws Exception {
        int x = k.getCurrentSpot().getrowNumber() - 1;
        int y = k.getCurrentSpot().getcolumn() - 1;
        try {

        } catch (Exception exception) {

        }

    }

    public void showPossibleMoves(Rook r) throws Exception {
        int x = r.getCurrentSpot().getrowNumber() - 1;
        int y = r.getCurrentSpot().getcolumn() - 1;
        int i = 7;
        //while( )

    }



    public void showPossibleMoves(Pawn p) throws Exception {
        int x = p.getCurrentSpot().getrowNumber() - 1;
        int y = p.getCurrentSpot().getcolumn() - 1;
        //boolean shownAll = false;
        int i = 1;
        //deal with first move in game being able to be two spots forward
        //check the three spots "in front of it", these will depend on which team, all of them will
        //if pawn gets to other side of the board, it can change to any piece the player wants




        // if(p.getCurrentSpot() ==  p.getStartingSpot()) //if it is at starting position
        // {

        // }
        // while(i >= -2)
        // {
        //     try 
        //     {
        //         if(p.getTeamNum() == 1)
        //         {
                    
        //             //grid[x][y+1].highlight();
        //             if(!grid[x-i][y+1].isAvailable()) //if the space is not taken by a piece
        //             {
        //                 if(grid[x-i][y+1].getPieceOn().getTeamNum() != p.getTeamNum())//if the piece diagonal up one is on the same team
        //                 {
        //                     grid[x-i][y+1].highlight();
        //                     i--;
        //                 }
        //             }
        //         }
        //         else
        //         {
        //             grid[x-i][y-1].highlight();
        //         }

                
            
        //     } 
        //     catch (Exception exception) 
        //     {
        //         i--;
        //     }
        // }


    }

    // one way to implement moves is by doing a bunch of function overriding for the
    // dif pieces



    public void removePiece(Pieces p)
    {
        /*
         * add code that removes the piece from teh board here, can use thi
         * when enemy captures a piece or 
         */
        p.getCurrentSpot().setAvailable(true); //move this to player
    }

    public void clearHighlights()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                //resets the background color to its original color
                //needs to happen after they click off of a piece
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
    }
}


