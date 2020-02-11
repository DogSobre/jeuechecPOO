package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Rook implements IMove {


    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        System.out.println("try");
        /*for (int i=1; i<8; i++){

            if (board.getPiece(p.y+i, p.x) == null){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y+i, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("n'est pas de ma couleur");
            }
            else if (board.getPiece(p.y+i, p.x).getColor()==board.getPiece(p.y, p.x).getColor()){
                System.out.println("est de ma couleur");
                break;
            }

        }*/

        System.out.println("qsdfg");

        for (int i=1; i<8; i++){
            System.out.println(list);
            if (board.getPiece(p.y-i, p.x) == null){
                list.add(new IChess.ChessPosition(p.x, p.y-i)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y-i, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x, p.y-i)) ;
                System.out.println("est de ma couleur");
                break;
            }
            else {
                break;
            }
        }

        System.out.println("azert");

        for (int i=1; i<8; i++){
            System.out.println(list);
            if (board.getPiece(p.y+i, p.x) == null){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y+i, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("est de ma couleur");
                break;
            }
            else {
                break;
            }
        }

        /*
        for (int i=1; i<8; i++){
            if (board.getPiece(p.y+i, p.x) == null){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y+i, p.x).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x, p.y+i)) ;
                System.out.println("est de ma couleur");
                break;
            }
            else {
                break;
            }
        }

        for (int i=1; i<8; i++){
            if (board.getPiece(p.y, p.x-i) == null){
                list.add(new IChess.ChessPosition(p.x-i, p.y)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y, p.x-i).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x-i, p.y)) ;
                System.out.println("est de ma couleur");
                break;
            }
            else {
                break;
            }
        }

        for (int i=1; i<8; i++){
            if (board.getPiece(p.y, p.x+i) == null){
                list.add(new IChess.ChessPosition(p.x+i, p.y)) ;
                System.out.println("est null");
            }
            else if (board.getPiece(p.y, p.x+i).getColor()!=board.getPiece(p.y, p.x).getColor()){
                list.add(new IChess.ChessPosition(p.x+i, p.y)) ;
                System.out.println("est de ma couleur");
                break;
            }
            else {
                break;
            }
        }*/


        return list;    }
}
