import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class EzEmails {
	private static Scanner scan;
	private static File inF;
	private static File outF;

	public static void main(String[] args) {
		generateEmails();
	}

	public static void generateEmails() {
		try {
			inF = new File("/src/roughEmails");
			outF = new File("/src/cleanEmails");
			scan = new Scanner(inF);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.contains("@")) {
					FileWriter f = null;
				}
			}
		} catch (Exception e) {

		}
	}
}
