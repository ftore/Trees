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
	
	public static TreeNode bstDelete(TreeNode root, int value) {
		if(root == null) {
			return root;
		}
		
		if(value < root.val) {
			root.left = bstDelete(root.left, value);
		} else if(value > root.val) {
			root.right = bstDelete(root.right, value);
		} else {
			// value for deletion is found, delete it
			
			// case 1: no child
			if(root.left == null & root.right == null) {
				root = null;
			} 
			// case 2: one child
			else if(root.left == null) {
				root = root.right;
			} else if(root.right == null) {
				root = root.left;
			}
			// case 3: two children
			else {
				TreeNode temp = bstFindMin(root.right);
				root.val = temp.val;
				root.right = bstDelete(root.right, temp.val);
			}
		}
		
		return root;
	}
	
	public static TreeNode bstFindMin(TreeNode root) {
		while(root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	public static int bstFindMinValue(TreeNode root) {
		if(root == null) {
			return -1;
		}
		
		while(root.left != null) {
			root = root.left;
		}
		
		return root.val;
	}
	
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		}

		if (root.val <= min || root.val >= max) {
			return false;
		}

		return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
	}
	
	public static void main(String[] args) {
		int[] vals = {12, 5, 17, 3, 7, 13, 19, 1, 9, 18, 8};
		
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
		root = bstDelete(root, 17);
		levelOrder(root);
		System.out.println();
		System.out.println("Min: " + bstFindMinValue(root));
		
	}
}
