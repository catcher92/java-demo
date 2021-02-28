package com.catcher92.demo.design.observer.test;

import com.catcher92.demo.design.observer.WeixinPublicUser;
import com.catcher92.demo.design.observer.WeixinUser;

/**
 * Created by caoxuedong on 2017/4/6.
 */
public class Test {

    public static void main(String[] args) {
        // 被观察者
        WeixinPublicUser publicUser = new WeixinPublicUser("公众号1");

        // 观察者
        WeixinUser user1 = new WeixinUser();
        user1.setName("张三");
        publicUser.addObserver(user1);
        WeixinUser user2 = new WeixinUser();
        user2.setName("李四");
        publicUser.addObserver(user2);
        WeixinUser user3 = new WeixinUser();
        user3.setName("王五");
        publicUser.addObserver(user3);

        publicUser.publishMsg("消息111");
    }

}
