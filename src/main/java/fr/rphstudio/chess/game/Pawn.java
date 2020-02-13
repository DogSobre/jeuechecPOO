package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to have a pattern of all the possible movement for a Pawn
 */
public class Pawn implements IMove {


    /**
     * This method is used to get the Piece's possible moves, depending of it's position
     * and the pieces on the board (needed for Pawn)
     * @param p     ChessPosition : the position of the piece
     * @param board ChessBoard : the chess board, used to know the piece environment
     * @return      List : ChessPosition's list
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        //need to test if the piece isn't null

        Piece piece = board.getPiece(p.y, p.x);
        if (piece.getColor()== IChess.ChessColor.CLR_WHITE && !piece.isAlreadyMove()){
            calculateFirst(p, board, list, -1);
        }
        else if (piece.getColor()== IChess.ChessColor.CLR_BLACK && !piece.isAlreadyMove()){
            calculateFirst(p, board, list, +1);
        }


        if (piece.getColor()== IChess.ChessColor.CLR_WHITE){
            calculateComonMove(p, board, list, piece, p.y-1);
            calculatePriseEnPassant(p, board, list, piece, p.y-1, 3);
        }
        else {
            calculateComonMove(p, board, list, piece, p.y+1);
            calculatePriseEnPassant(p, board, list, piece, p.y+1, 4);
        }





        return list;
    }


    private void calculatePriseEnPassant(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, Piece piece, int rowMove, int rowNeed){
        if (board.getListPawnMove().size()>0) {
            if (board.getListPawnMove().get(board.getListPawnMove().size() - 1)!=null ) {
                try {
                    int y = board.getListPawnMove().get(board.getListPawnMove().size()-1).y;
                    int x = board.getListPawnMove().get(board.getListPawnMove().size()-1).x;

                    if (piece.getColor() != board.getPiece(y, x).getColor() && rowNeed==p.y) {
                        if (x==p.x-1){
                            list.add(new IChess.ChessPosition(x, rowMove));
                        }
                        if (x==p.x+1){
                            list.add(new IChess.ChessPosition(x, rowMove));
                        }
                        //list.add(new IChess.ChessPosition(x, rowMove));
                    }

                } catch (Exception e) {
                }
            }
        }
    }


    private void calculateFirst(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signeY){
        try{
            if (board.getPiece(p.y+1*signeY, p.x)==null && board.getPiece(p.y+2*signeY, p.x)==null){
                list.add(new IChess.ChessPosition(p.x, p.y+2*signeY)) ;

            }
        }catch (Exception e){
        }
    }


    private void calculateComonMove(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, Piece piece, int rowMove){
        try{
            if (piece.getColor() != board.getPiece(rowMove, p.x+1).getColor()){
                list.add(new IChess.ChessPosition(p.x+1, rowMove)) ;
            }
        }catch (Exception e){
        }
        try{
            if (piece.getColor() != board.getPiece(rowMove, p.x-1).getColor()){
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

}
