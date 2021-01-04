package com.sebastian.gameTicTac.Controller;

import com.sebastian.gameTicTac.Model.Game;
import com.sebastian.gameTicTac.Model.Move;
import com.sebastian.gameTicTac.Model.Player;
import com.sebastian.gameTicTac.Service.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameManagerController {
    GameManagerService gameManagerService;

    @Autowired
    public GameManagerController(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    /**
     *
     * @param gameID is an id of game instance
     * @return Model - game instance, View - view of game instance
     */
    @GetMapping("/game/id/{gameID}")
    public ModelAndView getGame(@PathVariable String gameID){

        //TODO: when game is not found display error

        Game game = gameManagerService.getGameByID(gameID);
        if(game != null) {
            ModelAndView mav = new ModelAndView("game");
            mav.addObject("game", game);
            //return player to game page with id
            return mav;
        }
        ModelAndView mav2 = new ModelAndView("error");
        mav2.addObject("message", "Game not exists");
        return mav2;
    }

    /**
     * Function create new game
     * @return id of the game
     */
    @GetMapping("/game")
    @ResponseBody
    public String createNewGame(){
        Game gameID = gameManagerService.addGame();
        //return player to game page with id
        return gameID.getId();
    }

    /**
     * add player to game - player pressed a button: "join the game"
     */
    @PostMapping("game/id/{gameID}")
    @ResponseBody
    public void addPlayer(@RequestBody Player player, @PathVariable String gameID){

        //TODO: when game is full display error

        gameManagerService.addPlayerToGame(player, gameID);
    }

    /**
     * remove player from game - player closed the window or pressed a button
     */
    @PostMapping("game/id/{gameID}/leaveGame")
    @ResponseBody
    public void removePlayer(@RequestBody Player player, @PathVariable String gameID){
        gameManagerService.removePlayerFromGame(player, gameID);
    }

    /**
     * remove player from game - player closed the window or pressed a button
     */
    @PostMapping("game/id/{gameID}/startGame")
    @ResponseBody
    public void startGame(@PathVariable String gameID){
        gameManagerService.startGame(gameID);
    }

    @GetMapping("/game/model/id/{gameID}")
    @ResponseBody
    public Game getGameModel(@PathVariable String gameID){

        //TODO: when game is not found display error

        Game game = gameManagerService.getGameByID(gameID);
        return game;
    }

    @PostMapping("game/id/{gameID}/move")
    @ResponseBody
    public void setMove(@RequestBody Move move, @PathVariable String gameID){
        gameManagerService.setMove(move, gameID);
    }

    @MessageMapping("/gameMessage/{gameId}")
    @SendTo("/topic/gameMessageReceiver/{gameId}")
    public Game updateGame(@DestinationVariable String gameId) throws Exception {
        Game game = null;
        try {
            game = gameManagerService.getGameByID(gameId);
        }
        catch (java.lang.NullPointerException e){
            e.printStackTrace();
        }
        return game;
    }

    /**
     *
     * @param gameID - id of game
     * @return - null if there is no winner or Player object of the winner
     */
    @PostMapping("/game/id/{gameID}/checkWinner")
    @ResponseBody
    public Player checkWinner(@PathVariable String gameID){
        return gameManagerService.checkWinner(gameID);
    }

    /**
     * Kick player through admin Player
     *
     * @param gameID - id of game
     */
    @PostMapping("/game/id/{gameID}/kickPlayer")
    @ResponseBody
    public void kickPlayer(@PathVariable String gameID){
        gameManagerService.kickPlayer(gameID);
    }

    /**
     * Change id of current player round
     *
     * @param gameID - id of game
     */
    @PostMapping("/game/id/{gameID}/changePlayerRound")
    @ResponseBody
    public void changePlayerRound(@PathVariable String gameID){
        gameManagerService.changePlayerRound(gameID);
    }

    /**
     * Offer draw through player
     *
     * @param gameID - id of game
     */
    @PostMapping("/game/id/{gameID}/drawOffer")
    @ResponseBody
    public void drawOffer(@RequestBody Player player,@PathVariable String gameID){
        gameManagerService.offerDraw(player, gameID);
    }

    @PostMapping("/game/id/{gameID}/userReload")
    @ResponseBody
    public void userReload(@RequestBody Player player,@PathVariable String gameID){
        //update a logged in user's authorities
        Authentication authentication = new UsernamePasswordAuthenticationToken(player, player.getPassword(), player.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
