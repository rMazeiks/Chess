package chess;

public class Board {
	Square[][] piece = new Square[8][8];

	public void setRandom() {
		Kind[] choice = { Kind.BISHOP, Kind.KNIGHT, Kind.QUEEN, Kind.ROOK };
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (Math.random() > 0.9) {
					setPiece(choice[(int) (Math.random() * 4)],
							Math.random() > 0.5, x + 1, y + 1);
				} else {
					setPiece(Kind.EMPTY, false, x + 1, y + 1);
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			if (Math.random() > 0.6) {
				setPiece(Kind.PAWN, true, i + 1, 2);
			}
			if (Math.random() > 0.6) {
				setPiece(Kind.PAWN, false, i + 1, 7);
			}
		}
		setPiece(Kind.KING, true, (int) (Math.random() * 8) + 1,
				(int) (Math.random() * 8) + 1);
		setPiece(Kind.KING, false, (int) (Math.random() * 8) + 1,
				(int) (Math.random() * 8) + 1);

	}

	public void show() {
		System.out.print("    ");
		for (int i = 0; i < 8; i++) {
			System.out.print(" " + Utilities.letter(i) + "  ");
		}
		System.out.println();
		System.out.print("   +");
		for (int i = 0; i < 8; i++) {
			System.out.print("---+");
		}
		System.out.println("");
		for (int y = 7; y >= 0; y--) {
			System.out.print(" " + (y + 1) + " |");
			for (int x = 0; x < 8; x++) {
				String space = " ";
				if ((x + y) % 2 == 0) {
					space = "/";
				}
				System.out.print(space
						+ (piece[x][y].k == Kind.EMPTY ? space : piece[x][y]
								.toChar()) + space + "|");
			}
			System.out.println(" " + (y + 1));
			System.out.print("   +");
			for (int i = 0; i < 8; i++) {
				System.out.print("---+");
			}
			System.out.println("");
		}
		System.out.print("    ");
		for (int i = 0; i < 8; i++) {
			System.out.print(" " + Utilities.letter(i) + "  ");
		}
		System.out.print("\n\n");
	}

