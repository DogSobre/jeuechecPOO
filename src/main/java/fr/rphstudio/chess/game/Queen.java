package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int i=1; i<8; i++){
            int pos = p.y-i;
            try{
                if (board.getPiece(pos, p.x) == null){
                    list.add(new IChess.ChessPosition(p.x, pos)) ;
                }
                else if (board.getPiece(pos, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(p.x, pos)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }


        for (int i=1; i<8; i++){
            int pos = p.y+i;
            try{
                if (board.getPiece(pos, p.x) == null){
                    list.add(new IChess.ChessPosition(p.x, pos)) ;
                }
                else if (board.getPiece(pos, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(p.x, pos)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }


        for (int i=1; i<8; i++){
            int pos = p.x+i;
            try{
                if (board.getPiece(p.y, pos) == null){
                    list.add(new IChess.ChessPosition(pos, p.y)) ;
                }
                else if (board.getPiece(p.y, pos).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(pos, p.y)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }

        for (int i=1; i<8; i++){
            int pos = p.x-i;
            try{
                if (board.getPiece(p.y, pos) == null){
                    list.add(new IChess.ChessPosition(pos, p.y)) ;
                }
                else if (board.getPiece(p.y, pos).getColor()!=board.getPiece(p.y, p.x).getColor()){
                    list.add(new IChess.ChessPosition(pos, p.y)) ;
                    break;
                }
                else {
                    break;
                }
            }catch (Exception e){
            }
        }




        // diagonale moves
        for (int i=1; i<8; i++){
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
        return list;
    }
}
