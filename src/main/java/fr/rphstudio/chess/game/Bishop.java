package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {



    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();

        for (int i=1; i<8; i++){
            System.out.println(list);
            int posX = p.x-i;
            int posY = p.y-i;
            try{
                if (board.getPiece(posY, posX) == null){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                }
                else if (board.getPiece(posY, posX).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }


        for (int i=1; i<8; i++){
            System.out.println(list);
            int posX = p.x+i;
            int posY = p.y+i;
            try{
                if (board.getPiece(posY, posX) == null){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                }
                else if (board.getPiece(posY, posX).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }


        for (int i=1; i<8; i++){
            System.out.println(list);
            int posX = p.x+i;
            int posY = p.y-i;
            try{
                if (board.getPiece(posY, posX) == null){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                }
                else if (board.getPiece(posY, posX).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }

        for (int i=1; i<8; i++){
            System.out.println(list);
            int posX = p.x-i;
            int posY = p.y+i;
            try{
                if (board.getPiece(posY, posX) == null){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                }
                else if (board.getPiece(posY, posX).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(posX, posY)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }


        return list;    }
}
