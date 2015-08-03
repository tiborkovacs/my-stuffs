package yg0r2.examples.recursive;

public class DepthFirstTraversal {

	public static void main(String[] args) {
		TreeNode<String> root = new TreeNode<String>("root");

		//Child 1
		TreeNode<String> c1 = new TreeNode<String>("Child1");
		//TreeNode<String> c11 = new TreeNode<String>("Child1Child1");

		//c1.addChild(c11);

		//Child 2
		TreeNode<String> c2 = new TreeNode<String>("Child2");
		TreeNode<String> c21 = new TreeNode<String>("Child2Child1");

		TreeNode<String> c211 = new TreeNode<String>("Child2Child1Child1");
		c21.addChild(c211);

		c2.addChild(c21);

		//Child 3
		TreeNode<String> c3 = new TreeNode<String>("Child3");
		TreeNode<String> c31 = new TreeNode<String>("Child3Child1");

		c3.addChild(c31);

		//Assign children to root
		root.addChild(c1);
		root.addChild(c2);
		root.addChild(c3);

		System.out.println(depthFirst(root));
	}

	public static int depthFirst(TreeNode<?> root) {
		if ((root == null) || (root.numberChildren() == 0)) {
			return 0;
		}

		int depth = -1;

		for (int i = 0; i < root.numberChildren(); i++) {
			depth = Math.max(depthFirst(root.get(i)), depth);
		}

		if (depth >= 0) {
			depth++;
		}

		if (depth > _depth) {
			_depth = depth;
		}

		return depth;
	}

	private static int _depth = 0;

}