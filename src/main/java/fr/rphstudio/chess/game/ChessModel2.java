package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel2 implements IChess {
    private static ChessModel2 instance ;
    private IChess memory;


    public static IChess getInstance() {
        if(ChessModel2.instance==null){
            ChessModel2.instance = new ChessModel2();
        }
        return ChessModel2.instance;
    }


    @Override
    public void reinit() {

    }


    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.y==1 || p.y==6){
            return ChessType.TYP_PAWN;
        }
        else {
            if (p.y==0 || p.y==7){
                if (p.x==0 || p.x==7) {
                    return ChessType.TYP_ROOK;
                }
                else if (p.x==1 || p.x==6) {
                    return ChessType.TYP_KNIGHT;
                }
                else if (p.x==2 || p.x==5) {
                    return ChessType.TYP_BISHOP;
                }
                else if (p.x==3) {
                    return ChessType.TYP_QUEEN;
                }
                else if (p.x==4){
                    return ChessType.TYP_KING;
                }
            }
        }
        return null;
    }


    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.y<=1){
            return ChessColor.CLR_BLACK;
        }
        else {
            return ChessColor.CLR_WHITE;
        }
    }


    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return 0;
    }


    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        List<ChessPosition> list = new ArrayList<>();
        return list;
    }


    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

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
