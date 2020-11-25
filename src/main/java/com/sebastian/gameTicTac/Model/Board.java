package com.sebastian.gameTicTac.Model;

enum Content{
    EMPTY,
    CIRCLE,
    CROSS
}

public class Board {
    private static int sizeX = 25;
    private static int sizeY = 25;

    private Content[][] board;

    public Board() {
        this.board = new Content[sizeX][sizeY];
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static void setSizeX(int sizeX) {
        Board.sizeX = sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    public static void setSizeY(int sizeY) {
        Board.sizeY = sizeY;
    }

    public Content[][] getBoard() {
        return board;
    }

    public void setContentIntoBoard(int x, int y, Content c){
        if(x>=0 && y>=0 && x<sizeX && y<sizeY){
            this.board[x][y] = c;
        }
    }

}
