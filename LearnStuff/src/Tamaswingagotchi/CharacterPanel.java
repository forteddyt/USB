package Tamaswingagotchi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CharacterPanel extends JPanel
{
    public CharacterPanel()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        JPanel buttonUIFormatter = new JPanel(new BorderLayout());
        buttonUIFormatter.setBackground(Color.CYAN.darker().darker());
        buttonUIFormatter.setOpaque(true);
        buttonUIFormatter.add(Box.createVerticalStrut(18),
                BorderLayout.PAGE_START);
        buttonUIFormatter.add(Box.createVerticalStrut(18),
                BorderLayout.PAGE_END);
        buttonUIFormatter.add(new ButtonUI(), BorderLayout.CENTER);
        buttonUIFormatter.setBorder(BorderFactory.createMatteBorder(8, 0, 0, 0,
                Color.BLACK));

        add(new playBackground(), BorderLayout.CENTER);
        add(buttonUIFormatter, BorderLayout.PAGE_END);
    }

    public class playBackground extends JPanel
    {
        public playBackground()
        {

        }

    }

    public class ButtonUI extends JPanel
    {
        public ButtonUI()
        {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(Color.CYAN.darker().darker());
            setOpaque(true);

            JButton meal = new JButton(new ImageIcon(
                    "src/Tamaswingagotchi/Meal.png"));
            meal.setFocusable(false);
            meal.setOpaque(false);
            meal.setContentAreaFilled(false);

            JButton snack = new JButton(new ImageIcon(
                    "src/Tamaswingagotchi/Snack.png"));
            snack.setFocusable(false);
            snack.setOpaque(false);
            snack.setContentAreaFilled(false);

            JButton play = new JButton(new ImageIcon(
                    "src/Tamaswingagotchi/Play.png"));
            play.setFocusable(false);
            play.setOpaque(false);
            play.setContentAreaFilled(false);

            JButton scold = new JButton(new ImageIcon(
                    "src/Tamaswingagotchi/Scold.png"));
            scold.setFocusable(false);
            scold.setOpaque(false);
            scold.setContentAreaFilled(false);

            JButton pill = new JButton(new ImageIcon(
                    "src/Tamaswingagotchi/Pill.png"));
            pill.setFocusable(false);
            pill.setOpaque(false);
            pill.setContentAreaFilled(false);

            Dimension betweenPref = new Dimension(18, 1);
            Dimension betweenMin = new Dimension(6, 1);
            Dimension betweenMax = new Dimension(112, 1);

            Dimension outsidePref = new Dimension();
            Dimension outsideMin = new Dimension(2, 1);
            Dimension outsideMax = new Dimension(300, 1);

            add(new Box.Filler(outsideMin, outsidePref, outsideMax));
            add(meal);
            add(new Box.Filler(betweenMin, betweenPref, betweenMax));
            add(snack);
            add(new Box.Filler(betweenMin, betweenPref, betweenMax));
            add(play);
            add(new Box.Filler(betweenMin, betweenPref, betweenMax));
            add(scold);
            add(new Box.Filler(betweenMin, betweenPref, betweenMax));
            add(pill);
            add(new Box.Filler(outsideMin, outsidePref, outsideMax));

        }
    }
}
