package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manage a chronometer and get the game duration for each player
 */
public class Chronometer {

    private long whiteTime=0;
    private long blackTime=0;
    private long currentGameTime;
    private static Chronometer instance;
    private long gameStartTime=System.currentTimeMillis();

    private List<Long> blackTimesList;
    private List<Long> whiteTimesList;
    private  List<Long> currentTimesList;


    /**
     * This method is used as a design pattern singleton
     * @return  Chronometer : an unique instance of Chronometer
     */
    public static Chronometer getInstance() {
        if (Chronometer.instance == null) {
            Chronometer.instance = new Chronometer();
        }
        return Chronometer.instance;
    }


    /**
     * This method is used to update the chronometer time, and the time corresponding of each player
     * @param color         ChessColor : current player's color
     * @param isPlaying     boolean : true if the player is playing
     */
    public void updateChronometer(IChess.ChessColor color, boolean isPlaying){
        this.currentGameTime=System.currentTimeMillis();
        if (color == IChess.ChessColor.CLR_WHITE & isPlaying){
            whiteTime = this.currentGameTime - gameStartTime - blackTime;
        }
        else if (color == IChess.ChessColor.CLR_BLACK & isPlaying){
            blackTime = this.currentGameTime -gameStartTime - whiteTime ;
        }
    }


    /**
     * This method is used to reset the chronometer
     */
    public void reset(){
        whiteTime = 0;
        blackTime = 0;
        gameStartTime=System.currentTimeMillis();
        blackTimesList = new ArrayList<>();
        whiteTimesList = new ArrayList<>();
        currentTimesList = new ArrayList<>();
    }



    public long getPlayerDuration(IChess.ChessColor color, boolean isPlaying) {
        Chronometer chronometer = Chronometer.getInstance();
        chronometer.updateChronometer(color, isPlaying);

        if (color == IChess.ChessColor.CLR_WHITE && isPlaying){
            System.out.println(color);
            return whiteTime;
        }
        else if (color == IChess.ChessColor.CLR_BLACK && isPlaying){
            System.out.println(color);
            return blackTime;
        }
        else if (color == IChess.ChessColor.CLR_WHITE && !isPlaying){
            return whiteTime;
        }
        else {
            return blackTime;
        }
    }


    /**
     * This method is used to return the game duration of the white player
     * @return  long : white player's duration
     */
    public long whiteTime() {
        return whiteTime;
    }


    /**
     * This method is used to return the game duration of the black player
     * @return  long : black player's duration
     */
    public long blackTime() {
        return blackTime;
    }


    /**
     * This method is used to add
     */
    public void addWhiteTime(){
        whiteTimesList.add(System.currentTimeMillis());
    }
    public void addBlackTime(){
        blackTimesList.add(System.currentTimeMillis());
    }




    public void addCurrentTime(){
        currentTimesList.add(System.currentTimeMillis());
    }

    public void deleteCurrentTime(IChess.ChessColor color){
        if (currentTimesList.size()>1){
            long time = currentTimesList.get(currentTimesList.size()-1);

            currentTimesList.remove(currentTimesList.size()-1);

            gameStartTime+= (System.currentTimeMillis() - time);
            if (color == IChess.ChessColor.CLR_WHITE){
                whiteTime -= ( System.currentTimeMillis() - time);
            }
            else {
                blackTime -= (System.currentTimeMillis() - time);
            }
            System.out.println("rxxyfxfxgfgfxgfxgx");
        }
    }



    public void deleteWhiteTime(){
        if (whiteTimesList.size()>1){
            whiteTimesList.remove(whiteTimesList.size()-1);
            long time = whiteTimesList.get(whiteTimesList.size()-1);

            //gameStartTime+= (System.currentTimeMillis() - time);
            whiteTime = ( System.currentTimeMillis() - time);
            System.out.println("rxxyfxfxgfgfxgfxgx");
        }
    }

    public void deleteBlackTime(){
        if (blackTimesList.size()>1) {
            blackTimesList.remove(blackTimesList.size()-1);
            long time = blackTimesList.get(blackTimesList.size()-1);

            //gameStartTime+= (System.currentTimeMillis() - time);
            blackTime = ( System.currentTimeMillis() - time);
            System.out.println("azertyuioiuytrezazerty");
        }
    }
}
