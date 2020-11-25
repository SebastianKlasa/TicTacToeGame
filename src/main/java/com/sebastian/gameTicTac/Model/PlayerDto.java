package com.sebastian.gameTicTac.Model;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class PlayerDto {

    @NotNull
    @NotEmpty(message = "empty wartosc")
    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    public PlayerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
