package Trees;

import java.util.ArrayList;
import java.util.List;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> l = new ArrayList<>();
        helper(root, l);
        return String.join(",", l);
    }

    private void helper(TreeNode root, List<String> l) {
        if (root == null) {
            l.add("null");
            return;
        }
        l.add(Integer.toString(root.val));
        helper(root.left, l);
        helper(root.right, l);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        List<String> l = new ArrayList<>(List.of(arr));
        return helper1(l);
    }

    private TreeNode helper1(List<String> l) {
        String val = l.remove(0);
        if (val.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = helper1(l);
        node.right = helper1(l);
        return node;
    }
}