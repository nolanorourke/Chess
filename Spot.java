// Name: Nicolas Azzi and Nolan O'Rourke

import javax.swing.JPanel;
import java.awt.Color;

public class Spot extends JPanel
{
    private char horizontal;
    private int horznumber;
    private int vertical;
    private Color spaceColor;
    private static boolean isTeam1Color = true;
    private boolean available = true;
    private JPanel space;

    public Spot()
    {
        horizontal = ' ';
        horznumber = 0;
        vertical = 0;
        spaceColor = chooseColor();
        available = true;
        space = new JPanel();
        
    }
    public Spot(char letter, int number) //for initializing with the letter and number
    {
        horizontal = Character.toUpperCase(letter);
        horznumber = (int)horizontal - 64;
        vertical = number;
        spaceColor = chooseColor();
        available = true;
        space = new JPanel();
    }
    public Spot(int hor, int vert) //for array use
    {
        horznumber = hor+1;
        switch(hor)
        {
            case 0:
                horizontal = 'A';
                break;
            case 1:
                horizontal = 'B';
                break;
            case 2:
                horizontal = 'C';
                break;
            case 3:
                horizontal = 'D';
                break;
            case 4:
                horizontal = 'E';
                break;
            case 5:
                horizontal = 'F';
                break;
            case 6: 
                horizontal = 'G';
                break;
            case 7:
                horizontal = 'H';
                break;
            default:
            horizontal = ' ';
        }
        //could have also done this by adding hor to the ascii value of 'A', but i love switch statements
        vertical = vert + 1;
        spaceColor = chooseColor();
        available = true;
    }

    public static Color chooseColor()
    {
        Color c;
        if(isTeam1Color)
        {
            c = Color.BLACK;
            isTeam1Color = false;
        }
        else
        {
            c = Color.WHITE;
            isTeam1Color = true;
        }
        return c;
    }

    public boolean isAvailable()
    {
        return available;
    }
    public char getHorizontal()
    {
        return horizontal;
    }
    public int getHorznumber()
    {
        return horznumber;
    }
    public int getVertical()
    {
        return vertical;
    }
    
    public void setAvailable(boolean b)
    {
        available = b;
    }
}