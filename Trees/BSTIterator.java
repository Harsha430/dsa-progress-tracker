package Trees;

import java.util.Stack;

// ? 173. Binary Search Tree Iterator
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode curr = stack.pop();
        int result = curr.val;

        // If there is a right subtree, process its leftmost path
        if (curr.right != null) {
            pushLeft(curr.right);
        }

        return result;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
