import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CourseTally {
    public static void main(String[] args) {
        Map<String, Integer> courses = new TreeMap<String, Integer>();
        Scanner fin = null;
        PrintWriter pw = null;

        try {
            fin = new Scanner(new File("src/Requests.txt"));
            File output = new File("src/CummulativeRequests.txt");
            if (!output.exists())
                output.createNewFile();
            pw = new PrintWriter(output);
            while (fin.hasNextLine()) {
                String t = fin.nextLine();
                if (courses.containsKey(t))
                    courses.put(t, courses.get(t) + 1);
                else
                    courses.put(t, 1);
            }

            for (String s : courses.keySet()) {
                pw.printf("%.20s", s);
                pw.printf("%10d%n", courses.get(s));
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
