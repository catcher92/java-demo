package com.catcher92.demo.nio;

import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by catcher92 on 2017/4/26.
 */
public class NioTest {

    private static final String SRC_FILE_PATH = "D:\\tmp\\nio\\src.txt";
    private String targetFilePath = "D:\\tmp\\nio\\target.txt";

    public static void createFile() throws IOException {
        File file = new File(SRC_FILE_PATH);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
    }

    public static void main(String[] args) throws IOException {
        createFile();
        writeFile();
    }

    public static void writeFile() {
        String input;
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        String dateTimeStr;
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            dateTime = LocalDateTime.now();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            dateTimeStr = dateTime.format(formatter);
            System.out.println(input);
            write(SRC_FILE_PATH, dateTimeStr+ " " +input);
        }
        scanner.close();
    }

    private static void write(String filePath, String content) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(content.getBytes());
        buffer.put(System.getProperty("line.separator").getBytes());
        buffer.flip();
        try (
                FileOutputStream fos = new FileOutputStream(filePath, true);
                FileChannel fosChannel = fos.getChannel()
        ) {
            fos.flush();
            fosChannel.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFile() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        try (
                FileInputStream fileInputStream = new FileInputStream(SRC_FILE_PATH);
                FileChannel channel = fileInputStream.getChannel()
        ){
            long size = channel.size();
            System.out.println("size="+size);
            channel.read(buffer);
            Buffer bf = buffer.flip();
            System.out.println(bf.limit());
            byte[] bytes = buffer.array();
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopyFile() {

    }

    @After
    public void after() {

    }

}
