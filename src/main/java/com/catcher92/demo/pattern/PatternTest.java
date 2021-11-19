package com.catcher92.demo.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        String index = "whize-log-org11-scope22-instance33";
        final Pattern pattern = Pattern.compile("whize-log-org(?<org>\\d*)-scope(?<scope>\\d*)-instance(?<instance>\\d*)");
        final Matcher matcher = pattern.matcher(index);
        if (matcher.find()) {
            System.out.println(matcher.group("org"));
            System.out.println(matcher.group("scope"));
            System.out.println(matcher.group("instance"));
        }
    }

}
