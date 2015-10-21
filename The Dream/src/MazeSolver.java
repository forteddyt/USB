import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MazeSolver {
	private String[][] maze;

	public MazeSolver(String[][] maze) {
		this.maze = maze;
	}

	public String[][] doA1() {
		Attempt1 a = new Attempt1(maze);
		Thread t = new Thread(a);
		t.start();
		return maze;
	}

	public class Attempt1 implements Runnable {
		/*
		 * Attempt 1 "B" should be not possible paths "P" should be possible
		 * paths
		 */
		private String[][] startingMaze;

		public Attempt1(String[][] m) {
			startingMaze = m;
		}

		public String[][] solvedMaze() {
			return startingMaze;
		}

		@Override
		public void run() {
			deadEndStart();
			maze = startingMaze;
		}

		public void deadEndStart() {
			boolean s = false;
			Location start = new Location(0, 0);
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[0].length; c++) {
					if (maze[r][c].equals(" ")) {
						s = true;
						start = new Location(r, c);
						break;
					}
				}
				if (s) {
					break;
				}
			}
			// System.out.println("Starting at : [" + start.getCol() + ", " +
			// start.getRow() + "]");
			deadEndFiller(start);
		}

		public void deadEndFiller(Location l) {
			if (l.getCol() == 0 || l.getCol() == maze[0].length - 1 || l.getRow() == 0
					|| l.getRow() == maze.length - 1) {
				maze[l.getRow()][l.getCol()] = "P";
				// System.out.println("Door: 'P' at [" + l.getCol() + ", " +
				// l.getRow() + "]");
			} else if (l.openAt().size() == 1) {
				maze[l.getRow()][l.getCol()] = "B";
				// System.out.println("'B' at [" + l.getCol() + ", " +
				// l.getRow() +
				// "]");
				deadEndFiller(l.openAt().get(0));
			} else if (l.openAt().size() > 1) {
				maze[l.getRow()][l.getCol()] = "P";
				// System.out.println("'P' at [" + l.getCol() + ", " +
				// l.getRow() +
				// "]");

				for (int i = 0; i < l.openAt().size(); i++) {
					if (!maze[l.openAt().get(i).getRow()][l.openAt().get(i).getCol()].equals("P")) {
						deadEndFiller(l.openAt().get(i));
					}
				}
			}
		}

		public class Location {
			int row, col;

			public Location(int row, int col) {
				this.row = row;
				this.col = col;
			}

			public int getRow() {
				return row;
			}

			public int getCol() {
				return col;
			}

			public ArrayList<Location> openAt() {
				ArrayList<Location> l = new ArrayList<Location>();
				// System.out.println("Set started.");
				if (row - 1 >= 0) {
					// System.out.println("Adding row-1");
					l.add(new Location(row - 1, col));
				}
				if (row + 1 < maze.length) {
					// System.out.println("Adding row+1");
					l.add(new Location(row + 1, col));
				}
				if (col - 1 >= 0) {
					// System.out.println("Adding col-1");
					l.add(new Location(row, col - 1));
				}
				if (col + 1 < maze[0].length) {
					// System.out.println("Adding col+1");
					l.add(new Location(row, col + 1));
				}
				for (int i = 0; i < l.size(); i++) {
					if (maze[l.get(i).getRow()][l.get(i).getCol()].equals("X")
							|| maze[l.get(i).getRow()][l.get(i).getCol()].equals("B")) {
						l.remove(i);
						i--;
					}
				}
				// System.out.println("Set finished. Set size: " + l.size());

				return l;
			}
		}
	}

	public String[][] doA2() {
		Attempt2 a = new Attempt2(maze);
		Thread t = new Thread(a);
		t.start();
		return maze;
	}

	public class Attempt2 implements Runnable {
		private String[][] startingMaze;

		public Attempt2(String[][] m) {
			startingMaze = m;
		}

		@Override
		public void run() {
			startFilling();
			maze = startingMaze;
		}

		public void startFilling() {
			ArrayList<Location> deadEnds = getEnds();
			for (Location l : deadEnds) {
				fillEnds(l);
			}
		}

		public void fillEnds(Location l) {
			int row = l.nearMe().get(0).row;
			int col = l.nearMe().get(0).col;
			System.out.println("Setting [" + col + ", " + row + "] as a blocked path");
			startingMaze[row][col] = "B";
		}

		public ArrayList<Location> getEnds() {
			ArrayList<Location> l = new ArrayList<Location>();

			for (int r = 0; r < startingMaze.length; r++) {
				for (int c = 0; c < startingMaze[0].length; c++) {
					if (!(startingMaze[r][c].equals("X"))) {
						Location loc = new Location(r, c);
						int numWhites = 0;
						for (Location tLoc : loc.nearMe()) {
							boolean isDoor = tLoc.getCol() == 0 || tLoc.getCol() == startingMaze[0].length
									|| tLoc.getRow() == 0 || tLoc.getRow() == startingMaze.length;
							if (!isDoor && !(startingMaze[tLoc.getRow()][tLoc.getCol()] == "X")) {
								numWhites++;
							}
						}
						if (numWhites == 1) {
							l.add(loc);
						}
					}
				}
			}

			return l;
		}

		public class Location {
			private int row, col;

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

			public String myValue() {
				return startingMaze[this.row][this.col];
			}

			public ArrayList<Location> nearMe() {
				ArrayList<Location> t = new ArrayList<Location>();
				t.add(new Location(row - 1, col));
				t.add(new Location(row + 1, col));
				t.add(new Location(row, col - 1));
				t.add(new Location(row, col + 1));
				return t;
			}
		}

	}

	public String[][] doA3() {
		Attempt3 a = new Attempt3(maze);
		Thread t = new Thread(a);
		t.start();
		return maze;
	}

	public class Attempt3 implements Runnable {
		private String[][] m;

		public Attempt3(String[][] m) {
			this.m = m;
		}

		@Override
		public void run() {
			solveMaze();
			maze = m;
		}

		public void solveMaze() {
			int wallsCreated = -1;
			while (wallsCreated != 0) {
				wallsCreated = 0;
				for (int r = 0; r < m.length; r++) {
					for (int c = 0; c < m[0].length; c++) {
						if (!(m[r][c] == "X") && !(m[r][c] == "B")) {
							Location t = new Location(r, c);
							if (!(r == 0 || c == 0 || r == m.length || c == m[0].length) && t.nearMe().size() == 1) {
								wallsCreated++;
								m[r][c] = "B";
							} else {
								m[r][c] = "P";
							}
						}
					}
				}
			}
		}

		public class Location {
			private int row, col;

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

			public String myValue() {
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
					if (t.get(i).myValue() == "X") {
						t.remove(i);
						i--;
					}
				}
				return t;
			}
		}
	}
}
