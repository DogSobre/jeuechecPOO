package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.List;

/**
 * This interface is used to create different movement pattern , using the design pattern Strategy
 */
public interface IMove {

    /**
     * This method is used to get the Piece's possible moves, depending of it's position
     * and the pieces on the board (needed for Pawn)
     * @param p     ChessPosition : the position of the piece
     * @param board ChessBoard : the chess board, used to know the piece environment
     * @return      List : ChessPosition's list
     */
    List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board);


}
