public  class Bishop extends Pieces
{
    public Bishop()
    {
        super();
        setName("Bishop");
    }
    public Bishop(Spot start)
    {
        super('B', start);
    }
    

    public static void displayPossibleMoves()//calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {

    }
    public boolean checkPossibleMove(Spot s)
    {
        
    }
    
}
