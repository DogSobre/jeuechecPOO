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
            calculateFirstMove(p, board, list, -1);
        }
        else if (piece.getColor()== IChess.ChessColor.CLR_BLACK && !piece.isAlreadyMove()){
            calculateFirstMove(p, board, list, +1);
        }


        if (piece.getColor()== IChess.ChessColor.CLR_WHITE){
            calculateCommonMove(p, board, list, p.y-1);
            calculatePriseEnPassant(p, board, list, p.y-1, 3);
        }
        else {
            calculateCommonMove(p, board, list, p.y+1);
            calculatePriseEnPassant(p, board, list, p.y+1, 4);
        }

        return list;
    }


    /**
     * This method is used to allow the 'prise en passant' move for the pawn
     * @param p         ChessPosition : piece's position
     * @param board     ChessBoard : chessBoard , needed to know the piece environment
     * @param list      List : the allowed piece's move
     * @param rowMove   int : row movement. For a black piece, it will go one row down (p.y+1)
     * @param rowNeed   int : the unique row where the pawn could do this move
     */
    private void calculatePriseEnPassant(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int rowMove, int rowNeed){
        if (board.getListPawnMove().size()>0) {
            if (board.getListPawnMove().get(board.getListPawnMove().size() - 1)!=null ) {
                try {
                    int y = board.getListPawnMove().get(board.getListPawnMove().size()-1).y;
                    int x = board.getListPawnMove().get(board.getListPawnMove().size()-1).x;

                    if (board.getPiece(p.y, p.x).getColor() != board.getPiece(y, x).getColor() && rowNeed==p.y) {
                        if (x==p.x-1){
                            list.add(new IChess.ChessPosition(x, rowMove));
                        }
                        if (x==p.x+1){
                            list.add(new IChess.ChessPosition(x, rowMove));
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }


    /**
     * This method is used to allow the first move for a pawn (2 row)
     * @param p         ChessPosition : piece's position
     * @param board     ChessBoard : chessBoard , needed to know the piece environment
     * @param list      List : the allowed piece's move
     * @param signeY    int : the sign of row movement, necessary to check if the first cell is free
     */
    private void calculateFirstMove(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signeY){
        try{
            if (board.getPiece(p.y+1*signeY, p.x)==null && board.getPiece(p.y+2*signeY, p.x)==null){
                list.add(new IChess.ChessPosition(p.x, p.y+2*signeY)) ;

            }
        }catch (Exception e){
        }
    }


    /**
     * This method is used to allow the common pawn move, one row further
     * and in diagonal if there is an enemy
     * @param p         ChessPosition : piece's position
     * @param board     ChessBoard : chessBoard , needed to know the piece environment
     * @param list      List : the allowed piece's move
     * @param rowMove   int : row movement. For a black piece, it will go one row down (p.y+1)
     */
    private void calculateCommonMove(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int rowMove){
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

}
