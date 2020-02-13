package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to have a pattern of all the possible movement for a Knight
 */
public class Knight implements IMove {


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

        list.add(new IChess.ChessPosition(p.x+1, p.y-2)) ;
        list.add(new IChess.ChessPosition(p.x-1, p.y-2)) ;

        list.add(new IChess.ChessPosition(p.x+1, p.y+2)) ;
        list.add(new IChess.ChessPosition(p.x-1, p.y+2)) ;

        list.add(new IChess.ChessPosition(p.x+2, p.y-1)) ;
        list.add(new IChess.ChessPosition(p.x-2, p.y-1)) ;

        list.add(new IChess.ChessPosition(p.x+2, p.y+1)) ;
        list.add(new IChess.ChessPosition(p.x-2, p.y+1)) ;

        for (int i =0; i<list.size(); i++){
            //System.out.println("Knight pos: " +list.get(i).y + "." + list.get(i).x);
        }
        return list;    }
}
