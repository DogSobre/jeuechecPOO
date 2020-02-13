package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;


public class Chrono {

    private long whiteTime=0;
    private long blackTime=0;
    private long currentGameTime;
    private static Chrono instance;
    private long gameStartTime=System.currentTimeMillis();

    private List<Long> blackTimesList;
    private List<Long> whiteTimesList;
    private  List<Long> currentTimesList;


    public static Chrono getInstance() {
        if (Chrono.instance == null) {
            Chrono.instance = new Chrono();
        }
        return Chrono.instance;
    }

    public void updateChrono(IChess.ChessColor color, boolean isPlaying){
        this.currentGameTime=System.currentTimeMillis();
        if (color == IChess.ChessColor.CLR_WHITE & isPlaying){
            whiteTime = this.currentGameTime - gameStartTime - blackTime;
        }else if (color == IChess.ChessColor.CLR_BLACK & isPlaying){
            blackTime = this.currentGameTime -gameStartTime - whiteTime ;
        }
    }


    public void reset(){
        whiteTime = 0;
        blackTime = 0;
        gameStartTime=System.currentTimeMillis();
        blackTimesList = new ArrayList<>();
        whiteTimesList = new ArrayList<>();
    }

    public long whiteTime() {
        return whiteTime;
    }

    public long blackTime() {
        return blackTime;
    }



    public void addWhiteTime(){
        whiteTimesList.add(System.currentTimeMillis());
    }
    public void addBlackTime(){
        blackTimesList.add(System.currentTimeMillis());
    }
    public void addCurrentTime(){
        currentTimesList.add(System.currentTimeMillis());
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
