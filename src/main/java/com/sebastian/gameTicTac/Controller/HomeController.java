package com.sebastian.gameTicTac.Controller;

import com.sebastian.gameTicTac.Model.PlayerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
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
//    @GetMapping("/game")
//    public String game() {
//        return "Game";
//    }
}
