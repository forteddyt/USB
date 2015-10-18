import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Regex4 {
    public static void main(String[] args) {
        int charC = 0;
        Scanner fin = null;
        Scanner temp = null;
        PrintWriter pw = null;
        try {
            fin = new Scanner(new File("src/Input.txt"));
            File output = new File("src/Output.txt");
            if (!output.exists())
                output.createNewFile();
            pw = new PrintWriter(output);
            while (fin.hasNextLine()) {
                pw.write("\t");
                temp = new Scanner(fin.nextLine());
                while (temp.hasNext()) {
                    String tempNext = temp.next();
                    if (charC + tempNext.length() <= 60) {
                        pw.write(tempNext + " ");
                        charC += tempNext.length();
                    } else {
                        pw.write("\n" + tempNext + " ");
                        charC = tempNext.length();
                    }
                }
                charC = 0;
                pw.write("\n");
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println("Something broke... More specifically : " + e);
        } finally {
            if (fin != null)
                fin.close();
            if (temp != null)
                temp.close();
            if (pw != null)
                pw.close();
        }
    }
}