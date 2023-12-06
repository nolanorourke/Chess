package Chess_Pieces;

import java.util.Vector;

import Chess_Board.Spot;

public class Knight extends Pieces
{
    public Knight()
    {
        super();
        setName("Knight");
    }
    public Knight(Spot start)
    {
        super("Knight", start);
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
