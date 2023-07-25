package com.catcher92.demo.algorithm.linked;

import org.junit.Assert;
import org.junit.Test;

public class ListTest {

    @Test
    public void testInsert() {
        final List<Integer> list = new List<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        List.Node<Integer> node = list.head;
        for (int i = 3; i > 0; i--) {
            Assert.assertEquals(i, (int) node.data);
            node = node.next;
        }
    }

    @Test
    public void testFind() {
        final List<String> list = new List<>();
        list.insert("Java");
        list.insert("Python");
        list.insert("Scala");
        Assert.assertNotNull(list.find("Scala"::equals));
        Assert.assertNull(list.find("C++"::equals));
    }

    @Test
    public void testSize() {
        final List<String> list = new List<>();
        list.insert("Java");
        list.insert("Python");
        list.insert("Scala");
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testRemove() {
        final List<String> list = new List<>();
        list.insert("Java");
        list.insert("Python");
        list.insert("Scala");
        list.insert("C++");
        list.insert("GO");

        list.remove("GO");
        Assert.assertNull(list.find("GO"::equals));
        list.remove("Java");
        Assert.assertNull(list.find("Java"::equals));
        list.remove("Scala");
        Assert.assertNull(list.find("Scala"::equals));
    }

    @Test
    public void testReverse() {
        final List<Integer> list = new List<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.reverse();
        List.Node<Integer> node = list.head;
        for (int i = 1; i <= 3; i++) {
            Assert.assertEquals(i, (int) node.data);
            node = node.next;
        }
    }

    @Test
    public void testReverse1() {
        final List<Integer> list = new List<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.reverse1();
        List.Node<Integer> node = list.head;
        for (int i = 1; i <= 3; i++) {
            Assert.assertEquals(i, (int) node.data);
            node = node.next;
        }
    }

    @Test
    public void testReverse2() {
        final List<Integer> list = new List<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.reverse2();
        List.Node<Integer> node = list.head;
        for (int i = 1; i <= 3; i++) {
            Assert.assertEquals(i, (int) node.data);
            node = node.next;
        }
    }

    @Test
    public void testHasLoop() {
        final List<Integer> list = new List<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        Assert.assertFalse(list.hasLoop());
        final List.Node<Integer> node = list.find(i -> 1 == i);
        node.next = list.head;
        Assert.assertTrue(list.hasLoop());
    }
}
