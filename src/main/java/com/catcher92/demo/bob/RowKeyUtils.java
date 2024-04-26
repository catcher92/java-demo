package com.catcher92.demo.bob;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowKeyUtils {

    private static final Pattern PATTERN = Pattern.compile("\\d+_CN:(?<CN>.*?)_BN:(?<BN>.*?)(?:_SN:(?<SN>.*?))?_BZ:(?<BZ>.*?)(?:_TN:(?<TN>.*?))?_PK:(?<PK>.*?)$");
    public static RowKeyParseResult parseRowKey(String rowKey) {
        Matcher matcher = PATTERN.matcher(rowKey);

        if (matcher.find()) {
            System.out.println("CN: " + matcher.group("CN"));
            System.out.println("BN: " + matcher.group("BN"));
            System.out.println("SN: " + matcher.group("SN"));
            System.out.println("BZ: " + matcher.group("BZ"));
            System.out.println("TN: " + matcher.group("TN"));
            System.out.println("PK: " + matcher.group("PK"));
        }
        System.out.println("------");
        return null;
    }

    public static class RowKeyParseResult {
        String custNum;
        String cardNo;
        String serialNo;
        String biz;
        String txno;
        String primaryKeys;
    }

    public static void main(String[] args) {
        parseRowKey("11_CN:000000052013545_BN:4213173000721845_SN:ACOR001800000000209_BZ:DEPOSIT_TN:+AM3531_PK:0030103302289_001_20240215_800000000209_1");
        parseRowKey("10_CN:000000035646855_BN:6214680060317698_SN:AINS01240304198799_BZ:INS_PK:000000035646855_6214680060317698_01212917_12");
        parseRowKey("10_CN:000000035646855_BN:6214680060317698_BZ:INS_PK:000000035646855_6214680060317698_01212917_12");
        parseRowKey("10_CN:000000035646855_BN:_BZ:INS_PK:000000035646855_6214680060317698_01212917_12");
    }

}
