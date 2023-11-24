package Chess_Pieces;

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
    

    public void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        System.out.println("test");
    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
