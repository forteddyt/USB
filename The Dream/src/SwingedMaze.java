import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingedMaze {
	private JFrame frame = new JFrame();
	private JPanel top = new JPanel();
	/* ##################################### */
	private RandomMaze myMaze = new RandomMaze(50, 50);
	/* ##################################### */

	public SwingedMaze() {
		frame.setTitle("Maze: " + myMaze.numCols() + " x " + myMaze.numRows());

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1000, 1000);
		top.setLayout(new GridLayout(myMaze.numRows(), myMaze.numCols()));
		top.setBackground(Color.WHITE);

		drawMaze();

		top.setVisible(true);
		frame.add(top);
		frame.setVisible(true);
	}

	public void drawMaze() {
		String[][] m = myMaze.getMaze();
		for (int r = 0; r < myMaze.numRows(); r++) {
			for (int c = 0; c < myMaze.numCols(); c++) {
				JPanel l = new JPanel();
				if (m[r][c].equals("X")) {
					l.setBackground(Color.BLACK);
				} else if (m[r][c].equals(" ")) {
					l.setBackground(Color.WHITE);
				} else if (m[r][c].equals("W")) {
					l.setBackground(Color.RED);
				} else if (m[r][c].equals("P")) {
					l.setBackground(Color.GREEN);
				}
				l.setVisible(true);
				top.add(l);
			}
		}
	}

	public RandomMaze getRMaze() {
		return myMaze;
	}

	public static void main(String[] args) {
		SwingedMaze m = new SwingedMaze();
	}
}
