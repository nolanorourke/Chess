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
        return new Vector<>();
    }

    public boolean checkPossibleMove(Spot s)
    {
        return s.isAvailable();
    }
    
}
