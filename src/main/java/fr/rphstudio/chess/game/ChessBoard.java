package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessBoard {
    private Piece[][] typeTable = new Piece[8][8];


    public Piece[][] createTable(){

        for (int col=0; col<typeTable.length; col++){
            Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN, new Pawn());
            typeTable[1][col] = pieceBlack;
            Piece pieceWhite= new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN, new Pawn());
            typeTable[6][col] = pieceWhite;
        }

        for (int col=0; col<typeTable.length; col++){
            if (col==0 || col==7) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK, new Rook());
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK, new Rook());
                typeTable[7][col] = pieceWhite;
            }
            else if (col==1 || col==6) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT, new Knight());
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT, new Knight());
                typeTable[7][col] = pieceWhite;
            }
            else if (col==2 || col==5) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP, new Bishop());
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP, new Bishop());
                typeTable[7][col] = pieceWhite;
            }
            else if (col==3) {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
                typeTable[7][col] = pieceWhite;
            }
            else {
                Piece pieceBlack = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING, new King());
                typeTable[0][col] = pieceBlack;
                Piece pieceWhite = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING, new King());
                typeTable[7][col] = pieceWhite;
            }
        }

        return typeTable;
    }


    public int isRemaining(IChess.ChessColor color){
        int remaining = 0;
        for (int i = 0; i< typeTable.length; i++) {
            for (int j = 0; j< typeTable.length; j++) {
                Piece piece = typeTable[i][j];
                if (piece != null) {
                    if (piece.getColor() == color) {
                        remaining++;
                    }
                }
            }
        }
        return remaining;
    }
}
