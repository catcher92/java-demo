package com.catcher92.demo.design.decorator;

/**
 * Created by caoxuedong on 2017/4/6.
 * 构造要发布信息的核心内容
 */
public class PacketBodyCreator implements IPacketCreator{

    @Override
    public String handleContent() {
        return "content";
    }
}
