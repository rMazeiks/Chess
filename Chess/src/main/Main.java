package main;

import chess.Board;

public class Main {
	static Board board = new Board();
	static int turn = 0;

	public static void main(String[] args) {
		ui.Bar.make();
		ui.Create.make();
		
//		board.show();
//		for (int i = 0; i < 100; i++) {
//			System.out.println(board.possibleMoves(turn % 2 == 0)
//					+ " possible moves");
//			Move m = Thinker.arrangeMoves(board, turn % 2 == 0, 1)[0];
//			System.out.println(m.toString(board));
//			board.move(m);
//			board.show();
//			turn++;
//		}
	}
}
