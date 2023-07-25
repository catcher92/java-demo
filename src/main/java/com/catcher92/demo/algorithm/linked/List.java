package com.catcher92.demo.algorithm.linked;

import java.util.function.Predicate;

public class List<T> {

    Node<T> head;

    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public void insert(T value) {
        final Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
    }

    public Node<T> find(Predicate<T> predicate) {
        Node<T> node = head;
        while (node != null) {
            if (predicate.test(node.data)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(T value) {
        if (head == null) {
            return;
        }
        if (head.data.equals(value)) {
            head = head.next;
            return;
        }
        Node<T> prev = head;
        Node<T> current = head.next;
        while (current != null && !current.data.equals(value)) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            prev.next = current.next;
        }
    }

    public void remove1(T value) {
        Node<T> dummyHead = new Node<>(null);
        Node<T> current = dummyHead;
        while(current.next != null ) {
            if (current.next.data.equals(value)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        head = dummyHead.next;
    }

    public int size() {
        int c = 0;
        Node<T> node = head;
        while (node != null) {
            node = node.next;
            c++;
        }
        return c;
    }

    public void reverse() {
        final Node<T> newNode = new Node<>(null);
        Node<T> tmp;
        while (head != null) {
            tmp = head;
            head = head.next;
            tmp.next = newNode.next;
            newNode.next = tmp;
        }
        head = newNode.next;
    }

    // 3指针反转
    public void reverse1() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // 递归反转
    public void reverse2() {
        head = _reverse2(head);
    }

    private Node<T> _reverse2(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> rest = _reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    public boolean hasLoop() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
