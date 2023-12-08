// Name: Nicolas Azzi and Nolan O'Rourke

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
public class Chess
{
    public static void main (String args[])
    {
        // Sets frame
        JFrame myFrame = new JFrame("Chess");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(650, 550);
        myFrame.setMinimumSize(new Dimension(700, 600));

        // Adds chess icon, had to do Chess.class to specifiy that it's static method
        myFrame.setIconImage(new ImageIcon( Chess.class.getResource("Chess_Piece.jpg") ).getImage());

        myFrame.add(new ChessJPanel());
        myFrame.setVisible(true);


        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic('g');
        JMenuItem newg = new JMenuItem("Start New Game");
        newg.setMnemonic('n');
        JMenuItem resg = new JMenuItem("Reset Current Game");
        resg.setMnemonic('r');
        gameMenu.add(newg);
        gameMenu.add(resg);
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('h');
        JMenuItem instruct = new JMenuItem("Open Instructions Page");
        helpMenu.add(instruct);

        JMenuBar gameBar = new JMenuBar();
        myFrame.setJMenuBar(gameBar);
        gameBar.add(gameMenu);
        gameBar.add(helpMenu);
        //if it doesnt show up right on startup, make a chesspanel variable, add it in the myframe.add, and then set it to visible right under this li
    }


}