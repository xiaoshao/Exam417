package com.recursion;

public class Demo1 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        ListNode head = new Demo1().swapPairs(listNode);

        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
    public ListNode swapPairs(ListNode head) {
        if (head != null && head.next != null) {
            ListNode nextPair = head.next.next;

            ListNode nextPairHead = swapPairs(nextPair);
            head.next.next = nextPairHead;
            return swap2(head);
        }else {
            return head;
        }

    }


    public ListNode swap2(ListNode head) {
        if (head == null && head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = head.next.next;
        next.next = head;

        return next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }
}
