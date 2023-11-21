// Name: Nicolas Azzi and Nolan O'Rourke

import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.JPanel;


public class GameBoard extends JPanel
{
    private Spot grid[][];
    private GridLayout layout;

    public GameBoard()
    {
        layout = new GridLayout(8, 8);
        setLayout(layout);
        for(int i = 7; i <= 0; i--)
        {
            for(int j = 0; j < 8; j++)
                grid[i][j] = new Spot(j, i); //defines it based on the array constructor in spot
        }
        
    }
    public void showPossibleMoves(King k) throws Exception//NEED EXCEPTION HANDLING FOR OUT OF BOUNDS
    {
        int x = k.getCurrentSpot().getHorznumber();
        int y = k.getCurrentSpot().getVertical();
        try
        {

        }
        catch (Exception exception)
        {

        }
    }
    public void showPossibleMoves(Queen q) throws Exception
    {
        int x = q.getCurrentSpot().getHorznumber() - 1;
        int y = q.getCurrentSpot().getVertical() - 1;
        try
        {

        }
        catch (Exception exception)
        {

        }
        
    }
    public void showPossibleMoves(Bishop b) throws Exception
    {
        int x = b.getCurrentSpot().getHorznumber() - 1;
        int y = b.getCurrentSpot().getVertical() - 1;
        try
        {

        }
        catch (Exception exception)
        {

        }
        
    }
    public void showPossibleMoves(Knight k) throws Exception
    {
        int x = k.getCurrentSpot().getHorznumber() - 1;
        int y = k.getCurrentSpot().getVertical() - 1;
        try
        {

        }
        catch (Exception exception)
        {

        }
        
    }
    public void showPossibleMoves(Rook r) throws Exception
    {
        int x = r.getCurrentSpot().getHorznumber() - 1;
        int y = r.getCurrentSpot().getVertical() - 1;
        try
        {

        }
        catch (Exception exception)
        {

        }
        
    }
    public void showPossibleMoves(Pawn p) throws Exception
    {
        int x = p.getCurrentSpot().getHorznumber() - 1;
        int y = p.getCurrentSpot().getVertical() - 1;
        try
        {

        }
        catch (Exception exception)
        {

        }   
    }


    //one way to implement moves is by doing a bunch of function overriding for the dif pieces

}
