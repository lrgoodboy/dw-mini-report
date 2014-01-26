package com.anjuke.dw.mini.model;

import java.io.Serializable;

public class AnjukeUser implements Serializable {

    private static final long serialVersionUID = 2144361944645865191L;

    private String username;
    public AnjukeUser(String username) {
        this.username = username;
    }

}
