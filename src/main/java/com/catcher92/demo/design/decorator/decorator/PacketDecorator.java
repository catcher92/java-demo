package com.catcher92.demo.design.decorator.decorator;

import com.catcher92.demo.design.decorator.IPacketCreator;

/**
 * Created by caoxuedong on 2017/4/6.
 * 维护核心组建component对象，告知子类核心业务逻辑由component完成，自己仅负责增强
 */
public abstract class PacketDecorator implements IPacketCreator {

    IPacketCreator component;

    public PacketDecorator(IPacketCreator component) {
        this.component = component;
    }
}
