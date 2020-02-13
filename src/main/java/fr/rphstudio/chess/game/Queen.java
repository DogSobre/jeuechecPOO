package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to have a pattern of all the possible movement for a Queen
 */
public class Queen implements IMove {


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

        calculateLine(p, board, list, +1, 0 );
        calculateLine(p, board, list, -1, 0);
        calculateLine(p, board, list, 0, +1 );
        calculateLine(p, board, list, 0, -1 );


        calculateDiagonal(p, board, list, -1, -1 );
        calculateDiagonal(p, board, list, -1, +1);
        calculateDiagonal(p, board, list, +1, +1);
        calculateDiagonal(p, board, list, +1, -1);

        return list;
    }


    /**
     * This method is used to add possible movement on a Diagonal, the ints are used
     * to know which diagonal we will add to the possible move
     * @param p         ChessPosition : the position of the piece
     * @param board     ChessBoard : the chess board, used to know the environment of the piece
     * @param list      List : possible move's List , containing ChessPosition
     * @param signX    int : used for the column, could be -1, +1 . if 1, we adding a column
     * @param signY    int : used for the row, could be -1, +1.     if 0, we adding a row
     */
    private void calculateDiagonal(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signX, int signY) {
        for (int i=1; i<8; i++){
            int posX = p.x+i*signX;
            int posY = p.y+i*signY;
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


    /**
     * This method is used to add possible movement on a Row or a Column, the ints are used
     * to select the column or row we will add to the possible move
     * @param p         ChessPosition : the position of the piece
     * @param board     ChessBoard : the chess board, used to know the environment of the piece
     * @param list      List : possible move's List , containing ChessPosition
     * @param signX    int : used for the column, could be -1, 0, +1 . if 0, we adding a column
     * @param signY    int : used for the row, could be -1, 0, +1.     if 0, we adding a row
     */
    private void calculateLine(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signX, int signY) {
        for (int i=1; i<8; i++) {

            int posX = p.x+i*signX;
            int posY = p.y+i*signY;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }
    }
}
