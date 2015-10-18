import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * (\\w+) (\\w+ )- (\\w+ )+- \\d+ - (\\w+ ?)+\\.\\w+
 * \\w+: \\w+ \\w+, \\w+: \\w+, \\w+: \\w+, \\w+: \\d\\.\\w+
 * \\w+.+#\\d+.\\w+
 */

public class Regex3 {
    public static void main(String[] args) {
        Set<String> forSorting = new TreeSet<String>();
        Scanner fin = null;
        PrintWriter pw = null;
        Pattern normal = Pattern
                .compile("(\\w+) (\\w+ )- (\\w+ )+- \\d+ - (\\w+ ?)+\\.\\w+");
        Pattern format1 = Pattern
                .compile("\\w+: (.*), \\w+: (.*), \\w+: (.*), \\w+: (\\d)(\\.\\w+)");
        Pattern format2 = Pattern
                .compile("([\\w+\\.]+)#([\\w+\\.]+)#([\\w+\\.]+)#(\\d+).(\\w+)");

        try {
            fin = new Scanner(new File("src/SongList.txt"));
            File output = new File("src/CorrectSongList.txt");
            if (!output.exists())
                output.createNewFile();
            pw = new PrintWriter(output);
            while (fin.hasNextLine()) {
                String temp = fin.nextLine();
                Matcher regular = normal.matcher(temp);
                Matcher matcher1 = format1.matcher(temp);
                Matcher matcher2 = format2.matcher(temp);
                if (regular.matches()) {
                    forSorting.add(temp);
                } else if (matcher1.matches()) {
                    forSorting.add(matcher1.group(1) + " - "
                            + matcher1.group(2) + " - " + matcher1.group(4)
                            + " - " + matcher1.group(3) + matcher1.group(5));
                } else if (matcher2.matches()) {
                    forSorting.add(matcher2.group(1).replaceAll("\\.", " ")
                            + " - " + matcher2.group(3).replaceAll("\\.", " ")
                            + " - " + matcher2.group(4).replaceAll("\\.", " ")
                            + " - " + matcher2.group(2).replaceAll("\\.", " ")
                            + "." + matcher2.group(5).replaceAll("\\.", " "));
                } else {
                    forSorting.add("***" + temp);
                }
            }
            for (String s : forSorting) {
                pw.write(s + "\n");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (fin != null)
                fin.close();
            if (pw != null)
                pw.close();
        }
    }
}
