import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {
	public static void preOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		
		System.out.print(root.val + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public static void postOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		
		preOrder(root.left);
		preOrder(root.right);
		System.out.print(root.val + " ");
	}
	
	public static void inOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		
		preOrder(root.left);
		System.out.print(root.val + " ");
		preOrder(root.right);
	}
	
	/**
	 * Breadth-first search
	 */
	public static void levelOrder(TreeNode root) {
		
		if(root == null) {
			return;
		}
		
		Queue<TreeNode> toExplore = new LinkedList<>();
		toExplore.add(root);
		
		while(!toExplore.isEmpty()) {
			TreeNode curr = toExplore.remove();
			System.out.print(curr.val + " ");
			if(curr.left != null) {
				toExplore.add(curr.left);
			}
			if(curr.right != null) {
				toExplore.add(curr.right);
			}
		}
	}
	
	public static int height(TreeNode root) {
		if(root == null) return 0;
		return Math.max(1 + height(root.left), 1 + height(root.right));
	}
	
	public static boolean bstSearch(TreeNode root, int val) {
		if(root == null) {
			return false;
		}
		
		TreeNode curr = root;
		
		while(curr != null) {
			if(curr.val > val) {
				curr = curr.left;
			} else if(curr.val < val) {
				curr = curr.right;
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	public static TreeNode bstInsert(TreeNode root, int val) {
		if(root == null) {
			root = new TreeNode(val);
			return root;
		}
		
		if(root.val > val) {
			root.left = bstInsert(root.left, val);
		} else if(root.val < val) {
			root.right = bstInsert(root.right, val);
		}
		
		return root;
	}
	
	public static TreeNode bstInsertLoop(TreeNode root, int val) {
		if(root == null) {
			root = new TreeNode(val);
			return root;
		}
		
		TreeNode curr = root;
		
		boolean comp = curr.val > val;
		
		while(comp && curr.left != null ||
				!comp && curr.right != null) {
			if(comp) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
			
			comp = curr.val > val;
		}
		
		if(comp) {
			curr.left = new TreeNode(val);
		} else {
			curr.right = new TreeNode(val);
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		int[] vals = {4, 1, 2, 3, 5};
		
		TreeNode root = null;
		for(int i = 0; i < vals.length; i++) {
			root = bstInsert(root, vals[i]);
		}
		
		preOrder(root);
		System.out.println();
		postOrder(root);
		System.out.println();
		inOrder(root);
		System.out.println();
		levelOrder(root);
		System.out.println();
		System.out.println("Hight is: " + height(root));
	}
}
