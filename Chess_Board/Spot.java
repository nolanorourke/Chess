// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.String;
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
    private static boolean isTeam1Color = false;
    private boolean available;      // Better to name this emptySpace, not too sure what this is for (Nick)
    private Pieces pieceOn;
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
            image = new JLabel(pieceOn.returnIconImg());
            add(image);
            available = false;
        }
        else if ((row == 'A' || row == 'H'))
        {
            if (column == 1 || column == 8)
                pieceOn = new Rook(this);
            else if(column == 2 || column == 7)
                pieceOn = new Knight(this);
            else if (column == 3 || column == 6)
                pieceOn = new Bishop(this);
            else if (column == 5 && row == 'A')
                pieceOn = new Queen(this);
            else if (column == 5 && row == 'H')
                pieceOn = new Queen(this);
            else if (column == 4 && row == 'A')
                pieceOn = new King(this);
            else
                pieceOn = new King(this);
            
            image = new JLabel(pieceOn.returnIconImg());
            add(image);
            available = false;
        }
        else
        {
            pieceOn = null;
            available = true;
            image = null;
        }

    }

    // Used to resize chess images but not working
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

    public int getColumn() { return column; }

    public void setAvailable(boolean b) { available = b; }

    public Color getSpotColor(){ return spaceColor; }

    public JLabel getPieceImg () { return image; }

    public void removePiece ()
    {
        remove(image);
        image = null;
        pieceOn = null;
        available = true;
        repaint();
    }

    public void placePiece (Spot prevSpot)
    {
        if (image != null)
            remove (image);
        image = prevSpot.getPieceImg();
        pieceOn = prevSpot.getPieceOn();
        available = false;
        add (image);
        prevSpot.removePiece();
        pieceOn.setCurrentSpot(this);
        repaint();
    }

    public void replacePiece (Pieces replacement)
    {
        removePiece();
        image = new JLabel(replacement.returnIconImg());
        pieceOn = replacement;
        available = false;
        add (image);
        repaint();
    }

    public void highlight()
    {
        setBackground(Color.YELLOW);
        
    }
    public void removeHighlight()
    {
        setBackground(spaceColor);
    }

    public String toString() { return (String.valueOf(getrow()) + String.valueOf(getColumn())); }

    //doing this for the gameboard class so we can check which piece is on which space
    public Pieces getPieceOn() { return pieceOn; }

    public int returnPieceTeam() { return pieceOn == null ? 0 : pieceOn.getTeamNum(); }
    
    public void setPieceOn(Pieces p) { pieceOn = p; }


}