#!/bin/bash
stringTOT="`cat -n Bishop.java Blank.java Board.java ChessPanelButtons.java Evaluate.java Evaluator.java King.java Knight.java Location.java Main.java MoveComparator.java Pawn.java Piece.java Queen.java Rook.java | tail -n 1`"
stringNONBLANK="`cat -b Bishop.java Blank.java Board.java ChessPanelButtons.java Evaluate.java Evaluator.java King.java Knight.java Location.java Main.java MoveComparator.java Pawn.java Piece.java Queen.java Rook.java | tail -n 1`"

echo "TOTAL LINES:" ${stringTOT:0:7}
echo "NON-BLANK LINES:" ${stringNONBLANK:0:7}
