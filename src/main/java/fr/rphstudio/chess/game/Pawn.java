package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {

    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, IChess.ChessColor color) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        if (color== IChess.ChessColor.CLR_WHITE && p.y==6){
            list.add(new IChess.ChessPosition(p.x, p.y-2)) ;
        }
        else if (color== IChess.ChessColor.CLR_BLACK && p.y==1){
            list.add(new IChess.ChessPosition(p.x, p.y+2)) ;
        }

        if (color== IChess.ChessColor.CLR_WHITE){
            list.add(new IChess.ChessPosition(p.x, p.y-1)) ;
        }
        else {
            list.add(new IChess.ChessPosition(p.x, p.y+1)) ;
        }
        //list.add(new IChess.ChessPosition(p.x+1, p.y+1));

        return list;
    }

}
