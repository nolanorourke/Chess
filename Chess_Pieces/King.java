// Name: Nicolas Azzi and Nolan O'Rourke
package Chess_Pieces;

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
        super('K', start);
    }
    public void displayPossibleMoves()
    {
        // No idea what this does
        // if()
        // {

        // }
    }
    public boolean checkPossibleMove(Spot s)
    {
        return s.isAvailable();
    }
}