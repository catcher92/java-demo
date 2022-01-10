package com.catcher92.demo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.Test;

public class CacheTest {

    private static final LoadingCache<String, String> cache1 = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            return "hello";
        }
    });

    private static final LoadingCache<String, String> cache2 = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            return "world";
        }
    });

    @Test
    public void testCache() {
        System.out.println("cache1:" + cache1.size());
        System.out.println("cache2:" + cache2.size());
        System.out.println("-----------");

        for (int i = 0; i < 100; i++) {
            cache2.put("hello" + i, "hello" + i);
        }

        System.out.println("cache1:" + cache1.size());
        System.out.println("cache1:" + cache1);
        System.out.println("cache1:" + ObjectSizeCalculator.getObjectSize(cache1));
        System.out.println("-----------");
        System.out.println("cache2:" + cache2.size());
        System.out.println("cache2:" + cache2);
        System.out.println("cache1:" + ObjectSizeCalculator.getObjectSize(cache2));

    }
}
