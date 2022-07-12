package com.catcher92.demo.nio.file;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SuppressWarnings({"unused", "StatementWithEmptyBody", "UnusedAssignment"})
public class IoReadFileTest {

    private static final String FILENAME = "/tmp/words.txt";

    @Test
    public void readFileWithBIO() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILENAME));
        final byte[] bytes = new byte[8 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            // System.out.println(new String(bytes, 0, len));
        }
        bis.close();
        stopWatch.stop();
        System.out.println("times:" + stopWatch.getTime());
    }

    @Test
    public void readFileWithNIO() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
        final FileChannel channel = new FileInputStream(FILENAME).getChannel();
        int len;
        while ((len = channel.read(buffer)) != -1) {
            buffer.flip();
            // System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }
        channel.close();
        stopWatch.stop();
        System.out.println("times:" + stopWatch.getTime());
    }

    @Test
    public void readFileWithAIO() throws IOException, ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int batchSize = 8 * 1024;
        ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
        final AsynchronousFileChannel channel = AsynchronousFileChannel.open(FileSystems.getDefault().getPath(FILENAME), StandardOpenOption.READ);
        long position = 0;
        while (true) {
            final Future<Integer> read = channel.read(buffer, position);
            int readCounts = read.get();
            if (readCounts == -1) {
                break;
            }
            position += readCounts;
            buffer.flip();
            // System.out.println(new String(buffer.array(), 0, readCounts));
            buffer.clear();
            if (readCounts < batchSize) {
                break;
            }
        }
        channel.close();
        stopWatch.stop();
        System.out.println("times:" + stopWatch.getTime());
    }
}
