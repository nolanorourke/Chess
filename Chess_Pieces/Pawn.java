package Chess_Pieces;

import java.awt.Image;
import javax.swing.ImageIcon;
import Chess_Board.Spot;

public class Pawn extends Pieces
{
    private ImageIcon pawnImage;
    public Pawn()
    {
        super();
        setName("Pawn");
    }

    public Pawn(Spot start)
    {
        super("Pawn", start);
    }

    public ImageIcon returnImage() { return pawnImage; }


    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }

    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
}
