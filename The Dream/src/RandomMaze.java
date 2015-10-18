import java.util.LinkedHashSet;
import java.util.Set;
import org.omg.Messaging.SyncScopeHelper;

public class RandomMaze
{

	private static String[][] myMaze;

	public RandomMaze(int givenRow, int givenCol)
	{
		if (givenRow < 3 || givenCol < 3)
		{
			return;
		}
		myMaze = new String[givenRow][givenCol];
		for (int rowO = 0; rowO < givenRow; rowO++)
		{
			for (int colO = 0; colO < givenCol; colO++)
			{
				myMaze[rowO][colO] = "X";
			}
		}
		int startRow = (int) (Math.random() * (givenRow - 2)) + 1;
		int startCol = (int) (Math.random() * (givenRow - 2)) + 1;

		// System.out.println("Setting Entrance [0, " + startRow + "] to \"
		// \".");
		myMaze[startRow][startCol] = " ";
		createPath(startRow, startCol);
		createDoors();
	}

	public int numRows()
	{
		return myMaze.length;
	}

	public int numCols()
	{
		return myMaze[0].length;
	}

	public String[][] getMaze()
	{
		return myMaze;
	}

	public static void printMaze()
	{
		for (int r = 0; r < myMaze.length; r++)
		{
			for (int c = 0; c < myMaze[0].length; c++)
			{
				System.out.print(myMaze[r][c]);
			}
			System.out.println();
		}
	}

	public void createPath(int givenRow, int givenCol)
	{
		if (givenRow == 0 || givenRow == myMaze.length - 1 || givenCol == 0
				|| givenCol == myMaze.length - 1)
		{
			// System.out.println("Return 1");
			return;
		}
		// System.out.println("Setting position [" + givenCol + ", " + givenRow
		// + "] to \" \".");
		myMaze[givenRow][givenCol] = " ";
		Set<Integer> moves = new LinkedHashSet<Integer>();
		while (moves.size() < 4)
		{
			moves.add((int) (Math.random() * 4));
		}

		for (Integer n : moves)
		{
			switch (n)
			{
				case 0 :
					if (!(myMaze[givenRow - 1][givenCol].equals(" ")
							|| (givenRow - 2 >= 0
									&& myMaze[givenRow - 2][givenCol]
											.equals(" "))
							|| myMaze[givenRow - 1][givenCol + 1].equals(" ")
							|| myMaze[givenRow - 1][givenCol - 1].equals(" ")))
					{
						createPath(givenRow - 1, givenCol);
					}
					break;
				case 1 :
					if (!(myMaze[givenRow + 1][givenCol].equals(" ")
							|| (givenRow + 2 < myMaze.length
									&& myMaze[givenRow + 2][givenCol]
											.equals(" "))
							|| myMaze[givenRow + 1][givenCol + 1].equals(" ")
							|| myMaze[givenRow + 1][givenCol - 1].equals(" ")))
					{
						createPath(givenRow + 1, givenCol);
					}
					break;
				case 2 :
					if (!(myMaze[givenRow][givenCol - 1].equals(" ")
							|| (givenCol - 2 >= 0
									&& myMaze[givenRow][givenCol - 2]
											.equals(" "))
							|| myMaze[givenRow + 1][givenCol - 1].equals(" ")
							|| myMaze[givenRow - 1][givenCol - 1].equals(" ")))
					{
						createPath(givenRow, givenCol - 1);
					}
					break;
				case 3 :
					if (!(myMaze[givenRow][givenCol + 1].equals(" ")
							|| (givenCol + 2 < myMaze[0].length
									&& myMaze[givenRow][givenCol + 2]
											.equals(" "))
							|| myMaze[givenRow + 1][givenCol + 1].equals(" ")
							|| myMaze[givenRow - 1][givenCol + 1].equals(" ")))
					{
						createPath(givenRow, givenCol + 1);
					}
					break;
			}
		}
	}

	public void createDoors()
	{
		int doorsMade = 0;
		while (doorsMade < 2)
		{
			int rRow = -1;
			int rCol = -1;
			boolean doorable = false;
			if (Math.random() > .5)
			{
				rRow = ((int) (Math.random() * 2)) * (myMaze.length - 1);
				rCol = (int) (Math.random() * (myMaze[0].length - 2)) + 1;
			} else
			{
				rRow = (int) (Math.random() * (myMaze.length - 2)) + 1;
				rCol = ((int) (Math.random() * 2)) * (myMaze[0].length - 1);
			}
			if (myMaze[rRow - (rRow == 0 ? 0 : 1)][rCol] == " "
					|| myMaze[rRow
							+ (rRow == myMaze.length ? 1 : 0)][rCol] == " "
					|| myMaze[rRow][rCol - (rCol == 0 ? 0 : 1)] == " "
					|| myMaze[rRow][rCol
							+ (rCol == myMaze[0].length ? 1 : 0)] == " ")
			{
				doorable = true;
			}

			if (doorable)
			{
				myMaze[rRow][rCol] = " ";
				doorsMade++;

			}
		}
	}
}
