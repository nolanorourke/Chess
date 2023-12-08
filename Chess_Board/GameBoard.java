// Name: Nicolas Azzi and Nolan O'Rourke

package Chess_Board;

import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import Chess_Pieces.*;

public class GameBoard extends JPanel 
{
    // Might not need this depending on how we implment code
    private Spot grid[][];
    private static Spot prevSpot, curSpot;
    private int turn = 1; // Whose turn it is
    private String moveinterp;



    public GameBoard()
    {
        setLayout(new GridLayout(8, 8));
        grid = new Spot[8][8];

        for (int row = 0; row < 8; ++row)
            for (int col = 0; col < 8; ++col)
                add(grid[row][col] = new Spot(row, col));

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
    }

    // Gotta figure out how to just make it a square, probably gonna ask Myers
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }

    private class MouseHandler implements MouseListener {
        public void mouseExited(MouseEvent e) { /* System.out.println("Moved out of spot"); */ }

        public void mouseEntered(MouseEvent e) { /* System.out.println("Moved into spot"); */ }

        // Moves the chess pieces around the board, haven't added the game rules to
        // restrict pieces (Nick)
        public void mouseClicked(MouseEvent e)
        { 
            // Having logic issues in clicked as pressed and released are also called
            System.out.println("Clicked");
        }

        // Dragging events
        public void mousePressed(MouseEvent e)
        {
            prevSpot = (Spot) getComponentAt(getMousePosition());
            System.out.println("Pressed at " + prevSpot.getrow() + prevSpot.getColumn());
            if (!prevSpot.isAvailable() && prevSpot.returnPieceTeam() == turn)
                possibleMoves();
        }

        public void mouseReleased(MouseEvent e)
        {
            try
            {
                curSpot = (Spot) getComponentAt(getMousePosition());
                System.out.println("Released at " + curSpot.getrow() + curSpot.getColumn()); // DEBUG

                // Used to place piece or deselect it
                if (!prevSpot.isAvailable()) {
                    System.out.println(((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41)));
                    if (curSpot != prevSpot && ((LineBorder) curSpot.getBorder()).getLineColor().equals(new Color(52, 219, 41))) 
                    {
                        String middle = " ";
                        if (curSpot.isAvailable())
                            middle = " - ";
                        else if (curSpot.getPieceOn().getTeamNum() != prevSpot.getPieceOn().getTeamNum())
                            middle = " x ";
                        curSpot.placePiece(prevSpot);
                        moveinterp = prevSpot.toString() + middle + curSpot.toString();
                        // if(curSpot.getPieceOn().getName() == "Pawn")
                        // {
                        //     if(curSpot.getPieceOn().getTeamNum() == 1)
                        //     {
                        //         if(curSpot.getrow() == 'A')
                        //         {
                        //             String temp = showChoices(curSpot);
                        //             moveinterp += "= " + temp.charAt(0);
                        //             curSpot.upgrade(temp);
                                    
                        //         }
                        //     }
                        //     else if(curSpot.getPieceOn().getTeamNum() == 2)
                        //     {
                        //         if(curSpot.getrow() == 'G')
                        //         {
                        //             String temp = showChoices(curSpot);
                        //             moveinterp += "= " + temp.charAt(0);
                        //             curSpot.upgrade(temp);                                }
                        //     }
                        // }
                        turn *= -1;
                        //moveinterp = curSpot.toString() + middle + prevSpot.toString();

                        if (curSpot.getPieceOn().getName() == "Pawn" && ((curSpot.returnPieceTeam() == 1 && curSpot.getrow() == 'A') || curSpot.returnPieceTeam() == -1 && curSpot.getrow() == 'H'))
                        {
                            System.out.println("PAWN CHANGE");
                            
                            String temp = showChoices(curSpot);
                            if(temp.compareTo("Rook") == 0)
                            {
                                curSpot.replacePiece(new Rook(curSpot.returnPieceTeam(), curSpot));
                            }
                            else if(temp.compareTo("Knight") == 0)
                            {
                                curSpot.replacePiece(new Knight(curSpot.returnPieceTeam(), curSpot));
                            }
                            else if(temp.compareTo("Bishop") == 0)
                            {
                                curSpot.replacePiece(new Bishop(curSpot.returnPieceTeam(), curSpot));
                            }
                            else if(temp.compareTo("Queen") == 0)
                            {
                                curSpot.replacePiece(new Queen(curSpot.returnPieceTeam(), curSpot));
                            }
                            else 
                                curSpot.replacePiece(new Queen(curSpot.returnPieceTeam(), curSpot));
                        }

                    }
                    clearHighlights();
                }
                prevSpot = curSpot = null;
            }
            catch (Exception except)
            {
                clearHighlights();
            }
        }
    }

    public void possibleMoves()
    {
        // Current spot
        grid[prevSpot.getrowNumber() - 1][prevSpot.getColumn() - 1]
                .setBorder(BorderFactory.createLineBorder(Color.yellow));

        // All possible moves piece could do
        Vector<Integer> moveableSpots = prevSpot.getPieceOn().getPossibleMoves();
        // Used to see if spots are allowed if it's path is blocked by a piece
        boolean Continuous = true;
        for (int count = 0; count < moveableSpots.size(); count += 2) {
            System.out.println((moveableSpots.elementAt(count) - 1) + " " + (moveableSpots.elementAt(count + 1)- 1));
            boolean availableSpace = grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1)- 1].isAvailable();

            // Knights don't get blocked
            if (prevSpot.getPieceOn().getName() == "Knight")
                Continuous = true;

            // Block of code checks logic with moveableSpots to see if it's a VALID move
            // (Pawn special move)
            if (prevSpot.getPieceOn().getName() == "Pawn") 
            {
                if (moveableSpots.elementAt(count + 1) == prevSpot.getColumn() && availableSpace && Continuous) 
                {
                    System.out.println((moveableSpots.elementAt(count) - 1) + " , " + (moveableSpots.elementAt(count + 1) - 1));
                    grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                }
                else if (moveableSpots.elementAt(count + 1) == prevSpot.getColumn() && !availableSpace)
                    Continuous = false;
                else if (!availableSpace && moveableSpots.elementAt(count + 1) != prevSpot.getColumn()&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
                {
                    grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                }
            }
            else if (moveableSpots.size() != 0 && availableSpace && Continuous)
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
            else if (!availableSpace && Continuous&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& !availableSpace&& grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].returnPieceTeam() != prevSpot.returnPieceTeam()) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = false;
            } 
            else if (prevSpot.getPieceOn().nextToPiece(grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1])&& availableSpace) 
            {
                grid[moveableSpots.elementAt(count) - 1][moveableSpots.elementAt(count + 1) - 1].setBorder(BorderFactory.createLineBorder(new Color(52, 219, 41)));
                Continuous = true;
            } 
            else
                Continuous = false;
        }
    }

    public void clearHighlights()
    {
        // resets the background color to its original color
        // needs to happen after they click off of a piece
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public int getTurn() { return turn == 1 ? 1 : 2; }

    public String getMoveInterp() {
        return moveinterp == null ? "" : moveinterp;
    }

    
    public String showChoices(Spot s) 
    {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        
        String options[] = {"Queen", "Bishop", "Knight", "Rook"};
        final String pieceChose;

        Object selected = JOptionPane.showInputDialog(null, "What would you like to upgrade to?", "Empassant", JOptionPane.DEFAULT_OPTION, null, options, "Queen");

        if(selected == null)
            return "Queen";
        return selected.toString();

    //     //empassantPopup = new JOptionPane();
    //     pieceOptions = new JRadioButton[options.length];
    //     ButtonGroup group = new ButtonGroup();

    //     for(int i = 0; i < pieceOptions.length; i++)
    //     {
    //         pieceOptions[i] = new JRadioButton(options[i]);
    //     }
    //     confirm.addActionListener(new ActionListener(){
    //         public void actionPerformed(ActionEvent e)
    //         {
    //             for(int i = 0; i < pieceOptions.length; i++)
    //             {
    //                 if(pieceOptions[i].isSelected())
    //                 {
    //                     temp = options[i];
    //                     frame.dispose();
    //                 }
    //             }
    //         }
    //     });
    //     JPanel window = new JPanel();
    //     for(int i = 0; i < pieceOptions.length; i++)
    //     {
    //         pieceOptions[i] = new JRadioButton(options[i]);
    //         group.add(pieceOptions[i]);
    //         window.add(pieceOptions[i]);
    //     }
    //     window.add(confirm);
    //     frame.add(window);
    //     frame.setVisible(true);
    //     frame.setSize(getWidth()/2, getHeight()/2);
    //     frame.setResizable(false);
    //     frame.setLocationRelativeTo(this);
    //     return temp;
    }
}
