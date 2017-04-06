package com.catcher92.demo.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class WeixinUser implements Observer{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WeixinUser{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observable="+o+",arg="+arg);
    }
}
