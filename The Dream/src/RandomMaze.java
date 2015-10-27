import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomMaze {

	private static JPanel[][] myMaze;
	private JFrame frame = new JFrame();
	private ArrayList<JPanel> spots = new ArrayList<JPanel>();

	public static void main(String[] args) {
		RandomMaze m = new RandomMaze(30, 30);

	}

	public RandomMaze(int givenRow, int givenCol) {
		if (givenRow < 3 || givenCol < 3) {
			return;
		}
		myMaze = new JPanel[givenRow][givenCol];
		constructDisplay();
		int startRow = (int) (Math.random() * (givenRow - 2)) + 1;
		int startCol = (int) (Math.random() * (givenRow - 2)) + 1;

		// System.out.println("Setting Entrance [0, " + startRow + "] to \"
		// \".");
		myMaze[startRow][startCol].setBackground(Color.WHITE);
		createPath(startRow, startCol);
		createDoors();
	}

	public void constructDisplay() {
		frame.setName("Maze: " + numCols() + "x" + numRows());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		JPanel overlay = new JPanel();
		overlay.setLayout(new GridLayout(numCols(), numRows()));

		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numCols(); c++) {
				JPanel temp = new JPanel();
				temp.setBackground(Color.BLACK);
				temp.setVisible(true);
				myMaze[r][c] = temp;
				overlay.add(temp);
			}
		}

		overlay.setVisible(true);
		frame.add(overlay);
		frame.setVisible(true);

	}

	public int numRows() {
		return myMaze.length;
	}

	public int numCols() {
		return myMaze[0].length;
	}

	public JPanel[][] getMaze() {
		return myMaze;
	}

	public static void printMaze() {
		for (int r = 0; r < myMaze.length; r++) {
			for (int c = 0; c < myMaze[0].length; c++) {
				System.out.print(myMaze[r][c]);
			}
			System.out.println();
		}
	}

	public void createPath(int givenRow, int givenCol) {
		if (givenRow == 0 || givenRow == myMaze.length - 1 || givenCol == 0 || givenCol == myMaze.length - 1) {
			// System.out.println("Return 1");
			return;
		}
		// System.out.println("Setting position [" + givenCol + ", " + givenRow
		// + "] to \" \".");
		myMaze[givenRow][givenCol].setBackground(Color.WHITE);
		Set<Integer> moves = new LinkedHashSet<Integer>();
		while (moves.size() < 4) {
			moves.add((int) (Math.random() * 4));
		}

		for (Integer n : moves) {
			switch (n) {
			case 0:
				if (!(myMaze[givenRow - 1][givenCol].getBackground().equals(Color.WHITE)
						|| (givenRow - 2 >= 0 && myMaze[givenRow - 2][givenCol].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow - 1][givenCol + 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow - 1][givenCol - 1].getBackground().equals(Color.WHITE))) {
					createPath(givenRow - 1, givenCol);
				}
				break;
			case 1:
				if (!(myMaze[givenRow + 1][givenCol].getBackground().equals(Color.WHITE)
						|| (givenRow + 2 < myMaze.length
								&& myMaze[givenRow + 2][givenCol].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol + 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow + 1][givenCol - 1].getBackground().equals(Color.WHITE))) {
					createPath(givenRow + 1, givenCol);
				}
				break;
			case 2:
				if (!(myMaze[givenRow][givenCol - 1].getBackground().equals(Color.WHITE)
						|| (givenCol - 2 >= 0 && myMaze[givenRow][givenCol - 2].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol - 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow - 1][givenCol - 1].getBackground().equals(Color.WHITE))) {
					createPath(givenRow, givenCol - 1);
				}
				break;
			case 3:
				if (!(myMaze[givenRow][givenCol + 1].getBackground().equals(Color.WHITE)
						|| (givenCol + 2 < myMaze[0].length
								&& myMaze[givenRow][givenCol + 2].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol + 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow - 1][givenCol + 1].getBackground().equals(Color.WHITE))) {
					createPath(givenRow, givenCol + 1);
				}
				break;
			}
		}
	}

	public void createDoors() {
		int doorsMade = 0;
		while (doorsMade < 2) {
			int rRow = -1;
			int rCol = -1;
			boolean doorable = false;
			if (Math.random() > .5) {
				rRow = ((int) (Math.random() * 2)) * (myMaze.length - 1);
				rCol = (int) (Math.random() * (myMaze[0].length - 2)) + 1;
			} else {
				rRow = (int) (Math.random() * (myMaze.length - 2)) + 1;
				rCol = ((int) (Math.random() * 2)) * (myMaze[0].length - 1);
			}
			if (myMaze[rRow - (rRow == 0 ? 0 : 1)][rCol].getBackground().equals(Color.WHITE)
					|| myMaze[rRow + (rRow == myMaze.length ? 1 : 0)][rCol].getBackground().equals(Color.WHITE)
					|| myMaze[rRow][rCol - (rCol == 0 ? 0 : 1)].getBackground().equals(Color.WHITE)
					|| myMaze[rRow][rCol + (rCol == myMaze[0].length ? 1 : 0)].getBackground().equals(Color.WHITE)) {
				doorable = true;
			}

			if (doorable) {
				myMaze[rRow][rCol].setBackground(Color.WHITE);
				doorsMade++;

			}
		}
	}
}
