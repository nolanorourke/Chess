package Chess_Pieces;

import java.util.Vector;

import Chess_Board.Spot;

public class Bishop extends Pieces
{
    public Bishop()
    {
        super();
        setName("Bishop");
    }
    public Bishop(Spot start)
    {
        super("Bishop", start);
    }
    

    public Vector < Integer > getPossibleMoves() //calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        return new Vector<>();
    }

    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
