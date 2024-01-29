package com.catcher92.demo.bob;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class IndexBalancer {
    public static void main(String[] args) {
        List<IndexElement> elements = new ArrayList<>();

        elements.add(new IndexElement("A0_"));
        elements.add(new IndexElement("B0_"));
        elements.add(new IndexElement("A0"));
        elements.add(new IndexElement("B1_"));
        elements.add(new IndexElement("C1"));
        elements.add(new IndexElement("B2_"));
        elements.add(new IndexElement("A0'"));
        elements.add(new IndexElement("C1'"));
        elements.add(new IndexElement("A2"));
        elements.add(new IndexElement("A2_"));
        elements.add(new IndexElement("B2"));
        elements.add(new IndexElement("A2'"));
        elements.add(new IndexElement("A3_"));
        elements.add(new IndexElement("B1"));
        System.out.printf("      All elements: %s%n", Joiner.on(", ").join(elements));

        List<IndexElement> unMatchedElements = new ArrayList<>();
        List<IndexElement> outElements = new ArrayList<>();

        // 遍历索引
        for (int i = 0; i < elements.size(); i++) {
            IndexElement element = elements.get(i);

            if (element.needBalance()) {
                boolean matched = false;
                IndexElement matchedElement = null;
                unMatchedElements.add(element);

                for (int j = i + 1; j < elements.size(); j++) {
                    IndexElement e = elements.get(j);
                    if (e.canBalance(element)) {
                        matchedElement = e; // 记录匹配的元素
                        matched = true;
                        unMatchedElements.remove(element);
                        break;
                    }
                }

                if (matched) {
                    outElements.add(elements.remove(i)); // 移除需要配平元素
                    outElements.add(matchedElement); // 移除需要配平元素
                    elements.remove(matchedElement); // 移除匹配的元素
                    i--; // 减少索引以处理下一个元素
                }
            } else {
                if (unMatchedElements.isEmpty()) {
                    // 如果不需要匹配，直接移除
                    outElements.add(elements.remove(i));
                    i--; // 减少索引以处理下一个元素
                }
            }
        }

        // 打印剩余元素
        System.out.printf("Remaining elements: %s%n", Joiner.on(", ").join(elements));
        System.out.printf("UnMatched elements: %s%n", Joiner.on(", ").join(unMatchedElements));
        System.out.printf("      Out elements: %s%n", Joiner.on(", ").join(outElements));
        for (int i = 0; i < outElements.size(); i++) {
            IndexElement out = outElements.get(i);
            if (out.needBalance()) {
                System.out.println("出去元素:"+ out + " " + outElements.get(++i));
            } else {
                System.out.println("出去元素:"+ out);
            }
        }
    }
}
