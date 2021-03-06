import java.util.LinkedHashSet;
import java.util.TreeSet;

public class NoahsRandomMaze {

	private int[][] maze;

	private Direction[] directions;

	private final int OPEN_LOCATION = 3;
	private final int CLOSED_LOCATION = 0;
	private final int START = 2;
	private final int ENTRANCE = 3; // was 4
	private final int EXIT = 3; // was 5

	public String[][] getMaze() {
		String[][] tMaze = new String[maze.length][maze[0].length];
		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[0].length; c++) {
				if (maze[r][c] == OPEN_LOCATION || maze[r][c] == ENTRANCE || maze[r][c] == EXIT || maze[r][c] == START)
					tMaze[r][c] = " ";
				else if (maze[r][c] == CLOSED_LOCATION) {
					tMaze[r][c] = "X";
				}
			}
		}

		return tMaze;
	}

	public NoahsRandomMaze(int rows, int cols) {
		directions = new Direction[4];
		directions[0] = Direction.UP;
		directions[1] = Direction.DOWN;
		directions[2] = Direction.RIGHT;
		directions[3] = Direction.LEFT;

		fillMaze(rows, cols);
		makePath();
		makeEntranceAndExit();
		// printMaze();
	}

	public void fillMaze(int rows, int cols) {
		maze = new int[rows][cols];
		for (int[] x : maze) {
			for (int y = 0; y < x.length; y++) {
				x[y] = CLOSED_LOCATION;
			}
		}
	}

	public void makeEntranceAndExit() {
		int x = (int) (Math.random() * maze[0].length);
		int y = (int) (Math.random() * maze.length);
		while (!canBeEntranceExit(x, y)) {
			x = (int) (Math.random() * maze[0].length);
			y = (int) (Math.random() * maze.length);
		}
		maze[y][x] = ENTRANCE;
		while (!canBeEntranceExit(x, y)) {
			x = (int) (Math.random() * maze[0].length);
			y = (int) (Math.random() * maze.length);
		}
		maze[y][x] = EXIT;
	}

	public void printMaze() {
	}

	public void makePath() {
		int x = (int) (Math.random() * (maze.length - 2)) + 1;
		int y = (int) (Math.random() * (maze[0].length - 2)) + 1;
		maze[y][x] = START;
		makePath(x, y);
	}

	public void makePath(int x, int y) {
		LinkedHashSet<Direction> checkedSpots = new LinkedHashSet<Direction>();
		while (checkedSpots.size() < 4) {
			checkedSpots.add(directions[(int) (Math.random() * 4)]);
		}
		for (Direction dir : checkedSpots) {
			switch (dir) {
			case UP:
				if (check(x, y - 1, dir)) {
					if (check(x, y - 2, dir)) {
						maze[y - 1][x] = OPEN_LOCATION;
						maze[y - 2][x] = OPEN_LOCATION;
						makePath(x, y - 2);
					} else {
						// makePath( x, y - 1 );
					}
				}
				break;
			case DOWN:
				if (check(x, y + 1, dir)) {
					if (check(x, y + 2, dir)) {
						maze[y + 1][x] = OPEN_LOCATION;
						maze[y + 2][x] = OPEN_LOCATION;
						makePath(x, y + 2);
					} else {
						// makePath( x, y + 1 );
					}
				}
				break;
			case LEFT:
				if (check(x - 1, y, dir)) {
					if (check(x - 2, y, dir)) {
						maze[y][x - 1] = OPEN_LOCATION;
						maze[y][x - 2] = OPEN_LOCATION;
						makePath(x - 2, y);
					} else {
						// makePath( x - 1, y );
					}
				}
				break;
			case RIGHT:
				if (check(x + 1, y, dir)) {
					if (check(x + 2, y, dir)) {
						maze[y][x + 1] = OPEN_LOCATION;
						maze[y][x + 2] = OPEN_LOCATION;
						makePath(x + 2, y);
					} else {
						// makePath( x + 1, y );
					}
				}
				break;
			}
		}
	}

	public boolean check(int x, int y, Direction direction) {
		if (x < 0 || x >= maze.length) {
			return false;
		}
		if (y < 0 || y >= maze.length) {
			return false;
		}
		if (isEdge(x, y)) {
			return false;
		}
		if (!isWall(x, y)) {
			return false;
		}
		if (!canModify(x, y, direction)) {
			return false;
		}
		return true;
	}

	public boolean isEdge(int x, int y) {
		if (x == 0) {
			return true;
		}
		if (x == maze[0].length - 1) {
			return true;
		}
		if (y == 0) {
			return true;
		}
		if (y == maze.length - 1) {
			return true;
		}
		return false;
	}

	public boolean isWall(int x, int y) {
		return maze[y][x] == CLOSED_LOCATION;
	}

	public boolean canModify(int x, int y, Direction direction) {
		if (!isWall(x, y - 1) && direction != Direction.DOWN) {
			return false;
		}
		if (!isWall(x, y + 1) && direction != Direction.UP) {
			return false;
		}
		if (!isWall(x - 1, y) && direction != Direction.RIGHT) {
			return false;
		}
		if (!isWall(x + 1, y) && direction != Direction.LEFT) {
			return false;
		}
		return true;
	}

	public boolean canBeEntranceExit(int x, int y) {
		if (!isEdge(x, y) || !isWall(x, y)) {
			return false;
		}
		if (x == 0) {
			if (isWall(x + 1, y)) {
				return false;
			}
		}
		if (y == 0) {
			if (isWall(x, y + 1)) {
				return false;
			}
		}
		if (x == maze[0].length - 1) {
			if (isWall(x - 1, y)) {
				return false;
			}
		}
		if (y == maze.length - 1) {
			if (isWall(x, y - 1)) {
				return false;
			}
		}
		return true;
	}

	private enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

}
