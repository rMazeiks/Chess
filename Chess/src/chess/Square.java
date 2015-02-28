package chess;

public class Square {
	public Kind k;
	public boolean c;

	public Square(Kind kind, boolean color) {
		k = kind;
		c = color;
	}

	public char toChar() {
		char answer = ' ';
		if (k == Kind.BISHOP) {
			answer = 'b';
		} else if (k == Kind.KING) {
			answer = 'k';
		} else if (k == Kind.KNIGHT) {
			answer = 'h';
		} else if (k == Kind.PAWN) {
			answer = 'p';
		} else if (k == Kind.QUEEN) {
			answer = 'q';
		} else if (k == Kind.ROOK) {
			answer = 'r';
		}
		if (c) {
			answer = Character.toUpperCase(answer);
		}
		return answer;
	}

	public int value(boolean color) {
		int answer = 0;
		switch (k) {
		case KING:
		case PAWN:
			if (color) {
				answer += 1;
			} else {
				answer -= 1;
			}
			break;
		case ROOK:
			if (color) {
				answer += 5;
			} else {
				answer -= 5;
			}
			break;
		case KNIGHT:
		case BISHOP:
			if (color) {
				answer += 3;
			} else {
				answer -= 3;
			}
			break;
		case QUEEN:
			if (color) {
				answer += 10;
			} else {
				answer -= 10;
			}
			break;
		default:
			break;
		}
		return answer;
	}
}
