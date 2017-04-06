package com.catcher92.demo.design.decorator.test;

import com.catcher92.demo.design.decorator.IPacketCreator;
import com.catcher92.demo.design.decorator.PacketBodyCreator;
import com.catcher92.demo.design.decorator.decorator.PacketHTMLHeaderCreator;
import com.catcher92.demo.design.decorator.decorator.PacketHTTPHeaderCreator;

/**
 * Created by caoxuedong on 2017/4/6.
 * 测试装饰者模式，类似于OutputStream的用法
 */
public class DecoratorTest {

    public static void main(String[] args) {
        IPacketCreator packetCreator = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));
        System.out.println(packetCreator.handleContent());
    }

}
