import java.util.*;

public class Problem1 {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int numOfClass = scan.nextInt();
		int [] numStudents = new int[numOfClass];
		int [] meanScores = new int[numOfClass];
		ArrayList<List<Integer>> gradeSet = new ArrayList<List<Integer>>();
		for( int i = 0; i < numOfClass; i++){
			List<Integer> values = new ArrayList<Integer>();
			numStudents[i] = scan.nextInt();
			for( int n = 0; n < numStudents[i]; n++){
				values.add(scan.nextInt());
			}
			
			gradeSet.add(values);
		}
		
		
		for( int i = 0; i < numOfClass; i++){
			for( int q = 0; q < numStudents[i]; q++){
				int mean = 0;
				for( int s = 0; s < gradeSet.get(i).size(); s++){
					mean += gradeSet.get(i).get(s);
				}
				mean /= gradeSet.get(i).size();
				meanScores[i] = mean;
			}
		}
		
		
		int [] SD = new int[numOfClass];
		for( int i = 0; i < SD.length; i++ ){
			SD[i] = RMS(gradeSet.get(i), meanScores[i]);
		}
		
		
		ArrayList<List<String>> scoreSet = new ArrayList<List<String>>();
		for( int i = 0 ; i < numOfClass; i++){
			List<String> temp = new ArrayList<String>();
			for ( int n = 0; n < numStudents[i]; n++){
				if ( ((gradeSet.get(i).get(n) - meanScores[i]) / SD[i]) >= 1 )
					temp.add("A");
				if ( ((gradeSet.get(i).get(n) - meanScores[i]) / SD[i]) == 1 )
					temp.add("B");
				if ( ((gradeSet.get(i).get(n) - meanScores[i]) / SD[i]) == 0 )
					temp.add("C");
				if ( ((gradeSet.get(i).get(n) - meanScores[i]) / SD[i]) == -1 )
					temp.add("D");
				if ( ((gradeSet.get(i).get(n) - meanScores[i]) / SD[i]) <= -2)
					temp.add("F");
			}
			scoreSet.add(temp);
		}
		
		for( int i = 0; i < numStudents[0]; i++)
			System.out.println(scoreSet.get(0).get(i));
		
		for( int i = 0; i < numOfClass; i++){
			System.out.println("Class " + (i + 1));
			for ( int n = 0; n < numStudents[i]; n++){
				System.out.println( gradeSet.get(i).get(n) + " " + scoreSet.get(i).get(n));
			}
		}
	}
	
	public static int RMS( List<Integer> grades, int mean ){
		int sum = 0; 
		for( int i = 0; i < grades.size(); i++ ){
			sum += Math.pow((grades.get(i) - mean), 2); 
		}
		sum /= grades.size();
		return (int)(Math.pow(sum, .5) + .5);
	}
}
