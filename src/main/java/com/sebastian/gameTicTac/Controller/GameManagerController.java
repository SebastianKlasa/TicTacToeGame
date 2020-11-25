package com.sebastian.gameTicTac.Controller;

import com.sebastian.gameTicTac.Model.Player;
import com.sebastian.gameTicTac.Service.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameManagerController {
    GameManagerService gameManagerService;

    @Autowired
    public GameManagerController(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    //create new game
    @GetMapping("/game")
    public void createNewGame(){
        String gameID = gameManagerService.addGame();
        //return player to game page with id
    }

    //join to game
    @GetMapping("/game/id/{gameID}")
    public void joinGame(@PathVariable String gameID){
        //return player to game page with id
    }

    //add player to game - player pressed a button: "join the game"
    @PostMapping("game/id/{gameID}")
    public void addPlayer(@RequestBody Player player, @PathVariable String gameID){
        gameManagerService.addPlayerToGame(player, gameID);
    }
}
