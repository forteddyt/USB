import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SecondSwangProgram
{
    private Double tip;
    private Double cost;
    private Double[] tips =
    { 10.0, 12.5, 15.0, 17.5, 20.0 };
    private JFrame frame;
    private JPanel boxPanel;
    private JButton calculate;
    private JLabel percent;
    private JTextField enterBill, displayTip, displayTotal;
    private JComboBox<Double> tipChoices;

    public SecondSwangProgram()
    {
        // Create frame
        frame = new JFrame("-Codename: Bad Tipper-");
        frame.setSize(500, 250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create
        boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(1, 2));

        // Create, format, and add items to ComboBox. Top of the two boxPanels.
        boxPanel.add(new costBox());
        boxPanel.add(new tipBox());

        frame.add(boxPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new SecondSwangProgram();
    }

    private class costBox extends JPanel
    {
        Dimension minSize = new Dimension(10, 15);
        Dimension prefSize = new Dimension(10, 75);
        Dimension maxSize = new Dimension(10, 125);

        public costBox()
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 20));

            enterBill = new JTextField("Enter bill");
            enterBill.setHorizontalAlignment(JTextField.CENTER);
            enterBill
                    .setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
            enterBill.addActionListener(new updateMoney());
            enterBill.addMouseListener(new clickErase());

            displayTip = new JTextField("Tip: \t");
            displayTip.setEditable(false);
            displayTip.setBorder(BorderFactory
                    .createLineBorder(Color.BLACK, 10));

            displayTotal = new JTextField("Total: \t");
            displayTotal.setEditable(false);
            displayTotal.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                    10));

            add(enterBill);
            add(Box.createRigidArea(new Dimension(2, 20)));
            add(displayTip);
            add(Box.createRigidArea(new Dimension(2, 20)));
            add(displayTotal);
            setVisible(true);
        }

        private class clickErase implements MouseListener
        {
            public void mouseClicked(MouseEvent e)
            {
            }

            public void mouseEntered(MouseEvent e)
            {
            }

            public void mouseExited(MouseEvent e)
            {
            }

            public void mousePressed(MouseEvent e)
            {
                enterBill.setText("");
            }

            public void mouseReleased(MouseEvent e)
            {
            }
        }
    }

    private class tipBox extends JPanel
    {
        public tipBox()
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 50));

            tipChoices = new JComboBox<Double>(tips);
            percent = new JLabel("Percent");
            calculate = new JButton("Calculate");
            calculate.addActionListener(new updateMoney());

            percent.setAlignmentX(CENTER_ALIGNMENT);
            calculate.setAlignmentX(CENTER_ALIGNMENT);
            tipChoices.setSelectedIndex(2);
            tipChoices.setEditable(false);
            tipChoices.addActionListener(new updateMoney());
            tipChoices.setBorder(BorderFactory
                    .createLineBorder(Color.black, 10));
            add(percent);
            add(Box.createRigidArea(new Dimension(2, 20)));
            add(tipChoices);
            add(Box.createRigidArea(new Dimension(2, 20)));
            add(calculate);
        }
    }

    private class updateMoney implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DecimalFormat dc = new DecimalFormat("###,###.00");
            try
            {
                Double tip = Double.parseDouble(enterBill.getText().replaceAll("," , ""))
                        * (tips[tipChoices.getSelectedIndex()]/100);
                displayTip.setText("Tip: \t" + dc.format(tip));
                displayTotal.setText("Total: \t"
                        + dc.format(tip
                                + Double.parseDouble(enterBill.getText())));
            } catch (Exception e2)
            {
                
            }
        }
    }
}
