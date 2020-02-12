package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.OutOfBoardException;
import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;

public class ChessModel2 implements IChess {

    private static ChessModel2 instance;
    private Piece[][] typeTable = new Piece[8][8];
    private ChessModel2() {

    }

    public static ChessModel2 getInstance() {
        if (ChessModel2.instance == null) {
            ChessModel2.instance = new ChessModel2();
        }
            return ChessModel2.instance;
    }


    public void reinit() {
        typeTable = new ChessBoard().create();
    }

    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.typeTable[p.y][p.x];
        if (piece == null){
            throw new EmptyCellException();
        }
        return piece.getType();
    }

    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.typeTable[p.y][p.x];
        if (piece == null){
            throw new EmptyCellException();
        }
        return piece.getColor();
    }


    public int getNbRemainingPieces(ChessColor color) {
        return 0;
    }

    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        List<ChessPosition> list = new ArrayList<>();
        return list;
    }

    public void movePiece(ChessPosition p0, ChessPosition p1) {

    }

    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_THREATEN;
    }

    public List<ChessType> getRemovedPieces(ChessColor color) {
        List<ChessType> list = new ArrayList<>();
        return list;
    }

    public boolean undoLastMove() {
        return false;
    }

    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
