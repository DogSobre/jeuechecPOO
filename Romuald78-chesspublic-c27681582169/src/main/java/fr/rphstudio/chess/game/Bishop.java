package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {


    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int i=1; i<8; i++){
            list.add(new IChess.ChessPosition(p.x+i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x+i, p.y-i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y-i)) ;
        }

        return list;
    }
}
