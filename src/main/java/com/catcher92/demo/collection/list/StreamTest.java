package com.catcher92.demo.collection.list;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StreamTest {

    public static void main(String[] args) {
        List<String> topics = Arrays.asList("abc", "bcd", "def", "bbb");
        String patternString = null;
        final Pattern pattern =
                patternString != null && !patternString.isEmpty()
                        ? Pattern.compile(patternString)
                        : null;
        topics.stream().filter(
                topic ->
                        null == pattern
                                || pattern.matcher(
                                topic)
                                .matches()).forEach(System.out::println);
    }

}
