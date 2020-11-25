package com.sebastian.gameTicTac.Service;

import com.sebastian.gameTicTac.Dao.PlayerDao;
import com.sebastian.gameTicTac.Model.Player;
import com.sebastian.gameTicTac.Model.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements UserDetailsService {
    PlayerDao playerDao;

    @Autowired
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Player player = playerDao.getPlayerByName(s);
        if(player == null){
            throw new UsernameNotFoundException("User not exist with name :" +s);
        }
        return player;
    }

    public Player getPlayerById(int id){
        return playerDao.getPlayerById(id);
    }

    public List<Player> getAllPlayers(){
        return playerDao.getAllPlayers();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public boolean addPlayer(PlayerDto playerDto){
        Player player = new Player(playerDto.getName(), passwordEncoder().encode(playerDto.getPassword()),
                0, 0, 0, 0, 0, "USER");
        if(playerValidate(playerDto)){
            playerDao.addPlayer(player);
            return true;
        }
        else return false;
    }

    public void updatePlayer(Player player){
        playerDao.updatePlayer(player);
    }

    public void deletePlayer(int id){
        playerDao.deletePlayer(id);
    }

    private boolean playerExists(String name) {
        try{
            boolean flag = loadUserByUsername(name) != null;
            return true;
        }
        catch (UsernameNotFoundException e){
            return false;
        }
    }

    private boolean playerValidate(PlayerDto playerDto){
        if(playerDto.getName()==null || playerDto.getName()=="") return false;
        if(playerDto.getPassword()==null || playerDto.getPassword()=="") return false;
        if(!playerDto.getPassword().equals(playerDto.getMatchingPassword())) return false;
        if(playerExists(playerDto.getName())) return false;

        return true;
    }
}
