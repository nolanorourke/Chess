public class Queen extends Pieces
{
    public Queen()
    {
        super();
        setName("Queen");
    }
    public Queen(Spot start)
    {
        super('Q', start);
    }
    

    public static void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {
        
    }
    
}
