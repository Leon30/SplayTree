package dinamics;

public class Node<T> {

	protected T info;
	protected Node<T> right;
	protected Node<T> left;
	
	public Node(T info, Node<T> right, Node<T> left) {
		this.info = info;
		this.right = right;
		this.left = left;
	}
	
	public Node(T info){
		this.info = info;
	}
	
	public Node<T> copy(){
		return new Node<T>(info, right, left);
	}
	
	public void replace(Node<T> node){
		info = node.info;
		if(node.right != null) right = node.right.copy();
		if(node.left != null) left = node.left.copy();
	}
}
