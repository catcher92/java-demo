package com.catcher92.demo.nio.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class IoWriteFileTest {

    private static final String FILENAME = "/tmp/words.txt";
    // private static final int WORDS = 1_000_000_000;
    private static final int WORDS = 200;
    private static final int WORDS_LENGTH = 5;

    @BeforeClass
    public static void init() {
        FileUtils.deleteQuietly(new File(FILENAME));
    }

    @Test
    public void writeFileWithBIO() throws IOException {
        final Random random = new Random();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILENAME));
        for (int i = 0; i < WORDS; i++) {
            for (int j = 0; j < WORDS_LENGTH; j++) {
                bos.write(97 + random.nextInt(WORDS_LENGTH));
            }
            bos.write(" ".getBytes());
        }
        bos.close();
        stopWatch.stop();
        System.out.println("times:" + stopWatch.getTime());
    }

    @Test
    public void writeFileWithNIO() throws IOException {
        final Random random = new Random();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
        final FileChannel channel = new FileOutputStream(FILENAME).getChannel();
        for (int i = 0; i < WORDS; i++) {
            buffer.clear();
            for (int j = 0; j < WORDS_LENGTH; j++) {
                buffer.putChar((char) (97 + random.nextInt(WORDS_LENGTH)));
            }
            // buffer.put(" ".getBytes());
            buffer.flip();
            channel.write(buffer);
        }
        channel.close();
        stopWatch.stop();
        System.out.println("times:" + stopWatch.getTime());
    }

    @Test
    public void writeFileWithAIO() {

    }
}
