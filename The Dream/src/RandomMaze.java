import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomMaze {
	// #####################################
	private static final int ROW_LENGTH = 30;
	private static final int COL_LENGTH = 30;
	private static final SolveMethod solve = SolveMethod.Loop1;
	private int buildDelay = 2;
	private int solveDelay = 25;
	// #####################################

	private static JPanel[][] myMaze;
	private JFrame frame = new JFrame();
	private static RandomMaze m;
	solveButton b = new solveButton(solve, myMaze);

	public static void main(String[] args) {
		if (m == null) {
			m = new RandomMaze(COL_LENGTH, ROW_LENGTH);
		}
	}

	public RandomMaze() {

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
		b.rM = myMaze;
	}

	public void constructDisplay() {
		frame.setTitle("Maze: " + numCols() + "x" + numRows());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 950);
		JPanel overlay = new JPanel();
		JPanel buttonOverlay = new JPanel();
		JPanel mazeOverlay = new JPanel();
		mazeOverlay.setLayout(new GridLayout(numCols(), numRows()));

		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numCols(); c++) {
				JPanel temp = new JPanel();
				temp.setBackground(Color.BLACK);
				temp.setVisible(true);
				myMaze[r][c] = temp;
				mazeOverlay.add(temp);
			}
		}

		JButton button = new JButton();
		button.addActionListener(b);
		button.setFocusable(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setFont(new Font("Arial", Font.BOLD, 75));
		button.setText("Solve Maze!");
		mazeOverlay.setVisible(true);
		buttonOverlay.setVisible(true);
		overlay.setVisible(true);

		buttonOverlay.setLayout(new BorderLayout());
		buttonOverlay.setBackground(Color.GREEN.darker().darker());
		buttonOverlay.add(button, BorderLayout.CENTER);
		buttonOverlay.add(Box.createVerticalStrut(25), BorderLayout.PAGE_START);
		buttonOverlay.add(Box.createRigidArea(new Dimension(50, 150)), BorderLayout.LINE_START);
		buttonOverlay.add(Box.createVerticalStrut(25), BorderLayout.PAGE_END);
		buttonOverlay.add(Box.createRigidArea(new Dimension(50, 150)), BorderLayout.LINE_END);

		overlay.setLayout(new BorderLayout());
		overlay.add(mazeOverlay, BorderLayout.CENTER);
		overlay.add(buttonOverlay, BorderLayout.PAGE_END);
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
				if (myMaze[r][c].getBackground().equals(Color.BLACK)) {
					System.out.print("X");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public void doDelay() {
		try {
			TimeUnit.MILLISECONDS.sleep(buildDelay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPath(int givenRow, int givenCol) {
		doDelay();
		if (givenRow == 0 || givenRow == myMaze.length - 1 || givenCol == 0 || givenCol == myMaze.length - 1) {
			myMaze[givenRow][givenCol].setBackground(Color.BLACK);
			return;
		}
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
					myMaze[givenRow - 1][givenCol].setBackground(Color.BLUE.brighter());
					createPath(givenRow - 1, givenCol);
				}
				break;
			case 1:
				if (!(myMaze[givenRow + 1][givenCol].getBackground().equals(Color.WHITE)
						|| (givenRow + 2 < myMaze.length
								&& myMaze[givenRow + 2][givenCol].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol + 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow + 1][givenCol - 1].getBackground().equals(Color.WHITE))) {
					myMaze[givenRow + 1][givenCol].setBackground(Color.BLUE.brighter());
					createPath(givenRow + 1, givenCol);
				}
				break;
			case 2:
				if (!(myMaze[givenRow][givenCol - 1].getBackground().equals(Color.WHITE)
						|| (givenCol - 2 >= 0 && myMaze[givenRow][givenCol - 2].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol - 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow - 1][givenCol - 1].getBackground().equals(Color.WHITE))) {
					myMaze[givenRow][givenCol - 1].setBackground(Color.BLUE.brighter());
					createPath(givenRow, givenCol - 1);
				}
				break;
			case 3:
				if (!(myMaze[givenRow][givenCol + 1].getBackground().equals(Color.WHITE)
						|| (givenCol + 2 < myMaze[0].length
								&& myMaze[givenRow][givenCol + 2].getBackground().equals(Color.WHITE))
						|| myMaze[givenRow + 1][givenCol + 1].getBackground().equals(Color.WHITE)
						|| myMaze[givenRow - 1][givenCol + 1].getBackground().equals(Color.WHITE))) {
					myMaze[givenRow][givenCol + 1].setBackground(Color.BLUE.brighter());
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
				doDelay();
				myMaze[rRow][rCol].setBackground(Color.WHITE);
				doorsMade++;
			}
		}
		System.out.println("Maze Completed");
	}

	public class solveButton implements ActionListener {
		private JPanel[][] rM = new JPanel[ROW_LENGTH][COL_LENGTH];
		private MazeSolver solver;
		private SolveMethod s;

		public solveButton(SolveMethod s, JPanel[][] m) {
			rM = m;
			solver = new MazeSolver();
			this.s = s;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (s.equals(SolveMethod.Recursive1)) {

			} else if (s.equals(SolveMethod.Recursive2)) {

			} else if (s.equals(SolveMethod.Loop1)) {
				solver.doA3(rM, solveDelay);
			}
		}
	}

	enum SolveMethod {
		Recursive1, Recursive2, Loop1
	}
}
