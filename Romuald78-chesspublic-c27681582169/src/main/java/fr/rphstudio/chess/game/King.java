package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to have a pattern of all the possible movement for a King,
 * also contain the castling moves
 */
public class King implements fr.rphstudio.chess.game.IMove {


    /**
     * This method is used to get the Piece's possible moves, depending of it's position
     * and the pieces in the board (needed for Pawn)
     * @param p     ChessPosition : the position of the piece
     * @param board ChessBoard : the chess board, useful to know the piece environment
     * @return      List : ChessPosition's list
     */
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




        //great roque, for the left Rook
        try{
            if (ChessModel2.getKingStatus(board.getPieceColor(p)) == IChess.ChessKingState.KING_SAFE && !board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x-4).isAlreadyMove() && board.getPiece(p.y, p.x-4).getType()== IChess.ChessType.TYP_ROOK){
                if (board.getPiece(p.y, p.x-1)==null && board.moveTest(p, new IChess.ChessPosition(p.x-1, p.y)) && board.getPiece(p.y, p.x-2)==null && board.getPiece(p.y, p.x-3)==null){
                    list.add(new IChess.ChessPosition(p.x - 2, p.y));
                }
            }
        }catch (Exception e ){
        }
        try{
            if (ChessModel2.getKingStatus(board.getPieceColor(p)) == IChess.ChessKingState.KING_SAFE && !board.getPiece(p.y, p.x).isAlreadyMove()  && !board.getPiece(p.y, p.x+3).isAlreadyMove() && board.getPiece(p.y, p.x+3).getType()== IChess.ChessType.TYP_ROOK){
                if (board.getPiece(p.y, p.x+1)==null && board.getPiece(p.y, p.x+2)==null && board.moveTest(p, new IChess.ChessPosition(p.x+1, p.y)) ){
                    list.add(new IChess.ChessPosition(p.x+2, p.y)) ;
                }
            }
        }catch (Exception e ){
        }


        return list;
    }
}
