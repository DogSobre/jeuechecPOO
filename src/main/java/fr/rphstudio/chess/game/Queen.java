package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        list.add(new IChess.ChessPosition(p.x, p.y+1)) ;
        return list;
    }
}
