// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import Chess_Pieces.*;

public class Spot extends JPanel
{
    private char row;               // Row in chess terms
    private int rowNumber;          // What row it's at
    private int column;
    private Color spaceColor;
    private static boolean isTeam1Color = true;
    private boolean available;
    private Pieces pieceOn;
    private ImageIcon pieceImage;
    private JLabel image;

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
        // Sets feel of board
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());

        // ASCII casting
        row = (char) (letter + 65);
        rowNumber = (int) row - 64;

        column = col + 1;

        // Sets color
        chooseColor();        
        setBackground(spaceColor);
        if (column == 8)
            isTeam1Color = !isTeam1Color;

        // For Pawns, adds image
        if (row == 'B' || row == 'G')
        {
            pieceOn = new Pawn(this);
            pieceImage = pieceOn.returnImage();
            image = new JLabel(pieceImage);
            add(image);
        }

        available = true;
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        // Dynamically resize but image becomes blurry idk why... (Nick)
        /*
        if (row == 'B' || row == 'G')
        {
            pieceImage = pieceOn.returnImage();
            pieceImage.setImage(pieceImage.getImage().getScaledInstance((int) (getWidth() * 0.8), (int) (getHeight() * 0.9), Image.SCALE_SMOOTH));
            image.setIcon(pieceImage);

        }*/
        
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

    public void setAvailable(boolean b) {
        available = b;
    }

    public void highlight()
    {
        JPanel highlight = new JPanel();
        highlight.setBackground(Color.YELLOW);
        add(highlight);
    }
    //doing this for the gameboard class so we can check which piece is on which space
    public Pieces getPieceOn()
    {
        return pieceOn;
    }
    public void setPieceOn(Pieces p)
    {
        pieceOn = p;
    }

}