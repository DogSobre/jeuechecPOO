package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used as an interface between the GUI,  the Chessboard managing, and the chronometer
 */
public class ChessModel2 implements IChess {
    private static ChessModel2 instance ;
    private ChessBoard board = new ChessBoard();
    private static IChess.ChessKingState whiteKingStatus;
    private static IChess.ChessKingState blackKingStatus;



    /**
     * This method is used as a design pattern singleton
     * @return  IChess : an unique instance of an IChess
     */
    public static IChess getInstance() {
        if(ChessModel2.instance==null){
            ChessModel2.instance = new ChessModel2();
        }
        return ChessModel2.instance;
    }


    /**
     * This method is used to get at each time the current status king without checking all the table
     * @param color ChessColor : king's color from whom we want to know the status
     * @return      ChessKingState : the King status
     */
    public static ChessKingState getKingStatus(ChessColor color) {
        if (color==ChessColor.CLR_WHITE) {
            return whiteKingStatus;
        }
        else{
            return blackKingStatus;
        }
    }


    /**
     * This method is used to reinitialise the game, we reset the piece position and chronometer
     */
    @Override
    public void reinit() {
        board.reinitialise();
        Chronometer.getInstance().reset();
    }


    /**
     * This method is used to get the piece's type at a defined position
     * @param p x/y position on the board where we want to get the piece type.
     * @return  ChessType : the ChessType of the piece
     * @throws EmptyCellException   throw when there is no piece at the ChessPosition
     * @throws OutOfBoardException  throw when the given ChessPosition is out of the typeTable
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return  board.getPieceType(p);
    }


    /**
     * This method is used to get the piece's color at a defined position
     * @param p x/y position on the board where we want to get the piece color.
     * @return  ChessColor : the color of the piece
     * @throws EmptyCellException   throw when there is no piece at the ChessPosition
     * @throws OutOfBoardException  throw when the given ChessPosition is out of the typeTable
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return board.getPieceColor(p);
    }


    /**
     * This method is used to get the remaining piece's number
     * @param color the requested color of the pieces to count.
     * @return  int : the remaining piece's number
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return board.getNumberOfRemaining(color);
    }


    /**
     * This method is used to get the piece's possible move for a piece at a defined position
     * @param p requested piece position.
     * @return List : list of the piece's possible move
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        try {
            return board.testIfWillThreaten(p);
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
     * @return ChessKingState : the King status
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        if (color== IChess.ChessColor.CLR_WHITE){
            whiteKingStatus = board.getKingState(color);
        }
        if (color== IChess.ChessColor.CLR_BLACK){
            blackKingStatus = board.getKingState(color);
        }
        return board.getKingState(color);
    }


    /**
     * This method is used to get the removed Pieces depending of the player's color
     * @param color color of the removed pieces
     * @return List : piece's type list
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return RemovedPiece.getInstance().getRemovedPieces(color);
    }


    /**
     * This method is used to undo the last move
     * @return  boolean : true if we allow the undo action
     */
    @Override
    public boolean undoLastMove() {
        return board.undoMove();

    }


    /**
     * This method is used to get player duration, that will be display for each player
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return          long : a long value used to display the duration of the player
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        Chronometer chronometer = Chronometer.getInstance();
        chronometer.updateChronometer(color, isPlaying);
        return chronometer.getPlayerDuration(color, isPlaying);
    }
}
