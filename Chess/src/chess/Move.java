package chess;

public class Move {
	int startX;
	int startY;
	int endX;
	int endY;

	public Move(int startingX, int startingY, int endingX, int endingY) {
		startX = startingX;
		startY = startingY;
		endX = endingX;
		endY = endingY;
	}

	public boolean withinRange() {
		boolean answer = true;
		if ((((startX > 8) || (startX < 1)) || ((startY > 8) || (startY < 1)))
				|| (((endX > 8) || (endX < 1)) || ((endY > 8) || (endY < 1)))) {
			answer = false;
		}
		return answer;
	}

	public String toString(Board b) {
		String answer = "";
		Kind t = b.piece[this.startX-1][this.startY-1].k;
		answer += t.toString();
		if (t != Kind.EMPTY & t != Kind.KING || t != Kind.BISHOP) {
			answer += (" from " + (Utilities.letter(startX-1) + " " + (startY)));
		}
		answer += (" to " + (Utilities.letter(endX-1) + " " + (endY)));
		return answer;
	}
}