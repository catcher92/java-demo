package com.catcher92.demo.nio.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {

    private final String nickName;

    public NioClient(String nickName) {
        this.nickName = nickName;
    }

    public void start() throws IOException {
        // 连接服务器端
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8088));
        // 接受响应（新开线程处理）
        Selector selector = Selector.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();
        // 发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if (null != request && !request.isEmpty()) {
                channel.write(Charset.forName("utf-8").encode(nickName + ":" + request));
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("请输入昵称。");
            System.exit(-1);
        }
        String nickName = args[0];
        NioClient client = new NioClient(nickName);
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
