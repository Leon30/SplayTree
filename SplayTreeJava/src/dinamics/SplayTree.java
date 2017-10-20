package dinamics;

import java.util.Comparator;

public class SplayTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;
	
	/**
	 * Inicializa el comparador y la prioridad máxima.
	 * @param maxPriority Prioridad máxima a la que se puede llegar. 
	 */
	public SplayTree(Comparator<T> comparator){
		this.comparator = comparator;
	}
	
	/**
	 * Comprueba si el arbol está vacio.
	 * @return true, si root esta nulo.
	 */
	public boolean isEmpty(){
		return root == null;
	}

	/**
	 * Lanzador del método recursivo para insertar.
	 * @param info Información a ser insertada
	 */
	public void add(T info){
		if(root == null){
			root = new Node<T>(info);
		}else{
			addIn(info, root);
		}
	}
	
	/**
	 * Método recursivo para insertar.
	 * @param info Información a ser insertada.
	 * @param node Nodo actual.
	 */
	public void addIn(T info, Node<T> node){
		if(comparator.compare(info, node.info) < 0){
			if(node.left != null) addIn(info, node.left);
			else node.left = new Node<T>(info);
		}else{
			if(node.right != null) addIn(info, node.right);
			else node.right = new Node<T>(info);	
		}
		root = splay(root, info);
	}
	
	private Node<T> splay(Node<T> node, T info) {
        if (node == null) return null;

        if (comparator.compare(info, node.info) < 0) {
            if (node.left == null) {
                return node;
            }
            int cmp2 = comparator.compare(info, node.left.info);
            if (cmp2 < 0) {
                node.left.left = splay(node.left.left, info);
                node = rotateRight(node);
            }
            else if (cmp2 > 0) {
                node.left.right = splay(node.left.right, info);
                if (node.left.right != null)
                    node.left = rotateLeft(node.left);
            }
            
            if (node.left == null) return node;
            else                return rotateRight(node);
        }

        else if (comparator.compare(info, node.info) > 0) { 
            if (node.right == null) {
                return node;
            }

            int cmp2 = comparator.compare(info, node.right.info);
            if (cmp2 < 0) {
                node.right.left  = splay(node.right.left, info);
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            else if (cmp2 > 0) {
                node.right.right = splay(node.right.right, info);
                node = rotateLeft(node);
            }
            
            if (node.right == null) return node;
            else return rotateLeft(node);
        }else{
        	return node;
        }
	}

	/**
	 * Rota los elementos a la derecha.
	 * @param node Nodo raíz sobre el que se va a realizar la rotación.
	 */
	private Node<T> rotateRight(Node<T> node) {
		Node<T> aux = node.left;
		node.left = aux.right;
		aux.right = node;
		return aux;
	}

	/**
	 * Rota los elementos a la izquierda.
	 * @param node Nodo raíz sobre el que se va a realizar la rotación.
	 */
	private Node<T> rotateLeft(Node<T> node) {
		Node<T> aux = node.right;
		node.right = aux.left;
		aux.left = node;
		return aux;
	}
}
