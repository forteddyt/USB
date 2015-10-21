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
	private RandomMaze myMaze = new RandomMaze(20, 20);
	private NoahsRandomMaze noahsMaze = new NoahsRandomMaze(100, 100);
	/* ##################################### */

	String[][] m = myMaze.getMaze();

	private JPanel[][] tiles;

	public SwingedMaze() {
		tiles = new JPanel[m.length][m[0].length];

		frame.setTitle("Maze: " + m[0].length + " x " + m.length);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		mazeTop.setLayout(new GridLayout(m.length, m[0].length));
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
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
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
		MazeSolver solver = new MazeSolver(m);

		String[][] sM = solver.doA2();
		for (int r = 0; r < sM.length; r++) {
			for (int c = 0; c < sM[0].length; c++) {
				if (sM[r][c].equals("X")) {
					//System.out.println("Redrawing Wall");
					tiles[r][c].setBackground(Color.BLACK);
				} else if (sM[r][c].equals(" ")) {
					//System.out.println("Redrawing White");
					tiles[r][c].setBackground(Color.WHITE);
				} else if (sM[r][c].equals("B")) {
					//System.out.println("Redrawing Blocked");
					tiles[r][c].setBackground((Color.RED).darker());
				} else if (sM[r][c].equals("P")) {
					//System.out.println("Redrawing Path");
					tiles[r][c].setBackground(Color.GREEN);
				}

			}
		}
	}

	public RandomMaze getRMaze() {
		return myMaze;
	}

	public NoahsRandomMaze getNRMaze() {
		return noahsMaze;
	}

	public static void main(String[] args) {
		SwingedMaze m = new SwingedMaze();
	}
}
