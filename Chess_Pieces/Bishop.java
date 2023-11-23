package Chess_Pieces;

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
    

    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
