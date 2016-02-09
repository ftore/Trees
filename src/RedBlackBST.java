
public class RedBlackBST {
	
	private static final boolean RED   = true;
	private static final boolean BLACK = false;

	private TreeNode root;     // root of the BST
	private int N;         // number of key-value pairs in BST
	
	public int get(int val) {
		return get(root, val);
	}
	
	public int get(TreeNode root, int val) {
		while(root != null) {
			if(val < root.val) root = root.left;
			else if(val > root.val) root = root.right;
			else return root.val;
		}
		return -1;
	}
	
	public void put(int val) {
		root = insert(root, val);
		root.color = BLACK;
	}
	
	private TreeNode insert(TreeNode root, int val) {
		if(root == null) {
			N++;
			return new TreeNode(val);
		}
		
		if(val < root.val) {
			root.left = insert(root.left, val);
		} else if(val > root.val) {
			root.right = insert(root.right, val);
		} else {
			root.val = val;
		}
		
		// fix-up any right-leaning links
		if(isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
		if(isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
		if(isRed(root.right) && isRed(root.left)) flipColors(root);
		return root;
	}
	
	private boolean isRed(TreeNode x) {
		if(x == null) return false;
		return x.color == RED;
	}
	
	// rotate right
	private TreeNode rotateRight(TreeNode root) {
		TreeNode x = root.left;
		root.left = x.right;
		x.right = root;
		x.color = root.color;
		root.color = RED;
		return x;
	}
	
	// rotate left
	private TreeNode rotateLeft(TreeNode root) {
		TreeNode x = root.right;
		root.right = x.left;
		x.left = root;
		x.color = root.color;
		root.color = RED;
		return x;
	}
	
	// flip colors
	private void flipColors(TreeNode root) {
		root.color = RED;
		root.left.color = BLACK;
		root.right.color = BLACK;
	}
	
	public static void main(String[] args) {
		
	}

}
