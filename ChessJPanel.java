// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChessJPanel extends JPanel
{
    private int width, height;

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
        width = getWidth();
        height = getHeight();

        // Makes sure to update panels when resized
        revalidate();
    }


    public class EastPanel extends JPanel
    {
        private JLabel turns;
        private int fontSize;

        EastPanel ()
        {
            setLayout(new GridLayout(6, 1));

            turns = new JLabel("Player 1 turn");
            turns.setHorizontalAlignment(JLabel.CENTER);
            fontSize = turns.getFont().getSize();

            add(turns);
        }

        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            turns.setFont(new Font(turns.getFont().getName(), turns.getFont().getStyle(), (int) (fontSize * width * 0.002)));
            setPreferredSize(new Dimension((int) (width * 0.2), getPreferredSize().height));
        }
    }


    public class BottomPanel extends JPanel
    {
        BottomPanel ()
        {
            setLayout(new GridLayout(1, 5));

            for (int count = 0; count < 4; ++count)
                add(new JPanel());

            add(new JButton("Reset"));

        }

        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.1)));

        }
    }

    public class CenterPanel extends JPanel
    {
    
        CenterPanel ()
        {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.black));
            add(new TopPanel(), BorderLayout.NORTH);
        }
    }

    public class TopPanel extends JPanel
    {
        private JLabel title;
        private int fontSize;

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
            title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), (int) (fontSize * height * 0.003)));
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.07)));
        }
    }
}
