// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import Chess_Board.GameBoard;
import Chess_Board.Spot;
import Chess_Pieces.*;

public class ChessJPanel extends JPanel
{
    // Used to dynamically resize
    private int width, height;
    private GameBoard board = new GameBoard();
    private Player player[]; //two Players [0] is 1, [1] is 2
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
        private DefaultTableModel moveHistory;
        private JTable moveHistoryTable;
        

        EastPanel ()
        {
            moveHistory = new DefaultTableModel();
            moveHistoryTable = new JTable(moveHistory);
            JScrollPane scroller = new JScrollPane(moveHistoryTable);

            setLayout(new GridBagLayout());
            add(scroller);
            GridBagConstraints gbc = new GridBagConstraints();
            moveHistory.addColumn("Turn");
            moveHistory.addColumn("Team 1 Move");
            moveHistory.addColumn("Team 2 Move");

            
            //turns = new JLabel("Player 1 turn");
            //turns.setHorizontalAlignment(JLabel.CENTER);
            //fontSize = turns.getFont().getSize();


            // Sets the move grid
            //MoveGrid moves = new MoveGrid();
           
            // Whose turn
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.gridy = 0;
            gbc.weighty = 2;
            // add(moveList, gbc);

            //MoveGrid
            //gbc.gridy = 1;
            //gbc.gridheight = 6;
            //gbc.weighty = 8;
            add(scroller, gbc);

            //Adds padding
            // gbc.gridy = 8;
            // gbc.gridheight = 1;
            // gbc.weighty = 1;
            // add(new JPanel(), gbc);
            
            Dimension maxWidth = new Dimension(200, Integer.MAX_VALUE);
            setMaximumSize(maxWidth);
            //setPreferredSize(maxWidth); //for some reason this fucks up the code

        }

        // Dynamically resizes east Panel and components
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            moves.setWidth(width);
            setPreferredSize(new Dimension((int) (width * 0.2), getPreferredSize().height));
            turns.setFont(new Font(turns.getFont().getName(), turns.getFont().getStyle(), (int) (fontSize * width * 0.002)));
        }

        public void addMoveToTable(String move, int teamnum)
        {
            if(teamnum == 1)
            {
                //since team one goes first, it will create a new row
                moveHistory.addRow(new Object[]{moveHistory.getRowCount()+1, null,null}); 
                moveHistory.setValueAt(move, moveHistory.getRowCount()-1, 1);
            }   
            else
            {
                moveHistory.setValueAt(move, moveHistory.getRowCount()-1, 2);
            }
            //adds a rows, the first thing is the move count, second is team one move, third is team two move

        }
        public void undoMoveHistory()
        {
            //for IF we write undo button in bottom panel
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

    public void takePiece(Player taking, Spot s, Pieces p)//not sure what to make the parameters
    {
        taking.IncrementTaken();
        if(taking.getPlayerNumber() == 1)
            player[1].IncrementLost();
        else
            player[0].IncrementLost();

    }
}
