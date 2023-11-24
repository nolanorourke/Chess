// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;
import Chess_Board.Spot;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Chess_Pieces.*;

public class GameBoard extends JPanel {
    // Might not need this depending on how we implment code
    private Spot grid[][];

    public GameBoard()
    {
        setLayout(new GridLayout(8, 8));

        for (int row = 0; row < 8; ++row)
            for (int col = 0; col < 8; ++col)
                add(new Spot(row, col));
        
    }

    // Gotta figure out how to just make it a square, probably gonna ask Myers
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
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
        try {

        } catch (Exception exception) {

        }

    }

    public void showPossibleMoves(Pawn p) throws Exception {
        int x = p.getCurrentSpot().getrowNumber() - 1;
        int y = p.getCurrentSpot().getcolumn() - 1;
        //boolean shownAll = false;
        int i = 1;
        while(i >= -2)
        {
            try 
            {
                if(p.getTeamNum() == 1)
                {
                    
                    //grid[x][y+1].highlight();
                    if(!grid[x-i][y+1].isAvailable()) //if the space is not taken by a piece
                    {
                        if(grid[x-i][y+1].getPieceOn().getTeamNum() != p.getTeamNum())//if the piece diagonal up one is on the same team
                        {
                            grid[x-i][y+1].highlight();
                            i--;
                        }
                    }
                }
                else
                {
                    grid[x-i][y-1].highlight();
                }

                
            
            } 
            catch (Exception exception) 
            {
                i--;
            }
        }
    }

    // one way to implement moves is by doing a bunch of function overriding for the
    // dif pieces

}


