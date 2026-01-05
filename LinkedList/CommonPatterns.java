import org.w3c.dom.Node;

public class CommonPatterns {

    // ! Pointer Reversal Pattern
    // ? Template:
    // ? prev=null
    // ? curr=head
    // ? while(curr!=null)
    // ? {
    // ? next = curr.next
    // ? curr.next = prev
    // ? prev = curr
    // ? curr = next

    // * 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // * 92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }

    // * 25. Reverse Nodes in k-Group
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 1)
                return head;

            // Step 1: Count nodes
            int count = 0;
            ListNode temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }

            // Dummy node simplifies edge cases
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prevGroupEnd = dummy;
            ListNode curr = head;

            // Step 2: Reverse in groups
            while (count >= k) {
                ListNode groupStart = curr;
                ListNode prev = null;

                // Reverse k nodes
                for (int i = 0; i < k; i++) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                // Step 3: Reconnect
                prevGroupEnd.next = prev;
                groupStart.next = curr;

                // Move prevGroupEnd
                prevGroupEnd = groupStart;

                count -= k;
            }

            return dummy.next;
        }
    }

    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }

        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < count / 2; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head.next = count % 2 == 0 ? curr : curr.next;
        head = prev;

        temp = head;
        while (curr != null) {
            if (temp.val != curr.val) {
                return true;
            }
            curr = curr.next;
            temp = temp.next;

        }
        return false;

    }

    // ! Fast & Slow Pointer (Tortoise–Hare)

    // * 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // * 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // ! Dummy Node Pattern (Most Important)

    // ? Used For:
    // ? Insert/delete nodes safely
    // ? Head modification problems
    // ? Merging lists
    // ? Create a fake head to avoid edge cases.

    // ? ListNode dummy = new ListNode(0);
    // ? dummy.next = head;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }

        temp.next = list1 != null ? list1 : list2;
        return dummy.next;

    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummyLess = new ListNode(0);
        ListNode dummyHigh = new ListNode(0);

        ListNode d1 = dummyLess;
        ListNode d2 = dummyHigh;
        while (head != null) {
            if (head.val < x) {
                dummyLess.next = head;
                dummyLess = dummyLess.next;
            } else {
                dummyHigh.next = head;
                dummyHigh = dummyHigh.next;
            }
            head = head.next;
        }
        dummyHigh.next = null;
        dummyLess.next = d2.next;

        return d1.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                int duplicate = temp.val;
                while (temp != null && temp.val == duplicate) {
                    temp = temp.next;
                }
                prev.next = temp;
            } else {
                temp = temp.next;
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    // ! Two Pointer – Same Direction
    // ? Example:
    // ? fast = head;
    // ? slow = head;
    // ? move fast n times
    // ? then move both until fast == null
    // *Remove Nth Node From End
    // *Find Kth node from end

}
