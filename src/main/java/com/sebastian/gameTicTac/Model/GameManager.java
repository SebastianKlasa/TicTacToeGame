package com.sebastian.gameTicTac.Model;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class GameManager {
    private int maxGames = 100;
    private List<Game> games = new ArrayList<Game>();
    static final String AB = "abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public Game findGameById(String id){
        for(Game game: games){
            if(id.equals(game.getId())){
                return game;
            }
        }
        return null;
    }

    public boolean checkIdAvailability(String id){
        for(Game game: games){
            if(id==game.getId()){
                return false;
            }
        }
        return true;
    }

    public void addGame(Game game){
        for(Game ativeGame: games){
            if(MINUTES.between(ativeGame.getLastMoveTime(), LocalDateTime.now()) > 5){
                games.remove(ativeGame);
                System.out.println("gre usunięto z listy");
            }
        }
        if(games.size()<100){
            games.add(game);
        }
        System.out.println("ilosc gier: "+ games.size());
    }

    public String generateId(int len){
        StringBuilder sb = new StringBuilder(len);
        do {
            for (int i = 0; i < len; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        while (!checkIdAvailability(sb.toString()));
        return sb.toString();
    }

    public int getMaxGames() {
        return maxGames;
    }

    public void setMaxGames(int maxGames) {
        this.maxGames = maxGames;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
