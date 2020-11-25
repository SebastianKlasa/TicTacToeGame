package com.sebastian.gameTicTac.Service;

import com.sebastian.gameTicTac.Model.Game;
import com.sebastian.gameTicTac.Model.GameManager;
import com.sebastian.gameTicTac.Model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManagerService {
    GameManager gameManager;

    @Autowired
    public GameManagerService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public String addGame(){
        String gameID = gameManager.generateId(6);
        Game game = new Game(gameID);
        gameManager.addGame(game);
        return gameID;
    }

    public boolean addPlayerToGame(Player player, String gameID){
        Game game = gameManager.findGameById(gameID);
        if(game.getPlayers()[0] == null || game.getPlayers()[1] == null ) {
            game.addPlayer(player);
            return true;
        }
        return false;
    }
}
