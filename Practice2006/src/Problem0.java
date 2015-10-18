
import java.util.*;


public class Problem0 {
	public static void main(String [] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("");
		List<String> meh = new ArrayList<String>();
		int temp = scan.nextInt();
		for (int i = 0; i < temp; i ++)
			meh.add(scan.next());
		Object [] eh = meh.toArray();
		Arrays.sort(eh);
		for(int i = 0; i < eh.length; i++)
			System.out.println(eh[i]);
	}
}
