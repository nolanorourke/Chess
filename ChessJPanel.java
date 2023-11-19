// Name: Nicolas Azzi and Nolan O'Rourke
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
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
        revalidate();
    }


    public class EastPanel extends JPanel
    {
        EastPanel ()
        {
            setBorder(BorderFactory.createLineBorder(Color.black));
        }

        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setPreferredSize(new Dimension((int) (width * 0.2), getPreferredSize().height));
        }
    }


    public class BottomPanel extends JPanel
    {
        BottomPanel ()
        {
            setBorder(BorderFactory.createLineBorder(Color.black));
        }

        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.08)));
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

        TopPanel()
        {
            setBorder(BorderFactory.createLineBorder(Color.black));
        }
        
        // Dynamically resize
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setPreferredSize(new Dimension(getPreferredSize().width, (int) (height * 0.07)));
        }
    }
}
