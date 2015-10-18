import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingQuiz1
{
    private JFrame frame = new JFrame("Swing Quiz 1");
    private ArrayList<button> ew = new ArrayList<button>();
    private int counter = 1;
    private final Font FONT = new JLabel().getFont();

    public SwingQuiz1()
    {   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(480, 650);
        JPanel overlay = new JPanel();
        overlay.setLayout(new BorderLayout());
        overlay.setBackground(Color.WHITE);
        JLabel checkers = new JLabel("Checkers");
        checkers.setForeground(Color.BLUE);
        checkers.setFont(FONT.deriveFont(Font.PLAIN, 105));
        checkers.setHorizontalAlignment(JLabel.CENTER);
        overlay.add(checkers, BorderLayout.PAGE_START);

        JPanel outerBox = new JPanel();
        outerBox.setLayout(new BorderLayout());
        outerBox.setBackground(Color.YELLOW);
        outerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel innerBox = new JPanel();
        innerBox.setLayout(new GridLayout(8, 8));
        innerBox.setBackground(Color.BLACK);
        boolean bleh = false;
        for (int i = 0; i < 64; i++)
        {
            if (i % 8 == 0)
            {
                bleh = !bleh;
            }

            button b = new button(-1);
            if (bleh)
            {
                ew.add(b = new button(i % 2));
            } else
            {
                ew.add(b = new button((i - 1) % 2));
            }

            if (b.type == 1)
            {
                counter++;
            }

            innerBox.add(b);
        }
        innerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        outerBox.add(Box.createVerticalStrut(10), BorderLayout.PAGE_START);
        outerBox.add(Box.createVerticalStrut(10), BorderLayout.PAGE_END);
        outerBox.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START);
        outerBox.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);
        outerBox.add(innerBox, BorderLayout.CENTER);
        overlay.add(outerBox, BorderLayout.CENTER);
        overlay.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START);
        overlay.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);
        overlay.add(Box.createVerticalStrut(10), BorderLayout.PAGE_END);
        frame.add(overlay);
        frame.setVisible(true);
    }

    public void reset()
    {
        for (button b : ew)
        {
            if (b.type == 0)
            {
                b.setBackground(Color.RED);
            } else
            {
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setForeground(Color.WHITE);
            }

        }
    }

    public class button extends JButton
    {
        private int type;

        public button(int color)
        {
            addActionListener(new bAction());
            type = color;
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder());
            setFocusable(false);

            if (type == 0)
            {
                setBackground(Color.RED);
            } else
            {
                setText(counter + "");
                setBackground(Color.BLACK);
            }
        }

        public class bAction implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                reset();

                if (((button) (e.getSource())).type == 1)
                {
                    ((button) (e.getSource())).setBorder(BorderFactory
                            .createLineBorder(Color.WHITE, 3));
                    ((button) (e.getSource())).setForeground(Color.YELLOW);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new SwingQuiz1();
    }
}
