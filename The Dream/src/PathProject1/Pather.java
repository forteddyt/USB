package PathProject1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Pather {
	private int cityMatrix[][];

	public static void main(String[] args) {
		Pather p = new Pather();
		p.createCities();
	}

	public Pather() {

	}

	public void createCities() {
		File f = new File("src/PathProject1/City Information.txt");
		Scanner scan;
		try {
			scan = new Scanner(f);
		} catch (Exception e) {
			scan = null;
		}
		int n = scan.nextInt();
		HashMap<String, Integer> cities = new HashMap<String, Integer>();
		Letter[] l = Letter.values();
		for (int i = 0; i < Letter.values().length; i++) {
			cities.put(l[i].toString(), i);
		}
		for (int i = 0; i < n; i++) {
			String firstCity = scan.next();
			String secondCity = scan.next();
			int value = scan.nextInt();
			cityMatrix[cities.get(firstCity)][cities.get(secondCity)] = value;
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
		}

		for (int x = 0; x < cityMatrix.length; x++) {
			for (int y = 0; y < cityMatrix[0].length; y++) {
				System.out.println(cityMatrix[x][y] + ", ");
			}
			System.out.println();
		}
	}

	enum Letter {
		A, B, C, D, E, F, G, H, I, J
	}
}
