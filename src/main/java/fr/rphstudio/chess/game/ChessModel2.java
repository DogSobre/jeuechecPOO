package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used as an interface between the GUI and the Chessboard managing
 */
public class ChessModel2 implements IChess {
    private static ChessModel2 instance ;
    private  Piece[][] typeTable;
    private ChessBoard board = new ChessBoard();


    /**
     * This method is used as a design singleton
     * @return
     */
    public static IChess getInstance() {
        if(ChessModel2.instance==null){
            ChessModel2.instance = new ChessModel2();
        }
        return ChessModel2.instance;
    }


    /**
     * This method is used to reinitialise the board, we reset the piece position
     */
    @Override
    public void reinit() {
        typeTable = board.createTable();
    }


    /**
     * This method is used to get the piece's type at a defined position
     * @param p x/y position on the board where we want to get the piece type.
     * @return  ChessType : the ChessType of the piece
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return  board.getPieceType(p);
        /*if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getType();
        }*/
    }


    /**
     * This method is used to get the piece's color at a defined position
     * @param p x/y position on the board where we want to get the piece color.
     * @return  ChessColor : the color of the piece
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        /*if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getColor();
        }*/
        return board.getPieceColor(p);
    }


    /**
     * This method is used to get the remaining piece's number
     * @param color the requested color of the pieces to count.
     * @return  int : the remaining piece's number
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return board.isRemaining(color);
    }


    /**
     * This method is used to get the piece's possible move for a piece at a defined position
     * @param p requested piece position.
     * @return List : list of the piece's possible move
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        try {
            return board.getPieceMoves(p);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }


    /**
     * This method is used to move a piece at a defined position to a new position
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        board.movePiece(p0, p1);
    }


    /**
     * This method is used to get the King state depending of the given color
     * @param color the requested king color.
     * @return ChessKingState :
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }


    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        List<ChessType> list = new ArrayList<>();
        return list;
    }


    @Override
    public boolean undoLastMove() {
        return false;
    }


    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
