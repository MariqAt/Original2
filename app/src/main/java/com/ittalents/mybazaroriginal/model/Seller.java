package com.ittalents.mybazaroriginal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 22.3.2017 г..
 */

public class Seller extends User implements Serializable{

    private ArrayList<Notices> notices;


    public Seller(String username, String password, String mail) {
        super(username, password, mail);
        notices = new ArrayList<>();
    }

    public void addNotice (Notices n) {
        if (n != null) {
            notices.add(n);
        }
    }

    public List<Notices> getNotices() {
        return Collections.unmodifiableList(notices);
    }
}
