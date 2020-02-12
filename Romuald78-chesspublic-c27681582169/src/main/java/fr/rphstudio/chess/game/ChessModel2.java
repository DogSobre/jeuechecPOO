package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.OutOfBoardException;
import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;

public class ChessModel2 implements IChess {

    private static ChessModel2 instance;
    private Piece[][] typeTable;
    private ChessBoard board = new ChessBoard();
    private ChessModel2() {

    }

    public static ChessModel2 getInstance() {
        if (ChessModel2.instance == null) {
            ChessModel2.instance = new ChessModel2();
        }
            return ChessModel2.instance;
    }


    public void reinit() {
        typeTable = board.createTable();
    }

    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0) {
            throw new OutOfBoardException();
        }
        Piece piece = this.typeTable[p.y][p.x];
        if (piece == null){
            throw new EmptyCellException();
        }
        return piece.getType();
    }

    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }
        Piece piece = this.typeTable[p.y][p.x];
        if (piece == null){
            throw new EmptyCellException();
        }
        return piece.getColor();
    }


    public int getNbRemainingPieces(ChessColor color) {
        return board.isRemaining(color);
    }

    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        try {
            return board.getPieceMoves(p);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public void movePiece(ChessPosition p0, ChessPosition p1) {
        board.movePiece(p0, p1);
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