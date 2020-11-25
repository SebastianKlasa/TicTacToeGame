package com.sebastian.gameTicTac.Model;

public class Game {
    private String id;
    private Board board;
    private Player[] players;
    private boolean isActive;
    private Player winner;

    public Game(String id){
        this.id = id;
        this.board = new Board();
        this.players = new Player[2];
        this.isActive = false;
    }

    public void addPlayer(Player player){
        if(players[0] == null) {
            players[0] = player;
        }
        else{
            if(players[1]==null){
                players[1]=player;
            }
        }
    }

    public boolean startGame(){
        if(players[0] != null && players[1] != null){
            this.isActive = true;
            return true;
        }
        else return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
