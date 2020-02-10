package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color) {
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
        return list;
    }
}
