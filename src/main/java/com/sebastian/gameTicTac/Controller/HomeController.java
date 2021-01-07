package com.sebastian.gameTicTac.Controller;

import com.sebastian.gameTicTac.Model.PlayerDto;
import com.sebastian.gameTicTac.Service.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    GameManagerService gameManagerService;

    @Autowired
    public HomeController(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/player/registration")
    public String registration(WebRequest request, Model model) {
        PlayerDto playerDto = new PlayerDto();
        model.addAttribute("player", playerDto);
        return "registration";
    }
}
