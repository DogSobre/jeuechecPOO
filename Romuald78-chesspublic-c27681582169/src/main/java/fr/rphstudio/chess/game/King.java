package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

public class King implements IMove {


    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int row=-1; row<2; row++){
            for (int col=-1; col<2; col++){
                if (col!=0 || row!=0){
                    list.add(new IChess.ChessPosition(p.x+col, p.y+row)) ;
                }
            }
        }
        try{
            if (!board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x-4).isAlreadyMove()){
            /*if (IChess.ChessKingState.KING_SAFE==){

            }*/
                if (board.getPiece(p.y, p.x-1)==null && board.getPiece(p.y, p.x-2)==null && board.getPiece(p.y, p.x-3)==null){
                    list.add(new IChess.ChessPosition(p.x-2, p.y)) ;
                }

            }
        }catch (Exception e ){

        }

        try{
            if (!board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x+3).isAlreadyMove()){
            /*if (IChess.ChessKingState.KING_SAFE==){

            }*/
                if (board.getPiece(p.y, p.x+1)==null && board.getPiece(p.y, p.x+2)==null){
                    list.add(new IChess.ChessPosition(p.x+2, p.y)) ;
                }

            }
        }catch (Exception e ){

        }
        return list;
    }
}
