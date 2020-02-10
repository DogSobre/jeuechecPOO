package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Piece {
    private IChess.ChessColor color ;
    private IChess.ChessType type;
    private IMove move;


    public Piece(IChess.ChessColor color, IChess.ChessType type, IMove move){
        this.color=color;
        this.type = type;
        this.move = move;
    }

    public IChess.ChessColor getColor() {
        return color;
    }

    public IChess.ChessType getType() {
        return type;
    }

    public IMove getMove() {
        return move;
    }
}
