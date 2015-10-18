package Tamaswingagotchi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class DayAndTime extends JPanel
{
    private static int day, hour, minute = -1;
    private static Timer t = null;
    private static JTextArea timeDisplay;

    private static Pattern pTime = Pattern
            .compile("Time: (\\d+):(\\d+):(\\d+)");

    private static Scanner fin = null;

    public DayAndTime()
    {
        t = new Timer(5000, new updateTime());
        try
        {
            fin = new Scanner(new File("src/Tamaswingagotchi/Progress.txt"));
            while (fin.hasNextLine())
            {
                String s = fin.nextLine();
                Matcher mTime = pTime.matcher(s);
                if (mTime.find())
                {
                    if (mTime.matches())
                    {
                        day = Integer.parseInt(mTime.group(1));
                        hour = Integer.parseInt(mTime.group(2));
                        minute = Integer.parseInt(mTime.group(3));
                    }
                }
            }
            if (minute == -1)
            {
                day = 0;
                hour = 0;
                minute = 0;
                save();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        JLabel timeTitle = new JLabel("<html><u>Time</u></html>");
        timeDisplay = new JTextArea("     Day: " + day + "\n     Hour: " + hour
                + "\n     Minute: " + minute);
        timeDisplay.setFont(getFont().deriveFont(Font.BOLD, 14));
        timeTitle.setHorizontalAlignment(JLabel.CENTER);
        timeTitle.setFont(getFont().deriveFont(Font.BOLD, 14));
        timeDisplay.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        timeDisplay.setWrapStyleWord(true);
        timeDisplay.setLineWrap(true);
        timeDisplay.setEditable(false);
        timeDisplay.setFocusable(false);
        timeDisplay.setOpaque(false);

        add(timeTitle, BorderLayout.PAGE_START);

        JPanel displayFormat = new JPanel(new BorderLayout());
        displayFormat.add(timeDisplay, BorderLayout.PAGE_START);

        add(displayFormat, BorderLayout.CENTER);
    }

    public Timer myT()
    {
        return t;
    }

    public class updateTime implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            uTime();
        }
    }

    public void uTime()
    {
        minute++;
        if (minute >= 60)
        {
            minute = 0;
            hour++;
        }
        if (hour >= 24)
        {
            hour = 0;
            day++;
        }
        timeDisplay.setText("     Day: " + day + "\n     Hour: " + hour
                + "\n     Minute: " + minute);
        timeDisplay.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        save();
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
                Matcher m = pTime.matcher(s);
                if (!m.matches())
                {
                    pw.write(s + "\n");
                }
                if (m.matches())
                {
                    m.replaceAll("");
                }
            }
            pw.write("Time: " + day + ":" + hour + ":" + minute + "\n");

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

    public int getDay()
    {
        return day;
    }

    public int getHour()
    {
        return hour;
    }

    public int getminute()
    {
        return minute;
    }
}