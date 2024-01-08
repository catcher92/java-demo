package com.catcher92.demo.stream;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * 并行Stream执行时候线程池是用的<b>默认的ForkJoinPool执行</b>，不可自行指定<br/>
 * 默认的线程数量由<i>{@code Runtime.getRuntime().availableProcessors()}</i>得到的<br/>
 * 可以通过{@code System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "24")}来修改默认的线程数量
 * {@link java.util.concurrent.ForkJoinPool#ForkJoinPool(byte)} <br/>
 * 并行流会对<b>数据进行分块，每块不止一条数据</b>然后并行计算
 */
@SuppressWarnings("JavadocReference")
public class ParallelStreamTest {

    @Test
    public void testSum() {
        Map<String, Function<Long, Long>> funcs = new LinkedHashMap<>();
        funcs.put("串行iterate累加", ParallelStreamTest::sum0);
        funcs.put("并行iterate累加", ParallelStreamTest::sum0_1);
        funcs.put("串行range生成累加", ParallelStreamTest::sum1);
        funcs.put("并行range累加", ParallelStreamTest::sum1_1);
        funcs.put("串行+=累加", ParallelStreamTest::sum2);
        funcs.put("并行+=累加", ParallelStreamTest::sum2_1);
        funcs.put("串行Atomic累加", ParallelStreamTest::sum3);
        funcs.put("并行Atomic累加", ParallelStreamTest::sum3_1);
        long end = 9999999;
        funcs.forEach((key, value) -> {
            System.out.println(key);
            for (int i = 0; i < 3; i++) {
                long startTime = System.currentTimeMillis();
                long sum = value.apply(end);
                long endTime = System.currentTimeMillis();
                System.out.println("结果:" + sum + ",耗时:" + (endTime - startTime));
            }
            System.out.println("------");
        });
    }

    /**
     * iterate生成的是包装类型的Long会涉及装箱拆箱操作有开销
     */
    public static long sum0(long end) {
        return LongStream.iterate(1, i -> i + 1).limit(end).reduce(0, Long::sum);
    }

    /**
     * iterate生成的不容易拆分成块来并行执行
     */
    public static long sum0_1(long end) {
        return LongStream.iterate(1, i -> i + 1).limit(end).parallel().reduce(0, Long::sum);
    }

    /**
     * range生成的是原始类型的long不涉及装箱拆箱操作
     */
    public static long sum1(long end) {
        return LongStream.rangeClosed(1, end).sum();
    }

    /**
     * range生成的在并行模式下更加容易拆分成块来执行
     * 这种对long的累加是性能很高且稳定的
     */
    public static long sum1_1(long end) {
        return LongStream.rangeClosed(1, end).parallel().sum();
    }

    /**
     * 对原始long进行串行+=操作
     * 首次计算性能较低，重复计算时候性能很高
     */
    public static long sum2(long end) {
        Accumulator accumulator = new Accumulator1();
        LongStream.rangeClosed(1, end).forEach(accumulator::add);
        return accumulator.getTotal();
    }

    /**
     * 对原始long进行并行+=操作
     * 结果是错误的，时间也比较长
     */
    public static long sum2_1(long end) {
        Accumulator accumulator = new Accumulator1();
        LongStream.rangeClosed(1, end).parallel().forEach(accumulator::add);
        return accumulator.getTotal();
    }

    /**
     * 串行Atomic累加
     */
    public static long sum3(long end) {
        Accumulator accumulator = new Accumulator2();
        LongStream.rangeClosed(1, end).forEach(accumulator::add);
        return accumulator.getTotal();
    }

    /**
     * 并行Atomic累加，性能还不如串形Atomic高
     */
    public static long sum3_1(long end) {
        Accumulator accumulator = new Accumulator2();
        LongStream.rangeClosed(1, end).parallel().forEach(accumulator::add);
        return accumulator.getTotal();
    }

    interface Accumulator {
        void add(long n);

        long getTotal();
    }

    private static class Accumulator1 implements Accumulator {
        public long total = 0;

        // 并发执行会计算错误
        @Override
        public void add(long n) {
            total += n;
        }

        @Override
        public long getTotal() {
            return total;
        }
    }

    private static class Accumulator2 implements Accumulator {
        public AtomicLong total = new AtomicLong();

        @Override
        public void add(long n) {
            total.addAndGet(n);
        }

        @Override
        public long getTotal() {
            return total.get();
        }
    }
}
