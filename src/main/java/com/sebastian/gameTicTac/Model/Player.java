package com.sebastian.gameTicTac.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="Player")
public class Player implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="games")
    private int games;
    @Column(name="wins")
    private int wins;
    @Column(name="draw")
    private int draw;
    @Column(name="lost")
    private int lost;
    @Column(name="ended")
    private int ended;
    @Column(name = "role")
    private String role;

    public Player() {
    }

    public Player(int id, String name, String password, int games, int wins, int draw, int lost, int ended, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.games = games;
        this.wins = wins;
        this.draw = draw;
        this.lost = lost;
        this.ended = ended;
        this.role = role;
    }

    public Player(String name, String password, int games, int wins, int draw, int lost, int ended, String role) {
        this.name = name;
        this.password = password;
        this.games = games;
        this.wins = wins;
        this.draw = draw;
        this.lost = lost;
        this.ended = ended;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getEnded() {
        return ended;
    }

    public void setEnded(int ended) {
        this.ended = ended;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
