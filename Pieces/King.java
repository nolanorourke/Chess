// Name: Nicolas Azzi and Nolan O'Rourke

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
        super('K', start);
    }
    public static void displayPossibleMoves()
    {

        if()
        {

        }
    }
    public boolean checkPossibleMove(Spot s)
    {
        return s.isAvailable();
    }
}