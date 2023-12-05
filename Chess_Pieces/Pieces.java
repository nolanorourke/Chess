// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Pieces;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Chess_Board.Spot;


//for help with mouse controls, see the following examples: 11: 28_29, 31_32, 34_35 - drag, 
public abstract class Pieces implements Serializable {
    protected Spot startingSpot;        // Not too sure how this would be helpful, better if we have future spot (Nick)
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
        if(start.getrow() < 3)
            teamnum = 1;
        else
            teamnum = 2;
        // Sets up the piece image
        //set the call up so that we can either copy and paste or add it to parent constructor and it shoudl work for all of them
        String temp = name + "Piece" + teamnum + ".png";
        pieceImage = new ImageIcon(getClass().getResource(temp));
        // pieceImage.setImage(pieceImage.getImage().getScaledInstance(startingSpot.getWidth(), startingSpot.getHeight(), Image.SCALE_SMOOTH));
    }

    public abstract void displayPossibleMoves(); // calls check PossibleMove, glows if possible to move, glows other
                                                 // color if can capture piece

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
}