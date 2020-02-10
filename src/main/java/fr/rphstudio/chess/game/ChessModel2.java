package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel2 implements IChess {
    private static ChessModel2 instance ;
    private  Piece[][] typeTable;
    private ChessBoard board = new ChessBoard();


    public static IChess getInstance() {
        if(ChessModel2.instance==null){
            ChessModel2.instance = new ChessModel2();
        }
        return ChessModel2.instance;
    }


    @Override
    public void reinit() {
        typeTable = board.createTable();
    }


    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getType();
        }
    }


    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getColor();
        }
    }


    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return board.isRemaining(color);
    }


    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        try {
            return board.getPieceMoves(p);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }


    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        System.out.println("init: " + p0.y + "." + p0.x + ".  final: " + p1.y + "." + p1.x + "##############################################################################");
        board.movePiece(p0, p1);
    }


    @Override
    public ChessKingState getKingState(ChessColor color) {

        return ChessKingState.KING_SAFE;
    }


    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        List<ChessType> list = new ArrayList<>();


        return list;
    }


    @Override
    public boolean undoLastMove() {
        return false;
    }


    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
