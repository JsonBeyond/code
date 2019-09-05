package com.alex.interview.airthmatic;

import com.alex.arithmatic.help.ListNode;

/**
 * @ClassName TestNode
 * @Description TODO
 * @Author Alex
 * @CreateDate 2019/8/22 16:00
 * @Version 1.0
 */
public class TestNode {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);
        l1.next.next.next.next.next.next = new ListNode(7);
        l1.next.next.next.next.next.next.next = new ListNode(8);

    }

    //逆序单链表
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }
}
