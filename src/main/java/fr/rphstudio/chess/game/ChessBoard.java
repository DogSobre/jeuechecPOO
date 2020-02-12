package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manage all about the ChessBoard
 */
public class ChessBoard {
    private Piece[][] typeTable;
    private List<IChess.ChessType> listTypeBlack;
    private List<IChess.ChessType> listTypeWhite;
    private List<Piece[][]> listOfTable;
    private List<Boolean> listOfFirstMove;


    /**
     * This is the ChessBoard constructor
     */
    public ChessBoard(){
        reinitialise();
    }


    /**
     * This method is used to get the piece at a given position with two int (y and x)
     * @param y     int : corresponding to the row we choose
     * @param x     int : corresponding to the column we choose
     * @return      Piece : return the Piece at the given position
     */
    public Piece getPiece(int y, int x){
        Piece piece = this.typeTable[y][x];
        return piece;
    }


    /**
     * This method is used to get the Chessboard table of Piece
     * @return      Piece [][] : table containing all pieces
     */
    public Piece[][] getTypeTable() {
        return typeTable;
    }

    /**
     * This method is used to get a Piece type thanks to a given position
     * @param p ChessPosition : position of the piece we want
     * @return  ChessType : return the Piece's type
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
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


    /**
     * This method is used to get a Piece color thanks to a given position
     * @param p ChessPosition : position of the piece we want
     * @return  ChessColor : return the Piece's color
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
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


    /**
     * This method is used to get the KingState, depending of all the enemies possible movements
     * @param color ChessColor : the color of the king we want to find out
     * @return      ChessKingState : the King state
     */
    public IChess.ChessKingState getKingState(IChess.ChessColor color) {
        List<IChess.ChessPosition> finalList= new ArrayList<>();
        IChess.ChessPosition p = new IChess.ChessPosition(-10, -10);
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                try {
                    if (getPiece(i, j).getColor() != color) {
                        List<IChess.ChessPosition> list = getPieceMoves(new IChess.ChessPosition(j, i));
                        finalList.addAll(list);
                    }

                    if (getPiece(i, j).getColor() == color && getPiece(i, j).getType() == IChess.ChessType.TYP_KING) {
                        p = new IChess.ChessPosition(j, i);
                    }
                }catch (Exception e ){
                }
            }
        }


        for (int a = 0; a<finalList.size(); a++){
            if(finalList.get(a).equals(p)){
                return IChess.ChessKingState.KING_THREATEN;
            }
        }

        return IChess.ChessKingState.KING_SAFE;
    }


    public List<IChess.ChessPosition> getEnemiesMoves(IChess.ChessColor color){
        List<IChess.ChessPosition> finalList= new ArrayList<>();
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                try {
                    if (getPiece(i, j).getColor() != color) {
                        List<IChess.ChessPosition> list = getPieceMoves(new IChess.ChessPosition(j, i));
                        finalList.addAll(list);
                    }
                }catch (Exception e ){
                }
            }
        }
        return finalList;
    }


    /**
     * This method is used to reinitialise the Game, the Piece table and list of dead pieces
     * @return  Piece[][] : the table piece we have generated
     */
    public Piece[][] reinitialise(){
        typeTable = new Piece[8][8];
        listTypeBlack = new ArrayList<>();
        listTypeWhite = new ArrayList<>();
        listOfTable = new ArrayList<>();
        listOfFirstMove = new ArrayList<>();
        // will need to reinit all the move for this game the move

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


    /**
     * This method is used to calculate the number of remaining piece depending of the color given in parameter
     * @param color ChessColor : the color we choose, White or Black
     * @return      int : the number of remaining piece
     */
    public int numberOfRemaining(IChess.ChessColor color){
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


    /**
     * This method is used to get the piecesMoves depending of the position of the piece we want to find out
     * @param p ChessPosition : the position of the piece
     * @return  List : possible move's list
     */
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p) {
        List<IChess.ChessPosition> list = typeTable[p.y][p.x].getMove().getPieceMoves(p, this);
        List<IChess.ChessPosition> listFinal = new ArrayList<>();

        // the pattern move of pieces have to include all ways they could go, and here we limit
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).y >= 0 && list.get(i).y < 8 && list.get(i).x >= 0 && list.get(i).x < 8) {

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


    /**
     * This method is used to give the possible moves for a piece after checking if will
     * threaten the king, then will limit the possible moves
     * @param p ChessPosition : the position of the piece
     * @return  List : list of possible moves
     */
    public List<IChess.ChessPosition> testIfWillThreaten(IChess.ChessPosition p){
        List<IChess.ChessPosition> list = getPieceMoves(p);
        List<IChess.ChessPosition> listFinal = new ArrayList<>();
        IChess.ChessPosition p1;

        for (int i=0; i<list.size(); i++){
            try {
                p1 = list.get(i);
                //moveForKing(p, p1, listFinal);
                Piece save = typeTable[p1.y][p1.x];
                typeTable[p1.y][p1.x] = typeTable[p.y][p.x];
                typeTable[p.y][p.x] = null;
                if (getKingState(typeTable[p1.y][p1.x].getColor())== IChess.ChessKingState.KING_SAFE){
                    listFinal.add(p1);
                }
                typeTable[p.y][p.x] = typeTable[p1.y][p1.x];
                typeTable[p1.y][p1.x] = save;

            }catch (Exception e){

            }
        }
        return listFinal;
    }


    /**
     * This method is used to do the movement choose by the player, depending of the position of
     * the piece we choose and the position this pieces will go
     * @param oldP  ChessPosition : the old piece's position
     * @param newP  ChessPosition : the new piece's position
     */
    public void movePiece(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        listOfTable.add(writeTableSave());
        boolean isMoved = typeTable[oldP.y][oldP.x].isAlreadyMove();

        if (typeTable[newP.y][newP.x] == null){
        }else {
            if (typeTable[newP.y][newP.x].getColor()== IChess.ChessColor.CLR_WHITE){
                listTypeWhite.add(typeTable[newP.y][newP.x].getType());
            }
            else {
                listTypeBlack.add(typeTable[newP.y][newP.x].getType());
            }
        }

        typeTable[newP.y][newP.x] = typeTable[oldP.y][oldP.x];
        typeTable[oldP.y][oldP.x] = null;
        showTable();

        listOfFirstMove.add(typeTable[newP.y][newP.x].isAlreadyMove());
        typeTable[newP.y][newP.x].setAlreadyMove(true);

        castling(oldP, newP, isMoved);
        promote(newP.y, newP.x);

    }


    /**
     * This method is used to promote the pawn into a queen if he have reach the other side of the chessBoard
     * @param y int : the row number
     * @param x int : the column number
     */
    private void promote(int y, int x){
        if (typeTable[y][x].getType() == IChess.ChessType.TYP_PAWN && typeTable[y][x].getColor() == IChess.ChessColor.CLR_WHITE && y ==0){
            typeTable[y][x] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
        }
        if (typeTable[y][x].getType() == IChess.ChessType.TYP_PAWN && typeTable[y][x].getColor() == IChess.ChessColor.CLR_BLACK && y ==7){
            typeTable[y][x] =  new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
        }
    }


    /**
     * This method is used to do the castling movement, moving a Rook and the King
     * @param oldP  ChessPosition : the old position of the tower that could move
     * @param newP  ChessPosition : the new position of the tower that could move
     * @param hasMoved  boolean : necessary to know if the king already move or not
     */
    private void castling(IChess.ChessPosition oldP, IChess.ChessPosition newP, boolean hasMoved){
        if (typeTable[newP.y][newP.x].getType() == IChess.ChessType.TYP_KING && !hasMoved && getKingState(typeTable[newP.y][newP.x].getColor())== IChess.ChessKingState.KING_SAFE){

            if (newP.x-oldP.x <0 && typeTable[newP.y][0].getType()== IChess.ChessType.TYP_ROOK){
                typeTable[newP.y][newP.x+1]  = typeTable[newP.y][0];
                typeTable[newP.y][0]=null;
            }
            else if(newP.x-oldP.x >0 && typeTable[newP.y][7].getType()== IChess.ChessType.TYP_ROOK){
                typeTable[newP.y][newP.x-1]  = typeTable[newP.y][7];
                typeTable[newP.y][7]=null;
            }
        }
    }

    /**
     * This method is used to get the removed piece List , used to displayed the pieces in game
     * @param color ChessColor : the color of the player from which we want the removed pieces
     * @return      List : list of ChessType containing all the pieces type already removed
     */
    public List<IChess.ChessType> getRemovedPieces(IChess.ChessColor color){
        if (color== IChess.ChessColor.CLR_WHITE){
            return listTypeWhite;
        }
        else {
            return listTypeBlack;
        }
    }


    /**
     * This method is used to undo a move
     * @return  boolean : true if we allowed the undo action
     */
    public boolean undoMove(){

       System.out.println("\n\n try ---------------------------------------------------------------------------------------------");
        if (listOfTable.size()>0) {
            typeTable = listOfTable.get(listOfTable.size() - 1);
            listOfTable.remove(listOfTable.size() - 1);
            listOfFirstMove.remove(listOfFirstMove.size()-1);

            return true;
        }
        return false;
    }


    /**
     * This method is used to write a new Piece[][], with all pieces
     * it was needed to have a save at a t time of the Piece[][] (typeTable)
     * @return  Piece[][] : the table containing piece
     */
    public Piece[][] writeTableSave(){
        Piece[][] table = new Piece[8][8];
        for (int i =0; i<8; i++){
            for (int j =0; j<8; j++){
                //
                Piece myPiece = null;

                if (typeTable[i][j]==null){}
                else {

                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_KNIGHT) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT, new Knight());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT, new Knight());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_ROOK) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK, new Rook());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK, new Rook());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_BISHOP) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP, new Bishop());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP, new Bishop());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_PAWN) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN, new Pawn());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN, new Pawn());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_QUEEN) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                    if (typeTable[i][j].getType() == IChess.ChessType.TYP_KING) {
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_BLACK) {
                            myPiece = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING, new King());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                        if (typeTable[i][j].getColor() == IChess.ChessColor.CLR_WHITE) {
                            myPiece = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING, new King());
                            myPiece.setAlreadyMove(typeTable[i][j].isAlreadyMove());
                        }
                    }
                }
                table[i][j] = myPiece;

            }
        }
        return table;
    }


    /**
     * This method is used to see typeTable element in the console, with a format close to a ChessBoard
     */
    public void showTable(){
        String result = "####################################################\n";
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (typeTable[i][j]!=null){
                    result+=typeTable[i][j].getType() + "/" + typeTable[i][j].getColor() + " . ";
                }
                else {
                    result+= "--------/--------- . ";
                }
            }
            result+= "\n";
        }
        System.out.println(result);
    }
}
