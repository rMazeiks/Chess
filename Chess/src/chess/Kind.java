package chess;

public enum Kind {
	KING, PAWN, QUEEN, KNIGHT, BISHOP, ROOK, EMPTY;
	
	public String toString()  {
		String answer = "";
		switch(this)  {
		case BISHOP:
			answer = "Bishop";
			break;
		case KING:
			answer= "King";
			break;
		case KNIGHT:
			answer = "Knight";
			break;
		case PAWN:
			answer = "Pawn";
			break;
		case QUEEN:
			answer = "Queen";
			break;
		case ROOK:
			answer = "Rook";
			break;
		default:
			break;
		
		}
		return answer;
	}
}
