import java.util.*;

public class Problem9 {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		ArrayList<String> meh = new ArrayList<String>();
		int n = scan.nextInt();
		for(int i = 0 ;i < n; i++)
			if( scan.nextInt() > 1000 )
				meh.add("violation");
			else
				meh.add("legal");
		for( int i = 0; i < meh.size(); i++)
			System.out.println(meh.get(i));
	}
}
