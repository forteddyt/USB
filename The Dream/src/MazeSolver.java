import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeSolver {

	public MazeSolver() {
	}

	/*
	 * public String[][] doA1() { Attempt1 a = new Attempt1(maze);
	 * a.deadEndStart(); maze = a.startingMaze; return maze; }
	 * 
	 * public class Attempt1 {
	 * 
	 * Attempt 1 "B" should be not possible paths "P" should be possible paths
	 * 
	 * private String[][] startingMaze;
	 * 
	 * public Attempt1(String[][] m) { startingMaze = m; }
	 * 
	 * public String[][] solvedMaze() { return startingMaze; }
	 * 
	 * public void deadEndStart() { boolean s = false; Location start = new
	 * Location(0, 0); for (int r = 0; r < maze.length; r++) { for (int c = 0; c
	 * < maze[0].length; c++) { if (maze[r][c].equals(" ")) { s = true; start =
	 * new Location(r, c); break; } } if (s) { break; } } // System.out.println(
	 * "Starting at : [" + start.getCol() + ", " + // start.getRow() + "]");
	 * deadEndFiller(start); }
	 * 
	 * public void deadEndFiller(Location l) { if (l.getCol() == 0 || l.getCol()
	 * == maze[0].length - 1 || l.getRow() == 0 || l.getRow() == maze.length -
	 * 1) { maze[l.getRow()][l.getCol()] = "P"; // System.out.println(
	 * "Door: 'P' at [" + l.getCol() + ", " + // l.getRow() + "]"); } else if
	 * (l.openAt().size() == 1) { maze[l.getRow()][l.getCol()] = "B"; //
	 * System.out.println("'B' at [" + l.getCol() + ", " + // l.getRow() + //
	 * "]"); deadEndFiller(l.openAt().get(0)); } else if (l.openAt().size() > 1)
	 * { maze[l.getRow()][l.getCol()] = "P"; // System.out.println("'P' at [" +
	 * l.getCol() + ", " + // l.getRow() + // "]");
	 * 
	 * for (int i = 0; i < l.openAt().size(); i++) { if
	 * (!maze[l.openAt().get(i).getRow()][l.openAt().get(i).getCol()].equals("P"
	 * )) { deadEndFiller(l.openAt().get(i)); } } } }
	 * 
	 * public class Location { int row, col;
	 * 
	 * public Location(int row, int col) { this.row = row; this.col = col; }
	 * 
	 * public int getRow() { return row; }
	 * 
	 * public int getCol() { return col; }
	 * 
	 * public ArrayList<Location> openAt() { ArrayList<Location> l = new
	 * ArrayList<Location>(); // System.out.println("Set started."); if (row - 1
	 * >= 0) { // System.out.println("Adding row-1"); l.add(new Location(row -
	 * 1, col)); } if (row + 1 < maze.length) { // System.out.println(
	 * "Adding row+1"); l.add(new Location(row + 1, col)); } if (col - 1 >= 0) {
	 * // System.out.println("Adding col-1"); l.add(new Location(row, col - 1));
	 * } if (col + 1 < maze[0].length) { // System.out.println("Adding col+1");
	 * l.add(new Location(row, col + 1)); } for (int i = 0; i < l.size(); i++) {
	 * if (maze[l.get(i).getRow()][l.get(i).getCol()].equals("X") ||
	 * maze[l.get(i).getRow()][l.get(i).getCol()].equals("B")) { l.remove(i);
	 * i--; } } // System.out.println("Set finished. Set size: " + l.size());
	 * 
	 * return l; } } }
	 * 
	 * public String[][] doA2() { Attempt2 a = new Attempt2(maze);
	 * a.startFilling(); maze = a.startingMaze; return maze; }
	 * 
	 * public class Attempt2 { private String[][] startingMaze;
	 * 
	 * public Attempt2(String[][] m) { startingMaze = m; }
	 * 
	 * public void startFilling() { ArrayList<Location> deadEnds = getEnds();
	 * for (Location l : deadEnds) { fillEnds(l); } }
	 * 
	 * public void fillEnds(Location l) { int row = l.nearMe().get(0).row; int
	 * col = l.nearMe().get(0).col; System.out.println("Setting [" + col + ", "
	 * + row + "] as a blocked path"); startingMaze[row][col] = "B"; }
	 * 
	 * public ArrayList<Location> getEnds() { ArrayList<Location> l = new
	 * ArrayList<Location>();
	 * 
	 * for (int r = 0; r < startingMaze.length; r++) { for (int c = 0; c <
	 * startingMaze[0].length; c++) { if (!(startingMaze[r][c].equals("X"))) {
	 * Location loc = new Location(r, c); int numWhites = 0; for (Location tLoc
	 * : loc.nearMe()) { boolean isDoor = tLoc.getCol() == 0 || tLoc.getCol() ==
	 * startingMaze[0].length || tLoc.getRow() == 0 || tLoc.getRow() ==
	 * startingMaze.length; if (!isDoor &&
	 * !(startingMaze[tLoc.getRow()][tLoc.getCol()] == "X")) { numWhites++; } }
	 * if (numWhites == 1) { l.add(loc); } } } }
	 * 
	 * return l; }
	 * 
	 * public class Location { private int row, col;
	 * 
	 * public Location(int row, int col) { this.row = row; this.col = col; }
	 * 
	 * public String toString() { return "[" + col + "," + row + "]"; }
	 * 
	 * public int getRow() { return row; }
	 * 
	 * public int getCol() { return col; }
	 * 
	 * public String myValue() { return startingMaze[this.row][this.col]; }
	 * 
	 * public ArrayList<Location> nearMe() { ArrayList<Location> t = new
	 * ArrayList<Location>(); t.add(new Location(row - 1, col)); t.add(new
	 * Location(row + 1, col)); t.add(new Location(row, col - 1)); t.add(new
	 * Location(row, col + 1)); return t; } }
	 * 
	 * }
	 */

	public void doA3(JPanel[][] inMaze, int delay) {
		Attempt3 a = new Attempt3(inMaze, delay);
		a.solveMaze();
	}

	public class Attempt3 {
		private JPanel[][] iM;
		private int delay;

		public Attempt3(JPanel[][] m, int delay) {
			this.iM = m;
			this.delay = delay;
		}

		public void solveMaze() {
			int wallsCreated = -1;
			ArrayList<JPanel> ends = new ArrayList<JPanel>();
			while (wallsCreated != 0) {
				wallsCreated = 0;

			}

			for (JPanel j : ends) {
				j.setBackground(Color.GRAY);
			}
		}

		public class Location {
			private int row, col;
			private JPanel[][] m = iM;

			public Location(int row, int col) {
				this.row = row;
				this.col = col;
			}

			public String toString() {
				return "[" + col + "," + row + "]";
			}

			public int getRow() {
				return row;
			}

			public int getCol() {
				return col;
			}

			public JPanel myValue() {
				return m[this.row][this.col];
			}

			public ArrayList<Location> nearMe() {
				ArrayList<Location> t = new ArrayList<Location>();
				if (!(row - 1 < 0)) {
					t.add(new Location(row - 1, col));
				}
				if (!(row + 1 >= m.length)) {
					t.add(new Location(row + 1, col));
				}
				if (!(col - 1 < 0)) {
					t.add(new Location(row, col - 1));
				}
				if (!(col + 1 >= m[0].length)) {
					t.add(new Location(row, col + 1));
				}
				for (int i = 0; i < t.size(); i++) {
					if (t.get(i).myValue().getBackground().equals(Color.BLACK)
							|| t.get(i).myValue().getBackground().equals(Color.DARK_GRAY)) {
						t.remove(i);
						i--;
					}
				}
				return t;
			}
		}
	}

	public void doDelay(int delay) {
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
