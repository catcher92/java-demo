package com.catcher92.demo.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    private static final Random RANDOM = new Random();
    private static final List<Shop> SHOPS = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(Math.min(SHOPS.size(), 100), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static class Shop {

        private final String name;

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            // CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            // new Thread(() -> {
            //     try {
            //         double price = calculatePrice(product);
            //         futurePrice.complete(price);
            //     } catch (Exception e) {
            //         futurePrice.completeExceptionally(e);
            //     }
            // }).start();
            // return futurePrice;
            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        }

        private double calculatePrice(String product) {
            delay();
            return RANDOM.nextInt(100) * product.charAt(0) + product.charAt(1);
        }

        public static void delay() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Discount {
        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

            private final int percentage;
            Code(int percentage) {
                this.percentage = percentage;
            }
        }
    }

    public static List<String> findPrices(String product) {
        // return SHOPS.stream().map(s -> String.format("%s price is %.2f" , s.getName(), s.getPrice(product))).collect(Collectors.toList());
        // return SHOPS.parallelStream().map(s -> String.format("%s price is %.2f" , s.getName(), s.getPrice(product))).collect(Collectors.toList());
        List<CompletableFuture<String>> priceFutures = SHOPS.stream().map(s ->
                CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f", s, s.getPrice(product)), EXECUTOR)).collect(Collectors.toList());
        CompletableFuture<Integer> future1 = priceFutures.get(0).thenApply(Integer::parseInt);
        CompletableFuture<Integer> future2 = priceFutures.get(0).thenCompose(s -> CompletableFuture.supplyAsync(() -> Integer.parseInt(s)));
        priceFutures.get(0).thenCombine(priceFutures.get(1), (f1, f2) -> new StringBuilder(f1).append(f2));
        CompletableFuture<String>[] futures = priceFutures.toArray(new CompletableFuture[0]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All done");
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }
}
