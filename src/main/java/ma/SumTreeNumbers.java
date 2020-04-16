package ma;

// 129 求跟到子节点数字之和
public class SumTreeNumbers {
    public int sumNumbers(TreeNode root) {
        return visit(root, "");
    }


    public int visit(TreeNode node, String pv){
        if(node.left == null && node.right == null){
            return Integer.valueOf(pv + node.val);
        }
        int value = 0;
        if(node.left != null){
            value += visit(node.left, pv + node.val);
        }

        if(node.right != null) {
            value += visit(node.right, pv + node.val);
        }

        return value;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
