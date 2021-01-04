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
        clearBoard();
    }

    public void clearBoard(){
        for(int x = 0; x<sizeX;x++){
            for(int y = 0; y<sizeY;y++){
                board[x][y] = Content.EMPTY;
            }
        }
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

    public Content getSign(int x, int y){
        if(x>=0 && y>=0 && x<sizeX && y<sizeY){
            return this.board[x][y];
        }
        return null;
    }

    public void printContent(){
        System.out.println("------------------------------------------");
        for(int y = 0; y<sizeY;y++){
            for(int x = 0; x<sizeX;x++){
                if(board[x][y] == Content.EMPTY) System.out.print('.');
                else if(board[x][y] == Content.CROSS) System.out.print('X');
                else if(board[x][y] == Content.CIRCLE) System.out.print('O');
            }
            System.out.print('\n');
        }
        System.out.println("------------------------------------------");
    }

}
