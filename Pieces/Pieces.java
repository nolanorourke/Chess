// Name: Nicolas Azzi and Nolan O'Rourke

import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//for help with mouse controls, see the following examples: 11: 28_29, 31_32, 34_35 - drag, 
public abstract class Pieces implements Serializable
{
    protected Spot startingSpot;
    protected Spot currentSpot;
    protected String name;
    protected boolean alive;
    public Pieces()
    {
        startingSpot = new Spot();
        currentSpot = new Spot();
        name = new String(" ");
        alive = true;
    }

    //K is King, Q is Queen, B is Bishop, N is Knight, R is Rook, and P is Pawn
    public Pieces(char abbreviation, Spot start) 
    {
        switch(Character.toUpperCase(abbreviation))
        {
            case 'K':
            name = new String("King");
            break;
            case 'Q':
            name = new String("Queen");
            break;
            case 'B':
            name = new String("Bishop");
            break;
            case 'N':
            name = new String("Knight");
            break;
            case 'R':
            name = new String("Rook");
            break;
            case 'P':
            name = new String("Pawn");
            break;
            default:
            name = new String(" ");
        }
        startingSpot = start;
        currentSpot = start;
        alive = true;
    }

    public static abstract void displayPossibleMoves(); //calls check PossibleMove, glows if possible to move, glows other color if can capture piece
    public abstract boolean checkPossibleMove(Spot s); 

    public void moveTo(Spot s)
    {
        s.setAvailable(false);
        
    }
    
    public Spot getStartingSpot()
    {
        return startingSpot;
    }
    public Spot getCurrentSpot()
    {
        return currentSpot;
    }
    public String getName()
    {
        return name;
    }
    public boolean isAlive()
    {
        return alive;
    }

    public void setStartingSpot(Spot s)
    {
        startingSpot = s;
    }
    public void setCurrentSpot(Spot s)
    {
        currentSpot = s;
    }
    public void setName(String s)
    {
        name = s;
    }
    public void setAlive(boolean b)
    {
        alive = b;
    }


}