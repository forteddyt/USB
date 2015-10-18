import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ThirdSwangProgram
{
    private JFrame frame;
    private JPanel panel;
    private JTextField p;
    private JLabel sP;
    private JPanel sT;
    private ButtonGroup bg = new ButtonGroup();

    public ThirdSwangProgram()
    {
        p = new JTextField();

        frame = new JFrame("-Codename: Wishies-");
        frame.setSize(320, 240);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        panel.add(new titleComponent(), BorderLayout.PAGE_START);
        panel.add(new seasonComponent(), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class titleComponent extends JLabel
    {
        public titleComponent()
        {
            setText("Seasonal Weather");
            setBackground(Color.CYAN);
            setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            setOpaque(true);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            setHorizontalAlignment(CENTER);
        }
    }

    private class seasonComponent extends JPanel
    {
        String[] seasons =
        { "Spring", "Summer", "Fall", "Winter" };

        public seasonComponent()
        {
            setLayout(new GridLayout(1, 2));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            sT = new seasonText();
            add(sT);
            sP = new seasonPicture();
            add(sP);
        }

        private class seasonText extends JPanel
        {
            public seasonText()
            {
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                for (int i = 0; i < 4; i++)
                {
                    JRadioButton tempText = new text(seasons[i]);
                    if (i == 0)
                    {
                        tempText.setSelected(true);
                    }
                    bg.add(tempText);
                    add(tempText);
                }
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            }

            private class text extends JRadioButton
            {
                public text(String s)
                {
                    setText(s);
                    addMouseListener(new updateSeason());
                }
            }

            private class updateSeason implements MouseListener
            {
                public void mouseClicked(MouseEvent e)
                {
                }

                public void mouseEntered(MouseEvent e)
                {
                }

                public void mouseExited(MouseEvent e)
                {
                }

                public void mousePressed(MouseEvent e)
                {
                }

                public void mouseReleased(MouseEvent e)
                {
                    String pic = ((JRadioButton) e.getSource()).getText();
                    sP.setIcon(new ImageIcon("src/" + pic + ".jpg"));
                }
            }
        }

        private class seasonPicture extends JLabel
        {
            public seasonPicture()
            {
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                setIcon(new ImageIcon("src/spring.jpg"));
            }
        }
    }

    public static void main(String[] args)
    {
        new ThirdSwangProgram();
    }
}
