package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private Piece[][] typeTable;


    public ChessBoard(){
        createTable();
    }


    public Piece getPiece(int y, int x){
        Piece piece = this.typeTable[y][x];
        return piece;
    }


    public Piece[][] getTypeTable() {
        return typeTable;
    }

    public IChess.ChessType getPieceType(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getType();
        }
    }

    public IChess.ChessType getPieceType(int y, int x) throws EmptyCellException, OutOfBoardException {

        Piece piece = this.typeTable[y][x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getType();
        }
    }



    public IChess.ChessColor getPieceColor(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=typeTable.length || p.y>=typeTable.length || p.x<0 || p.y<0){
            throw new OutOfBoardException();
        }

        Piece piece = this.typeTable[p.y][p.x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getColor();
        }
    }


    public IChess.ChessColor getPieceColor(int y, int x) throws EmptyCellException, OutOfBoardException {

        Piece piece = this.typeTable[y][x];
        if (piece==null){
            throw new EmptyCellException();
        }
        else {
            return piece.getColor();
        }
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
        List<IChess.ChessPosition> list = typeTable[p.y][p.x].getMove().getPieceMoves(p, this);
        List<IChess.ChessPosition> listFinal = new ArrayList<>();

        for (int i=0; i<list.size(); i++) {
            if (list.get(i).y >= 0 && list.get(i).y < 8 && list.get(i).x >= 0 && list.get(i).x < 8) {

                //listFinal.add(list.get(i));

                //limit when is the same color than the piece selected
                if (typeTable[list.get(i).y][list.get(i).x]==null){
                    listFinal.add(list.get(i));
                }
                else if (typeTable[list.get(i).y][list.get(i).x].getColor()!=typeTable[p.y][p.x].getColor()) {
                    listFinal.add(list.get(i));
                }
            }
        }
        return listFinal;
    }


    public void movePiece(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        boolean isMoved = typeTable[oldP.y][oldP.x].isAlreadyMove();
        typeTable[newP.y][newP.x] = typeTable[oldP.y][oldP.x];
        typeTable[oldP.y][oldP.x] = null;
        showTable();

        typeTable[newP.y][newP.x].setAlreadyMove(true);


        roque(oldP, newP, isMoved);
        promote(newP.y, newP.x);
    }


    private void promote(int y, int x){
        if (typeTable[y][x].getType() == IChess.ChessType.TYP_PAWN && typeTable[y][x].getColor() == IChess.ChessColor.CLR_WHITE && y ==0){
            typeTable[y][x] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
        }
        if (typeTable[y][x].getType() == IChess.ChessType.TYP_PAWN && typeTable[y][x].getColor() == IChess.ChessColor.CLR_BLACK && y ==7){
            typeTable[y][x] =  new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
        }
    }



    private void roque(IChess.ChessPosition oldP, IChess.ChessPosition newP, boolean hasMoved){
        if (typeTable[newP.y][newP.x].getType() == IChess.ChessType.TYP_KING && !hasMoved){

            if (newP.x-oldP.x <0){
                typeTable[newP.y][newP.x+1]  = typeTable[newP.y][0];
                typeTable[newP.y][0]=null;
            }
            else if(newP.x-oldP.x >0){
                typeTable[newP.y][newP.x-1]  = typeTable[newP.y][7];
                typeTable[newP.y][7]=null;
            }
        }
    }


    public void showTable(){
        String result = "";
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (typeTable[i][j]!=null){
                    result+=typeTable[i][j].getType() + " . ";
                }
                else {
                    result+= "-------- . ";
                }
            }
            result+= "\n";
        }
        System.out.println(result);
    }
}
