package Tamaswingagotchi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameplayFrame extends JFrame
{
    private static Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();

    public GameplayFrame()
    {
        setTitle("Tamaswingagotchi");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new GameplayPanel());

        setLocation((int) ((screenSize.getWidth() / 2) - getWidth() / 2),
                (int) ((screenSize.getHeight() / 2) - getHeight() / 2));

        setResizable(false);
        setVisible(false);
    }

    public class GameplayPanel extends JPanel
    {
        public GameplayPanel()
        {
            setLayout(new BorderLayout());
            add(new StatsPanel(), BorderLayout.LINE_END);
            add(new CharacterPanel(), BorderLayout.CENTER);
        }
    }
}
