package com.sebastian.gameTicTac.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {
    private String id;
    private Board board;
    private Player[] players;
    private boolean isActive;
    private Player winner;
    private int playerRound;
    private int previousGamePlayerStart;
    private boolean isDraw;
    private boolean[] drawOffer;
    private LocalDateTime lastMoveTime;

    private int convertSignToPlayerIndex(Content sign){
        if(sign == Content.CROSS) return 0;
        else if (sign == Content.CIRCLE) return 1;
        return -1;
    }

    public void gameOver(){
        this.isActive = false;
        //update players
    }

    public Game(String id){
        this.id = id;
        this.board = new Board();
        this.players = new Player[2];
        this.drawOffer = new boolean[2];
        this.isActive = false;
        this.playerRound = 0;
        this.isDraw = false;
        this.previousGamePlayerStart = 1;
        this.lastMoveTime = LocalDateTime.now(); // Create a date object
    }

    public void addPlayer(Player player){
        this.lastMoveTime = LocalDateTime.now(); // Create a date object
        if(players[0] == null) {
            players[0] = player;
        }
        else{
            if(players[1]==null && players[0].getId()!=player.getId()){
                players[1]=player;
            }
        }
    }

    public void removePlayer(Player player){
        if(player.getId() == this.players[0].getId()){
            this.players[0] = this.players[1];
            this.players[1] = null;
        }
        else if(player.getId() == this.players[1].getId()){
            this.players[1] = null;
        }
    }

    public boolean startGame(){
        this.lastMoveTime = LocalDateTime.now(); // Create a date object
        if(players[0] != null && players[1] != null){
            //game must to be an active
            this.isActive = true;
            //clear board
            this.board.clearBoard();
            //reset winner and draw
            this.winner = null;
            this.isDraw = false;
            //reset player round
            this.playerRound = (this.previousGamePlayerStart + 1) % 2;
            this.previousGamePlayerStart = this.playerRound;
            //draw array clean
            drawOffer[0] = false;
            drawOffer[1] = false;

            return true;
        }
        else return false;
    }

    public int getPreviousGamePlayerStart() {
        return previousGamePlayerStart;
    }

    public void setPreviousGamePlayerStart(int previousGamePlayerStart) {
        this.previousGamePlayerStart = previousGamePlayerStart;
    }

    public LocalDateTime getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(LocalDateTime lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }

    public boolean[] getDrawOffer() {
        return drawOffer;
    }

    public void setDrawOffer(boolean[] drawOffer) {
        this.drawOffer = drawOffer;
    }

    public int getPlayerRound() {
        return playerRound;
    }

    public void setPlayerRound(int playerRound) {
        this.playerRound = playerRound;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
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

    public void changePlayerRound(){
        this.playerRound = (this.playerRound + 1) % 2;
    }

    /**
     * Player with id 0 in Array has cross sign, otherwise player
     * with id 1 has circle
     *
     * @param move exists of Player who set sign, x and y coordinates
     */
    public void setMove(Move move){
        this.lastMoveTime = LocalDateTime.now();
        //reset draw offers when move is made
        drawOffer[0] = false;
        drawOffer[1] = false;

        int id = -1;
        int x = move.x;
        int y = move.y;
        try {
            if (move.getPlayer().getId() == players[0].getId()) id = 0;
            else if (move.getPlayer().getId() == players[1].getId()) id = 1;

            //condidtion id == playerRound care of good order of the game
            if (id != -1 && this.board.getSign(x, y) == Content.EMPTY && id == playerRound) {
                if (id == 0) this.board.setContentIntoBoard(x, y, Content.CROSS);
                if (id == 1) this.board.setContentIntoBoard(x, y, Content.CIRCLE);

                changePlayerRound();
                checkDraw();
                checkWinner();
            }
        }
        catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Method checks winning conditions every round
     * Possible conditions:
     * - 5 signs in a row (vertical, horizontal, obliquely)
     * - one of the players has exceeded round time ???
     */
    public Player checkWinner(){
        int sizeX = Board.getSizeX(), sizeY = Board.getSizeY();

        for(int y = 0; y<sizeY; y++){
            for(int x = 0; x<sizeX; x++){
                Content sign = board.getSign(x, y);

                if(sign == Content.EMPTY) continue;

                //check horizontally
                if(x <= sizeX - 4){
                    int c = 0;
                    for(int i=1; i<5; i++){
                        if(board.getSign(x + i, y) == sign){
                            c++;
                        }
                    }
                    if(c ==4){
                        winner = players[convertSignToPlayerIndex(sign)];
                        gameOver();
                        return winner;
                    }
                }

                //check vertically
                if(y <= sizeY - 4){
                    int c = 0;
                    for(int i=1; i<5; i++){
                        if(board.getSign(x, y + i) == sign){
                            c++;
                        }
                    }
                    if(c ==4){
                        winner = players[convertSignToPlayerIndex(sign)];
                        gameOver();
                        return winner;
                    }
                }
                //check obliquely
                // direction: \
                if(x <= sizeX - 4 && y <= sizeY - 4){
                    int c = 0;
                    for(int i=1; i<5; i++){
                        if(board.getSign(x + i, y + i) == sign){
                            c++;
                        }
                    }
                    if(c ==4){
                        winner = players[convertSignToPlayerIndex(sign)];
                        gameOver();
                        return winner;
                    }
                }
                // direction: /
                if(x - 4 >= 0 && y <= sizeY - 4){
                    int c = 0;
                    for(int i=1; i<5; i++){
                        if(board.getSign(x - i, y + i) == sign){
                            c++;
                        }
                    }
                    if(c ==4){
                        winner = players[convertSignToPlayerIndex(sign)];
                        gameOver();
                        return winner;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Check is on the board any empty place
     * @return value of isDraw variable
     */
    public boolean checkDraw(){
        for(int y = 0; y<Board.getSizeY(); y++){
            for(int x = 0; x<Board.getSizeX(); x++){
                if(this.board.getSign(x, y) == Content.EMPTY){
                    this.isDraw = false;
                    return false;
                }
            }
        }
        this.isDraw = true;
        gameOver();
        return true;
    }

    /**
     *
     * @param player - player who offers the draw
     * @return true if 2 players offered draw in one round
     */
    public boolean drawOffer(Player player){
        if(player.getId() == this.players[0].getId()){
            this.drawOffer[0] = true;
        }
        else if(player.getId() == this.players[1].getId()){
            this.drawOffer[1] = true;
        }
        if(this.drawOffer[0] == true && this.drawOffer[1] == true){
            this.isDraw = true;
            gameOver();
            return true;
        }
        return false;
    }

}
