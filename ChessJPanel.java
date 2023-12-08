
// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.net.URL;

import Chess_Board.GameBoard;
import Chess_Board.Spot;
import Chess_Pieces.*;

public class ChessJPanel extends JPanel {

    // Used to dynamically resize
    private int width, height;
    private GameBoard currentBoard;
    private CenterPanel centerPanel;
    private EastPanel eastPanel;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;

    ChessJPanel() {
        // Sets JPanel
        setLayout(new BorderLayout());

        // Adds panels
        centerPanel = new CenterPanel();
        eastPanel = new EastPanel();
        bottomPanel = new BottomPanel();
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
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
        private int fontSize, teamnum = 1; // Needed to resize font
        private String currentString = "";

        TopPanel() {
            title = new JLabel("Player " + currentBoard.getTurn() + " turn");
            fontSize = title.getFont().getSize();
            add(title);
        }

        // Dynamically resize
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.07)));

            // System.out.println(currentBoard.getMoveInterp() + " " + currentString);

            if (currentString != currentBoard.getMoveInterp()) {
                currentString = currentBoard.getMoveInterp();
                eastPanel.setTurnNum(eastPanel.addMoveToTable(currentString, teamnum, eastPanel.getTurnNum()));
                if (teamnum == 1)
                    ++teamnum;
                else
                    --teamnum;
            }
            title.setText("Player " + currentBoard.getTurn() + " turn");
            // Resizes font
            title.setFont(
                    new Font(title.getFont().getName(), title.getFont().getStyle(), (int) (fontSize * height * 0.003)));
        }
    }

    public class CenterPanel extends JPanel 
    {
        CenterPanel() {
            setLayout(new BorderLayout());
            currentBoard = new GameBoard();
            topPanel = new TopPanel();
            add(topPanel, BorderLayout.NORTH);
            add(currentBoard);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

    }

    public class EastPanel extends JPanel 
    {
        private JLabel turns;
        private MoveGrid moves;
        private int fontSize; // Needed to resize font
        private DefaultTableModel moveHistory;
        private JTable moveHistoryTable;
        private JScrollPane scroller;
        private int turnNum;

        EastPanel() {
            turnNum = 1;
            moveHistory = new DefaultTableModel() {
                // overwriting the isCellEditable function to always be false
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            moveHistoryTable = new JTable(moveHistory);
            scroller = new JScrollPane(moveHistoryTable);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(scroller);

            moveHistory.addColumn("Turn");
            moveHistory.addColumn("Team 1 Move");
            moveHistory.addColumn("Team 2 Move");

            moveHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            moveHistoryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            moveHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            scroller.setPreferredSize(new Dimension(250, getHeight()));
        }

        // Dynamically resizes east Panel and components
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            moveHistoryTable.getColumnModel().getColumn(0).setWidth(getWidth() / 20);
            moveHistoryTable.getColumnModel().getColumn(1).setWidth(getWidth() / 10);
            moveHistoryTable.getColumnModel().getColumn(2).setWidth(getWidth() / 10);
            scroller.setPreferredSize(new Dimension(moveHistoryTable.getColumnModel().getColumn(0).getWidth() +
                    moveHistoryTable.getColumnModel().getColumn(1).getWidth() +
                    moveHistoryTable.getColumnModel().getColumn(2).getWidth(), getHeight()));
        }

        public int addMoveToTable(String move, int teamnum, int turn) {
            if (teamnum == 1) {
                // since team one goes first, it will create a new row
                moveHistory.addRow(new Object[] { turn, null, null });
                moveHistory.setValueAt(move, moveHistory.getRowCount() - 1, 1);
                return turn+1;
            } else if (teamnum == 2) {

                moveHistory.setValueAt(move, moveHistory.getRowCount() - 1, 2);
            }
            return turn;
            // adds a rows, the first thing is the move count, second is team one move,
            // third is team two move
        }

        public void undoMoveHistory() {
            // for IF we write undo button in bottom panel
        }

        public void clearMoveHistory() {
            moveHistory.getDataVector().removeAllElements();
        }
        public int getTurnNum()
        {
            return turnNum;
        }
        public void setTurnNum(int n)
        {
            turnNum = n;
        }
    }

    public class BottomPanel extends JPanel 
    {
        private JButton newGame, reset, instructions;
        private JLabel newGameLabel, resetLabel, howToPlayLabel;
        private Icon newGame1, newGame2, reset1, reset2, howToPlay1, howToPlay2;

        BottomPanel() 
        {
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, getWidth()/3));
            // newGame1 = new ImageIcon(getClass().getResource("NewGame1.png"));
            // newGame2 = new ImageIcon(getClass().getResource("NewGame2.png"));

            // reset1 = new ImageIcon(getClass().getResource("Reset1.png"));

            // howToPlay1 = new ImageIcon(getClass().getResource("HowToPlay1.png"));
            // howToPlay2 = new ImageIcon(getClass().getResource("HowToPlay2.png"));  

            //resizes images using function, stay tuned
            newGame1 = resizeImageIcon(new ImageIcon(getClass().getResource("NewGame1.png")));
            reset1 = resizeImageIcon(new ImageIcon(getClass().getResource("Reset1.png")));
            reset2 = resizeImageIcon(new ImageIcon(getClass().getResource("Reset2.png")));
            howToPlay1 = resizeImageIcon(new ImageIcon(getClass().getResource("HowToPlay1.png")));
            newGame2 = resizeImageIcon(new ImageIcon(getClass().getResource("NewGame2.png")));
            howToPlay2 = resizeImageIcon(new ImageIcon(getClass().getResource("HowToPlay2.png")));

            newGameLabel = new JLabel("New Game", newGame1, SwingConstants.RIGHT);
            newGameLabel.setToolTipText("Start a new game");
            newGameLabel.setVerticalTextPosition( SwingConstants.BOTTOM );
            newGameLabel.setHorizontalTextPosition( SwingConstants.CENTER );


            resetLabel = new JLabel("Reset", reset1, SwingConstants.LEFT);
            resetLabel.setToolTipText("Reset current game to start");
            resetLabel.setVerticalTextPosition( SwingConstants.BOTTOM );
            resetLabel.setHorizontalTextPosition( SwingConstants.CENTER );


            howToPlayLabel = new JLabel("How To Play", howToPlay1, SwingConstants.CENTER);
            howToPlayLabel.setToolTipText("Open page to Instructions");
            howToPlayLabel.setVerticalTextPosition( SwingConstants.BOTTOM );
            howToPlayLabel.setHorizontalTextPosition( SwingConstants.CENTER );

            MouseHandler mousey = new MouseHandler();
            newGameLabel.addMouseListener(mousey);
            resetLabel.addMouseListener(mousey);
            howToPlayLabel.addMouseListener(mousey);

            add(newGameLabel);
            add(resetLabel);
            add(howToPlayLabel);
//15_16
            //Resets game button
            // newGame = new JButton("New Game", newGame1);
            // newGame.addActionListener(new ActionListener()
            // {
            //     public void actionPerformed(ActionEvent e)
            //     {

            //     }
            // });
            // reset = new JButton("Reset", reset1);
            // reset.addActionListener(new ActionListener() 
            // {
            //     public void actionPerformed(ActionEvent e) 
            //     {
                    
            //     }
            // });
            // //instructions = new JButton("How To Play", howToPlay1);



            // //Takes off the border around text when you click button
            // newGame.setFocusPainted(false);
            // reset.setFocusPainted(false);

            // newGame.setPreferredSize(new Dimension(95, 26));

            // add(newGame);
            // add(reset);

        }
        private class MouseHandler implements MouseListener {
        public void mouseExited(MouseEvent e) {
            if(e.getSource() ==newGameLabel)
            {
                newGameLabel.setIcon(newGame1);
            }
            else if(e.getSource() == resetLabel)
            {
                resetLabel.setIcon(reset1);
            }
            else if(e.getSource() == howToPlayLabel)
            {
                howToPlayLabel.setIcon(howToPlay1);
            }

         }

        public void mouseEntered(MouseEvent e) {
            if(e.getSource() ==newGameLabel)
            {
                newGameLabel.setIcon(newGame2);
            }
            else if(e.getSource() == resetLabel)
            {
                resetLabel.setIcon(reset2);
            }
            else if(e.getSource() == howToPlayLabel)
            {
                howToPlayLabel.setIcon(howToPlay2);
            }

         }

        public void mouseClicked(MouseEvent e) {
            if(e.getSource() ==newGameLabel)
            {
                newGameLabel.setIcon(newGame2);
                newGameLabel.setIcon(newGame1);
                startNewGame();
            }
            else if(e.getSource() == resetLabel)
            {
                resetLabel.setIcon(reset2);
                resetLabel.setIcon(reset1);
                resetBoard();
            }
            else if(e.getSource() == howToPlayLabel)
            {
                howToPlayLabel.setIcon(howToPlay2);
                howToPlayLabel.setIcon(howToPlay1);
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e){ 
        }
    }

    private ImageIcon resizeImageIcon(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the size as needed
        return new ImageIcon(scaledImage);
    }
        // Dynamically resize
        public void paintComponent(Graphics g) {
            // Resizes Frame
            super.paintComponent(g);
            //setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.1)));




            // Resizes button and font
            // newGameLabel.setPreferredSize(new Dimension((int) (0.15 * width), (int) (0.06 * height)));
            // newGameLabel.setFont(new Font(newGame.getFont().getName(), newGame.getFont().getStyle(), (int) ((height + width) * 0.01)));
            // resetLabel.setPreferredSize(new Dimension((int) (0.12 * width), (int) (0.06 * height)));
            //resetLabel.setFont(new Font(reset.getFont().getName(), reset.getFont().getStyle(), (int) ((height + width) * 0.01)));
        }
    }

    public void resetBoard()
    {
        remove(currentBoard);
        remove(centerPanel);
        remove(topPanel);
        currentBoard = new GameBoard();
        centerPanel = new CenterPanel();
        topPanel = new TopPanel();
        add(centerPanel);

        eastPanel.setTurnNum(eastPanel.addMoveToTable("RESET", 1, 0));
        eastPanel.setTurnNum(eastPanel.addMoveToTable("RESET", 2, 0));
        eastPanel.setTurnNum(1);
        update(getGraphics());
        
    }
    public void startNewGame()
    {
        resetBoard();
        eastPanel.clearMoveHistory();
        eastPanel.turnNum = 1;
        update(getGraphics());
    }
}