package com.anjuke.dw.mini.model;

import java.io.Serializable;

public class AnjukeUser implements Serializable {

    private static final long serialVersionUID = 2144361944645865191L;

    private String username;
    private String name;
    private String email;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
