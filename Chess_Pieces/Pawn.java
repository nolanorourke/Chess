package Chess_Pieces;

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
    

    public static void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {

    }
}
