package com.ittalents.mybazaroriginal.model;

import java.io.Serializable;

/**
 * Created by ASUS on 22.3.2017 г..
 */

public class Notices implements Serializable {

    private String title;
    private String description;
    private int image;
    private int price;
    private String phone;

    public Notices(String title, String description, int image, int price, String phone) {
        setTitle(title);
        setDescription(description);
        this.image = image;
        setPrice(price);
        setPhone(phone);
    }

    public void setTitle(String title) {
        if (!title.isEmpty() && title == null) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (description == null && !description.isEmpty()) {
            this.description = description;
        }
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void setPhone(String phone) {
        if (phone == null && !phone.isEmpty()) {
            this.phone = phone;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }
}
