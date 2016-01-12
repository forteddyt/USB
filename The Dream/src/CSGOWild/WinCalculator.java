package CSGOWild;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinCalculator {
	private LinkedHashSet<WildGame> games = new LinkedHashSet<WildGame>();
	private Scanner scan;
	private File winFile = new File("src/CSGOWild/My Winnings.txt");
	private Matcher m;
	private Pattern p = Pattern
			.compile("COINFLIP_(\\d+)\\s*\\w*\\s*Coin Flip\\s*(\\d+.\\d+)\\s*\\w*\\s*\\([\\w\\s:]+\\s*(\\d+)\\)");
	// Group 1: coinflip ID, Group 2: Amount traded, Group 3: Offer ID

	public WinCalculator() {
		clearPH();
		createGames();
		calcPercentage();
	}

	public void createGames() {
		try {
			scan = new Scanner(winFile);
		} catch (Exception e) {
			e.printStackTrace();
			scan = new Scanner("");
		}
		while (scan.hasNext()) {
			String s = scan.nextLine();
			m = p.matcher(s);
			if (m.matches()) {
				int cID = Integer.parseInt(m.group(1));
				String aT = m.group(2);
				int oID = Integer.parseInt(m.group(3));
				WildGame g = new WildGame(cID, aT, oID);
				games.add(g);
			}
		}
	}

	public void calcPercentage() {
		int win = 0;
		int loss = 0;
		LinkedHashSet<Integer> temp = new LinkedHashSet<Integer>();
		for (WildGame g : games) {
			if (temp.add(g.getMatchID())) {
				loss++;
			} else {
				loss--;
				win++;
			}
		}
		String s = (int) ((win / (win + loss * 1.0)) * 10000) / (100.0) + "% of " + (loss + win)
				+ " games have been won,\t Games Won: " + win + ",\t Games Lost: " + loss;
		addToPH(s);
	}

	public void clearPH() {
		try {
			File f = new File("src/CSGoWild/Percentage History.txt");
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addToPH(String s) {
		try {
			File f = new File("src/CSGOWild/Percentage History.txt");

			FileReader fr = new FileReader(f.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			String previousText = "";
			String brLine = null;
			while ((brLine = br.readLine()) != null) {
				previousText += brLine + "\n";
			}
			br.close();

			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			bw.write(s + "\t\t\t- " + dateFormat.format(new Date()) + " -\n" + previousText);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WinCalculator wcalc = new WinCalculator();
	}
}