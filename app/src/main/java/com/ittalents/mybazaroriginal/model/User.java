package com.ittalents.mybazaroriginal.model;

import java.io.Serializable;

/**
 * Created by ASUS on 22.3.2017 г..
 */

public class User implements Serializable {

    private String username;
    private String password;
    private String mail;

    public User(String username, String password, String mail) {
        if (!username.isEmpty() && username != null){
            this.username = username;
        }
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        if (mail != null && !mail.isEmpty()) {
            this.mail = mail;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

}
