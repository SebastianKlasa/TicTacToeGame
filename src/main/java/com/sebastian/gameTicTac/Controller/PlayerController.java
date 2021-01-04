package com.sebastian.gameTicTac.Controller;

import com.sebastian.gameTicTac.Model.Player;
import com.sebastian.gameTicTac.Model.PlayerDto;
import com.sebastian.gameTicTac.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/player/registration")
    public ModelAndView addPlayer(@ModelAttribute("player") @Valid PlayerDto playerDto, Errors errors,
                                  HttpServletRequest request){
        if(playerService.addPlayer(playerDto)){
            return new ModelAndView("home");
        }
        else return new ModelAndView("registration");
    }

    @GetMapping("/player/id/{id}")
    public Player getPlayerById(@PathVariable int id){
        return playerService.getPlayerById(id);
    }

    @GetMapping("/player/name/{name}")
    public UserDetails getPlayerByName(@PathVariable String name){
        return playerService.loadUserByUsername(name);
    }

    @GetMapping("/player")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PutMapping("/player")
    public void updatePlayer(@RequestBody Player player){
        playerService.updatePlayer(player);
    }

    @DeleteMapping("/player/id/{id}")
    public void deletePlayer(@PathVariable int id){
        playerService.deletePlayer(id);
    }

    @GetMapping("/player/reload/id/{id}")
    public Player reloadUserData(@PathVariable int id, HttpServletRequest req){
        Player player = playerService.getPlayerById(id);
        return player;
    }

}
