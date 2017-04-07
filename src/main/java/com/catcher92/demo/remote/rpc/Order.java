package com.catcher92.demo.remote.rpc;

import java.io.Serializable;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class Order implements Serializable{

    private int id;
    private String goodname;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodname=" + goodname +
                ", username=" + username +
                '}';
    }
}
