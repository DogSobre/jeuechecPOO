package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int i=1; i<8; i++){
            list.add(new IChess.ChessPosition(p.x+i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y+i)) ;
            list.add(new IChess.ChessPosition(p.x+i, p.y-i)) ;
            list.add(new IChess.ChessPosition(p.x-i, p.y-i)) ;
        }

        /* // same result
        for (int row=-7; row<8; row++){
            for (int col=-7; col<8; col++){
                if (Math.abs(col)==Math.abs(row)){
                    list.add(new IChess.ChessPosition(p.x+col, p.y+row)) ;
                }
            }
        }*/

        return list;
    }
}
