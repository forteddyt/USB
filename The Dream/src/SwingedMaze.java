import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingedMaze {
	private JFrame frame = new JFrame();

	public SwingedMaze() {

		/* ##################################### */
		RandomMaze myMaze = new RandomMaze(50, 50);
		/* ##################################### */

		frame.setTitle("Maze: " + myMaze.numCols() + " x " + myMaze.numRows());

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1000, 1000);

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(myMaze.numRows(), myMaze.numCols()));
		top.setBackground(Color.WHITE);
		String[][] m = myMaze.getMaze();
		for (int r = 0; r < myMaze.numRows(); r++) {
			for (int c = 0; c < myMaze.numCols(); c++) {
				JPanel l = new JPanel();
				if (m[r][c].equals("X")) {
					l.setBackground(Color.BLACK);
				} else {
					l.setBackground(Color.WHITE);
				}
				l.setVisible(true);
				top.add(l);
			}
		}

		top.setVisible(true);
		frame.add(top);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingedMaze m = new SwingedMaze();
	}
}
