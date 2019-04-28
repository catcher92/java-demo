package com.catcher92.demo.nio.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public void start() throws IOException {
        // 1.创建selector
        Selector selector = Selector.open();
        // 2.通过ServerSocketChannel创建channel通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 3.为channel绑定监听端口
        channel.bind(new InetSocketAddress(8088));
        // 4.设置channel为非阻塞模式
        channel.configureBlocking(false);
        // 5.channel注册到selector上，监听连接事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功.");
        // 6.循环等待新连接接入
        while (true) {
            // 获取可用的channel数量
            int readyChannels = selector.select();
            if (readyChannels != 0) {
                // 获取可用channel集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 移除set中当前SelectionKey
                    iterator.remove();
                    // 7.根据就绪状态，调用对应方法处理业务逻辑
                    if (selectionKey.isAcceptable()) {
                        acceptHandler(channel, selector);
                    } else if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        }
    }

    /**
     * 接入事件处理器
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        // 创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        // 注册selector，监听可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 回复客户端提示消息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎进入聊天室."));
    }

    /**
     * 可读事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        // 从selectionKey中获取已经就绪的channel
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read;
        while (channel.isOpen() && (read = channel.read(byteBuffer)) != 0) {
            if (read == -1) {
                channel.close();
                System.out.println("连接断开");
            } else {
                // 循环读取客户端消息
                StringBuilder request = new StringBuilder();
                // 切换buffer为读模式
                byteBuffer.flip();
                // 读取buffer中内容
                request.append(Charset.forName("utf-8").decode(byteBuffer));
                // 将channel注册到selector上，监听可读事件
                channel.register(selector, SelectionKey.OP_READ);
                if (request.length() > 0) {
                    // 将客户端发送消息广播给其他客户端
                    broadcast(channel, request.toString(), selector);
                }
            }
        }

    }

    private void broadcast(SocketChannel channel, String msg, Selector selector) {
        Set<SelectionKey> keys = selector.keys();
        keys.forEach(selectionKey -> {
            SelectableChannel targetChannel = selectionKey.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != channel) {
                try {
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(msg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        NioServer server = new NioServer();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
