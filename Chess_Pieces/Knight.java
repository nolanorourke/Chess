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

        moves.add(currentSpot.getrowNumber() - (2 * teamnum));
        moves.add(currentSpot.getColumn() - (1 * teamnum));
    
        moves.add(currentSpot.getrowNumber() - (2 * teamnum));
        moves.add(currentSpot.getColumn() + (1 * teamnum));
        
        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getColumn() + (2 * teamnum));

        moves.add(currentSpot.getrowNumber() - (1 * teamnum));
        moves.add(currentSpot.getColumn() - (2 * teamnum));

        moves.add(currentSpot.getrowNumber() + (2 * teamnum));
        moves.add(currentSpot.getColumn() - (1 * teamnum));
    
        moves.add(currentSpot.getrowNumber() + (2 * teamnum));
        moves.add(currentSpot.getColumn() + (1 * teamnum));
        
        moves.add(currentSpot.getrowNumber() + (1 * teamnum));
        moves.add(currentSpot.getColumn() + (2 * teamnum));

        moves.add(currentSpot.getrowNumber() + (1 * teamnum));
        moves.add(currentSpot.getColumn() - (2 * teamnum));

        for (Integer integer : moves)
            System.out.print(integer + " ");
        System.out.println();
        for (int count = 0; count < moves.size(); count += 2)
            if (moves.elementAt(count) <= 0 || moves.elementAt(count) > 8 || moves.elementAt(count + 1) <= 0 || moves.elementAt(count + 1) > 8)
            {
                moves.removeElementAt(count);
                moves.removeElementAt(count);
                count-=2;
            }
    
        return moves;
    }
    public boolean checkPossibleMove(Spot s)
    {
        return true;
    }
    
}
