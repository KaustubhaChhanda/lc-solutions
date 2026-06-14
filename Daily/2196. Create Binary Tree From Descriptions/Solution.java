import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> parents = new HashSet<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            parents.add(parent);
            children.add(child);

            TreeNode parentNode = null;
            if (map.containsKey(parent)) {
                parentNode = map.get(parent);
            } else {
                parentNode = new TreeNode(parent);
                map.put(parent, parentNode);
            }

            TreeNode childNode = null;
            if (map.containsKey(child)) {
                childNode = map.get(child);
            } else {
                childNode = new TreeNode(child);
                map.put(child, childNode);
            }

            if (isLeft == 1) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }

        TreeNode root = null;
        for (int parent : parents) {
            if (!children.contains(parent)) {
                root = map.get(parent);
                break;
            }
        }

        return root;
    }
}