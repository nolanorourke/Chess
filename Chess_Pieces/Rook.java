package Chess_Pieces;

import Chess_Board.Spot;

public class Rook extends Pieces
{
    public Rook()
    {
        super();
        setName("Rook");
    }
    public Rook(Spot start)
    {
        super('R', start);
    }
    

    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
