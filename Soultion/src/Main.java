import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();

		for(int i = 123 ; i <= 987 ; i++) {
			int third = i%10; int second = (i/10)%10; int first = (i/100);
			if(first == second || first == third || second == third || first == 0 || second == 0 || third == 0) continue;
			set.add(i);
		}
		/*
		* 123 ~ 987 다른 숫자
		* */
		for (int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String number = String.valueOf(st.nextToken());
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			String guess = "";
			
			for(int j = 123 ; j <= 987 ; j++) {
				guess = String.valueOf(j);
				int first = guess.charAt(0) - '0'; int second = guess.charAt(1) - '0'; int third = guess.charAt(2) - '0';
				if(first == second || first == third || second == third || first == 0 || second == 0 || third == 0) continue;

				if(calculate(number, guess, strike, ball)){
					if(set.contains(j)) {
						set.remove(j);
					}
				}
			}
		}
		System.out.println(set.size());
	}

	static boolean calculate(String number, String guess, int strike, int ball) {
		int strikeCounter = 0; int ballCounter = 0;
		for(int i = 0 ; i < 3 ; i++) {
			if(guess.charAt(i) == number.charAt(i)) strikeCounter++;
		}
		if(strikeCounter != strike) return true;

		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(i == j) continue;
				if(guess.charAt(i) == number.charAt(j)) ballCounter++;
			}
		}
		if(ballCounter != ball) return true;

		return false;
	}
}
