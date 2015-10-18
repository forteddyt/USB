package Tamaswingagotchi;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel
{
    private Font stats = null;
    private static DayAndTime dayTime;

    public StatsPanel()
    {
        dayTime = new DayAndTime();

        try
        {
            stats = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "src/Tamaswingagotchi/stormfaze.ttf"));
        } catch (Exception e)
        {
            System.out.println("Error creating fonts.");
        }
        setBorder(BorderFactory.createMatteBorder(4, 0, 4, 4, Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Statistics");
        title.setFont(stats.deriveFont(Font.PLAIN, 20));
        title.setBorder(BorderFactory
                .createMatteBorder(0, 0, 2, 0, Color.BLACK));
        title.setAlignmentX(CENTER_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(20));
        add(dayTime);
        add(Box.createVerticalStrut(20));
        add(new Age());

        add(Box.createHorizontalStrut(150));

        dayTime.myT().start();
    }

    public class Age extends JPanel
    {
        private int age;
        private Pattern pAge = Pattern.compile("Age: (\\d+)");
        private Scanner fin = null;
        private JLabel ageDisplay = null;

        public Age()
        {
            try
            {
                fin = new Scanner(new File("src/Tamaswingagotchi/Progress.txt"));
                while (fin.hasNextLine())
                {
                    String s = fin.nextLine();
                    Matcher mAge = pAge.matcher(s);
                    if (mAge.find())
                    {
                        if (mAge.matches())
                        {
                            age = Integer.parseInt(mAge.group(1));
                        }
                    }
                }
                if (age == -1)
                {
                    age = 0;
                    save();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            ageDisplay = new JLabel("Age: " + age);
            add(ageDisplay);
        }

        public void updateAge()
        {
            age = dayTime.getHour();
            ageDisplay.setText("Age: " + age);
        }

        public void save()
        {
            try
            {
                File f = new File("src/Tamaswingagotchi/Progress.txt");
                File old = new File("src/Tamaswingagotchi/ProgressTemp.txt");
                if (!f.exists())
                {
                    f.createNewFile();
                }
                if (!old.exists())
                {
                    old.createNewFile();
                }

                Scanner scan1 = new Scanner(f);
                PrintWriter pw1 = new PrintWriter(old);
                while (scan1.hasNextLine())
                {
                    String s = scan1.nextLine();
                    pw1.write(s + "\n");
                }
                pw1.flush();
                Scanner scan = new Scanner(old);
                PrintWriter pw = new PrintWriter(f);
                while (scan.hasNextLine())
                {
                    String s = scan.nextLine();
                    Matcher m = pAge.matcher(s);
                    if (!m.matches())
                    {
                        pw.write(s + "\n");
                    }
                    if (m.matches())
                    {
                        m.replaceAll("");
                    }
                }
                pw.write("Age: " + age + "\n");

                pw.flush();
                pw.close();
                pw1.close();
                scan1.close();
                scan.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public class Weight extends JPanel
    {
        public Weight()
        {

        }
    }

    public class Sickness extends JPanel
    {
        public Sickness()
        {

        }
    }
}
