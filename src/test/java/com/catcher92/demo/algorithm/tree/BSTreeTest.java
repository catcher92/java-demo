package com.catcher92.demo.algorithm.tree;

import org.junit.Test;

public class BSTreeTest {

    @Test
    public void testAdd() {
        final BSTree<Integer> tree = new BSTree<>();
        tree.add(10);
        tree.add(7);
        tree.add(5);
        tree.add(8);
        tree.add(15);
        tree.add(30);
        tree.add(21);
        System.out.println("前序遍历");
        BSTree.preOrder(tree.root);
        System.out.println("中序遍历");
        BSTree.inOrder(tree.root);
        System.out.println("后序遍历");
        BSTree.postOrder(tree.root);
        System.out.println("广度优先遍历");
        BSTree.bfs(tree.root);
    }

    @Test
    public void testReverse() {
        final BSTree<Integer> tree = new BSTree<>();
        tree.add(10);
        tree.add(7);
        tree.add(5);
        tree.add(8);
        tree.add(15);
        tree.add(30);
        tree.add(21);

        BSTree.reverse(tree.root);
        System.out.println("中序遍历");
        BSTree.inOrder(tree.root);
    }

    @Test
    public void testMaxDepth() {
        final BSTree<Integer> tree = new BSTree<>();
        tree.add(10);
        tree.add(7);
        tree.add(5);
        tree.add(8);
        tree.add(15);
        tree.add(30);
        tree.add(21);
        System.out.println("树的深度:"+BSTree.maxDepth(tree.root));
    }
}
