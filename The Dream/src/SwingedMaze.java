import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingedMaze {
	private JFrame frame = new JFrame();
	private JPanel top = new JPanel();
	private JPanel mazeTop = new JPanel();
	private JButton b = new JButton();
	/* ##################################### */
	private RandomMaze myMaze = new RandomMaze(100, 100);
	/* ##################################### */
	private JPanel[][] tiles;

	public SwingedMaze() {
		String[][] m = myMaze.getMaze();
		tiles = new JPanel[m.length][m[0].length];

		frame.setTitle("Maze: " + myMaze.numCols() + " x " + myMaze.numRows());

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		mazeTop.setLayout(new GridLayout(myMaze.numRows(), myMaze.numCols()));
		mazeTop.setBackground(Color.WHITE);

		drawMaze();
		mazeTop.setVisible(true);

		b.addActionListener(new bAction());

		top.setLayout(new BorderLayout());
		top.add(mazeTop, BorderLayout.CENTER);
		top.add(b, BorderLayout.PAGE_END);
		top.setVisible(true);
		frame.add(top);
		frame.setVisible(true);
	}

	public class bAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Solving...");
			drawSolved();
			System.out.println("Solved!");
		}
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
				}
				l.setVisible(true);
				mazeTop.add(l);
				tiles[r][c] = l;
			}
		}
	}

	public void drawSolved() {
		MazeSolver solver = new MazeSolver(myMaze.getMaze());

		String[][] sM = solver.getMaze();
		for (int r = 0; r < sM.length; r++) {
			for (int c = 0; c < sM[0].length; c++) {
				if (sM[r][c].equals("X")) {
					tiles[r][c].setBackground(Color.BLACK);
				} else if (sM[r][c].equals(" ")) {
					tiles[r][c].setBackground(Color.WHITE);
				} else if (sM[r][c].equals("B")) {
					tiles[r][c].setBackground((Color.RED).darker());
				} else if (sM[r][c].equals("P")) {
					tiles[r][c].setBackground(Color.GREEN);
				}
				
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
