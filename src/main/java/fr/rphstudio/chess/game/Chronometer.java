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
    private static Chronometer instance;
    private long gameStartTime=System.currentTimeMillis();
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
        long currentGameTime=System.currentTimeMillis();
        if (color == IChess.ChessColor.CLR_WHITE & isPlaying){
            whiteTime = currentGameTime - gameStartTime - blackTime;
        }
        else if (color == IChess.ChessColor.CLR_BLACK & isPlaying){
            blackTime = currentGameTime -gameStartTime - whiteTime ;
        }
    }


    /**
     * This method is used to reset the chronometer
     */
    public void reset(){
        whiteTime = 0;
        blackTime = 0;
        gameStartTime=System.currentTimeMillis();
        currentTimesList = new ArrayList<>();
    }


    /**
     * This method is used to get each player duration, that will be display and will
     * ask an update of the time for the playing player
     * @param color     ChessColor : player's color
     * @param isPlaying boolean : true if this is the player who is playing
     * @return          long : a long value used to display the duration of the player
     */
    public long getPlayerDuration(IChess.ChessColor color, boolean isPlaying) {
        Chronometer chronometer = Chronometer.getInstance();
        chronometer.updateChronometer(color, isPlaying);

        if (color == IChess.ChessColor.CLR_WHITE ){
            return whiteTime;
        }
        else {
            return blackTime;
        }
    }


    /**
     * This method is used to add the the current time on a list of time ,
     * this method should be call when a piece do a move
     */
    public void addCurrentTime(){
        currentTimesList.add(System.currentTimeMillis());
    }


    /**
     * This method is used to delete the last saved time from the list of currentTimesList
     * and set the time of each player when an undo action is performed
     * @param color ChessColor : player's color
     */
    public void deleteCurrentTime(IChess.ChessColor color){
        if (currentTimesList.size()>0){
            long time = currentTimesList.get(currentTimesList.size()-1);
            currentTimesList.remove(currentTimesList.size()-1);

            long deltaTime = System.currentTimeMillis() - time;

            gameStartTime+= deltaTime;
            for (int i=0; i<currentTimesList.size()-1; i++){
                currentTimesList.set(i, currentTimesList.get(i)+deltaTime);
            }
            if (currentTimesList.size()>0) {
                long oldTime = time - currentTimesList.get(currentTimesList.size() - 1) + deltaTime;
                System.out.println(oldTime);
                //
                if (color == IChess.ChessColor.CLR_WHITE) {

                    blackTime -= /*time - gameStartTime - */oldTime;
                    //whiteTime -= /*time - gameStartTime - */oldTime;

                } else {

                    whiteTime -= /*time - gameStartTime - */oldTime;
                    //blackTime -= /*time - gameStartTime - */oldTime;

                }
            }

            //ensure that when we back to the initial position we reset the chrono
            if (currentTimesList.size()==0){
                reset();
            }
        }
    }
}
