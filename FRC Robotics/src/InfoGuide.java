import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InfoGuide extends JFrame
{
    private JPanel panel = new JPanel();
    private Color ILITEPurple = new Color(112, 63, 144),
            ILITEGreen = new Color(106, 175, 54), noneGrey = new Color(64, 64,
                    64);
    private static Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();

    public InfoGuide()
    {
        setSize(400, 450);
        setResizable(false);
        setTitle("Information - How To");

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(ILITEPurple.darker(), 5));

        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation((int) ((screenSize.getWidth() / 2) - getWidth() / 2),
                (int) ((screenSize.getHeight() / 2) - getHeight() / 2));
        setVisible(false);
    }
}
