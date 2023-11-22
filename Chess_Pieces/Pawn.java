package Chess_Pieces;

import Chess_Board.Spot;

public class Pawn extends Pieces
{
    public Pawn()
    {
        super();
        setName("Pawn");
    }
    public Pawn(Spot start)
    {
        super('P', start);
    }
    

    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
}
