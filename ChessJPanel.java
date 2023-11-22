// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import Chess_Board.GameBoard;
import Chess_Board.Spot;

public class ChessJPanel extends JPanel
{
    // Used to dynamically resize
    private int width, height;
    private GameBoard board = new GameBoard();
    private Player player[]; //two Players
    ChessJPanel()
    {
        // Sets JPanel
        setLayout(new BorderLayout());

        // Adds panels
        add(new CenterPanel(), BorderLayout.CENTER);
        add(new EastPanel(), BorderLayout.EAST);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }

    // Used to dynamically get width, height, and make sure Panel is refereshed
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        width = getWidth();
        height = getHeight();
    }

    public class TopPanel extends JPanel
    {
        private JLabel title;
        private int fontSize;       // Needed to resize font

        TopPanel()
        {
            title = new JLabel("Player 1 vs Player 2");
            fontSize = title.getFont().getSize();
            add(title);
        }
        
        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.07)));

            // Resizes font
            title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), (int) (fontSize * height * 0.003)));
        }
    }

    public class CenterPanel extends JPanel
    {

        CenterPanel()
        {
            setLayout(new BorderLayout());
            add(new TopPanel(), BorderLayout.NORTH);
            add(new GameBoard());
            
        }

    }

    public class EastPanel extends JPanel
    {
        private JLabel turns;
        private MoveGrid moves;
        private int fontSize;       // Needed to resize font

        EastPanel ()
        {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            turns = new JLabel("Player 1 turn");
            turns.setHorizontalAlignment(JLabel.CENTER);
            fontSize = turns.getFont().getSize();


            // Sets the move grid
            moves = new MoveGrid();

            // Whose turn
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.gridy = 0;
            gbc.weighty = 2;
            add(turns, gbc);

            // Move Grid
            gbc.gridy = 1;
            gbc.gridheight = 6;
            gbc.weighty = 8;
            add(moves, gbc);

            // Adds pading
            gbc.gridy = 8;
            gbc.gridheight = 1;
            gbc.weighty = 1;
            add(new JPanel(), gbc);

        }

        // Dynamically resizes east Panel and components
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            moves.setWidth(width);
            setPreferredSize(new Dimension((int) (width * 0.2), getPreferredSize().height));
            turns.setFont(new Font(turns.getFont().getName(), turns.getFont().getStyle(), (int) (fontSize * width * 0.002)));
        }
    }


    public class BottomPanel extends JPanel
    {
        private JButton newGame, reset;

        BottomPanel ()
        {
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            
            // Buttons just for inital design
            newGame = new JButton("New Game");
            reset = new JButton("Reset");

            // Takes off the border around text when you click button
            newGame.setFocusPainted(false);
            reset.setFocusPainted(false);

            newGame.setPreferredSize(new Dimension(95, 26));
            
            add(newGame);
            add(reset);
            
        }

        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            // Resizes Frame
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.1)));

            // Resizes button and font
            newGame.setPreferredSize(new Dimension((int) (0.15 * width), (int) (0.06 * height)));
            newGame.setFont(new Font(newGame.getFont().getName(), newGame.getFont().getStyle(), (int) ((height + width) * 0.01)));
            reset.setPreferredSize(new Dimension((int) (0.12 * width), (int) (0.06 * height)));
            reset.setFont(new Font(reset.getFont().getName(), reset.getFont().getStyle(), (int) ((height + width) * 0.01)));
        }
    }
}
