package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.List;

public class ChessModel2 implements IChess {

    private static ChessModel2 instance;

    private ChessModel2() {

    }

    public static ChessModel2 getInstance() {
        if (ChessModel2.instance == null) {
            ChessModel2.instance = new ChessModel2();
        }
        return instance;
    }


    @Override
    public void reinit() {

    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return null;
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(p.y<=1){
            return ChessColor.CLR_WHITE;
        }
        else{
            return ChessColor.CLR_BLACK;
        }
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        int remaining =  0;
        for (remaining = 0; remaining<0; remaining++){
            
        }
        return remaining;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        return null;
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return null;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return null;
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
