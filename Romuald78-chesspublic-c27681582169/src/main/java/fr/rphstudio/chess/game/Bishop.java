package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

public class Bishop implements IMove {


    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, ChessBoard board) {
        List<IChess.ChessPosition> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            int posX = p.x - i;
            int posY = p.y - i;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }


        for (int i = 1; i < 8; i++) {
            int posX = p.x + i;
            int posY = p.y + i;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }


        for (int i = 1; i < 8; i++) {
            int posX = p.x + i;
            int posY = p.y - i;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }

        for (int i = 1; i < 8; i++) {
            int posX = p.x - i;
            int posY = p.y + i;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }
        calculateDiagonal(p, board, list, -1, -1);
        calculateDiagonal(p, board, list, -1, +1);
        calculateDiagonal(p, board, list, +1, +1);
        calculateDiagonal(p, board, list, +1, -1);

        return list;
    }


    private void calculateDiagonal(IChess.ChessPosition p, ChessBoard board, List<IChess.ChessPosition> list, int signeX, int signeY) {
        for (int i = 1; i < 8; i++) {
            int posX = p.x + i * signeX;
            int posY = p.y + i * signeY;
            try {
                if (board.getPiece(posY, posX) == null) {
                    list.add(new IChess.ChessPosition(posX, posY));
                } else if (board.getPiece(posY, posX).getColor() != board.getPiece(p.y, p.x).getColor()) {
                    list.add(new IChess.ChessPosition(posX, posY));
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
            }
        }
    }
}
