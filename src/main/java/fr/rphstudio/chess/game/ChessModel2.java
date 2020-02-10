package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel2 implements IChess {
    private static ChessModel2 instance ;
    private IChess memory;
    private  List<Piece> typeList;

    public static IChess getInstance() {
        if(ChessModel2.instance==null){
            ChessModel2.instance = new ChessModel2();
        }
        return ChessModel2.instance;
    }


    @Override
    public void reinit() {
        ChessModel2.instance=null;// probably not the solution

        this.typeList=new ArrayList<>();

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (i==1){
                    Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_PAWN);
                    this.typeList.add(piece);
                }
                else if (i==6){
                    Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_PAWN);
                    this.typeList.add(piece);
                }

                else if (i==0){
                    if (j==0 || j==7) {
                        Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_ROOK);
                        this.typeList.add(piece);
                    }
                    else if (j==1 || j==6) {
                        Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_KNIGHT);
                        this.typeList.add(piece);
                    }
                    else if (j==2 || j==5) {
                        Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_BISHOP);
                        this.typeList.add(piece);
                    }
                    else if (j==3) {
                        Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_QUEEN);
                        this.typeList.add(piece);
                    }
                    else {
                        Piece piece = new Piece(ChessColor.CLR_BLACK, ChessType.TYP_KING);
                        this.typeList.add(piece);
                    }
                }

                else if (i==7){
                    if (j==0 || j==7) {
                        Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_ROOK);
                        this.typeList.add(piece);
                    }
                    else if (j==1 || j==6) {
                        Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_KNIGHT);
                        this.typeList.add(piece);
                    }
                    else if (j==2 || j==5) {
                        Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_BISHOP);
                        this.typeList.add(piece);
                    }
                    else if (j==3) {
                        Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_QUEEN);
                        this.typeList.add(piece);
                    }
                    else {
                        Piece piece = new Piece(ChessColor.CLR_WHITE, ChessType.TYP_KING);
                        this.typeList.add(piece);
                    }
                }

            }
        }
        System.out.println(typeList);
    }


    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        /*if (p.y==1 || p.y==6){
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
        }*/
        if (p.y<=1){
            return typeList.get(p.y*8+p.x).getType();
        }
        if (p.y>=6){
            return typeList.get((p.y-4)*8+p.x).getType();
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
        int remaining = 0;
        for (int i=0; i<typeList.size(); i++) {
            if (typeList.get(i).getColor()==color){
                remaining++;
            }
        }
        return remaining;
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
