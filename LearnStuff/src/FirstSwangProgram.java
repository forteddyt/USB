import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstSwangProgram
{
    private JFrame frame;
    private JLabel magicalText, magicalAnswer;
    private JPanel panel;
    private JButton magicalShake;

    public FirstSwangProgram()
    {
        //Create frame
        frame = new JFrame("-Codename: Magic-");
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Make, assign, and format the components that will go onto frame
        magicalText = new JLabel("Magic 8 Ball");
        magicalAnswer = new JLabel("");
        magicalShake = new JButton("Shake Me!");
        magicalShake.addActionListener(new randomMagicalAnswer());
        magicalText.setAlignmentX(Component.CENTER_ALIGNMENT);
        magicalShake.setAlignmentX(Component.CENTER_ALIGNMENT);
        magicalAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Make a panel that has a BoxLayout design
        panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        //Create dimensions for goodlooks for later
        Dimension minSize = new Dimension(10,15);
        Dimension prefSize = new Dimension(10,75);
        Dimension maxSize = new Dimension(10,125);
        
        //Add the components and goodlooks to the panel
        panel.add(new Box.Filler(minSize, prefSize, maxSize));
        panel.add(magicalText);
        panel.add(new Box.Filler(minSize, prefSize, maxSize));
        panel.add(magicalShake);
        panel.add(new Box.Filler(minSize, prefSize, maxSize));
        panel.add(magicalAnswer);
        panel.add(new Box.Filler(minSize, prefSize, maxSize));
        
        //Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new FirstSwangProgram();
    }

    private class randomMagicalAnswer implements ActionListener
    {
        String[] ans =
        { "Signs point to yes.", "Yes.", "Reply hazy, try again.",
                "Without a doubt.", "My sources say no.", "As I see it, yes.",
                "You may rely on it.", "Concentrate and ask again.",
                "Outlook not so good.", "It is decidedly so.",
                "Better not tell you now.", "Very doubtful.",
                "Yes - definitely.", "It is certain.", "Cannot predict now.",
                "Most likely.", "Ask again later.", "My reply is no.",
                "Outlook good.", "Don't count on it.", "Aaron is the best swanger world Kappa" };

        public void actionPerformed(ActionEvent e)
        {
            int temp = (int)(Math.random() * ans.length);
            magicalAnswer.setText(ans[temp]);
        }
    }
}
