package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private Piece[][] typeTable;


    public ChessBoard(){
        createTable();
    }


    public Piece[][] createTable(){
        typeTable = new Piece[8][8];

        // pawn set-up
        for (int col=0; col<typeTable.length; col++){
            typeTable[1][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN, new Pawn());
            typeTable[6][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN, new Pawn());
        }

        for (int col=0; col<typeTable.length; col++){
            if (col==0 || col==7) {
                typeTable[0][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK, new Rook());
                typeTable[7][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK, new Rook());
            }
            else if (col==1 || col==6) {
                typeTable[0][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT, new Knight());
                typeTable[7][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT, new Knight());
            }
            else if (col==2 || col==5) {
                typeTable[0][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP, new Bishop());
                typeTable[7][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP, new Bishop());
            }
            else if (col==3) {
                typeTable[0][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
                typeTable[7][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
            }
            else {
                typeTable[0][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING, new King());
                typeTable[7][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING, new King());
            }
        }

        return typeTable;
    }


    public int isRemaining(IChess.ChessColor color){
        int remaining = 0;
        for (int i = 0; i< typeTable.length; i++) {
            for (int j = 0; j< typeTable.length; j++) {
                Piece piece = typeTable[i][j];
                if (piece != null) {
                    if (piece.getColor() == color) {
                        remaining++;
                    }
                }
            }
        }
        return remaining;
    }


    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p) {
        List<IChess.ChessPosition> list = typeTable[p.y][p.x].getMove().getPieceMoves(p, typeTable[p.y][p.x].getColor());
        // remove wrong move
        //System.out.println("try:   --------------- " +typeTable[p.y][p.x].getType() + " " + typeTable[p.y][p.x].getColor());
        /*for (int i=0; i<list.size(); i++){
            if (list.get(i).y<0 || list.get(i).y>7 || list.get(i).x<0 || list.get(i).x>7){
                list.remove(i);
                i--;
            }
            System.out.println(p.y +"-" + p.x  + ".  " + i + ":  " + list.get(i).y + "-" + list.get(i).x);
        }*/
        return list;
    }


    public void movePiece(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        typeTable[newP.y][newP.x] = typeTable[oldP.y][oldP.x];
        typeTable[oldP.y][oldP.x] = null;
    }
}
