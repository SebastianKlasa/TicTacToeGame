package com.sebastian.gameTicTac.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameManager {
    private static int maxGames = 100;
    private static List<Game> games = new ArrayList<Game>();
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public Game findGameById(String id){
        for(Game game: games){
            if(id==game.getId()){
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
        games.add(game);
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

    public static int getMaxGames() {
        return maxGames;
    }

    public static void setMaxGames(int maxGames) {
        GameManager.maxGames = maxGames;
    }

    public static List<Game> getGames() {
        return games;
    }

    public static void setGames(List<Game> games) {
        GameManager.games = games;
    }
}
