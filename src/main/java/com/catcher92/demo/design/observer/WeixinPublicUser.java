package com.catcher92.demo.design.observer;

import java.util.Observable;

/**
 * Created by caoxuedong on 2017/4/6.
 * 公众号
 */
public class WeixinPublicUser extends Observable{

    private String name;

    public WeixinPublicUser(String name) {
        this.name = name;
    }

    /**
     * <p>功能描述：发布消息</p>
     * @return 
     * @author caoxuedong
     * @date 2017/4/6 16:56
     */
    public void publishMsg(String message) {
        // 标记此Observable对象为已改变的对象
        setChanged();
        // 通知所有观察者
        notifyObservers(message);
    }

    @Override
    public String toString() {
        return "WeixinPublicUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
