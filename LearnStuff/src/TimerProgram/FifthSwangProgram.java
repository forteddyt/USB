package TimerProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class FifthSwangProgram
{
    private JFrame frame;
    private static Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
    private JButton beginButton = null;
    private long start = 0, end = 0;
    private int phase = 0;
    private static double fastest = -1;
    private static JLabel highScore = new JLabel();

    public FifthSwangProgram()
    {
        try
        {
            File f = new File("src/TimerProgram/highest.txt");
            if (!f.exists())
            {
                f.createNewFile();
            }

            Scanner scan = new Scanner(f);
            if (scan.hasNextLine())
            {
                String s = scan.nextLine();
                double d = Double.parseDouble(s);
                if (d > 0)
                {
                    fastest = d;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        updateFastest();
        frame = new JFrame("Reaction Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(450, 350));
        frame.setResizable(false);
        frame.setLocation(
                (int) ((screenSize.getWidth() / 2) - frame.getWidth() / 2),
                (int) ((screenSize.getHeight() / 2) - frame.getHeight() / 2));

        JPanel overPanel = new JPanel(new BorderLayout());
        JTextArea description = new JTextArea();
        JPanel textFormatter = new JPanel(new BorderLayout());
        description
                .setText("This application will test how quick your reflexes are. \nWhen you begin the game, a Red Button will appear. When that button changes it's color to Green, click it as quick as possible.");
        description.setFont(description.getFont().deriveFont(Font.BOLD, 15));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFocusable(false);
        description.setEditable(false);
        description.setOpaque(false);
        textFormatter.add(description, BorderLayout.CENTER);
        textFormatter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(3, 3, 3, 3),
                        BorderFactory.createLineBorder(Color.YELLOW, 2)),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        textFormatter.setOpaque(false);
        overPanel.add(textFormatter, BorderLayout.PAGE_START);
        overPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        beginButton = new JButton("Begin");
        beginButton.addActionListener(new BeginAL());
        beginButton.setFocusable(false);
        beginButton.setBackground(Color.WHITE);
        JPanel beginFormatter = new JPanel(new BorderLayout());
        beginFormatter.add(beginButton, BorderLayout.CENTER);
        beginFormatter.add(Box.createVerticalStrut(50), BorderLayout.PAGE_END);
        beginFormatter
                .add(Box.createVerticalStrut(50), BorderLayout.PAGE_START);
        beginFormatter.add(Box.createHorizontalStrut(50),
                BorderLayout.LINE_START);
        beginFormatter
                .add(Box.createHorizontalStrut(50), BorderLayout.LINE_END);
        beginFormatter.setOpaque(false);

        overPanel.add(highScore, BorderLayout.PAGE_END);
        overPanel.add(beginFormatter, BorderLayout.CENTER);
        overPanel.setBackground(Color.GRAY);

        frame.add(overPanel);
        frame.setVisible(true);
    }

    public JLabel highScore()
    {
        if (getFastest() != -1)
        {
            highScore.setText("<html>The current fastest reaction is: "
                    + getFastest() + " seconds.</html>");
        } else
        {
            highScore
                    .setText("<html>There is currently no fastest time.</html>");
        }
        highScore.setFont(highScore.getFont().deriveFont(Font.BOLD, 15));
        highScore.setHorizontalAlignment(JLabel.CENTER);
        return highScore;
    }

    public double getFastest()
    {
        return fastest;
    }

    public void updateFastest()
    {
        if ((fastest == -1 || fastest > Integer.parseInt(end - start + "") / 1000.0)
                && Integer.parseInt(end - start + "") / 1000.0 != 0)
        {
            fastest = Integer.parseInt(end - start + "") / 1000.0;
        }
        highScore();
    }

    public class BeginAL implements ActionListener
    {
        Timer waitforit = null;

        public void actionPerformed(ActionEvent e)
        {
            if (phase == 0)
            {
                phase = 1;
                beginButton.setBackground(Color.RED);
                beginButton.setText("Wait for it...");
                new redButton();
            } else if (phase == 1)
            {
                waitforit.stop();
                phase = 0;
                beginButton.setBackground(Color.WHITE);
                beginButton.setText("Begin");
                JOptionPane
                        .showMessageDialog(frame,
                                "You weren't patient... wait until the box turns green!");
            } else if (phase == 2)
            {
                end = System.currentTimeMillis();
                phase = 0;
                updateFastest();
                beginButton.setBackground(Color.WHITE);
                beginButton.setText("Begin");
                JOptionPane.showMessageDialog(frame, "You reacted in "
                        + Integer.parseInt(end - start + "") / 1000.0
                        + " seconds.");

                PrintWriter pw;
                try
                {
                    pw = new PrintWriter(new File(
                            "src/TimerProgram/highest.txt"));
                    pw.print(getFastest() + "\n");
                    pw.flush();
                } catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        }

        public class redButton
        {

            public redButton()
            {
                waitforit = new Timer((int) (Math.random() * 4000) + 1000,
                        new switchRed());
                waitforit.start();
            }

            public class switchRed implements ActionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    phase = 2;
                    beginButton.setBackground(Color.GREEN);
                    beginButton.setText("Click it!");
                    start = System.currentTimeMillis();
                    waitforit.stop();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new FifthSwangProgram();
    }
}
