// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private boolean available;      // Better to name this emptySpace, not too sure what this is for (Nick)
    private Pieces pieceOn;
    private ImageIcon pieceImage;
    private JLabel image;
    private static ImageIcon moveImage = null;          // Copies the image to current panel
    private static Spot prevSpot;                       // Used for deleting prev image (Chess piece)
    private Spot curSpot;                               //feel like these last two should not be in the this class (Nolan)

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
            pieceImage = pieceOn.returnIconImg();
            image = new JLabel(pieceImage);
            add(image);
        }
        else
            pieceOn = null;
        //MouseHandler handler = new MouseHandler();
        //addMouseListener(handler);

        available = true;
        curSpot = this;
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

    public int getcolumn() { return column; }

    public void setAvailable(boolean b) { available = b; }

    public Color getSpotColor(){ return spaceColor; }

    public void removePiece ()
    {
        remove(image);
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

    //doing this for the gameboard class so we can check which piece is on which space
    public Pieces getPieceOn() { return pieceOn; }
    public void setPieceOn(Pieces p) { pieceOn = p; }


    // Starting to move mouse movements to Game Board (Nick)
    /*// Mouse events below
    private class MouseHandler implements MouseListener
    {
        
        public void mouseExited (MouseEvent e) { /*System.out.println("Moved out of spot"); }
            
        public void mouseEntered(MouseEvent e) { /*System.out.println("Moved into spot"); }

        // Moves the chess pieces around the board, haven't added the game rules to restrict pieces (Nick)
        public void mouseClicked (MouseEvent e)
        {
            // Will have better naming convention by Saturday
            if (moveImage == null && pieceImage != null)
            {
                moveImage = pieceImage;
                prevSpot = curSpot;
            }
            else if (moveImage != null && pieceOn == null && prevSpot != curSpot)
            {
                pieceImage = moveImage;
                image = new JLabel(pieceImage);
                add(image);
                moveImage = null;
                prevSpot.removePiece();
                prevSpot = null;
            }
            else
            {
                moveImage = null;
                prevSpot = null;
            }
        }

        // Dragging events
        public void mousePressed(MouseEvent e)
        {
            System.out.println("Moused pressed on " + row + column);
        }
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released on " + row + column);
        }

    }*/

}