import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class LinkedList {

    // ! 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;
            int valu = digit1 + digit2 + carry;
            int modi = valu % 10;
            ListNode newNode = new ListNode(modi);
            tail.next = newNode;
            tail = tail.next;
            carry = valu / 10;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        return dummy.next;
    }

    // ! 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    // ! 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode();
        ListNode tail = ans;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                tail = tail.next;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = tail.next;
                list2 = list2.next;
            }
        }

        tail.next = list1 != null ? list1 : list2;
        return ans.next;
    }

    // ! 23. Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> p = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                p.offer(lists[i]);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!p.isEmpty()) {
            ListNode node = p.poll();
            curr.next = node;
            curr = curr.next;

            if (node.next != null) {
                p.offer(node.next);
            }
        }

        return dummy.next;
    }

    // ! 24. Swap Nodes in Pairs
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // move to next pair
            prev = first;
        }

        return dummy.next;
    }

    // ! 61. Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        int count = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            count++;
        }

        k = k % count;
        if (k == 0)
            return head;

        int itr = count - k;
        ListNode prev = head;

        for (int i = 1; i < itr; i++) {
            prev = prev.next;
        }

        ListNode save = prev.next;
        prev.next = null;
        tail.next = head;

        return save;
    }

    // ! 83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    // ! 82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode temp = head;

        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                int dupVal = temp.val;
                while (temp != null && temp.val == dupVal) {
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

    // ! 86. Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode d1 = new ListNode(0);
        ListNode curr = d1;
        ListNode d2 = new ListNode(0);
        ListNode curr2 = d2;

        while (head != null) {
            if (head.val < x) {
                curr.next = head;
                curr = curr.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
            }
            head = head.next;
        }

        curr2.next = null;
        curr.next = d2.next;
        return d1.next;
    }

    // ! 92. Reverse Linked List II
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
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }

        return dummy.next;
    }

    // ! 143. Reorder List
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        second = reverse(second);

        // 3. Merge alternately
        ListNode first = head;
        while (second != null) {
            ListNode t1 = first.next;
            ListNode t2 = second.next;

            first.next = second;
            second.next = t1;

            first = t1;
            second = t2;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // ! 147. Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            ListNode prev = dummy;

            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }
            curr.next = prev.next;
            prev.next = curr;

            curr = next;

        }

        return dummy.next;
    }

    // ! 445. Add Two Numbers II
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        l1 = reverse(l1);
        l2 = reverse(l2);

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null || l2 != null) {
            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;

            int sum = a + b + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return reverse(dummy.next);
    }

    // ! 817. Linked List Components
    public int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int components = 0;
        while (head != null) {
            if (set.contains(head.val) &&
                    (head.next == null || !set.contains(head.next.val))) {
                components++;
            }
            head = head.next;
        }
        return components;
    }

    // ! 1019. Next Greater Node In Linked List
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> s = new ArrayList<>();
        while (head != null) {
            s.add(head.val);
            head = head.next;
        }
        int[] arr = new int[s.size()];
        Stack<Integer> ss = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!ss.isEmpty() && s.get(i) > s.get(ss.peek())) {
                arr[ss.pop()] = s.get(i);
            }
            ss.push(i);
        }
        return arr;
    }

    // ! 237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // ! 160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    // ! 203. Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;
    }

    // ! 725. Split Linked List in Parts
    public ListNode[] splitListToParts(ListNode head, int k) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int base = count / k;
        int rem = count % k;

        ListNode[] arr = new ListNode[k];
        ListNode curr = head;

        // 2. Split
        for (int i = 0; i < k; i++) {
            arr[i] = curr;
            int partSize = base + (i < rem ? 1 : 0);

            for (int j = 1; j < partSize && curr != null; j++) {
                curr = curr.next;
            }

            if (curr != null) {
                ListNode next = curr.next;
                curr.next = null;
                curr = next;
            }
        }
        return arr;
    }

    // ! 1721. Swapping Nodes in a Linked List
    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = null;
        ListNode second = head;
        ListNode curr = head;

        int count = 1;
        while (curr != null) {
            if (count == k) {
                first = curr;
            }
            if (count > k) {
                second = second.next;
            }
            curr = curr.next;
            count++;
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;

        return head;
    }

    // ! 2095. Delete the Middle Node of a Linked List
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            prev = prev.next;
            fast = fast.next.next;
        }
        prev.next = prev.next.next;

        return head;
    }

    // public int[] nextLargerNodes(ListNode head) {
    // List<Integer> s = new ArrayList<>();
    // while (head != null) {
    // s.add(head.val);
    // head = head.next;
    // }
    // int[] arr = new int[s.size()];
    // Stack<Integer> ss = new Stack<>();
    // for (int i = 0; i < arr.length; i++) {
    // while (!ss.isEmpty() && s.get(i) > s.get(ss.peek())) {
    // arr[ss.pop()] = s.get(i);
    // }
    // ss.push(i);
    // }
    // return arr;
    // }

    // public ListNode swapNodes(ListNode head, int k) {
    //     if (head == null)
    //         return null;
    //     ListNode right = head;
    //     ListNode left = head;
    //     for (int i = 1; i < k; i++) {
    //         right = right.next;
    //     }

    //     left = right;
    //     ListNode curr = head;
    //     while (right.next != null) {
    //         right = right.next;
    //         curr = curr.next;
    //     }

    //     int temp = left.val;
    //     left.val = curr.val;
    //     curr.val = temp;
    //     return head;
    // }
}
