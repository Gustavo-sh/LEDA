package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if(isEmpty()) {
			return -1;
		}
		int left = height((BSTNode<T>) root.getLeft());
		int right = height((BSTNode<T>) root.getRight());
		if(left > right) {
			return left;
		}
		return right;
	}
	
	public int height(BSTNode<T> node) {
		int result = 0;
		if(node.isEmpty()) {
			return 0;
		}
		if(size((BSTNode<T>) node.getLeft()) > size((BSTNode<T>) node.getRight())) {
			result += 1 + height((BSTNode<T>) node.getLeft());
		} else {
			result += 1 + height((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		if(isEmpty()) {
			return new BSTNode<T>();
		}
		return search(root, element);
	}

	public BSTNode<T> search(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			return new BSTNode<T>();
		}
		if(node.getData().equals(element) || node.isEmpty()) {
			return node;
		}
		if(node.getData().compareTo(element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		insert(root, element);
	}
	
	public void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			BSTNode<T> auxR = new BSTNode<T>();
			auxR.setParent(node);
			BSTNode<T> auxL = new BSTNode<T>();
			auxL.setParent(node);
			node.setRight(auxR);
			node.setLeft(auxL);
		} else {
			if(node.getData().compareTo(element) < 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if(node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) {
			return null;
		}
		return maximum(root);
	}
	
	public BSTNode<T> maximum(BSTNode<T> node) {
		if(node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) {
			return null;
		}
		return minimum(root);
	}
	
	public BSTNode<T> minimum(BSTNode<T> node) {
		if(node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if(isEmpty()) {
			return null;
		}
		BSTNode b = search(element);
		if(!b.isEmpty()) {
			return sucessor(b);
		}
		return b;
	}
	
	public BSTNode<T> sucessor(BSTNode<T> node) {
		if(!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}
		if(node.equals(maximum())) {
			return null;
		}
		BSTNode<T> aux = (BSTNode<T>) node.getParent();
		while(!aux.isEmpty() && aux.getRight().equals(node)) {
			node = aux;
			aux = (BSTNode<T>) aux.getParent();
		}
		return aux; 
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if(isEmpty()) {
			return null;
		}
		BSTNode b = search(element);
		if(!b.isEmpty()) {
			return predecessor(b);
		}
		return b;
	}
	
	public BSTNode<T> predecessor(BSTNode<T> node){
		if(!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		}
		if(node.equals(minimum())) {
			return null;
		}
		BSTNode<T> aux = (BSTNode<T>) node.getParent();
		while(!aux.isEmpty() && aux.getLeft().equals(node)) {
			node = aux;
			aux = (BSTNode<T>) aux.getParent();
		}
		return aux;
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) {
			return;
		}
		BSTNode<T> node = search(element);
		remove(node);
	}

	public void remove(BSTNode<T> node) {
		if(!node.isEmpty()) {
			if(node.isLeaf()) {
				node.setData(null);
			} else if(node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				if(!node.equals(root)) {
					if(node.getParent().getLeft().equals(node)) {
						if(!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
						} else {
							node.getParent().setLeft(node.getRight());
						}
					} else {
						if(!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
						} else {
							node.getParent().setRight(node.getRight());
						}
					}
				} else {
					if(!root.getLeft().isEmpty()) {
						root = (BSTNode<T>) root.getLeft();
					} else {
						root = (BSTNode<T>) root.getRight();
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(node);
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		ArrayList list = new ArrayList<T>();
		preOrder(root, list);
		Integer[] array = new Integer[list.size()];
		preenche(array, list);
		return (T[]) array;
	}
	
	public void preenche(Integer[] array, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			array[i] = (Integer) list.get(i);
		}
	}
	
	public void preOrder(BSTNode<T> node, ArrayList list) {
		if(!node.isEmpty()) {
			list.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), list);
			preOrder((BSTNode<T>) node.getRight(), list);
		}
	}

	@Override
	public T[] order() {
		ArrayList list = new ArrayList<T>();
		order(root, list);
		Integer[] array = new Integer[list.size()];
		preenche(array, list);
		return (T[]) array;
	}
	
	public void order(BSTNode<T> node, ArrayList list) {
		if(!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), list);
			list.add(node.getData());
			order((BSTNode<T>) node.getRight(), list);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList list = new ArrayList<T>();
		postOrder(root, list);
		Integer[] array = new Integer[list.size()];
		preenche(array, list);
		return (T[]) array;
	}
	
	public void postOrder(BSTNode<T> node, ArrayList list) {
		if(!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), list);
			postOrder((BSTNode<T>) node.getRight(), list);
			list.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
