package Chess_Pieces;

import java.util.Vector;

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
    

    public Vector < Integer > getPossibleMoves() //calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    {
        Vector < Integer > moves = new Vector< Integer >();
        //could do two possible vectors, for pieces that have finite number of moves, keeps track of different calculations, says if there is a piece on it or not before displaying


        for (int count = -1; count < 2; ++count)
        {
            
        }
        return new Vector<>();
    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
