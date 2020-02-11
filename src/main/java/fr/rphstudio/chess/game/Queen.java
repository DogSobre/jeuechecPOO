package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int i=1; i<8; i++){
            list.add(new IChess.ChessPosition(p.x, p.y + i));
            list.add(new IChess.ChessPosition(p.x, p.y - i));
            list.add(new IChess.ChessPosition(p.x + i, p.y));
            list.add(new IChess.ChessPosition(p.x- i, p.y));
        }

        for (int i=1; i<8; i++){
            list.add(new IChess.ChessPosition(p.x+i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x+i, p.y-i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y-i)) ;
        }
        return list;
    }
}