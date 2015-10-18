package Tamaswingagotchi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuFrame extends JFrame
{
    private GameplayFrame gpF = new GameplayFrame();
    private static Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
    private static JFrame frame = null;
    private Font esquisito = null, mongoBondo = null;

    public MenuFrame()
    {
        try
        {
            esquisito = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "src/Tamaswingagotchi/Esquisito.ttf"));
            mongoBondo = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "src/Tamaswingagotchi/Mongo Bondo.ttf"));
        } catch (Exception e)
        {
            System.out.println("Error creating fonts.");
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setTitle("Menu");
        setLocation((int) ((screenSize.getWidth() / 2) - getWidth() / 2),
                (int) ((screenSize.getHeight() / 2) - getHeight() / 2));

        JPanel overlay = new JPanel(new BorderLayout());
        overlay.setBorder(BorderFactory.createLineBorder(Color.YELLOW.darker(),
                5));
        overlay.setBackground(Color.CYAN.darker().darker());

        JPanel options = new Options();
        JPanel title = new Title();

        overlay.add(title, BorderLayout.PAGE_START);
        overlay.add(options, BorderLayout.CENTER);

        add(overlay);
        setVisible(true);
    }

    public class Title extends JPanel
    {
        public Title()
        {
            setLayout(new BorderLayout());
            JLabel title = new JLabel("Tamagotchi");
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(esquisito.deriveFont(Font.PLAIN, 105));
            add(Box.createVerticalStrut(20), BorderLayout.PAGE_START);
            add(title, BorderLayout.CENTER);
            setOpaque(false);
        }
    }

    public class Options extends JPanel
    {
        public Options()
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setOpaque(false);

            JButton play = new JButton(" Play ");
            play.setAlignmentX(CENTER_ALIGNMENT);
            play.setFont(mongoBondo.deriveFont(Font.BOLD, 35));
            play.setFocusable(false);
            play.setBackground(Color.YELLOW.darker());
            play.setBorder(BorderFactory.createLineBorder(Color.GRAY.darker(),
                    3));
            play.addActionListener(new PlayAL());

            JButton info = new JButton(" Information ");
            info.setAlignmentX(CENTER_ALIGNMENT);
            info.setFont(mongoBondo.deriveFont(Font.BOLD, 35));
            info.setFocusable(false);
            info.setBackground(Color.YELLOW.darker());
            info.setBorder(BorderFactory.createLineBorder(Color.GRAY.darker(),
                    3));
            info.addActionListener(new InfoAL());

            JButton reset = new JButton(" Reset ");
            reset.setAlignmentX(CENTER_ALIGNMENT);
            reset.setFont(mongoBondo.deriveFont(Font.BOLD, 35));
            reset.setFocusable(false);
            reset.setBackground(Color.YELLOW.darker());
            reset.setBorder(BorderFactory.createLineBorder(Color.GRAY.darker(),
                    3));
            reset.addActionListener(new RresetAL());

            add(Box.createVerticalStrut(48));
            add(play);
            add(Box.createVerticalStrut(40));
            add(info);
            add(Box.createVerticalStrut(40));
            add(reset);
        }

        public class PlayAL implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                gpF.setMinimumSize(new Dimension(475, 400));
                gpF.setVisible(true);
            }
        }

        public class InfoAL implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        }

        public class RresetAL implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int yesNo = JOptionPane.YES_NO_OPTION;
                yesNo = JOptionPane.showConfirmDialog(getContentPane(),
                        "Are you sure you want to reset all progress?",
                        "Reset Data", yesNo);
                if (yesNo == JOptionPane.YES_OPTION)
                {
                    resetData();
                    JOptionPane.showMessageDialog(getContentPane(),
                            "Progress has been reset.", "Reset Data",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            public void resetData()
            {
                try
                {
                    File f = new File("src/Tamaswingagotchi/Progress.txt");
                    PrintWriter p = new PrintWriter(f);
                    p.write("");
                    p.flush();
                    p.close();
                } catch (Exception e)
                {
                    System.out.println("Error clearing progess.");
                }

            }
        }
    }

    public static void main(String[] args)
    {
        frame = new MenuFrame();
    }
}
