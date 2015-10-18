import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemberGuide extends JFrame
{
    private JPanel panel = new JPanel();
    private Color ILITEPurple = new Color(112, 63, 144),
            ILITEGreen = new Color(106, 175, 54), noneGrey = new Color(64, 64,
                    64);
    private static Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();

    public MemberGuide()
    {
        setSize(400, 450);
        setResizable(false);
        setTitle("Member Setup");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(ILITEPurple.darker(), 5));

        JPanel pixEdit = new JPanel(new BorderLayout());
        JLabel pixPic = new JLabel(new ImageIcon(""));
        JPanel pixPicFormatter = new JPanel(new BorderLayout());
        pixPicFormatter.add(pixPic, BorderLayout.LINE_END);
        pixPicFormatter.add(Box.createHorizontalStrut(20),
                BorderLayout.LINE_START);
        pixEdit.add(pixPicFormatter, BorderLayout.LINE_START);

        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation((int) ((screenSize.getWidth() / 2) - getWidth() / 2),
                (int) ((screenSize.getHeight() / 2) - getHeight() / 2));
        setVisible(false);
    }
}
