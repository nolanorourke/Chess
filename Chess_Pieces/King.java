// Name: Nicolas Azzi and Nolan O'Rourke
package Chess_Pieces;

import java.util.Vector;

import Chess_Board.Spot;

public class King extends Pieces
{
    private boolean switched = false; //if it switched with a rook 
    public King()
    {
        super();
        setName("King"); 
    }
    public King(Spot start)
    {
        super("King", start);
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