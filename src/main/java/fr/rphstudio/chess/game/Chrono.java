package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Chrono {

        public Chrono(IChess.ChessColor color, boolean isPlaying){
                long chrono = System.currentTimeMillis();
                long time = 0;
                long result;

                if (color == IChess.ChessColor.CLR_WHITE & isPlaying == true){
                        result = chrono;

                }else if (color == IChess.ChessColor.CLR_BLACK & isPlaying == true){
                       result = chrono;
                }
        }
}
