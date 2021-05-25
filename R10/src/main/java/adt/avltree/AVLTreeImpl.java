package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	public void insert(T element) {
		insert(this.root, element);
	}
	
	public void insert(BSTNode node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode());
			node.getRight().setParent(node);
		} else {
			if(element.compareTo((T) node.getData()) < 0) {
				insert((BSTNode) node.getLeft(), element);
			} else if(element.compareTo((T) node.getData()) > 0) {
				insert((BSTNode) node.getRight(), element);
			}
			rebalance(node);
		}
	}
	
	public void remove(T element) {
		BSTNode noh = search(element);
		remove(noh);
	}
	
	public void remove(BSTNode node) {
		if(!node.isEmpty()) {
			if(node.isLeaf()) {
				node.setData(null);
				node.setLeft(new BSTNode());
				node.setRight(new BSTNode());
				rebalanceUp(node);
			} else if(node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if(node.equals(root)) {
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
					if(node.getLeft().isEmpty()) {
						root = (BSTNode) node.getRight();
					} else {
						root = (BSTNode) node.getLeft();
					}
					rebalanceUp(node);
				}
			} else {
				BSTNode sucessor = sucessor(node);
			    node.setData(sucessor.getData());
			    remove(sucessor);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if(!node.isEmpty()) {
			result = height((BSTNode) node.getLeft()) - height((BSTNode) node.getRight());
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(balance > 1) {
			if(!node.getLeft().getLeft().isEmpty()) {
				root = Util.rightRotation(node);
			} else if(!node.getLeft().getRight().isEmpty()) {
				node.setLeft(Util.leftRotation((BSTNode) node.getLeft()));
				root = Util.rightRotation(node);
			} else {
				root = Util.rightRotation(node);
			}
		} else if(balance < -1) {
			if(!node.getRight().getRight().isEmpty()) {
				root = Util.leftRotation(node);
			} else if(!node.getRight().getLeft().isEmpty()) {
				node.setRight(Util.rightRotation((BSTNode) node.getRight()));
				root = Util.leftRotation(node);
			} else {
				root = Util.leftRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode parent = (BSTNode) node.getParent();
		while(parent != null) {
			rebalance(parent);
			parent = (BSTNode) parent.getParent();
		}
	}
}
