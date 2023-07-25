package com.catcher92.demo.algorithm.linked;

import org.junit.Test;

import java.util.Stack;

public class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        final Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        cur = dummy;
        int i = 1;
        while (!stack.empty()) {
            final ListNode pop = stack.pop();
            if (i != n) {
                pop.next = cur.next;
                cur.next = pop;
            }
            i++;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int size = 0;
        // 1 2 3 4 5
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur != null) {
            cur = cur.next;
            size++;
        }
        cur = dummy;
        for (int i = 1; i < size - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println();
    }

    @Test
    public void testRemoveNthFromEnd() {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        for (int i = 1; i <= 5; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode head = dummyHead.next;
        removeNthFromEnd(head, 5);
        print(head);
    }
}
