// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

public class Spot extends JPanel
{
    private char row;               // Row in chess terms
    private int rowNumber;          // What row it's at
    private int column;
    private Color spaceColor;
    private static boolean isTeam1Color = true;
    private boolean available = true;

    // Not too sure why we would need this but imma leave it (Nick)
    public Spot() 
    {
        row = ' ';
        rowNumber = 0;
        column = 0;
        chooseColor();
        available = true;

    }

    public Spot(int letter, int col) // for initializing with the letter and number
    {
        setBorder(BorderFactory.createLineBorder(Color.black));

        // ASCII casting
        row = (char) (letter + 65);
        rowNumber = (int) row - 64;

        column = col + 1;

        // Sets color
        chooseColor();        
        setBackground(spaceColor);
        if (column == 8)
            isTeam1Color = !isTeam1Color;

        available = true;
    }

    // Everything below I'm not really focusing on (Nick)
    public void chooseColor() {
        if (isTeam1Color)
            spaceColor = new Color(161, 118, 84);
        else
            spaceColor = new Color(214, 185, 163);

        // Flips teamcolor
        isTeam1Color = !isTeam1Color;
    }

    public boolean isAvailable() { return available; }

    public char getrow() { return row; }

    public int getrowNumber() { return rowNumber; }

    public int getcolumn() { return column; }

    public void setAvailable(boolean b) { available = b; }
}