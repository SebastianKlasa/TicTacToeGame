package com.sebastian.gameTicTac.Model;

public class Move {
    Player player;
    int x;
    int y;

    public Move(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Move() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
