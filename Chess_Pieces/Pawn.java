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
    public Pawn(int teamnum)
    {
        super("Pawn", teamnum);
    }

    public ImageIcon returnImage() { return pawnImage; }


    public Vector < Integer > getPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        Vector < Integer > moves = new Vector< Integer >();             // Row, Column
        
        System.out.println("At: " + (currentSpot.getrowNumber()) + "," + (currentSpot.getColumn()));
        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getColumn());
        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getColumn() + 1);
        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getColumn() - 1);

        if (currentSpot == startingSpot)
        {
            System.out.println("Double jump would be at " + (currentSpot.getrowNumber() - (2 * teamnum)) + ", " + currentSpot.getColumn());
            moves.add(currentSpot.getrowNumber() - (2 * teamnum));
            moves.add(currentSpot.getColumn());
        }

        for (int count = 0; count < moves.size(); count += 2)
        {
            if (moves.elementAt(count) <= -1 || moves.elementAt(count) >= 9 || moves.elementAt(count + 1) <= -1 || moves.elementAt(count + 1) >= 9)
            {
                moves.removeElementAt(count);
                moves.removeElementAt(count);
            }
        }


        return moves;
    }

    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
}
