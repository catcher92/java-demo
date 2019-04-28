package com.catcher92.demo.nio.socket.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable {

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            // 获取可用的channel数量
            int readyChannels = 0;
            try {
                readyChannels = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readyChannels != 0) {
                // 获取可用channel集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 移除set中当前SelectionKey
                    iterator.remove();
                    // 7.根据就绪状态，调用对应方法处理业务逻辑
                    // 连接就绪
                    try {
                        if (selectionKey.isConnectable()) {
                            connectHandler(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            readHandler(selectionKey, selector);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void connectHandler(SelectionKey selectionKey) throws IOException {
        // 判断是否成功
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        if (channel.finishConnect()) {
            System.out.println("客户端连接建立成功。");
        } else {
            System.out.println("客户端连接建立失败。");
        }
    }

    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        // 从selectionKey中获取已经就绪的channel
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 循环读取服务端响应信息
        StringBuilder response = new StringBuilder();
        while (channel.read(byteBuffer) != 0) {
            // 切换buffer为读模式
            byteBuffer.flip();
            // 读取buffer中内容
            response.append(Charset.forName("utf-8").decode(byteBuffer));
            // 将channel注册到selector上，监听可读事件
            channel.register(selector, SelectionKey.OP_READ);
        }
        if (response.length() > 0) {
            System.out.println(response);
        }
    }
}
