import java.util.Scanner;

public class ProblemB {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scan.nextInt();
	}
	
	public static int fib(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 0) {
			return 0;
		} else {
			return (fib(n - 1) + fib(n - 2));
		}
	}
}
