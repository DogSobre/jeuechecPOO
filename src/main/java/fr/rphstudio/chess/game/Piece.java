package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;


/**
 * This class is used to have pieces defined by their color, type and movement pattern
 */
public class Piece {
    private IChess.ChessColor color ;
    private IChess.ChessType type;
    private IMove move;
    private boolean alreadyMove = false;


    /**
     * Piece constructor, used to create a piece that we will had to the chessboard
     * @param color ChessColor : piece's color
     * @param type  ChessTyp : piece's type
     * @param move  IMove : piece's pattern movement, defined how it will move on the chessboard
     */
    public Piece(IChess.ChessColor color, IChess.ChessType type, IMove move){
        this.color=color;
        this.type = type;
        this.move = move;
    }


    /**
     * This method is used to get the piece color
     * @return  ChessColor : piece's color
     */
    public IChess.ChessColor getColor() {
        return color;
    }


    /**
     * This method is used to get the piece type
     * @return  ChessType : piece's type
     */
    public IChess.ChessType getType() {
        return type;
    }


    /**
     * This method is used to get the piece move pattern
     * @return  ChessColor : piece's pattern
     */
    public IMove getMove() {
        return move;
    }


    /**
     * This method is used to know if the piece already move
     * @return  boolean : true if the piece has already move
     */
    public boolean isAlreadyMove() {
        return alreadyMove;
    }


    /**
     * This method is used to set that the piece already moved
     * @param alreadyMove   boolean : true if the piece already move
     */
    public void setAlreadyMove(boolean alreadyMove) {
        this.alreadyMove = alreadyMove;
    }
}
