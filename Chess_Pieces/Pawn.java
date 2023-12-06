package Chess_Pieces;

import java.awt.Image;
import java.util.Vector;

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


    public Vector < Integer > getPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        Vector < Integer > moves = new Vector< Integer >();             // Row, Column
        
        System.out.println("At: " + (currentSpot.getrowNumber()) + "," + (currentSpot.getcolumn()));
        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getcolumn());

        if (currentSpot == startingSpot)
        {
            moves.add(currentSpot.getrowNumber() - (2 * teamnum));
            moves.add(currentSpot.getcolumn());
        }

        for (Integer integer : moves)
        {
            System.out.println(integer);
            if (integer < 0)
                moves.remove(integer);
        }

        return moves;
    }

    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
}
