import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FourthSwangProgram
{
    private static JFrame accountManager;
    private static displayHeroesCreator displayHeroes;
    private static addHeroesCreator addHeroes;
    private static deleteHeroesCreator deleteHeroes;
    private static Color myYellow = new Color(255, 255, 204);
    private static Font meermansFont = null;

    public FourthSwangProgram()
    {
        try
        {
            meermansFont = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "src/big_noodle_titling.ttf"));
            meermansFont = meermansFont.deriveFont(Font.BOLD, 35);
        } catch (Exception e)
        {
        }

        accountManager = new accountManagerCreator();
        displayHeroes = new displayHeroesCreator();
        addHeroes = new addHeroesCreator();
        deleteHeroes = new deleteHeroesCreator();

        accountManager.setVisible(true);
    }

    private class accountManagerCreator extends JFrame
    {
        public accountManagerCreator()
        {
            setTitle("Account Manager");
            setSize(400, 465);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            add(new accManagerUI());
        }

        private class accManagerUI extends JPanel
        {
            private JButton button1, button2, button3;

            public accManagerUI()
            {
                setLayout(new BorderLayout());
                JPanel buttonPanel = new JPanel();
                JPanel titlePanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel,
                        BoxLayout.Y_AXIS));
                buttonPanel.setBackground(myYellow);
                titlePanel
                        .setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
                titlePanel.setBackground(myYellow);

                JLabel title = new JLabel();
                title.setText("Account Management");
                title.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0,
                        Color.black));
                title.setFont(meermansFont);

                title.setAlignmentX(CENTER_ALIGNMENT);
                title.setBackground(myYellow);
                title.setOpaque(true);

                button1 = new JButton();
                button1.setText("Display Heroes");
                button1.setFont(meermansFont.deriveFont(Font.PLAIN, 20));
                button1.setAlignmentX(CENTER_ALIGNMENT);
                button1.addActionListener(new accManagerAL());

                button2 = new JButton();
                button2.setText("Add Hero");
                button2.setFont(meermansFont.deriveFont(Font.PLAIN, 20));
                button2.setAlignmentX(CENTER_ALIGNMENT);
                button2.addActionListener(new accManagerAL());

                button3 = new JButton();
                button3.setText("Delete Hero");
                button3.setFont(meermansFont.deriveFont(Font.PLAIN, 20));
                button3.setAlignmentX(CENTER_ALIGNMENT);
                button3.addActionListener(new accManagerAL());

                button1.setMaximumSize(new Dimension(
                        button1.getMinimumSize().width, button1
                                .getMinimumSize().height));
                button2.setMaximumSize(new Dimension(
                        button1.getMinimumSize().width, button2
                                .getMinimumSize().height));
                button3.setMaximumSize(new Dimension(
                        button1.getMinimumSize().width, button3
                                .getMinimumSize().height));

                titlePanel.add(Box.createVerticalStrut(25));
                titlePanel.add(title);
                add(titlePanel, BorderLayout.PAGE_START);
                buttonPanel.add(Box.createVerticalStrut(75));
                buttonPanel.add(button1);
                buttonPanel.add(Box.createVerticalStrut(50));
                buttonPanel.add(button2);
                buttonPanel.add(Box.createVerticalStrut(50));
                buttonPanel.add(button3);
                add(buttonPanel, BorderLayout.CENTER);
            }

            private class accManagerAL implements ActionListener
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    try
                    {
                        if (((JButton) ae.getSource()).equals(button1))
                        {
                            if (!displayHeroes.isVisible())
                            {
                                displayHeroes.setLocation((int) (accountManager
                                        .getLocation().getX()),
                                        (int) (accountManager.getLocation()
                                                .getY() + 25));
                                displayHeroes.setVisible(true);
                            }
                        } else if (((JButton) ae.getSource()).equals(button2))
                        {
                            if (!addHeroes.isVisible())
                            {
                                addHeroes.setLocation((int) (accountManager
                                        .getLocation().getX()),
                                        (int) (accountManager.getLocation()
                                                .getY() + accountManager
                                                .getHeight() / 6.5));
                                addHeroes.setVisible(true);
                            }
                        } else if (((JButton) ae.getSource()).equals(button3))
                        {
                            if (!deleteHeroes.isVisible())
                            {
                                deleteHeroes.setLocation((int) (accountManager
                                        .getLocation().getX()),
                                        (int) (accountManager.getLocation()
                                                .getY() + accountManager
                                                .getHeight() / 4));
                                deleteHeroes.setVisible(true);
                            }
                        }
                        deleteHeroes.updateOptions();
                    } catch (Exception e)
                    {

                    }
                }
            }
        }
    }

    public class displayHeroesCreator extends JFrame
    {
        private specifics s = null;
        private heroesUI heroUI;

        public displayHeroesCreator()
        {
            setTitle("Hero Database");
            setSize(400, 465);
            setResizable(false);
            addWindowListener(new WL());

            heroUI = new heroesUI();

            add(heroUI);
        }

        public class WL implements WindowListener
        {

            @Override
            public void windowActivated(WindowEvent e)
            {
            }

            @Override
            public void windowClosed(WindowEvent e)
            {
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                if (s != null)
                {
                    s.dispose();
                }
                s = null;
                dispose();
            }

            @Override
            public void windowDeactivated(WindowEvent e)
            {
            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowOpened(WindowEvent e)
            {
                // TODO Auto-generated method stub

            }
        }

        public heroesUI getHeroUI()
        {
            return heroUI;
        }

        public specifics getSpecifics()
        {
            return s;
        }

        private class heroesUI extends JPanel
        {
            private TreeMap<String, String> heroesEgo = new TreeMap<String, String>();
            private JList heroList = new JList();

            public heroesUI()
            {
                setLayout(new BorderLayout());

                JLabel title = new JLabel();
                JButton select = new JButton("Select");

                title.setText("Indexed Heroes");
                title.setFont(meermansFont.deriveFont(Font.BOLD, 30));
                title.setFocusable(false);
                title.setBackground(myYellow);
                title.setOpaque(true);
                title.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0),
                                10), BorderFactory.createLineBorder(
                                Color.black, 10)));
                title.setAlignmentX(CENTER_ALIGNMENT);
                title.setHorizontalAlignment(JLabel.CENTER);

                heroList.setBackground(myYellow);
                heroList.setFont(meermansFont.deriveFont(Font.PLAIN, 15));

                JScrollPane pane = new JScrollPane(heroList);
                pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                        .createLineBorder(new Color(0, 0, 0, 0), 10),
                        BorderFactory.createLineBorder(Color.black, 10)));
                pane.setOpaque(true);
                pane.setBackground(myYellow);

                select.setFont(meermansFont.deriveFont(Font.PLAIN, 20));
                select.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0),
                                10), BorderFactory.createLineBorder(
                                Color.black, 10)));
                select.setBackground(myYellow);
                select.addActionListener(new heroesUIAL());
                select.setOpaque(true);

                add(pane, BorderLayout.CENTER);
                add(title, BorderLayout.PAGE_START);
                add(select, BorderLayout.PAGE_END);
            }

            public void removeSpecs()
            {
                if (s != null)
                    s.dispose();
                s = null;
            }

            public void updateHeroList(TreeMap<String, String> temp)
            {
                heroList.setListData(temp.keySet().toArray());
            }

            public TreeMap<String, String> getHeroesEgo()
            {
                return heroesEgo;
            }

            private class heroesUIAL implements ActionListener
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        if (s == null)
                        {
                            s = new specifics(
                                    (String) (heroList.getSelectedValue()),
                                    heroesEgo.get((String) (heroList
                                            .getSelectedValue())));
                        } else if (!s.isVisible())
                        {
                            s.setVisible(true);
                            s.setHeroName((String) (heroList.getSelectedValue()));
                            s.updateAELabel((String) (heroList
                                    .getSelectedValue()));
                            s.updateNLabel(heroesEgo.get((String) (heroList
                                    .getSelectedValue())));
                        } else
                        {
                            s.setHeroName((String) (heroList.getSelectedValue()));
                            s.updateAELabel((String) (heroList
                                    .getSelectedValue()));
                            s.updateNLabel(heroesEgo.get((String) (heroList
                                    .getSelectedValue())));
                        }

                    } catch (Exception exc)
                    {
                    }
                }
            }
        }

        private class specifics extends JFrame
        {
            private String heroName = "";
            private JLabel aELabel, nLabel;

            public specifics(String alterEgo, String name)
            {
                heroName = alterEgo;
                setSize(350, 250);
                setResizable(false);
                setTitle("Specifics");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocation((int) (displayHeroes.getLocation().getX() + 25),
                        (int) (displayHeroes.getLocation().getY() + 25));

                JPanel panel = new JPanel();
                JPanel panelBuffer1 = new JPanel();
                JPanel panelBuffer2 = new JPanel();
                aELabel = new JLabel();
                nLabel = new JLabel();

                aELabel.setFont(meermansFont.deriveFont(Font.BOLD, 20));
                aELabel.setText("Hero Name : " + alterEgo);
                aELabel.setBackground(myYellow);
                aELabel.setOpaque(true);

                nLabel.setFont(meermansFont.deriveFont(Font.BOLD, 20));
                nLabel.setText("Civilian Name : " + name);
                nLabel.setBackground(myYellow);
                nLabel.setOpaque(true);

                panelBuffer1.setLayout(new BorderLayout());
                panelBuffer1.add(aELabel, BorderLayout.CENTER);
                panelBuffer1.add(Box.createHorizontalStrut(20),
                        BorderLayout.LINE_START);
                panelBuffer1.add(Box.createHorizontalStrut(20),
                        BorderLayout.LINE_END);
                panelBuffer1.setBorder(BorderFactory.createLineBorder(
                        Color.black, 10));
                panelBuffer1.setOpaque(true);
                panelBuffer1.setBackground(myYellow);

                panelBuffer2.setLayout(new BorderLayout());
                panelBuffer2.add(nLabel, BorderLayout.CENTER);
                panelBuffer2.add(Box.createHorizontalStrut(20),
                        BorderLayout.LINE_START);
                panelBuffer2.add(Box.createHorizontalStrut(20),
                        BorderLayout.LINE_END);
                panelBuffer2.setBorder(BorderFactory.createLineBorder(
                        Color.black, 10));
                panelBuffer2.setOpaque(true);
                panelBuffer2.setBackground(myYellow);

                panel.setLayout(new GridLayout(2, 1));
                panel.add(panelBuffer1);
                panel.add(panelBuffer2);

                add(panel);

                setVisible(true);
            }

            public void updateAELabel(String s)
            {
                aELabel.setText("Hero Name : " + s);
            }

            public void updateNLabel(String s)
            {
                nLabel.setText("Civilian Name : " + s);
            }

            public void setHeroName(String s)
            {
                heroName = s;
            }

            public String getHeroName()
            {
                return heroName;
            }
        }
    }

    private class addHeroesCreator extends JFrame
    {
        private addHeroesUI addHeroesUI;

        public addHeroesCreator()
        {
            setTitle("Add Heroes");
            setSize(400, 325);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            addHeroesUI = new addHeroesUI();

            add(addHeroesUI);
        }

        private class addHeroesUI extends JPanel
        {
            JTextField enterName;
            JTextField enterAlterEgo;

            public addHeroesUI()
            {
                setLayout(new BorderLayout());

                JLabel title = new JLabel();

                title.setText("Add Hero");
                title.setBackground(myYellow);
                title.setOpaque(true);
                title.setHorizontalAlignment(JLabel.CENTER);
                title.setFont(meermansFont.deriveFont(Font.BOLD, 35));

                add(title, BorderLayout.PAGE_START);
                add(new creationPanel(), BorderLayout.CENTER);
                add(new createButton(), BorderLayout.PAGE_END);
            }

            private class creationPanel extends JPanel
            {
                public creationPanel()
                {
                    setLayout(new GridLayout(2, 1));

                    enterName = new JTextField();
                    enterAlterEgo = new JTextField();
                    JLabel alterEgo = new JLabel();
                    JLabel name = new JLabel();

                    alterEgo.setText("Hero Name : ");
                    alterEgo.setFont(meermansFont.deriveFont(Font.PLAIN, 25));
                    alterEgo.setBackground(myYellow);
                    alterEgo.setOpaque(true);
                    name.setText("Civilian Name : ");
                    name.setFont(meermansFont.deriveFont(Font.PLAIN, 25));
                    name.setBackground(myYellow);
                    name.setOpaque(true);

                    alterEgo.setMaximumSize(new Dimension(
                            Integer.MAX_VALUE / 10,
                            name.getMaximumSize().height));

                    enterName.setFont(meermansFont.deriveFont(Font.PLAIN, 25));
                    enterAlterEgo.setFont(meermansFont.deriveFont(Font.PLAIN,
                            25));

                    JPanel enterNamePanel = new JPanel();
                    enterNamePanel.setLayout(new BorderLayout());
                    JPanel temp = new JPanel();
                    temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
                    temp.add(alterEgo);
                    temp.add(enterName);
                    temp.setBackground(myYellow);
                    temp.setOpaque(true);

                    enterNamePanel.add(temp, BorderLayout.CENTER);
                    enterNamePanel.add(Box.createVerticalStrut(20),
                            BorderLayout.PAGE_END);
                    enterNamePanel.setBackground(myYellow);
                    enterNamePanel.setOpaque(true);

                    JPanel enterAlterEgoPanel = new JPanel();
                    enterAlterEgoPanel.setLayout(new BorderLayout());
                    enterAlterEgoPanel.add(name, BorderLayout.LINE_START);
                    enterAlterEgoPanel.add(enterAlterEgo, BorderLayout.CENTER);
                    enterAlterEgoPanel.add(Box.createVerticalStrut(20),
                            BorderLayout.PAGE_END);
                    enterAlterEgoPanel.setBackground(myYellow);
                    enterAlterEgoPanel.setOpaque(true);

                    JPanel dumbWay1 = new JPanel();
                    JPanel dumbWay2 = new JPanel();

                    dumbWay1.setLayout(new BorderLayout());
                    dumbWay1.setBackground(myYellow);
                    dumbWay1.setOpaque(true);

                    dumbWay2.setLayout(new BorderLayout());
                    dumbWay2.setBackground(myYellow);
                    dumbWay2.setOpaque(true);

                    dumbWay1.add(Box.createHorizontalStrut(20),
                            BorderLayout.LINE_START);
                    dumbWay1.add(Box.createHorizontalStrut(20),
                            BorderLayout.LINE_END);
                    dumbWay1.add(enterNamePanel);
                    dumbWay1.add(Box.createVerticalStrut(30),
                            BorderLayout.PAGE_START);

                    dumbWay2.add(Box.createHorizontalStrut(20),
                            BorderLayout.LINE_START);
                    dumbWay2.add(Box.createHorizontalStrut(20),
                            BorderLayout.LINE_END);
                    dumbWay2.add(Box.createVerticalStrut(30),
                            BorderLayout.PAGE_END);
                    dumbWay2.add(enterAlterEgoPanel);

                    add(dumbWay1);
                    add(dumbWay2);
                }
            }

            private class createButton extends JButton
            {
                public createButton()
                {
                    setText("Create Hero");
                    setFont(meermansFont.deriveFont(Font.PLAIN, 30));
                    setBackground(myYellow);
                    setOpaque(true);
                    setHorizontalAlignment(JButton.CENTER);
                    addActionListener(new createHeroAL());
                }
            }

            private class createHeroAL implements ActionListener
            {

                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    TreeMap<String, String> heroes = displayHeroes.getHeroUI()
                            .getHeroesEgo();
                    if (enterName.getText().length() == 0
                            || heroes.containsKey(enterName.getText()))
                    {
                        JOptionPane.showMessageDialog(addHeroes,
                                "Hero Not Created", "Hero Creation",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else
                    {
                        JOptionPane.showMessageDialog(addHeroes,
                                "Hero Created", "Hero Creation",
                                JOptionPane.INFORMATION_MESSAGE);
                        heroes.put(enterName.getText(), enterAlterEgo.getText());
                        displayHeroes.getHeroUI().updateHeroList(heroes);
                    }
                    deleteHeroes.updateOptions();
                }
            }
        }
    }

    private class deleteHeroesCreator extends JFrame
    {
        JComboBox heroList;

        public deleteHeroesCreator()
        {
            setSize(400, 232);
            setResizable(false);
            setTitle("Delete Heroes");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(myYellow);
            panel.setOpaque(true);

            JLabel title = new JLabel();
            title.setText("Delete Hero");
            title.setBackground(myYellow);
            title.setOpaque(true);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(meermansFont.deriveFont(Font.BOLD, 35));

            JButton button = new JButton();
            button.setText("Delete");
            button.setBackground(myYellow);
            button.setOpaque(true);
            button.setFont(meermansFont.deriveFont(Font.PLAIN, 30));
            button.addActionListener(new deleteAL());

            heroList = new JComboBox(displayHeroes.getHeroUI().getHeroesEgo()
                    .keySet().toArray());
            heroList.setFont(meermansFont.deriveFont(Font.BOLD, 35));
            heroList.setAlignmentX(CENTER_ALIGNMENT);
            heroList.setBackground(myYellow);
            heroList.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            heroList.setOpaque(true);

            JPanel selectionPanel = new JPanel();
            selectionPanel.setLayout(new BorderLayout());
            selectionPanel.add(heroList, BorderLayout.CENTER);
            selectionPanel.add(Box.createHorizontalStrut(28),
                    BorderLayout.LINE_START);
            selectionPanel.add(Box.createHorizontalStrut(28),
                    BorderLayout.LINE_END);
            selectionPanel.add(Box.createVerticalStrut(25),
                    BorderLayout.PAGE_START);
            selectionPanel.add(Box.createVerticalStrut(25),
                    BorderLayout.PAGE_END);
            selectionPanel.setBackground(myYellow);
            selectionPanel.setOpaque(true);

            panel.add(title, BorderLayout.PAGE_START);
            panel.add(button, BorderLayout.PAGE_END);
            panel.add(selectionPanel, BorderLayout.CENTER);

            add(panel);
        }

        public void updateOptions()
        {
            DefaultComboBoxModel cbm = new DefaultComboBoxModel();
            for (String s : displayHeroes.getHeroUI().getHeroesEgo().keySet())
            {
                cbm.addElement(s);
            }
            heroList.setModel(cbm);
        }

        private class deleteAL implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TreeMap<String, String> temp = displayHeroes.getHeroUI()
                        .getHeroesEgo();
                try
                {
                    if (temp.size() == 0)
                    {
                        throw new Exception();
                    }

                    temp.remove(heroList.getSelectedItem());
                    JOptionPane.showMessageDialog(deleteHeroes, "Hero Deleted",
                            "Hero Deletion", JOptionPane.INFORMATION_MESSAGE);
                    if (displayHeroes.getSpecifics() != null
                            && displayHeroes
                                    .getSpecifics()
                                    .getHeroName()
                                    .equals(heroList.getSelectedItem()
                                            .toString()))
                    {
                        displayHeroes.getHeroUI().removeSpecs();
                    }
                    displayHeroes.getHeroUI().updateHeroList(temp);
                    updateOptions();
                } catch (Exception e2)
                {
                    if (temp.size() == 0)
                    {
                        JOptionPane.showMessageDialog(deleteHeroes,
                                "No Hero Selected", "Hero Deletion",
                                JOptionPane.ERROR_MESSAGE);
                    } else
                    {
                        JOptionPane.showMessageDialog(deleteHeroes,
                                "Hero Not Deleted", "Hero Deletion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new FourthSwangProgram();
    }
}
