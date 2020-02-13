package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;


/**
 * this class is used to have the information about removed pieces from each player
 */
public class RemovedPiece {
    private static RemovedPiece instance ;
    private List<IChess.ChessType> listTypeBlack= new ArrayList<>();
    private List<IChess.ChessType> listTypeWhite = new ArrayList<>();


    /**
     * This method is used as a design pattern singleton
     * @return  RemovedPiece : an unique instance of RemovedPiece
     */
    public static RemovedPiece getInstance() {
        if(RemovedPiece.instance==null){
            RemovedPiece.instance = new RemovedPiece();
        }
        return RemovedPiece.instance;
    }


    /**
     * This method is used to reinitialise the removed piece list for each player
     */
    public void reinitialiseList(){
        listTypeBlack = new ArrayList<>();
        listTypeWhite = new ArrayList<>();
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
     * This method is used to get number of removed piece depending of the color given in parameter.
     * It used to avoid to go through all the table to count all the piece on board
     * for a specific color, so two loop less.
     * @param color     ChessColor : the player's color
     * @return          int : the number
     */
    public int getRemovedPiecesNumber(IChess.ChessColor color){
        if (color== IChess.ChessColor.CLR_WHITE){
            return listTypeWhite.size();
        }
        else {
            return listTypeBlack.size();
        }
    }


    /**
     * This method is used to add a black piece's Type to the corresponding list
     * @param type  ChessTyp : piece's type
     */
    public void addBlack(IChess.ChessType type){
        listTypeBlack.add(type);
    }


    /**
     * This method is used to add a white piece's Type to the corresponding list
     * @param type  ChessTyp : piece's type
     */
    public void addWhite(IChess.ChessType type){
        listTypeWhite.add(type);
    }


    /**
     * This method is used to removed the last black piece's Type of the corresponding list
     */
    public void removedBlack(){
        listTypeBlack.remove(listTypeBlack.size()-1);
    }


    /**
     * This method is used to removed the last black piece's Type of the corresponding list
     */
    public void removedWhite(){
        listTypeWhite.remove(listTypeWhite.size()-1);
    }
}