	public Board() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				setPiece(Kind.EMPTY, false, x + 1, y + 1);
			}
		}
		for (int i = 0; i < 8; i++) {
			setPiece(Kind.PAWN, true, i + 1, 2);
			setPiece(Kind.PAWN, false, i + 1, 7);
		}
		setPiece(Kind.ROOK, true, 1, 1);
		setPiece(Kind.ROOK, true, 8, 1);

		setPiece(Kind.ROOK, false, 1, 8);
		setPiece(Kind.ROOK, false, 8, 8);

		setPiece(Kind.KNIGHT, true, 2, 1);
		setPiece(Kind.KNIGHT, true, 7, 1);

		setPiece(Kind.KNIGHT, false, 2, 8);
		setPiece(Kind.KNIGHT, false, 7, 8);

		setPiece(Kind.BISHOP, true, 3, 1);
		setPiece(Kind.BISHOP, true, 6, 1);

		setPiece(Kind.BISHOP, false, 3, 8);
		setPiece(Kind.BISHOP, false, 6, 8);

		setPiece(Kind.QUEEN, true, 4, 1);
		setPiece(Kind.KING, true, 5, 1);

		setPiece(Kind.QUEEN, false, 4, 8);
		setPiece(Kind.KING, false, 5, 8);
	}

	public Board(Board copy) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Kind k = copy.piece[x][y].k;
				boolean c = copy.piece[x][y].c;
				setPiece(k, c, x + 1, y + 1);
			}
		}
	}

	void setTo(Board copy) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Kind k = copy.piece[x][y].k;
				boolean c = copy.piece[x][y].c;
				setPiece(k, c, x + 1, y + 1);
			}
		}
	}

	public int points(boolean color) {
		int answer = 0;
		if (won(color)) {
			answer = 700;
		} else if (won(!color)) {
			answer = -700;
		} else {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					answer += piece[x][y].value(color);
				}
			}
		}
		return answer;
	}

	public boolean checkMate(boolean color) {
		boolean answer = false;
		if (inCheck(color) & possibleMoves(color) == 0) {
			answer = true;
		}
		return answer;
	}

	/**
	 * Checks if the player of the specified color has won the game.
	 * 
	 * @param color
	 *            the color of the player to check.
	 * @return true if the player has won.
	 */
	public boolean won(boolean color) {
		boolean answer = false;
		if (checkMate(!color)) {
			answer = true;
		}
		return answer;
	}

	/**
	 * Returns the number of moves the player with the specified color can do.
	 * 
	 * @param color
	 *            the color to count moves.
	 */
	public int possibleMoves(boolean color) {

		int answer = 0;
		for (int sy = 0; sy < 8; sy++) {
			for (int sx = 0; sx < 8; sx++) {
				for (int ey = 0; ey < 8; ey++) {
					for (int ex = 0; ex < 8; ex++) {
						if (this.legalMove(new Move(sx + 1, sy + 1, ex + 1,
								ey + 1))) {
							answer++;
						}
					}
				}
			}
		}
		return answer;
	}

	/**
	 * Checks if a move can be done. Will only return true if the piece on the
	 * starting square can do the move and the move doesn't cause the player to
	 * be in check.
	 * 
	 * @param m
	 *            move to chack.
	 * @return true if the piece on the starting square can do the move and the
	 *         move doesn't cause the player to be in check.
	 */
	public boolean legalMove(Move m) {
		boolean answer = false;
		boolean col = piece[m.startX - 1][m.startY - 1].c;
		if (canGo(m)) {
			Board before = new Board(this);
			move(m);
			if (!inCheck(col)) {
				answer = true;
			}
			this.setTo(before);
		}
		return answer;
	}

	public boolean inCheck(boolean c) {
		boolean answer = false;

		int kingX = -1;
		int kingY = -1;

		searchKing: for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if ((piece[x][y].k == Kind.KING) & (piece[x][y].c == c)) {
					kingX = x;
					kingY = y;
					break searchKing;
				}
			}
		}
		if (kingX != -1) {
			search: for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (canGo(new Move((x + 1), (y + 1), (kingX + 1),
							(kingY + 1)))) {
						answer = true;
						break search;
					}
				}
			}
		}

		return answer;
	}

	public boolean canGo(Move m) {
		boolean answer = false;
		if (m.withinRange()) {
			switch (piece[m.startX - 1][m.startY - 1].k) {
			case BISHOP:
				answer = bishopCanGo(m);
				break;
			case KING:
				answer = kingCanGo(m);
				break;
			case KNIGHT:
				answer = knightCanGo(m);
				break;
			case PAWN:
				answer = pawnCanGo(m);
				break;
			case QUEEN:
				if (rookCanGo(m) || bishopCanGo(m)) {
					answer = true;
				}
				break;
			case ROOK:
				answer = rookCanGo(m);
				break;
			case EMPTY:
				break;
			default:
				System.out
						.println("The square (" + m.startX + "; " + m.startY
								+ ") contains a "
								+ piece[m.startX - 1][m.startY - 1].k);
				break;
			}
			if (!free(m.endX, m.endY, piece[m.startX - 1][m.startY - 1].c)) {
				answer = false;
			}
		}
		return answer;
	}

	private boolean kingCanGo(Move m) {
		boolean answer = false;

		if ((Math.abs(m.startX - m.endX) < 2)
				&& (Math.abs(m.startY - m.endY) < 2)) {
			answer = true;
		}
		return answer;
	}

	private boolean rookCanGo(Move m) {
		boolean answer = false;

		int sx = m.startX - 1;
		int sy = m.startY - 1;
		int ex = m.endX - 1;
		int ey = m.endY - 1;

		check: {
			int moveX = 0;
			int moveY = 0;

			if (sy == ey) {
				if (sx == ex) {
					break check;
				}
				moveX = (sx > ex) ? -1 : 1;
			} else if (sx == ex) {
				moveY = (sy > ey) ? -1 : 1;
			} else {
				break check;
			}

			sx += moveX;
			sy += moveY;

			search: {
				while (!((sx == ex) || (sy == ey))) {
					if (piece[sx][sy].k != Kind.EMPTY) {
						break search;
					}
					sx += moveX;
					sy += moveY;
				}
				answer = true;
			}
		}

		return answer;
	}

	private boolean bishopCanGo(Move m) {
		boolean answer = false;

		int nx = m.startX - 1;
		int ny = m.startY - 1;
		int ex = m.endX - 1;
		int ey = m.endY - 1;

		if (Math.abs(nx - ex) == Math.abs(ny - ey)) {

			int moveX = (nx > ex) ? -1 : 1;
			int moveY = (ny > ey) ? -1 : 1;

			nx += moveX;
			ny += moveY;
			while (((nx < 8) && (nx >= 0)) && ((ny < 8) && (ny >= 0))) {
				if (nx == ex) {
					answer = true;
					break;
				} else if (piece[nx][ny].k != Kind.EMPTY) {
					break;
				}
				nx += moveX;
				ny += moveY;
			}
		}
		return answer;
	}

	private boolean knightCanGo(Move m) {
		boolean answer = false;
		int sx = m.startX;
		int sy = m.startY;
		int ex = m.endX;
		int ey = m.endY;

		if (((Math.abs(ex - sx) == 2) && (Math.abs(ey - sy) == 1))
				|| ((Math.abs(ex - sx) == 1) && (Math.abs(ey - sy) == 2))) {
			answer = true;
		}
		return answer;
	}

	private boolean pawnCanGo(Move m) { // no en passant
		boolean answer = false;
		int sx = m.startX - 1;
		int sy = m.startY - 1;
		int ex = m.endX - 1;
		int ey = m.endY - 1;

		int co = (piece[sx][sy].c) ? 1 : -1;

		if (piece[ex][ey].k == Kind.EMPTY) {
			if (sx == ex) {
				if (sy + co == ey) {
					answer = true;
				}
				if (((sy == 1) && (co == 1)) || ((sy == 6) && (co == -1))) {
					if ((sy + (2 * co) == ey)
							& (piece[sx][sy + co].k == Kind.EMPTY)) {
						answer = true;
					}
				}
			}
		} else if (piece[ex][ey].c != piece[sx][sy].c) {
			if ((ey - sy == co) & (Math.abs(sx - ex) == 1)) {
				answer = true;
			}
		}

		return answer;
	}

	public void move(Move m) {
		Kind k = piece[m.startX - 1][m.startY - 1].k;
		boolean c = piece[m.startX - 1][m.startY - 1].c;
		int x = m.endX;
		int y = m.endY;

		setPiece(k, c, x, y);
		setPiece(Kind.EMPTY, false, m.startX, m.startY);
	}

	private void setPiece(Kind setTo, boolean setColor, int setX, int setY) {
		piece[setX - 1][setY - 1] = new Square(setTo, setColor);
	}

	/**
	 * 
	 * @param x
	 *            the x value of the square between 1 and 8
	 * @param y
	 *            the y value of the square between 1 and 8
	 * @param c
	 *            the color to check
	 * @return
	 */
	private boolean free(int x, int y, boolean c) {
		boolean answer = false;

		if ((piece[x - 1][y - 1].k == Kind.EMPTY)
				|| (piece[x - 1][y - 1].c != c)) {
			answer = true;
		}

		return answer;
	}
}
