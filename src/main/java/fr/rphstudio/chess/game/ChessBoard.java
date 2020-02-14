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
    private List<Piece[][]> listOfTable;
    private List<IChess.ChessColor> listOfRemovedColor;
    private List<IChess.ChessPosition> listPawnMove;



    /**
     * This is the ChessBoard constructor
     */
    public ChessBoard(){
        reinitialise();
    }


    /**
     * This method is used to get Pawn position list
     * @return  List : Pawn ChessPosition list
     */
    public List<IChess.ChessPosition> getListPawnMove() {
        return listPawnMove;
    }


    /**
     * This method is used to get the piece at a given position with two int (y and x)
     * @param y     int : corresponding to the row we choose
     * @param x     int : corresponding to the column we choose
     * @return      Piece : return the Piece at the given position
     */
    public Piece getPiece(int y, int x){
        return this.typeTable[y][x];
    }


    /**
     * This method is used to get a Piece type thanks to a given position
     * @param p ChessPosition : position of the piece we want
     * @return  ChessType : return the Piece's type
     * @throws EmptyCellException   throw when there is no piece at the ChessPosition
     * @throws OutOfBoardException  throw when the given ChessPosition is out of the typeTable
     */
    public IChess.ChessType getPieceType(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=IChess.BOARD_WIDTH|| p.y>=IChess.BOARD_HEIGHT|| p.x<0 || p.y<0){
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
     * @throws EmptyCellException   throw when there is no piece at the ChessPosition
     * @throws OutOfBoardException  throw when the given ChessPosition is out of the typeTable
     */
    public IChess.ChessColor getPieceColor(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (p.x>=IChess.BOARD_WIDTH || p.y>=IChess.BOARD_HEIGHT || p.x<0 || p.y<0){
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
        IChess.ChessPosition p = new IChess.ChessPosition(-1, -1);
        for (int row = 0; row<IChess.BOARD_HEIGHT; row++){
            for (int column = 0; column<IChess.BOARD_WIDTH; column++){
                try {
                    if (getPiece(row, column).getColor() != color) {
                        List<IChess.ChessPosition> list = getPieceMoves(new IChess.ChessPosition(column, row));
                        finalList.addAll(list);
                    }

                    if (getPiece(row, column).getColor() == color && getPiece(row, column).getType() == IChess.ChessType.TYP_KING) {
                        p = new IChess.ChessPosition(column, row);
                    }
                }catch (Exception e ){
                }
            }
        }

        IChess.ChessKingState kingState = IChess.ChessKingState.KING_SAFE;
        for (int a = 0; a<finalList.size(); a++){
            if(finalList.get(a).equals(p)){
                kingState = IChess.ChessKingState.KING_THREATEN;
                break;
            }
        }

        return kingState;
    }


    /**
     * This method is used to reinitialise the Game, the Piece table and list of dead pieces
     * @return  Piece[][] : the table piece we have generated
     */
    public Piece[][] reinitialise(){
        typeTable = new Piece[IChess.BOARD_HEIGHT][IChess.BOARD_WIDTH];
        RemovedPiece.getInstance().reinitialiseList();
        listOfTable = new ArrayList<>();
        listOfRemovedColor = new ArrayList<>();
        listPawnMove = new ArrayList<>();

        // pawn set-up
        for (int col=0; col<IChess.BOARD_WIDTH; col++){
            typeTable[IChess.BOARD_POS_Y_BLACK_PAWNS][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN, new Pawn());
            typeTable[IChess.BOARD_POS_Y_WHITE_PAWNS][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN, new Pawn());
        }


        //king's line set-up
        for (int col=0; col<IChess.BOARD_WIDTH; col++){
            if (col==IChess.BOARD_POS_X_QUEENSIDE_ROOK || col==IChess.BOARD_POS_X_KINGSIDE_ROOK) {
                typeTable[IChess.BOARD_POS_Y_BLACK_PIECES][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK, new Rook());
                typeTable[IChess.BOARD_POS_Y_WHITE_PIECES][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK, new Rook());
            }
            else if (col== IChess.BOARD_POS_X_KINGSIDE_KNIGHT || col==IChess.BOARD_POS_X_QUEENSIDE_KNIGHT) {
                typeTable[IChess.BOARD_POS_Y_BLACK_PIECES][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT, new Knight());
                typeTable[IChess.BOARD_POS_Y_WHITE_PIECES][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT, new Knight());
            }
            else if (col==IChess.BOARD_POS_X_KINGSIDE_BISHOP || col==IChess.BOARD_POS_X_QUEENSIDE_BISHOP) {
                typeTable[IChess.BOARD_POS_Y_BLACK_PIECES][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP, new Bishop());
                typeTable[IChess.BOARD_POS_Y_WHITE_PIECES][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP, new Bishop());
            }
            else if (col==IChess.BOARD_POS_X_QUEEN) {
                typeTable[IChess.BOARD_POS_Y_BLACK_PIECES][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN, new Queen());
                typeTable[IChess.BOARD_POS_Y_WHITE_PIECES][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN, new Queen());
            }
            else {
                typeTable[IChess.BOARD_POS_Y_BLACK_PIECES][col] = new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING, new King());
                typeTable[IChess.BOARD_POS_Y_WHITE_PIECES][col] = new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING, new King());
            }
        }

        return typeTable;
    }


    /**
     * This method is used to calculate the number of remaining piece depending of the color given in parameter
     * @param color ChessColor : the color we choose, White or Black
     * @return      int : the number of remaining piece
     */
    public int getNumberOfRemaining(IChess.ChessColor color){
        /*int remaining = 0;
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
        */
        //now we could calculate this int this way: initial pieces numbers - listSize corresponding to the color

        return 16 - RemovedPiece.getInstance().getRemovedPiecesNumber(color);
    }


    /**
     * This method is used to get the piecesMoves depending of the position of the piece we want to find out
     * @param p ChessPosition : the position of the piece
     * @return  List : possible move's list
     */
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p) {
        List<IChess.ChessPosition> list = typeTable[p.y][p.x].getMovePattern().getPieceMoves(p, this);
        List<IChess.ChessPosition> listFinal = new ArrayList<>();

        // the pattern move of pieces have to include all ways they could go without limits, and here we limit them
        for (int i=0; i<list.size(); i++) {
            // here we limit by checking if the position is really in the board
            if (list.get(i).y >= 0 && list.get(i).y < IChess.BOARD_HEIGHT && list.get(i).x >= 0 && list.get(i).x < IChess.BOARD_WIDTH) {

                // limit when is the same color than the piece selected
                if (typeTable[list.get(i).y][list.get(i).x]==null || typeTable[list.get(i).y][list.get(i).x].getColor()!=typeTable[p.y][p.x].getColor()){
                    listFinal.add(list.get(i));
                }
            }
        }
        return listFinal;
    }


    /**
     * This method is used to give the possible moves for a piece after checking if it will
     * threaten the king, then will limit the possible moves
     * @param p ChessPosition : the position of the piece
     * @return  List : list of possible moves
     */
    public List<IChess.ChessPosition> testIfWillThreaten(IChess.ChessPosition p){
        List<IChess.ChessPosition> list = getPieceMoves(p);
        List<IChess.ChessPosition> listFinal = new ArrayList<>();
        IChess.ChessPosition p1;


        // allow a move if the king stay safe for a defined move
        for (int i=0; i<list.size(); i++){
            try {
                p1 = list.get(i);
                if (moveTest(p, p1)){
                    listFinal.add(p1);
                }

            }catch (Exception e){

            }
        }
        return listFinal;
    }


    /**
     * This method is used to test a move for a piece, to know if
     * the king will stay safe, and then confirm that the move is allowed
     * @param oldP  ChessPosition : piece's old position
     * @param newP  ChessPosition : piece's new position
     * @return      boolean : true if the movement is allowed
     */
    public boolean moveTest(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        Piece save = typeTable[newP.y][newP.x];
        boolean isMovePossible= false;
        typeTable[newP.y][newP.x] = typeTable[oldP.y][oldP.x];
        typeTable[oldP.y][oldP.x] = null;
        if (getKingState(typeTable[newP.y][newP.x].getColor())== IChess.ChessKingState.KING_SAFE){
            isMovePossible= true;
        }
        typeTable[oldP.y][oldP.x] = typeTable[newP.y][newP.x];
        typeTable[newP.y][newP.x] = save;

        return isMovePossible;
    }


    /**
     * This method is used to do the movement choose by the player, depending of the position of
     * the piece we choose and the position this pieces will go
     * @param oldP  ChessPosition : the old piece's position
     * @param newP  ChessPosition : the new piece's position
     */
    public void movePiece(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        listOfTable.add(writeTableCopy());
        boolean isMoved = typeTable[oldP.y][oldP.x].isAlreadyMove();

        if (typeTable[newP.y][newP.x] == null){
            listOfRemovedColor.add(null);
        }
        else {
            RemovedPiece.getInstance().addToRemovedTypeList(typeTable[newP.y][newP.x].getColor(), typeTable[newP.y][newP.x].getType());
            listOfRemovedColor.add(typeTable[newP.y][newP.x].getColor());
        }

        // move
        typeTable[newP.y][newP.x] = typeTable[oldP.y][oldP.x];
        typeTable[oldP.y][oldP.x] = null;
        showTable();
        typeTable[newP.y][newP.x].setAlreadyMove(true);

        priseEnPassant(oldP, newP);
        if (typeTable[newP.y][newP.x].getType() == IChess.ChessType.TYP_PAWN && Math.abs(newP.y-oldP.y)==2){
            listPawnMove.add(newP);
        }
        else {
            listPawnMove.add(null);
        }
        castling(oldP, newP, isMoved);
        promote(newP.y, newP.x);
        Chronometer.getInstance().addCurrentTime();

    }


    /**
     * This method is used to do the 'prise en passant' move
     * if the constraint are all checked
     * @param oldP  ChessPosition : piece's old position
     * @param newP  ChessPosition : piece's new position
     */
    private void priseEnPassant(IChess.ChessPosition oldP, IChess.ChessPosition newP){
        if (getListPawnMove().size()>1 && typeTable[newP.y][newP.x].getType() == IChess.ChessType.TYP_PAWN) {
            if (getListPawnMove().get(getListPawnMove().size() - 1)!=null ) {
                try {
                    //Black action
                    if (typeTable[newP.y][newP.x].getColor()== IChess.ChessColor.CLR_BLACK){
                        if ( 4==oldP.y) {
                            RemovedPiece.getInstance().addToRemovedTypeList(typeTable[newP.y-1][newP.x].getColor(), typeTable[newP.y-1][newP.x].getType());
                            listOfRemovedColor.set(listOfRemovedColor.size()-1, typeTable[newP.y-1][newP.x].getColor());
                            typeTable[newP.y-1][newP.x]=null;
                        }
                    }
                    else //white action
                    {
                        if ( 3==oldP.y) {
                            RemovedPiece.getInstance().addToRemovedTypeList(typeTable[newP.y+1][newP.x].getColor(), typeTable[newP.y+1][newP.x].getType());
                            listOfRemovedColor.set(listOfRemovedColor.size()-1, typeTable[newP.y+1][newP.x].getColor());
                            typeTable[newP.y+1][newP.x]=null;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
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

            if (newP.x-oldP.x <-1 && typeTable[newP.y][0].getType()== IChess.ChessType.TYP_ROOK){
                typeTable[newP.y][newP.x+1]  = typeTable[newP.y][0];
                typeTable[newP.y][0]=null;
            }
            else if(newP.x-oldP.x >1 && typeTable[newP.y][7].getType()== IChess.ChessType.TYP_ROOK){
                typeTable[newP.y][newP.x-1]  = typeTable[newP.y][7];
                typeTable[newP.y][7]=null;
            }
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
            Chronometer.getInstance().deleteCurrentTime();
            listPawnMove.remove(listPawnMove.size()-1);


            RemovedPiece.getInstance().removedFromTypeList(listOfRemovedColor.get(listOfRemovedColor.size()-1));

            listOfRemovedColor.remove(listOfRemovedColor.size()-1);

            return true;
        }
        return false;
    }


    /**
     * This method is used to copy our typeTable Piece[][], with all pieces
     * needed to have a save of the Piece[][] (typeTable) and not a copy of references
     * @return  Piece[][] : the table containing piece
     */
    private Piece[][] writeTableCopy(){
        Piece[][] table = new Piece[IChess.BOARD_HEIGHT][IChess.BOARD_WIDTH];
        for (int row =0; row<IChess.BOARD_HEIGHT; row++){
            for (int column =0; column<IChess.BOARD_WIDTH; column++){
                Piece myPiece = typeTable[row][column];
                if (typeTable[row][column]!=null){
                    myPiece = new Piece(myPiece.getColor(), myPiece.getType(), myPiece.getMovePattern());
                    myPiece.setAlreadyMove(typeTable[row][column].isAlreadyMove());
                }
                table[row][column] = myPiece;
            }
        }
        return table;
    }


    /**
     * This method is used to see typeTable element in the console, with a format close to a ChessBoard
     */
    private void showTable(){
        String result = "####################################################\n";
        for (int row=0; row<8; row++){
            for (int column=0; column<8; column++){
                if (typeTable[row][column]!=null){
                    result+=typeTable[row][column].getType() + "/" + typeTable[row][column].getColor() + " . ";
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
