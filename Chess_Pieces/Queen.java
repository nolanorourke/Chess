package Chess_Pieces;

import java.util.Vector;

import Chess_Board.Spot;

public class Queen extends Pieces
{
    public Queen ()
    {
        super();
        setName("Queen");
    }

    public Queen(Spot start)
    {
        super("Queen", start);
    }

 
    public Vector < Integer > getPossibleMoves() //calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        Vector < Integer > moves = new Vector< Integer >();

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() - (count * teamnum));
            moves.add(currentSpot.getColumn());
        }
        
        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() + (count * teamnum));
            moves.add(currentSpot.getColumn());
        }

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber());
            moves.add(currentSpot.getColumn() + (count * teamnum));
        }

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber());
            moves.add(currentSpot.getColumn() - (count * teamnum));
        }

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() - (count * teamnum));
            moves.add(currentSpot.getColumn() - (count * teamnum));
        }
        
        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() + (count * teamnum));
            moves.add(currentSpot.getColumn() + (count * teamnum));
        }

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() + (count * teamnum));
            moves.add(currentSpot.getColumn() - (count * teamnum));
        }

        for (int count = 1; count < 8; ++count)
        {
            moves.add(currentSpot.getrowNumber() - (count * teamnum));
            moves.add(currentSpot.getColumn() + (count * teamnum));
        }

        for (int count = 0; count < moves.size(); count += 2)
            if (moves.elementAt(count) <= 0 || moves.elementAt(count) > 8 || moves.elementAt(count + 1) <= 0 || moves.elementAt(count + 1) > 8)
            {
                moves.removeElementAt(count);
                moves.removeElementAt(count);
                count-=2;
            }

        return moves;
    }

    public boolean checkPossibleMove(Spot s)
    {
        return s.isAvailable();
    }
    
}
