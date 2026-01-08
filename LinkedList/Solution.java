import java.util.Random;

// ? 382. Linked List Random Node
class Solution {

    private ListNode head;
    private Random random;

    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int result = 0;
        int count = 1;
        ListNode curr = head;

        while (curr != null) {
            if (random.nextInt(count) == 0) {
                result = curr.val;
            }
            count++;
            curr = curr.next;
        }

        return result;
    }
}