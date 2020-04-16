package ma;

// 86 分割链表
public class ParititionList {

    public static void main(String[] args) {
//        1->4->3->2->5->2

        ListNode head = new ListNode(2);
        ListNode next = new ListNode(1);
        head.next = next;
//        next.next = new ListNode(3);
//        next = next.next;
//
//        next.next = new ListNode(2);
//        next = next.next;
//
//        next.next = new ListNode(5);
//        next = next.next;
//        next.next = new ListNode(2);

        next = head;
        while (next != null) {
            System.out.print(next.val + "->");
            next = next.next;
        }

        head = new ParititionList().partition(head, 2);

        System.out.println();
        next = head;
        while (next != null) {
            System.out.print(next.val + "->");
            next = next.next;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode smallHeader = new ListNode(0);
        ListNode biggerHeader = new ListNode(Integer.MAX_VALUE);

        ListNode smallNext = smallHeader;
        ListNode biggerNext = biggerHeader;

        while (head != null) {
            if (head.val >= x) {
                biggerNext.next = head;
                biggerNext = head;
            } else {
                smallNext.next = head;
                smallNext = head;
            }
            head = head.next;
        }

        if (smallHeader.next == null) {
            return biggerHeader.next;
        }

        if (biggerHeader.next == null) {
            return smallHeader.next;
        }

        smallNext.next = biggerHeader.next;
        biggerNext.next = null;

        return smallHeader.next;
    }
}