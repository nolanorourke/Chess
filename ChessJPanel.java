
// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import Chess_Board.GameBoard;
import Chess_Board.Spot;
import Chess_Pieces.*;

public class ChessJPanel extends JPanel {

    // Used to dynamically resize
    private int width, height;
    private GameBoard currentBoard;
    private CenterPanel centerPanel;
    private EastPanel eastPanel;

    ChessJPanel() {
        // Sets JPanel
        setLayout(new BorderLayout());

        // Adds panels
        centerPanel = new CenterPanel();
        eastPanel = new EastPanel();
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
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
                eastPanel.addMoveToTable(currentString, teamnum);
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
            add(new TopPanel(), BorderLayout.NORTH);
            add(currentBoard);
            eastPanel = new EastPanel();

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

        // Resets the chess board
        public void resetBoard() {
            remove(currentBoard);
            currentBoard = new GameBoard();
            add(currentBoard);
            eastPanel.clearMoveHistory();
            update(getGraphics());
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

        EastPanel() {
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

        public void addMoveToTable(String move, int teamnum) {
            if (teamnum == 1) {
                // since team one goes first, it will create a new row
                moveHistory.addRow(new Object[] { moveHistory.getRowCount() + 1, null, null });
                moveHistory.setValueAt(move, moveHistory.getRowCount() - 1, 1);
            } else if (teamnum == 2) {

                moveHistory.setValueAt(move, moveHistory.getRowCount() - 1, 2);
            }
            // adds a rows, the first thing is the move count, second is team one move,
            // third is team two move
        }

        public void undoMoveHistory() {
            // for IF we write undo button in bottom panel
        }

        public void clearMoveHistory() {
            moveHistory.getDataVector().removeAllElements();
        }
    }

    public class BottomPanel extends JPanel 
    {
        private JButton newGame, reset;

        BottomPanel() {
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

            // Resets game button
            newGame = new JButton("New Game");
            newGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    centerPanel.resetBoard();
                }
            });
            reset = new JButton("Reset");

            // Takes off the border around text when you click button
            newGame.setFocusPainted(false);
            reset.setFocusPainted(false);

            newGame.setPreferredSize(new Dimension(95, 26));

            add(newGame);
            add(reset);

        }

        // Dynamically resize
        public void paintComponent(Graphics g) {
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