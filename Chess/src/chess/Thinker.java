package chess;

import java.util.ArrayList;
import java.util.List;

public class Thinker {

	public static Move[] arrangeMoves(Board b, boolean col, int max) {
		List<Move> answer = new ArrayList<Move>();
		List<Integer> p = new ArrayList<Integer>();

		Board before = new Board(b);

		for (int sy = 0; sy < 8; sy++) {
			for (int sx = 0; sx < 8; sx++) {
				if ((b.piece[sx][sy].c == col)) {

					for (int ey = 0; ey < 8; ey++) {
						for (int ex = 0; ex < 8; ex++) {
							Move m = new Move(sx + 1, sy + 1, ex + 1, ey + 1);
							if (b.legalMove(m)) {
								b.move(m);
								int c = points(b, col, 0, max);

								for (int i = 0; i <= answer.size(); i++) {
									if (i == answer.size()) {
										answer.add(m);
										p.add(new Integer(c));
										break;
									} else {
										if (c > p.get(i)) {
											answer.add(i, m);
											p.add(i, new Integer(c));
											break;
										}
									}
								}
							}
							b = new Board(before);
						}
					}
				}
			}
		}
		Move finalAnswer[] = new Move[answer.size()];
		for (int i = 0; i < answer.size(); i++) {
			finalAnswer[i] = answer.get(i);
		}

		return finalAnswer;
	}

	static int points(Board b, boolean col, int l, int max) {
		int answer = 0;

		boolean thisCol = col;
		if (l % 2 == 0) {
			thisCol = !thisCol;
		}
		if (l <= max) {
			Board before = new Board(b);

			for (int sy = 0; sy < 8; sy++) {
				for (int sx = 0; sx < 8; sx++) {
					if (b.piece[sx][sy].c == thisCol) {

						for (int ey = 0; ey < 8; ey++) {
							for (int ex = 0; ex < 8; ex++) {
								
								Move m = new Move(sx + 1, sy + 1, ex + 1,
										ey + 1);
								if ((b.legalMove(m))) {
									b.move(m);
									int c = points(b, col, l+1, max);
									if (((c > answer) & (l % 2 == 1))
											|| ((c < answer) & (l % 2 == 0))) {
										answer = c;
									}
									b = new Board(before);
								}
							}
						}
					}
				}
			}
		} else {
			answer = b.points(col);
		}

		return answer;
	}
}
