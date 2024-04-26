package com.catcher92.demo.bob;

import java.util.*;

class QueueElement {
    private String id;
    private boolean isValid;
    private String name;

    public QueueElement(String id, boolean isValid, String name) {
        this.id = id;
        this.isValid = isValid;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "QueueElement{" +
                "id='" + id + '\'' +
                ", isValid=" + isValid +
                ", name='" + name + '\'' +
                '}';
    }
}

public class QueueProcess {

    public static void main(String[] args) {
        List<QueueElement> queue = new ArrayList<>();
        queue.add(new QueueElement("1", false, "A"));
        queue.add(new QueueElement("2", false, "B"));
        queue.add(new QueueElement("3", false, "C"));
        queue.add(new QueueElement("4", false, "A"));
        queue.add(new QueueElement("5", false, "B"));
        queue.add(new QueueElement("6", true, "A"));
        queue.add(new QueueElement("7", true, "B"));
        queue.add(new QueueElement("8", false, "D"));

        List<QueueElement> invalidElements = filterQueue(queue);
        System.out.println("Invalid Elements:");
        invalidElements.forEach(element -> System.out.println("Id: " + element.getId() + ", Name: " + element.getName()));
        System.out.println("\nRemaining elements in queue:");
        queue.forEach(element -> System.out.println("Id: " + element.getId() + ", Name: " + element.getName()));
    }

    public static List<QueueElement> filterQueue(List<QueueElement> queue) {
        List<QueueElement> invalidElements = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            QueueElement element = queue.get(i);
            if (element.isValid()) {
                // 将当前元素添加到 invalidElements 中
                invalidElements.add(queue.get(i));
                String name = element.getName();
                // 将当前元素和之前所有同名元素加入到 invalidElements 中
                for (int j = i - 1; j >= 0; j--) {
                    if (queue.get(j).getName().equals(name)) {
                        invalidElements.add(queue.get(j));
                    }
                }
            }
        }
        queue.removeAll(invalidElements);
        return invalidElements;
    }

    public static List<QueueElement> getInvalidElements(List<QueueElement> queue) {
        List<QueueElement> invalidElements = new ArrayList<>();
        List<String> processedNames = new ArrayList<>();
        for (QueueElement current : queue) {
            if (current.isValid()) {
                String name = current.getName();
                if (!processedNames.contains(name)) {
                    invalidElements.addAll(findElementsWithSameName(queue, name));
                    processedNames.add(name);
                }
            }
        }
        queue.removeAll(invalidElements); // 移除已添加到结果列表的元素
        return invalidElements;
    }

    private static List<QueueElement> findElementsWithSameName(List<QueueElement> queue, String name) {
        List<QueueElement> elementsWithSameName = new ArrayList<>();
        for (QueueElement element : queue) {
            if (element.getName().equals(name)) {
                elementsWithSameName.add(element);
            }
        }
        return elementsWithSameName;
    }

    public static List<QueueElement> getInvalidElements1(List<QueueElement> queue) {
        List<QueueElement> invalidElements = new ArrayList<>();

        for (int i = 0; i < queue.size(); i++) {
            QueueElement current = queue.get(i);
            if (current.isValid()) {
                // 添加当前有效元素
                invalidElements.add(current);
                // 当遇到有效元素时，向前查找并添加所有相同名称的元素
                String name = current.getName();
                for (int j = i - 1; j >= 0; j--) {
                    QueueElement prev = queue.get(j);
                    if (prev.getName().equals(name)) {
                        invalidElements.add(prev);
                    }
                }
            }
        }

        return invalidElements;
    }
}
