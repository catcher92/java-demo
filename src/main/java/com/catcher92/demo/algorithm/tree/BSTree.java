package com.catcher92.demo.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T extends Comparable<T>> {

    Node<T> root;

    static class Node<T> {
        Node<T> left;
        Node<T> right;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public void add(T element) {
        assert null != element;
        if (root == null) {
            root = new Node<>(element);
        } else {
            add(root, element);
        }
    }

    private void add(Node<T> node, T element) {
        if (element.compareTo(node.value) <= 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
            } else {
                add(node.left, element);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(element);
            } else {
                add(node.right, element);
            }
        }
    }

    public static <T> void preOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static <T> void inOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    public static <T> void postOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    public static <T> void reverse(Node<T> node) {
        if (node == null) {
            return;
        }
        Node<T> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        reverse(node.left);
        reverse(node.right);
    }

    public static <T> void bfs(Node<T> node) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            final Node<T> item = queue.poll();
            System.out.println(item.value);
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }
    }

    public static <T> int maxDepth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
}
