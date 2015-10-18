import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingQuiz2
{
    private JFrame frame;
    private boolean submittable = true;
    private JPanel bigPanel;
    private JTextField nameBox;
    private JLabel nameLabel;
    private ButtonGroup bg1, bg2;
    private String ans1 = "cemetary";
    private String ans2 = "accommodate";
    private ArrayList<JRadioButton> jbGroup1 = new ArrayList<JRadioButton>();
    private ArrayList<JRadioButton> jbGroup2 = new ArrayList<JRadioButton>();
    private String[] answerSet1 =
    { "cemetery", "sematary", "cemetary", "cematery" };
    private String[] answerSet2 =
    { "acomodate", "acommodate", "accommodate", "accomodate" };

    public static void main(String[] args)
    {
        new SwingQuiz2();
    }

    public SwingQuiz2()
    {
        frame = new JFrame("The SOL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(false);

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());

        bigPanel.add(new NameComponent(), BorderLayout.PAGE_START);
        bigPanel.add(new QuestionComponent(), BorderLayout.CENTER);
        bigPanel.add(Box.createRigidArea(new Dimension(200, 1)),
                BorderLayout.PAGE_END);
        bigPanel.add(new AnswerComponent(), BorderLayout.PAGE_END);

        frame.add(bigPanel);
        frame.setVisible(true);
    }

    private class NameComponent extends JPanel
    {
        public NameComponent()
        {
            setLayout(new BorderLayout());

            nameLabel = new JLabel("Type your name: ");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            nameLabel.setForeground(Color.white);
            nameBox = new JTextField("", 15);
            nameBox.setBackground(Color.BLACK);
            nameBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            nameBox.setOpaque(true);
            nameBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
            nameBox.setForeground(Color.WHITE);

            add(nameLabel, BorderLayout.LINE_START);
            add(nameBox, BorderLayout.CENTER);

            setBackground(Color.black);
            setBorder(BorderFactory.createLineBorder(Color.black, 20));
        }
    }

    private class QuestionComponent extends JPanel
    {
        public QuestionComponent()
        {
            setBorder(BorderFactory
                    .createLineBorder(new Color(0, 138, 184), 20));
            setLayout(new GridLayout(2, 1));
            add(new Questions(1));
            add(new Questions(2));
        }

        private class Questions extends JPanel
        {
            String q1 = "1. This word, a synonym for graveyard, has its origins in the Latin word coimeterium and the Greek word koimetrion, meaning \"a sleeping place.\"";
            String q2 = "2. How do you spell the word which has various meanings including \"to oblige\" and \"to provide with a room.\"";

            public Questions(int num)
            {
                setBorder(BorderFactory.createLineBorder(new Color(153, 204,
                        255), 14));
                setLayout(new BorderLayout());

                JTextArea question = new JTextArea();
                JPanel answerPanel = new JPanel();
                answerPanel.setLayout(new GridLayout(4, 1));
                question.setWrapStyleWord(true);
                question.setLineWrap(true);
                question.setEditable(false);
                question.setFocusable(false);
                question.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                question.setBackground(new Color(255, 255, 204));
                question.setBorder(BorderFactory.createLineBorder(new Color(
                        255, 255, 204), 10));
                question.setOpaque(true);

                if (num == 1)
                {
                    bg1 = new ButtonGroup();
                    question.setText(q1);
                    for (int i = 0; i < 4; i++)
                    {
                        JRadioButton answers = new JRadioButton(answerSet1[i]);
                        jbGroup1.add(answers);
                        bg1.add(answers);
                        answerPanel.add(answers);
                    }
                } else if (num == 2)
                {
                    bg2 = new ButtonGroup();
                    question.setText(q2);
                    for (int i = 0; i < 4; i++)
                    {
                        JRadioButton answers = new JRadioButton(answerSet2[i]);
                        jbGroup2.add(answers);
                        bg2.add(answers);
                        answerPanel.add(answers);
                    }
                }

                add(question, BorderLayout.PAGE_START);
                add(answerPanel, BorderLayout.CENTER);
            }
        }
    }

    private class AnswerComponent extends JPanel
    {
        public AnswerComponent()
        {
            setBackground(new Color(0, 138, 184));
            JButton button = new JButton();
            button.setText("     Submit     ");
            button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            button.setBackground(Color.black);
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createLineBorder(Color.white, 4,
                    true));
            button.addActionListener(new submitAnswers());

            add(Box.createHorizontalStrut(frame.getWidth() - 165));
            add(button);
        }
    }

    private class submitAnswers implements ActionListener
    {
        String wrong = "          Incorrect!";
        String right = "          Correct!";

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (bg1.getSelection() == null || bg2.getSelection() == null)
            {
                JOptionPane.showMessageDialog(frame, "Select Answer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!submittable)
            {
                JOptionPane.showMessageDialog(frame,
                        "You have already submitted your answers");
            } else
            {
                int n = 0;
                submittable = false;
                for (JRadioButton b : jbGroup1)
                {
                    b.setText(answerSet1[n]);
                    if (b.isSelected())
                    {
                        if (b.getText().equals(ans1))
                        {
                            b.setText(b.getText() + right);
                        } else
                        {
                            b.setText(b.getText() + wrong);
                        }
                    }
                    n++;
                }
                n = 0;

                for (JRadioButton b : jbGroup2)
                {
                    b.setText(answerSet2[n]);
                    if (b.isSelected())
                    {
                        if (b.getText().equals(ans2))
                        {
                            b.setText(b.getText() + right);
                        } else
                        {
                            b.setText(b.getText() + wrong);
                        }
                    }
                    n++;
                }
            }
        }

    }
}
