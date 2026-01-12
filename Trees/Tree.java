package Trees;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    // * Pattern 1 — DFS Traversals

    // ! 94. Binary Tree Inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(list, root);
        return list;
    }

    private void inOrder(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(list, node.left);
        list.add(node.val);
        inOrder(list, node.right);

    }

    // ! 144. Binary Tree Preorder Traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }

    private void preOrder(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(list, node.left);
        preOrder(list, node.right);

    }

    // ! 145. Binary Tree Postorder Traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(list, root);
        return list;
    }

    private void postOrder(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(list, node.left);
        postOrder(list, node.right);
        list.add(node.val);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null)
            return false;

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    public String tree2str(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        modifiedPreOrder(root, sb);
        return sb.toString();
    }

    private void modifiedPreOrder(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;

        sb.append(root.val);

        if (root.left != null || root.right != null) {
            sb.append("(");
            modifiedPreOrder(root.left, sb);
            sb.append(")");
        }

        if (root.right != null) {
            sb.append("(");
            modifiedPreOrder(root.right, sb);
            sb.append(")");
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        traverse(ans, root);
        return ans;
    }

    private void traverse(List<Integer> ans, Node root) {
        if (root == null)
            return;

        ans.add(root.val);
        for (Node kid : root.children) {
            traverse(ans, kid);
        }
    }

}
