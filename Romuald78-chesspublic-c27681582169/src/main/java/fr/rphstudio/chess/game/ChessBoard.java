package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessBoard {


    public Piece[][] create() {
        Piece[][] typeTable = new Piece[8][8];

        for (int col = 0; col < typeTable.length; col++) {
            Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN);
            typeTable[1][col] = pieceBlack;
            Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN);
            typeTable[6][col] = pieceWhite;
        }

        for (int col = 0; col < typeTable.length; col++) {
            if (col == 0 || col == 7) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK);
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK);
                typeTable[7][col] = pieceWhite;
            }
            else if (col == 1 || col == 6) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT);
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT);
                typeTable[7][col] = pieceWhite;
            }
            else if (col == 2 || col == 5) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP);
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP);
                typeTable[7][col] = pieceWhite;
            }
            else if (col == 3) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN);
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN);
                typeTable[7][col] = pieceWhite;
            }
            else {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING);
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING);
                typeTable[7][col] = pieceWhite;
            }
        }
        return typeTable;
    }
}