package com.catcher92.demo.design.decorator.decorator;

import com.catcher92.demo.design.decorator.IPacketCreator;
import com.catcher92.demo.design.decorator.decorator.PacketDecorator;

/**
 * Created by caoxuedong on 2017/4/6.
 * 将给定的内容转化为HTML格式的内容
 */
public class PacketHTMLHeaderCreator extends PacketDecorator {

    public PacketHTMLHeaderCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handleContent() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<body>");
        builder.append(component.handleContent());
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }
}
