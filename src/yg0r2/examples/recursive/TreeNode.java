package yg0r2.examples.recursive;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<E> {
	public TreeNode(E e) {
		_value = e;
		_connections = new ArrayList<TreeNode<E>>();
	}

	public void addChild(TreeNode<E> child) {
		_connections.add(child);
	}

	public void addChild(E childValue) {
		TreeNode<E> child = new TreeNode<E>(childValue);

		_connections.add(child);
	}

	public TreeNode<E> get(int index){
		return _connections.get(index);
	}

	public E getValue(int index) {
		return _value;
	}

	public int numberChildren(){
		return _connections.size();
	}

	private List<TreeNode<E>> _connections;
	private E _value;

}