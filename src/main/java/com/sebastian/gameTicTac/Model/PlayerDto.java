package com.sebastian.gameTicTac.Model;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class PlayerDto {

    @NotNull
    @NotEmpty(message = "Name can not be blank")
    private String name;

    @NotNull
    @NotEmpty(message = "Password can not be blank")
    private String password;

    @NotNull
    @NotEmpty(message = "Password can not be blank")
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
