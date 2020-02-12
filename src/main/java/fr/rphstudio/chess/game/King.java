package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove{


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int row=-1; row<2; row++){
            for (int col=-1; col<2; col++){
                if (col!=0 || row!=0){
                    list.add(new IChess.ChessPosition(p.x+col, p.y+row)) ;
                }
            }
        }


        //great roque
        try{
            if (!board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x-4).isAlreadyMove() &&board.getPiece(p.y, p.x-4).getType()== IChess.ChessType.TYP_ROOK){
            /*if (IChess.ChessKingState.KING_SAFE==){

            }*/
                if (board.getPiece(p.y, p.x-1)==null && board.getPiece(p.y, p.x-2)==null && board.getPiece(p.y, p.x-3)==null){
                    list.add(new IChess.ChessPosition(p.x-2, p.y)) ;
                }

            }
        }catch (Exception e ){

        }

        try{
            if (!board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x+3).isAlreadyMove() && board.getPiece(p.y, p.x+3).getType()== IChess.ChessType.TYP_ROOK){
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

    //roi n'apas bougé, tour non plus
    //roi pas deja en echec
    //roi jamais en echec sur les cases traversé
    //vide entre tour et roi

    // le roi avance 2 case vers tour, la tour une case plus au centre
}
