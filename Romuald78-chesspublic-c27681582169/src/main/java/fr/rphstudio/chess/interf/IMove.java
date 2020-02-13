package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.ChessBoard;
import java.util.List;

public interface IMove {
    List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board);
}
