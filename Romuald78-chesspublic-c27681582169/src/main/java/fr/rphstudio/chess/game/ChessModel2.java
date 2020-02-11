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


    public void reinit() {

    }

    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return null;
    }


    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(p.y<=1){
            return ChessColor.CLR_WHITE;
        }
        else{
            return ChessColor.CLR_BLACK;
        }
    }


    public int getNbRemainingPieces(ChessColor color) {
        int remaining =  0;
        for (int i = 0; i<0; i++){
            for (int j = 0; j<0; j++){
                
            }
        }
        return remaining;
    }

    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        return null;
    }

    public void movePiece(ChessPosition p0, ChessPosition p1) {

    }

    public ChessKingState getKingState(ChessColor color) {
        return null;
    }

    public List<ChessType> getRemovedPieces(ChessColor color) {
        return null;
    }

    public boolean undoLastMove() {
        return false;
    }

    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
