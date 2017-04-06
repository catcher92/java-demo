package com.catcher92.demo.design.decorator.decorator;

import com.catcher92.demo.design.decorator.IPacketCreator;
import com.catcher92.demo.design.decorator.decorator.PacketDecorator;

import java.util.Date;

/**
 * Created by caoxuedong on 2017/4/6.
 * 给给定的内容加上http头部信息
 */
public class PacketHTTPHeaderCreator extends PacketDecorator {

    public PacketHTTPHeaderCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handleContent() {
        StringBuilder builder = new StringBuilder();
        builder.append("CacheControl:no-cache\n");
        builder.append("Date:"+new Date().toGMTString()+"\n");
        builder.append(component.handleContent());
        return builder.toString();
    }
}
