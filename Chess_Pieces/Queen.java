package Chess_Pieces;

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
        super('Q', start);
    }

 
    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }

    public boolean checkPossibleMove(Spot s)
    {
        return s.isAvailable();
    }
    
}
