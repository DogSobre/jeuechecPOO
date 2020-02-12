package fr.rphstudio.chess.interf;

import java.util.List;

public interface IMove {

    List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color);
}
