import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MazeSolver {
	/*
	 * "B" should be not possible paths "P" should be possible paths
	 * 
	 * password: run
	 * 
	 */

	private String[][] maze;

	public MazeSolver(String[][] maze) {
		this.maze = maze;
		deadEndStart();
	}

	public void deadEndStart() {
		boolean s = false;
		Location start = new Location(0, 0);
		while (!s) {
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[0].length; c++) {
					if (maze[r][c].equals(" ")) {
						s = true;
						start = new Location(r, c);
						break;
					}
					if (s) {
						break;
					}
				}
			}
		}
		System.out.println("Starting at : [" + start.getCol() + ", " + start.getRow() + "]");
		deadEndFiller(start);
	}

	public void deadEndFiller(Location l) {
		
		if (l.getCol() == 0 || l.getCol() == maze[0].length || l.getRow() == 0 || l.getRow() == maze.length) {
			maze[l.getRow()][l.getCol()] = "P";
		} else if (l.openAt().size() == 1) {
			maze[l.getRow()][l.getCol()] = "B";
			deadEndFiller(l.openAt().get(0));
		} else if (l.openAt().size() > 1) {
			maze[l.getRow()][l.getCol()] = "P";
			LinkedHashSet<Integer> rMove = new LinkedHashSet<Integer>();
			if (rMove.size() > 0) {
				while (rMove.size() < l.openAt().size()) {
					rMove.add((int) (Math.random() * l.openAt().size()));
				}
				for (Integer n : rMove) {
					if (!maze[l.openAt().get(n).getRow()][l.openAt().get(n).getCol()].equals("P")) {
						deadEndFiller(l.openAt().get(n));
					}
				}
			}
		}

	}

	public String[][] getMaze() {
		return maze;
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
			System.out.println("Set started.");
			if (row - 1 >= 0) {
				System.out.println("Adding row-1");
				l.add(new Location(row - 1, col));
			}
			if (row + 1 < maze.length) {
				System.out.println("Adding row+1");
				l.add(new Location(row + 1, col));
			}
			if (col - 1 >= 0) {
				System.out.println("Adding col-1");
				l.add(new Location(row, col - 1));
			}
			if (col + 1 < maze[0].length) {
				System.out.println("Adding col+1");
				l.add(new Location(row, col + 1));
			}
			for (int i = 0; i < l.size(); i++) {
				if (maze[l.get(i).getRow()][l.get(i).getCol()].equals("X")
						|| maze[l.get(i).getRow()][l.get(i).getCol()].equals("B")) {
					l.remove(i);
					i--;
				}
			}
			System.out.println("Set finished. Set size: " + l.size());

			return l;
		}
	}
}
