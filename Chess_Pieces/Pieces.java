// Name: Nicolas Azzi and Nolan O'Rourke
package Chess_Pieces;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Chess_Board.Spot;
import java.util.Vector;


//for help with mouse controls, see the following examples: 11: 28_29, 31_32, 34_35 - drag, 
public abstract class Pieces implements Serializable {
    protected Spot startingSpot;        // I lied it's really useful
    protected Spot currentSpot;
    protected String name;
    protected boolean alive;
    protected int teamnum;
    protected ImageIcon pieceImage;

    public Pieces()
    {
        startingSpot = new Spot();
        currentSpot = new Spot();
        name = new String(" ");
        alive = true;
        teamnum = 0;
    }

    // K is King, Q is Queen, B is Bishop, N is Knight, R is Rook, and P is Pawn
    public Pieces(String piece, Spot start) 
    {    
        name = piece;
        startingSpot = start;
        currentSpot = start;
        alive = true;
        if(start.getrow() > 'D')
            teamnum = 1;
        else
            teamnum = 2;
        // Sets up the piece image
        //set the call up so that we can either copy and paste or add it to parent constructor and it shoudl work for all of them
        String temp = name + "Piece" + teamnum + ".png";
        pieceImage = new ImageIcon(getClass().getResource(temp));
        if (teamnum == 2)
            teamnum = -1;
        // pieceImage.setImage(pieceImage.getImage().getScaledInstance(startingSpot.getWidth(), startingSpot.getHeight(), Image.SCALE_SMOOTH));
    }

    public abstract Vector < Integer > getPossibleMoves();     // Better to make this return possible moves it COULD do, allows for Gameboard to handle the game

    public abstract boolean checkPossibleMove(Spot s);

    public ImageIcon returnIconImg()
    {
        return pieceImage;
    }

    public void moveTo(Spot s) { s.setAvailable(false); }

    public Spot getStartingSpot() { return startingSpot; }

    public Spot getCurrentSpot() { return currentSpot; }

    public String getName() { return name; }

    public boolean isAlive() { return alive; }

    public void setStartingSpot(Spot s) { startingSpot = s; }

    public void setCurrentSpot(Spot s) { currentSpot = s; }

    public void setName(String s) { name = s; }

    public void setAlive(boolean b) { alive = b; }

    public int getTeamNum() { return teamnum; }

    public boolean nextToPiece (Spot s)
    {
        if (s.getrow() - 1 == currentSpot.getrow() && s.getColumn() == currentSpot.getColumn() || s.getrow() + 1 == currentSpot.getrow() && s.getColumn() == currentSpot.getColumn()
            || s.getrow() == currentSpot.getrow() && s.getColumn() + 1 == currentSpot.getColumn() || s.getrow() == currentSpot.getrow() && s.getColumn() - 1 == currentSpot.getColumn()
            || s.getrow() + 1 == currentSpot.getrow() && s.getColumn() + 1 == currentSpot.getColumn() || s.getrow() - 1 == currentSpot.getrow() && s.getColumn() - 1 == currentSpot.getColumn()
            || s.getrow() - 1 == currentSpot.getrow() && s.getColumn() + 1 == currentSpot.getColumn() || s.getrow() + 1 == currentSpot.getrow() && s.getColumn() - 1 == currentSpot.getColumn()
            || s.getrow() + 1 == currentSpot.getrow() && s.getColumn() - 1 == currentSpot.getColumn() || s.getrow() - 1 == currentSpot.getrow() && s.getColumn() + 1 == currentSpot.getColumn())
            return true;
        return false;
    }
}