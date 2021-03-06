package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to have a pattern of all the possible movement for a Bishop
 */
public class Bishop implements IMove {


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

        calculateDiagonal(p, board, list, -1, -1 );
        calculateDiagonal(p, board, list, -1, +1);
        calculateDiagonal(p, board, list, +1, +1);
        calculateDiagonal(p, board, list, +1, -1);

        return list;    }


    /**
     * This method is used to add possible movement on a Diagonal, the ints are used
     * to know which diagonal we will add to the possible move
     * @param p         ChessPosition : the position of the piece
     * @param board     ChessBoard : the chess board, used to know the environment of the piece
     * @param list      List : possible move's List , containing ChessPosition
     * @param signX     int : used for the column, could be -1, +1 . if 1, we go one column right
     * @param signY     int : used for the row, could be -1, +1.     if 1, we go one row down
     */
    private void calculateDiagonal(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signX, int signY) {
        for (int cell=1; cell<IChess.BOARD_WIDTH; cell++){
            int posX = p.x+cell*signX;
            int posY = p.y+cell*signY;
            try{
                if (board.getPiece(posY, posX) == null){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                }
                else if (board.getPiece(posY, posX).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }
    }
}
