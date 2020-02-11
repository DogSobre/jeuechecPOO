package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        //need to test if the piece isn't null

        if (board.getPiece(p.y, p.x).getColor()== IChess.ChessColor.CLR_WHITE && p.y==6){
            try{
                if (board.getPiece(p.y-1, p.x)==null){
                    list.add(new IChess.ChessPosition(p.x, p.y-2)) ;
                }
            }catch (Exception e){
            }
            //list.add(new IChess.ChessPosition(p.x, p.y-2)) ;
        }
        else if (board.getPiece(p.y, p.x).getColor()== IChess.ChessColor.CLR_BLACK && p.y==1){
            try{
                if (board.getPiece(p.y+1, p.x)==null){
                    list.add(new IChess.ChessPosition(p.x, p.y+2)) ;
                }
            }catch (Exception e){
            }
            //list.add(new IChess.ChessPosition(p.x, p.y+2)) ;
        }

        if (board.getPiece(p.y, p.x).getColor()== IChess.ChessColor.CLR_WHITE){
            int rowMove = p.y-1;
            try{
                if (board.getPiece(p.y, p.x).getColor() != board.getPiece(rowMove, p.x+1).getColor()){
                    list.add(new IChess.ChessPosition(p.x+1, rowMove)) ;
                }
            }catch (Exception e){
            }

            try{
                if (board.getPiece(p.y, p.x).getColor() != board.getPiece(rowMove, p.x-1).getColor()){
                    list.add(new IChess.ChessPosition(p.x-1, rowMove)) ;
                }
            }catch (Exception e){
            }

            try{
                if (board.getPiece(rowMove, p.x)==null){
                    list.add(new IChess.ChessPosition(p.x, rowMove)) ;
                }
            }catch (Exception e){
            }
        }

        else {
            int rowMove = p.y+1;

            try{
                if (board.getPiece(p.y, p.x).getColor() != board.getPiece(rowMove, p.x+1).getColor()){
                    list.add(new IChess.ChessPosition(p.x+1, rowMove)) ;
                }
            }catch (Exception e){
            }

            try{
                if (board.getPiece(p.y, p.x).getColor() != board.getPiece(rowMove, p.x-1).getColor()){
                    list.add(new IChess.ChessPosition(p.x-1, rowMove)) ;
                }
            }catch (Exception e){
            }
            try{
                if (board.getPiece(rowMove, p.x)==null){
                    list.add(new IChess.ChessPosition(p.x, rowMove)) ;
                }
            }catch (Exception e){
            }

        }


        //list.add(new IChess.ChessPosition(p.x+1, p.y+1));

        return list;    }
}
