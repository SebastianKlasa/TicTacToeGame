package com.sebastian.gameTicTac.Service;

import com.sebastian.gameTicTac.Dao.PlayerDao;
import com.sebastian.gameTicTac.Model.Game;
import com.sebastian.gameTicTac.Model.GameManager;
import com.sebastian.gameTicTac.Model.Move;
import com.sebastian.gameTicTac.Model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManagerService {
    GameManager gameManager;
    PlayerDao playerDao;

    @Autowired
    public GameManagerService(GameManager gameManager, PlayerDao playerDao) {
        this.gameManager = gameManager;
        this.playerDao = playerDao;
    }

    private void gameOver(String gameID){
        Game game = gameManager.findGameById(gameID);
        if(game == null) return;
        Player[] players = new Player[2];
        players = game.getPlayers();
        Player winner = game.getWinner();
        if(winner != null || game.isDraw()) {
            if(winner!=null) game.setDraw(false);
            game.gameOver();
            //update players
//          games ended
            players[0].setGames(players[0].getGames() + 1);
            players[1].setGames(players[1].getGames() + 1);
            players[0].setEnded(players[0].getEnded() + 1);
            players[1].setEnded(players[1].getEnded() + 1);
//          wins lost
            if (winner != null) {
                if (winner.getId() == players[0].getId()) {
                    players[0].setWins(players[0].getWins() + 1);
                    players[1].setLost(players[1].getLost() + 1);
                }
                if (winner.getId() == players[1].getId()) {
                    players[1].setWins(players[1].getWins() + 1);
                    players[0].setLost(players[0].getLost() + 1);
                }
            }
//          draw
            if (game.isDraw()) {
                players[0].setDraw(players[0].getDraw() + 1);
                players[1].setDraw(players[1].getDraw() + 1);
            }
            playerDao.updatePlayer(players[0]);
            playerDao.updatePlayer(players[1]);
        }
    }

    public Game addGame(){
        String gameID = gameManager.generateId(6);
        Game game = new Game(gameID);
        gameManager.addGame(game);
        return game;
    }

    public boolean addPlayerToGame(Player player, String gameID){
        Game game = gameManager.findGameById(gameID);
        if(game != null){
            if(game.getPlayers()[0] == null || game.getPlayers()[1] == null ) {
                game.addPlayer(player);
                return true;
            }
        }
        return false;
    }

    public boolean removePlayerFromGame(Player player, String gameID){
        Game game = gameManager.findGameById(gameID);
        if(game != null) {
            game.removePlayer(player);
            return true;
        }
        return false;
    }

    public Game getGameByID(String id){
        return gameManager.findGameById(id);
    }

    public void setMove(Move move, String id){
        Game game = gameManager.findGameById(id);
        if(game == null) return;
        game.setMove(move);
        gameOver(id);
    }

    public void startGame(String id){
        Game game = gameManager.findGameById(id);
        if(game == null) return;
        game.startGame();
    }

    public Player checkWinner(String id){
        Game game = gameManager.findGameById(id);
        if(game == null) return null;
        Player winner = game.checkWinner();
        if(winner != null){
            gameOver(id);
            game.setDraw(false);
        }
        return winner;
    }

    public void kickPlayer(String id){
        Game game = gameManager.findGameById(id);
        if(game == null) return;
        Player playerToKick = game.getPlayers()[1];
        if(playerToKick != null && !game.isActive()){
            game.removePlayer(playerToKick);
        }
    }

    public void changePlayerRound(String id){
        Game game = gameManager.findGameById(id);
        if(game == null) return;
        game.changePlayerRound();
    }

    public void offerDraw(Player player, String gameID){
        Game game = gameManager.findGameById(gameID);
        if(game == null) return;
        game.drawOffer(player);
        gameOver(gameID);
    }

}
