package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import java.util.ArrayList;
import java.util.List;

public class RemovedPiece {
    private static RemovedPiece instance ;
    private List<IChess.ChessType> listTypeBlack= new ArrayList<>();
    private List<IChess.ChessType> listTypeWhite = new ArrayList<>();

    /**
     * This method is used as a design singleton
     * @return
     */
    public static RemovedPiece getInstance() {
        if(RemovedPiece.instance==null){
            RemovedPiece.instance = new RemovedPiece();
        }
        return RemovedPiece.instance;
    }


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


    public int getRemovedPiecesNumber(IChess.ChessColor color){
        if (color== IChess.ChessColor.CLR_WHITE){
            return listTypeWhite.size();
        }
        else {
            return listTypeBlack.size();
        }
    }


    public void addBlack(IChess.ChessType type){
        listTypeBlack.add(type);
    }

    public void addWhite(IChess.ChessType type){
        listTypeWhite.add(type);
    }

    public void removedBlack(){
        listTypeBlack.remove(listTypeBlack.size()-1);
    }

    public void removedWhite(){
        listTypeWhite.remove(listTypeWhite.size()-1);
    }

}
