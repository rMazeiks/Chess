package chess;

public class Utilities {
	public static char letter(int i) {
		char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		return a[i];
	}
	
	public static String prefix(int i)  {
		String answer = "";
		if(i>10 & i<20)  {
			answer = "th";
		} else  {
			if(i%10==1)  {
				answer = "st";
			} else if(i%10==2)  {
				answer = "nd";
			} else if(i%10==3)  {
				answer = "rd";
			} else  {
				answer = "th";
			}
		}
		return answer;
	}
}
