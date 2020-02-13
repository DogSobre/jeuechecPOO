package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {


    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        Piece piece = board.getPiece(p.y, p.x);
        if (piece.getColor()== IChess.ChessColor.CLR_WHITE && !piece.isAlreadyMove()){
            calculateFirst(p, board, list, -1);
        }
        else if (piece.getColor()== IChess.ChessColor.CLR_BLACK && !piece.isAlreadyMove()){
        calculateFirst(p, board, list, +1);
    }
        if (piece.getColor()== IChess.ChessColor.CLR_WHITE){
            calculateComonMove(p, board, list, piece, p.y-1);
            //list.add(new IChess.ChessPosition(p.x, p.y-2)) ;
        }
        else {
            calculateComonMove(p, board, list, piece, p.y+1);

        }
        return list;
    }

    private void calculateFirst(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signeY){
        try{
            if (board.getPiece(p.y+1*signeY, p.x)==null && board.getPiece(p.y+2*signeY, p.x)==null){
                list.add(new IChess.ChessPosition(p.x, p.y+2*signeY)) ;

            }
        }
        catch (Exception e){
        }
    }
    private void calculateComonMove(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, Piece piece, int rowMove){
        try{
            if (piece.getColor() != board.getPiece(rowMove, p.x+1).getColor()){
                list.add(new IChess.ChessPosition(p.x+1, rowMove)) ;
            }
        }
        catch (Exception e){
        }
        try{
            if (piece.getColor() != board.getPiece(rowMove, p.x-1).getColor()){
                list.add(new IChess.ChessPosition(p.x-1, rowMove)) ;
            }
        }
        catch (Exception e){
        }
        try{
            if (board.getPiece(rowMove, p.x)==null){
                list.add(new IChess.ChessPosition(p.x, rowMove)) ;
            }
        }
        catch (Exception e){
        }
    }
}